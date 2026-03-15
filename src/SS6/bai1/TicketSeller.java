package SS6.bai1;

public class TicketSeller implements Runnable {
    private String sellerName;
    private TicketPool pool;
    private int soldCount = 0;

    public TicketSeller(String sellerName, TicketPool pool) {
        this.sellerName = sellerName;
        this.pool = pool;
    }

    @Override
    public void run() {
        // Bán cho đến khi hết vé và Supplier không còn cấp thêm nữa
        while (true) {
            Ticket t = pool.sellTicket();
            if (t != null) {
                soldCount++;
                System.out.println(sellerName + " đã bán vé " + t.getCode());
                // Nghỉ một chút để giả lập việc xử lý khách hàng
                try { Thread.sleep(300); } catch (InterruptedException e) {}
            } else {
                // Nếu tạm thời hết vé, chờ 1 giây xem Supplier có thêm vé không
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                // Nếu sau khi chờ vẫn hết vé (và có thể check thêm điều kiện dừng ở Main)
                if (pool.getRemainingCount() == 0) break; 
            }
        }
    }

    public int getSoldCount() {
        return soldCount;
    }
}