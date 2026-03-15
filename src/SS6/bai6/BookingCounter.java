package SS6.bai6;

import java.util.Random;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool[] rooms;
    private int soldCount = 0;
    private Random random = new Random();
    private boolean isVIP;
    private volatile boolean paused = false;

    public BookingCounter(String counterName, TicketPool[] rooms, boolean isVIP) {
        this.counterName = counterName;
        this.rooms = rooms;
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                while (paused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            TicketPool chosenRoom;
            if (isVIP) {
                chosenRoom = rooms[0]; 
            } else {
                chosenRoom = rooms[random.nextInt(rooms.length)];
            }

            Ticket heldTicket = chosenRoom.holdTicket(isVIP);
            if (heldTicket != null) {
                System.out.println(counterName + ": Da giu ve " + heldTicket.getTicketId() + (isVIP ? " (VIP)" : "")
                        + ". Vui long thanh toan trong 5s");

                try {
                    Thread.sleep(3000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                if (chosenRoom.sellHeldTicket(heldTicket)) {
                    soldCount++;
                    System.out.println(counterName + ": Thanh toan thanh cong ve " + heldTicket.getTicketId());
                } else {
                    System.out.println(counterName + ": Khong the thanh toan ve " + heldTicket.getTicketId());
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public synchronized void pause() {
        paused = true;
    }

    public synchronized void resume() {
        paused = false;
        notify();
    }

    public int getSoldCount() {
        return soldCount;
    }
}