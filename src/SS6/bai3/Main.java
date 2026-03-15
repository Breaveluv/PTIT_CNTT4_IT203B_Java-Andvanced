package SS6.bai3;

public class Main {
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        boolean isFixMode = false; 

        if (!isFixMode) {
            System.out.println("--- Đang chạy chế độ: GÂY DEADLOCK ---");
            Thread q1 = new Thread(new BookingCounter("Quầy 1", roomA, roomB, false));
            Thread q2 = new Thread(new BookingCounter("Quầy 2", roomB, roomA, false));
            q1.start();
            q2.start();
        } else {
            System.out.println("--- Đang chạy chế độ: ĐÃ SỬA LỖI ---");
            Thread q1 = new Thread(new BookingCounter("Quầy 1", roomA, roomB, true));
            Thread q2 = new Thread(new BookingCounter("Quầy 2", roomB, roomA, true));
            q1.start();
            q2.start();
        }
    }
}