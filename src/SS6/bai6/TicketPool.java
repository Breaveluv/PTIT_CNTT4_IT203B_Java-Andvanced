package SS6.bai6;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets;
    private int initialCount;

    public TicketPool(String roomName, int count) {
        this.roomName = roomName;
        this.initialCount = count;
        this.tickets = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String id = roomName + "-" + String.format("%03d", i);
            tickets.add(new Ticket(id, roomName, false, 50000)); // regular price
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

    public synchronized void addTickets(int count) {
        int currentMax = tickets.size();
        for (int i = 1; i <= count; i++) {
            currentMax++;
            String id = roomName + "-" + String.format("%03d", currentMax);
            tickets.add(new Ticket(id, roomName, false, 50000));
        }
        notifyAll();
    }

    public synchronized int getRemainingCount() {
        int count = 0;
        for (Ticket t : tickets) {
            if (!t.isSold())
                count++;
        }
        return count;
    }

    public synchronized int getSoldCount() {
        int count = 0;
        for (Ticket t : tickets) {
            if (t.isSold())
                count++;
        }
        return count;
    }

    public synchronized double getRevenue() {
        double revenue = 0;
        for (Ticket t : tickets) {
            if (t.isSold())
                revenue += t.getPrice();
        }
        return revenue;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getInitialCount() {
        return initialCount;
    }
}