package SS6.bai6;

public class TimeoutManager implements Runnable {
    private TicketPool[] rooms;
    private volatile boolean paused = false;

    public TimeoutManager(TicketPool[] rooms) {
        this.rooms = rooms;
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

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            for (TicketPool room : rooms) {
                room.releaseExpiredTickets();
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
}