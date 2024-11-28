/**
 * Class: B.Sc. in Computing
 * Instructor: Maria Boyle
 * Description: Tester for the Date class
 * Date: dd/mm/yyyy
 * @author Maria Boyle
 * @version 1.0
**/
package ie.atu.testers;

import java.util.Scanner;

import ie.atu.hotel.Date;

public class DateTester{
	public static void main(String[] args) {	   
	   int day=55,month=13,year=-2009;
	   boolean goodInput=false;
	   Scanner keyboard = new Scanner(System.in);
	   do {
	       try {
	    	    Date dateA=new Date();
				// Put date class into an incorrect state
				dateA.setDay(day);
				dateA.setMonth(month);
				dateA.setYear(year);
				System.out.println(dateA);
				goodInput=true;
			}
			catch(IllegalArgumentException e) {
				System.out.println("Incorrect day, month or year.Re-enter.");
				
				System.out.print("Enter day [1-31]:");
				day = keyboard.nextInt();
				System.out.print("Enter month [1-12]:");
				month = keyboard.nextInt();
				System.out.print("Enter Year [above 1900]:");
				year = keyboard.nextInt();							
			}
		}while(!goodInput);
		
		day=62;month=15;year=10;
		goodInput=false;
		do {
			try {
				Date dateB = new Date(day,month,year);
				System.out.println(dateB);
				goodInput=true;
			}
			catch(IllegalArgumentException e) {
				System.out.println("Incorrect day, month or year.Re-enter.");
					
				System.out.print("Enter day [1-31]:");
				day = keyboard.nextInt();
				System.out.print("Enter month [1-12]:");
				month = keyboard.nextInt();
				System.out.print("Enter Year [above 1900]:");
				year = keyboard.nextInt();							
			}
		}while(!goodInput);

		goodInput=false;
		do {
			try {		
				Date myDate3 = new Date();
				myDate3.read();
				System.out.println(myDate3);
				goodInput=true;
			}
			catch(IllegalArgumentException e) {
				System.out.println("Invalid. Re-enter.");				
			}
		}while(!goodInput);

       // Original Date Tester
       Date d1 = new Date();
       System.out.println(d1);      

	   d1.setDay(4);
	   d1.setMonth(6);
	   d1.setYear(1941);
		
	   System.out.println(d1);
      
	   Date d2 = new Date(28, 12, 1982);
       System.out.println(d2);
      
	   if(d1.equals(d2))
	      System.out.println("Date's are equal.");
	   else
	   	  System.out.println("Date's are not equal.");
	
   }
}
