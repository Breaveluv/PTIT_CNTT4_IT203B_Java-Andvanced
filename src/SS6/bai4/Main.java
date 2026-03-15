package SS6.bai4;

public class Main {
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomA, roomB);

        TicketSupplier supplier = new TicketSupplier(roomA, roomB);

        Thread t1 = new Thread(counter1, "Quầy 1");
        Thread t2 = new Thread(counter2, "Quầy 2");
        Thread tSupplier = new Thread(supplier, "Nhà cung cấp");

        t1.start();
        t2.start();
        tSupplier.start();

        try {
            Thread.sleep(5000); 
            t1.interrupt();
            t2.interrupt();
            tSupplier.interrupt();
            t1.join();
            t2.join();
            tSupplier.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("----------------------------");
        System.out.println("Ket thuc chuong trinh");
        System.out.println("Quay 1 ban duoc: " + counter1.getSoldCount() + " ve");
        System.out.println("Quay 2 ban duoc: " + counter2.getSoldCount() + " ve");
        System.out.println("Ve con lai phong A: " + roomA.getRemainingCount());
        System.out.println("Ve con lai phong B: " + roomB.getRemainingCount());
    }
}
