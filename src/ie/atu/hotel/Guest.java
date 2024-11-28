/**
 * Class: Software Implementation
 * Instructor: Maria Boyle
 * Description: Class Guest-Developed for the Hotel System
 * Date: dd/mm/yyyy
 * @author Software Implementation Students
 * @version 1.0
 */
package ie.atu.hotel;

import java.util.Scanner;

public class Guest extends Person {	//INHERITANCE-Guest IS-A Person
	// Guest has name & phoneNumber from Person
	private String emailAddress;    // AND an emailAddress
	private CreditCard creditCard;  // AND a creditCard	

	// Default Constructor
	// Called when a Guest object is created like this ==> 
	//   Guest g1 = new Guest();	
	public Guest() {
		super();	// Don't need to call super() explicitly.
		emailAddress="";
	}
	
	// Overloaded initialization constructor
	// Called when a Guest object is created like this ==>
	//    Name name=new Name("Mr", "Joe", "Bloggs");
	//    CreditCard creditCard=new CreditCard(5555666677778888,new Date(31,12,2026),123);
	//    Guest g2 = new Guest(name, "087 1234567", "joe@gmail.com" =, creditCard);
	public Guest(Name name, String phoneNumber, String emailAddress, CreditCard creditCard) {
		super(name,phoneNumber);
		this.emailAddress=emailAddress;
		this.creditCard=creditCard;
	}

	// OVERRIDING the Person toString() method!
	// Calling Persons toString() method, and adding emailAddress
	@Override
	public String toString() {
		return super.toString() + "\t" + emailAddress + ".";
	}
	
	// no equals() method because the super class equals() is sufficient
	// We can compare two Guest objects using their name and phoneNumber only
	// if(g1.equals(g2))
	//    given that there is no equals method in Guest it will call Persons equals
	//    and compare g1's name and phoneNumber with g2's name and phoneNumber

	// get() and set() methods
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
		
	// read() method
	// ==> Called when you when you read() a Guest details from the user, e.g.
	//      guest.read();
	public boolean read() {
		System.out.println("GUEST DETAILS");
		
		// call Person's read() to read name and phoneNumber...
		super.read();
		
		// ...then read emailAddress...
		Scanner kb=new Scanner(System.in);
		
		System.out.print("Email Address:");
		emailAddress=kb.nextLine();
		
		//  ...and finally read creditCard
		//  (CreditCard read() method is static, so call it on class not object!)
		CreditCard.read();
		return true;
	}
}
