package SS6.bai4;

public class TicketSupplier implements Runnable {
    private TicketPool roomA;
    private TicketPool roomB;

    public TicketSupplier(TicketPool roomA, TicketPool roomB) {
        this.roomA = roomA;
        this.roomB = roomB;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000); 
            roomA.addTickets(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}