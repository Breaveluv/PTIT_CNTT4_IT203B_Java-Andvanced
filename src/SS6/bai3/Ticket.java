package SS6.bai3;

import java.util.ArrayList;
import java.util.List;

class Ticket {
    String id;
    public Ticket(String id) { this.id = id; }
}

class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int count) {
        this.roomName = roomName;
        for (int i = 1; i <= count; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i)));
        }
    }

    public synchronized Ticket sellTicket() {
        if (!tickets.isEmpty()) return tickets.remove(0);
        return null;
    }

    // Phương thức trả lại vé nếu giao dịch thất bại
    public synchronized void addBackTicket(Ticket t) {
        tickets.add(0, t);
    }

    public synchronized int getRemainingCount() { return tickets.size(); }
    public String getRoomName() { return roomName; }
}