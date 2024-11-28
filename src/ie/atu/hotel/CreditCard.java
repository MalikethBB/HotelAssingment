/**
 * Class: Software Implementation
 * Instructor: Maria Boyle
 * Description: Class CreditCard - Developed for the Hotel System
 * Date: dd/mm/yyyy
 * @author Software Implementation Students
 * @version 1.0
 */
package ie.atu.hotel;

import java.util.Scanner;

public class CreditCard {
	private long number;
	private Date expiryDate;
	private int securityCode;

	// NO DEFAULT CONSTRUCTOR - prevents user creating a null CreditCard

	// Initialization constructor
	// Called when a CreditCard object is created like this ==>
	//    CreditCard bensCard=new CreditCard(1111222233334444,new Date(31,12,2025),999);
	public CreditCard(long number, Date expiry, int secCode) {   
       this.number=number;
       this.expiryDate=expiry;
       this.securityCode=secCode;
	}
	
	// toString() method
	// ==> Called when a String of the class is used, e.g. - 
	//       System.out.print(joesCard);
	//		 or System.out.print(bensCard.toString());
	@Override
	public String toString(){
	   return "Card Number:" + number;
	}	

	// equals() method
	// ==> Called when comparing an object with another object, e.g. -
	//       if(bensCard.equals(new CreditCard(1111222233334444,new Date(31,12,2025),999))
	@Override
	public boolean equals(Object obj){
	   CreditCard ccObject;
	   if (obj instanceof CreditCard)
		   ccObject = (CreditCard)obj;
	   else
	       return false;
	 
	   return this.number==ccObject.number;
	}

	// NO set() METHODS - prevents user re-setting instance variables
	// You shouldn't be able to reset a CreditCard number for example
	
	public long getNumber() {
		return number;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public int getSecurityCode() {
		return securityCode;
	}
	
	// read() method
	// ==> Called when you when you read() a CreditCards details from the user, e.g.
	//      bensCard.read();
	// The method is static so that we can call it without needing an object of the class
	// static methods are called on the Class, not on objects of the Class!
	// read() will read CreditCard details, and construct and return a CreditCard object
	public static CreditCard read() {
		Scanner kb=new Scanner(System.in);
		
		System.out.println("CARD DETAILS");
		System.out.print("Card Number:");
		long number=kb.nextLong();
		System.out.println("Expiry Date:");
		System.out.print("Day:");int day=kb.nextInt();
		System.out.print("Month:");int month=kb.nextInt();
		System.out.print("Year:");int year=kb.nextInt();
		System.out.print("Security Code:");
		int securityCode=kb.nextInt();
		
		return new CreditCard(number,new Date(day,month,year),securityCode);
	}	
}




