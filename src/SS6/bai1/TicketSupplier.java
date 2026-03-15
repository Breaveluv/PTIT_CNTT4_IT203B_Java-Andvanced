package SS6.bai1;

public class TicketSupplier implements Runnable {
    private TicketPool roomA;
    private TicketPool roomB;
    private int supplyCount;
    private int interval;
    private int rounds;

    public TicketSupplier(TicketPool roomA, TicketPool roomB, int supplyCount, int interval, int rounds) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.supplyCount = supplyCount;
        this.interval = interval;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < rounds; i++) {
                Thread.sleep(interval); // Tạm dừng 3 giây theo yêu cầu
                
                roomA.addTickets(supplyCount);
                System.out.println("Nhà cung cấp: Đã thêm " + supplyCount + " vé vào phòng " + roomA.getRoomName());
                
                roomB.addTickets(supplyCount);
                System.out.println("Nhà cung cấp: Đã thêm " + supplyCount + " vé vào phòng " + roomB.getRoomName());
            }
        } catch (InterruptedException e) {
            System.out.println("Supplier bị ngắt quãng.");
        }
    }
}