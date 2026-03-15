package SS6.bai1;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();
    private int totalCreated = 0; // Để đánh số vé không bị trùng

    public TicketPool(String roomName, int initialCount) {
        this.roomName = roomName;
        addTickets(initialCount);
    }

    // Phương thức thêm vé mới (được gọi bởi Supplier)
    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            totalCreated++;
            String code = roomName + "-" + String.format("%03d", totalCreated);
            tickets.add(new Ticket(code));
        }
    }

    public synchronized Ticket sellTicket() {
        if (!tickets.isEmpty()) {
            return tickets.remove(0);
        }
        return null;
    }

    public synchronized int getRemainingCount() {
        return tickets.size();
    }

    public String getRoomName() {
        return roomName;
    }
}