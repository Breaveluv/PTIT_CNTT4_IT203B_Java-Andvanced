package SS6.bai5;

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
            tickets.add(new Ticket(id, roomName, false)); // regular tickets
        }
    }

    public synchronized Ticket holdTicket(boolean isVIP) {
        while (true) {
            for (Ticket t : tickets) {
                if (!t.isSold() && !t.isHeld()) {
                    t.setHeld(true);
                    t.setHoldExpiryTime(System.currentTimeMillis() + 5000);
                    return t;
                }
            }
            // No available, print next ticket id
            int nextId = 1;
            for (Ticket t : tickets) {
                if (!t.isSold())
                    break;
                nextId++;
            }
            String nextTicketId = roomName + "-" + String.format("%03d", nextId);
            System.out.println(
                    Thread.currentThread().getName() + ": Ve " + nextTicketId + " dang duoc giu boi quay khac, cho...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
    }

    public synchronized boolean sellHeldTicket(Ticket ticket) {
        if (ticket.isHeld() && !ticket.isSold()) {
            ticket.setSold(true);
            ticket.setHeld(false);
            tickets.remove(ticket);
            return true;
        }
        return false;
    }

    public synchronized void releaseExpiredTickets() {
        long now = System.currentTimeMillis();
        boolean released = false;
        for (Ticket t : tickets) {
            if (t.isHeld() && t.getHoldExpiryTime() < now) {
                t.setHeld(false);
                System.out.println("TimeoutManager: Ve " + t.getTicketId() + " het han giu, da tra lai kho");
                released = true;
            }
        }
        if (released) {
            notifyAll();
        }
    }

    public synchronized int getRemainingCount() {
        int count = 0;
        for (Ticket t : tickets) {
            if (!t.isSold())
                count++;
        }
        return count;
    }

    public String getRoomName() {
        return roomName;
    }
}