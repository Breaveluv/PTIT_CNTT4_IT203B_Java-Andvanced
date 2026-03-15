package SS6.bai2;

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
        while (roomA.getRemainingCount() > 0 || roomB.getRemainingCount() > 0) {
            Ticket t = null;
            
            int choice = random.nextInt(2);
            
            if (choice == 0) {
                t = roomA.sellTicket();
                if (t == null) t = roomB.sellTicket(); 
            } else {
                t = roomB.sellTicket();
                if (t == null) t = roomA.sellTicket(); 
            }

            if (t != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé " + t.getTicketId());
                
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public int getSoldCount() {
        return soldCount;
    }
}