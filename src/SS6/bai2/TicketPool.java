package SS6.bai2;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets;

    public TicketPool(String roomName, int count) {
        this.roomName = roomName;
        this.tickets = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String id = roomName + "-" + String.format("%03d", i);
            tickets.add(new Ticket(id, roomName));
        }
    }

    public synchronized Ticket sellTicket() {
        if (!tickets.isEmpty()) {
            Ticket t = tickets.remove(0); 
            t.setSold(true);
            return t;
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