package SS9.smartsim.engine;

import SS9.smartsim.exception.CollisionException;
import SS9.smartsim.exception.TrafficJamException;
import SS9.smartsim.model.Vehicle;
import SS9.smartsim.traffic.TrafficLight;
import SS9.smartsim.traffic.TrafficLightState;
import SS9.smartsim.util.LogUtil;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lớp Intersection đại diện cho ngã tư, nơi điều phối các phương tiện đi qua.
 */
public class Intersection implements SS9.smartsim.traffic.TrafficLightObserver {
    private final ReentrantLock lock = new ReentrantLock(true); // Lock công bằng (fairness) để xe đến trước đi trước
    private final Condition greenCondition = lock.newCondition();

    private final BlockingQueue<Vehicle> waitingQueue = new LinkedBlockingQueue<>();
    private final int maxQueueSize;
    private final TrafficLight trafficLight;

    private volatile int passedCount = 0;
    private volatile int jamCount = 0;

    public Intersection(TrafficLight trafficLight, int maxQueueSize) {
        this.trafficLight = trafficLight;
        this.maxQueueSize = maxQueueSize;
        this.trafficLight.registerObserver(this);
    }

    /**
     * Xe đi vào hàng chờ tại ngã tư.
     */
    public void enterQueue(Vehicle vehicle) throws InterruptedException {
        if (waitingQueue.size() >= maxQueueSize) {
            jamCount++;
            throw new TrafficJamException("Hàng chờ quá dài (" + waitingQueue.size() + ")");
        }
        waitingQueue.put(vehicle);
    }

    /**
     * Xe đợi đèn xanh và thực hiện hành động băng qua ngã tư.
     */
    public void awaitGreenAndCross(Vehicle vehicle) throws InterruptedException {

        while (true) {
            Vehicle head = waitingQueue.peek();
            if (head == null) {
                TimeUnit.MILLISECONDS.sleep(50);
                continue;
            }

            // Nếu xe đang đứng đầu hàng chờ
            if (head.equals(vehicle)) {
                break;
            }

            // Nếu là xe ưu tiên và có thể vượt qua các xe thường phía trước
            if (vehicle.isPriority() && canOvertake(priorityAhead(), vehicle)) {
                break;
            }

            TimeUnit.MILLISECONDS.sleep(50);
        }

        lock.lock();
        try {
            // Chờ cho đến khi đèn xanh hoặc là xe ưu tiên
            while (!canCross(vehicle)) {
                greenCondition.await(100, TimeUnit.MILLISECONDS);
            }

            // Xóa xe khỏi hàng chờ khi xe bắt đầu đi qua
            if (!waitingQueue.remove(vehicle)) {
                throw new CollisionException("Xe " + vehicle + " không nằm trong hàng chờ khi chuẩn bị vào giao lộ");
            }

            LogUtil.log("%s đang đi qua ngã tư", vehicle);
            TimeUnit.MILLISECONDS.sleep(vehicle.getSpeedMillis());
            passedCount++;
            LogUtil.log("%s đã qua giao lộ", vehicle);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Kiểm tra xem có xe ưu tiên nào khác đang đứng trước xe này không.
     */
    private boolean priorityAhead() {
        for (Vehicle v : waitingQueue) {
            if (v.isPriority()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Logic cho phép xe ưu tiên vượt lên trước các xe thường.
     */
    private boolean canOvertake(boolean priorityAhead, Vehicle vehicle) {
        if (!vehicle.isPriority()) {
            return false;
        }
        if (!priorityAhead) {
            return true;
        }

        // Nếu có xe ưu tiên khác ở phía trước, phải đợi xe đó đi trước.
        for (Vehicle v : waitingQueue) {
            if (v.equals(vehicle)) {
                return true;
            }
            if (v.isPriority()) {
                return false;
            }
        }
        return true;
    }

    private boolean canCross(Vehicle vehicle) {
        boolean isGreen = vehicle.isGreenLight();
        return isGreen || vehicle.isPriority();
    }

    @Override
    public void onLightChanged(TrafficLightState newState) {
        // Đánh thức tất cả các luồng xe đang chờ khi đèn chuyển sang màu Xanh
        if ("GREEN".equals(newState.getName())) {
            lock.lock();
            try {
                greenCondition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public int getPassedCount() {
        return passedCount;
    }

    public int getJamCount() {
        return jamCount;
    }

    public int getQueueSize() {
        return waitingQueue.size();
    }

    public void shutdown() {
        trafficLight.unregisterObserver(this);
    }
}