package SS6.bai4;

import java.util.Random;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount = 0;
    private Random random = new Random();

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Ticket t = null;

            int choice = random.nextInt(2);

            if (choice == 0) {
                t = roomA.sellTicket();
            } else {
                t = roomB.sellTicket();
            }

            if (t != null) {
                soldCount++;
                System.out.println(counterName + " ban ve " + t.getTicketId());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public int getSoldCount() {
        return soldCount;
    }
}