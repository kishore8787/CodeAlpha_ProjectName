
public class Room {
	private int roomNumber;
	private String guestName;
	private boolean occupied;
	
	public Room(int roomNumber) {
		this.roomNumber = roomNumber;
		this.guestName = "";
		this.occupied = false;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	
	public String getGuestName() {
		return guestName;
	}
	
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	public String toString() {
		return "Room :" + roomNumber + " : " + (occupied ? "occupied by " + guestName : "Available");
	}
}
