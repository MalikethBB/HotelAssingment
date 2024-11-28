package ie.atu.hotel;
/**
 * Class: Software Implementation
 * Instructor: Maria Boyle
 * Description: Class Room - Developed for the Hotel System
 * Date: dd/mm/yyyy
 * @author Software Implementation Students
 * @version 1.0
 */
import java.text.DecimalFormat;

public class Room{
	private int roomNumber;
	private String roomType;
	private double pricePerNight;
    private boolean allocated;

	public Room(int roomNumber, String roomType, double pricePerNight, boolean allocated){
		this.roomNumber=roomNumber;
		this.roomType=roomType;
		this.pricePerNight=pricePerNight;
		this.allocated=false;
	}

	@Override
	public String toString() {
		DecimalFormat df=new DecimalFormat("#.00");

		return roomNumber + " " + roomType + " ROOM @ ï¿½" + df.format(pricePerNight) + " per night " + (allocated ? "BOOKED" : "FREE");
	}
	
	@Override
	public boolean equals(Object obj){
	   Room rObject;
	   if (obj instanceof Room)
		   rObject = (Room)obj;
	   else
	       return false;
	 
	   return this.roomNumber == (rObject.roomNumber);
	}

	public void setRoomNumber(int roomNumber){
		this.roomNumber=roomNumber;
	}
	public void setRoomType(String roomType){
		this.roomType=roomType;
	}
	public void setPricePerNight(double pricePerNight){
		this.pricePerNight=pricePerNight;
	}
	public void setAllocated(boolean allocated) {
		this.allocated=allocated;
	}
	public int getNumber() {
		return roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public double getPricePerNight() {
		return pricePerNight;
	}
	public boolean getAllocated() {
		return allocated;
	}
}