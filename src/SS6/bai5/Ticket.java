package SS6.bai5;

public class Ticket {
    private String ticketId;
    private String roomName;
    private boolean isSold;
    private boolean isHeld;
    private long holdExpiryTime;
    private boolean isVIP;

    public Ticket(String ticketId, String roomName, boolean isVIP) {
        this.ticketId = ticketId;
        this.roomName = roomName;
        this.isSold = false;
        this.isHeld = false;
        this.holdExpiryTime = 0;
        this.isVIP = isVIP;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean isHeld() {
        return isHeld;
    }

    public void setHeld(boolean held) {
        isHeld = held;
    }

    public long getHoldExpiryTime() {
        return holdExpiryTime;
    }

    public void setHoldExpiryTime(long holdExpiryTime) {
        this.holdExpiryTime = holdExpiryTime;
    }

    public boolean isVIP() {
        return isVIP;
    }
}