package SS6.bai4;

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
        while (tickets.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + ": Het ve phong " + roomName + ", dang cho...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        Ticket t = tickets.remove(0);
        t.setSold(true);
        return t;
    }

    public synchronized void addTickets(int count) {
        for (int i = 1; i <= count; i++) {
            int nextId = tickets.size() + 1;
            String id = roomName + "-" + String.format("%03d", nextId);
            tickets.add(new Ticket(id, roomName));
        }
        System.out.println("Nha cung cap: Da them " + count + " ve vao phong " + roomName);
        notifyAll();
    }

    public synchronized int getRemainingCount() {
        return tickets.size();
    }

    public String getRoomName() {
        return roomName;
    }
}