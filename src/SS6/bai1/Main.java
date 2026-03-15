package SS6.bai1;

public class Main {
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 5);
        TicketPool roomB = new TicketPool("B", 5);

        TicketSeller seller1 = new TicketSeller("Quầy 1", roomA);
        TicketSeller seller2 = new TicketSeller("Quầy 2", roomB);
        
        TicketSupplier supplier = new TicketSupplier(roomA, roomB, 3, 3000, 2);

        Thread t1 = new Thread(seller1);
        Thread t2 = new Thread(seller2);
        Thread tSupplier = new Thread(supplier);

        t1.start();
        t2.start();
        tSupplier.start();

        try {
            t1.join();
            t2.join();
            tSupplier.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------------------");
        System.out.println("Kết thúc chương trình");
        System.out.println("Quầy 1 bán được: " + seller1.getSoldCount() + " vé");
        System.out.println("Quầy 2 bán được: " + seller2.getSoldCount() + " vé");
        System.out.println("Vé còn lại phòng A: " + roomA.getRemainingCount());
        System.out.println("Vé còn lại phòng B: " + roomB.getRemainingCount());
    }
}