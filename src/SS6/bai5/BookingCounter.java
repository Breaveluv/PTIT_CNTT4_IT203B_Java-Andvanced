package SS6.bai5;

import java.util.Random;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private TicketPool roomC;
    private int soldCount = 0;
    private Random random = new Random();
    private boolean isVIP;

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB, TicketPool roomC, boolean isVIP) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
        this.roomC = roomC;
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            TicketPool chosenRoom;
            String roomChoice;
            if (isVIP) {
               
                chosenRoom = roomA;
                roomChoice = "A";
            } else {
                
                int choice = random.nextInt(3);
                if (choice == 0) {
                    chosenRoom = roomA;
                    roomChoice = "A";
                } else if (choice == 1) {
                    chosenRoom = roomB;
                    roomChoice = "B";
                } else {
                    chosenRoom = roomC;
                    roomChoice = "C";
                }
            }

            Ticket heldTicket = chosenRoom.holdTicket(isVIP);
            if (heldTicket != null) {
                System.out.println(counterName + ": Da giu ve " + heldTicket.getTicketId() + (isVIP ? " (VIP)" : "")
                        + ". Vui long thanh toan trong 5s");

                try {
                    Thread.sleep(6000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }

                if (chosenRoom.sellHeldTicket(heldTicket)) {
                    soldCount++;
                    System.out.println(counterName + ": Thanh toan thanh cong ve " + heldTicket.getTicketId());
                } else {
                    System.out.println(counterName + ": Khong the thanh toan ve " + heldTicket.getTicketId());
                }
            }

            try {
                Thread.sleep(1000); // wait before next attempt
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public int getSoldCount() {
        return soldCount;
    }
}