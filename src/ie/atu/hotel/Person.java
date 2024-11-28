package ie.atu.hotel;
import java.io.Serializable;
import java.util.Scanner;

// Person is an abstract class.  This means we use it ONLY to inherit from!
// We cannot create an object from class Person!
public abstract class Person implements Serializable{
	protected Name name;	// COMPOSITION - Person HAS-A name
	protected String phoneNumber;

	// Default Constructor
	// Will be called by subclasses 
	public Person(){
		name=new Name();
		phoneNumber="";
	}
	
	// Overloaded Initialization Constructor
	// Called when object would have been created like this (not possible cos abstract!)
	//    ==> Name name = new Name("Mr","Joe","Bloggs");
	//        Person pObj = new Person(name, "087 1234567");
	// Will be called by subclasses	
	public Person(Name name, String phoneNumber) {
		this.name=name;
		this.phoneNumber=phoneNumber;
	}
	
	// toString() method
	// ==> Calls Name's toString() to display name and
	//		then displays address and phoneNumber
	@Override  // Overrides Object toString()
	public String toString() {
		return name + "\t" + phoneNumber;		
	}
	
	// equals() method
	// ==> Called when comparing an object with another object, 
	//     e.g. - if(p1.equals(p2))				
	// ==> Calls Name's equals() to compare name to personIn's name, and
	//		compares phoneNumber to personIn's phoneNumber
	@Override  // Overrides Object equals()
	public boolean equals(Object obj) {
		Person pObj;
		if(obj instanceof Person)
			pObj=(Person)obj;
		else
			return false;
		
		return name.equals(pObj.name)&&
			   phoneNumber.equals(pObj.phoneNumber);		
	}

	
	// get() methods
	public Name getName() {
		return this.name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	// set() methods
	public void setName(Name name) {
		this.name=name;
	}
	public void setPhoneNumber(String phoneNumberIn) {
		phoneNumber=phoneNumberIn;
	}

	// read() method
	// ==> Called by subclesses to read() a Persons details from the user, e.g.
	//      super.read();
	public boolean read() {
		Scanner kb=new Scanner(System.in);
		
		System.out.print("Title:");
		this.name.setTitle(kb.nextLine());
		System.out.print("First Name:");
		this.name.setFirstName(kb.nextLine());
		System.out.print("Surname:");
		this.name.setSurname(kb.nextLine());
		
		System.out.print("Phone Number:");
		this.phoneNumber=kb.nextLine();
		return true;
	}
}
