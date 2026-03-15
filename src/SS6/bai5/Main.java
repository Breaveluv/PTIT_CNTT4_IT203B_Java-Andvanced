package SS6.bai5;

public class Main {
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 8);
        TicketPool roomC = new TicketPool("C", 12);

        BookingCounter counter1 = new BookingCounter("Quay 1", roomA, roomB, roomC, true); // VIP
        BookingCounter counter2 = new BookingCounter("Quay 2", roomA, roomB, roomC, false);
        BookingCounter counter3 = new BookingCounter("Quay 3", roomA, roomB, roomC, false);
        BookingCounter counter4 = new BookingCounter("Quay 4", roomA, roomB, roomC, false);
        BookingCounter counter5 = new BookingCounter("Quay 5", roomA, roomB, roomC, false);

        TimeoutManager timeoutManager = new TimeoutManager(roomA, roomB, roomC);

        Thread t1 = new Thread(counter1, "Quay 1");
        Thread t2 = new Thread(counter2, "Quay 2");
        Thread t3 = new Thread(counter3, "Quay 3");
        Thread t4 = new Thread(counter4, "Quay 4");
        Thread t5 = new Thread(counter5, "Quay 5");
        Thread tTimeout = new Thread(timeoutManager, "TimeoutManager");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        tTimeout.start();

        try {
            Thread.sleep(15000);
            t1.interrupt();
            t2.interrupt();
            t3.interrupt();
            t4.interrupt();
            t5.interrupt();
            tTimeout.interrupt();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            tTimeout.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("----------------------------");
        System.out.println("Ket thuc chuong trinh");
        System.out.println("Quay 1 ban duoc: " + counter1.getSoldCount() + " ve");
        System.out.println("Quay 2 ban duoc: " + counter2.getSoldCount() + " ve");
        System.out.println("Quay 3 ban duoc: " + counter3.getSoldCount() + " ve");
        System.out.println("Quay 4 ban duoc: " + counter4.getSoldCount() + " ve");
        System.out.println("Quay 5 ban duoc: " + counter5.getSoldCount() + " ve");
        System.out.println("Ve con lai phong A: " + roomA.getRemainingCount());
        System.out.println("Ve con lai phong B: " + roomB.getRemainingCount());
        System.out.println("Ve con lai phong C: " + roomC.getRemainingCount());
    }
}
