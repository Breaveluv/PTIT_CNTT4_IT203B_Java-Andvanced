package SS6.bai3;

public class BookingCounter implements Runnable {
    private String name;
    private TicketPool pool1, pool2;
    private boolean fixDeadlock;

    public BookingCounter(String name, TicketPool p1, TicketPool p2, boolean fixDeadlock) {
        this.name = name;
        this.pool1 = p1;
        this.pool2 = p2;
        this.fixDeadlock = fixDeadlock;
    }

    @Override
    public void run() {
        while (true) {
            if (fixDeadlock) {
                sellComboFixed();
            } else {
                sellComboDeadlock();
            }
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }

    private void sellComboDeadlock() {
        synchronized (pool1) { 
            System.out.println(name + ": Đã lấy vé phòng " + pool1.getRoomName());
            try { Thread.sleep(500); } catch (InterruptedException e) {} 
            
            System.out.println(name + ": Đang chờ vé phòng " + pool2.getRoomName() + "...");
            synchronized (pool2) { 
                Ticket t1 = pool1.sellTicket();
                Ticket t2 = pool2.sellTicket();
                if (t1 != null && t2 != null) {
                    System.out.println(name + " bán combo thành công: " + t1.id + " & " + t2.id);
                }
            }
        }
    }

    private void sellComboFixed() {
        TicketPool first = pool1.getRoomName().compareTo(pool2.getRoomName()) < 0 ? pool1 : pool2;
        TicketPool second = (first == pool1) ? pool2 : pool1;

        synchronized (first) {
            synchronized (second) {
                Ticket tFirst = first.sellTicket();
                Ticket tSecond = second.sellTicket();
                
                if (tFirst != null && tSecond != null) {
                    System.out.println(name + " bán combo thành công: " + tFirst.id + " & " + tSecond.id);
                } else {
                    if (tFirst != null) first.addBackTicket(tFirst);
                    if (tSecond != null) second.addBackTicket(tSecond);
                    System.out.println(name + ": Thất bại, một trong hai phòng hết vé.");
                    if (first.getRemainingCount() == 0 || second.getRemainingCount() == 0) {
                        System.out.println(name + " dừng hoạt động.");
                        Thread.currentThread().stop(); 
                    }
                }
            }
        }
    }
}