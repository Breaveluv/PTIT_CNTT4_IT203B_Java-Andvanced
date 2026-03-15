package SS6.bai5;

public class TimeoutManager implements Runnable {
    private TicketPool roomA;
    private TicketPool roomB;
    private TicketPool roomC;

    public TimeoutManager(TicketPool roomA, TicketPool roomB, TicketPool roomC) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.roomC = roomC;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            roomA.releaseExpiredTickets();
            roomB.releaseExpiredTickets();
            roomC.releaseExpiredTickets();
        }
    }
}