package ie.atu.hotel;
import java.io.Serializable;
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// INHERITANCE - Employee IS-A Person, and CAN-DO Payable methods
public class Employee extends Person implements Payable, Serializable  {
    //private Date dob;		// Employee has name&phoneNumber from Person
    //private Date startDate;	// AND dob,startdate,salary, & number	
    private double salary;
    private int number;

	private static int nextNumber=10000;	// static for unique number - starts off at 1
	
	private final double MAX_SALARY = 150000;	

    // Default Constructor
	// Called when object is created like this ==> Employee eObj = new Employee();	
    public Employee(){
    	super();		// NOTE:Not needed
    	//dob=new Date();
    	//startDate=new Date();
 		salary=0.0;
		// Set number to static nextNumber before incrementing nextNumber
 		//number=nextNumber;
 		this.number=nextNumber;
 		nextNumber++;
    }
	
    // Initialization Constructor
    // Called when object is created like this ==>
    //  Name name=new Name("Mr","Joe","Cole");
    //  Employee e2=new Employee(name,"087 1234567", new Date(25,12,1970),
    //                           new Date(10,11,2009),55000);
    public Employee(Name name, String phoneNo, Date dob,Date startDate, double salary){
          // Call super class constructor - Passing parameters required by Person ONLY!
	   super(name, phoneNo);
	   // And then initialise Employees own instance variables
	   //this.dob=dob;
	   //this.startDate=startDate;	// Set instance variable to parameter
	   this.salary=salary;
	   
		// Set number to static nextNumber before incrementing nextNumber
	   number = nextNumber++;						
	}
    
	// OVERRIDING the Person toString() method!
	// Calling Persons toString() method, and adding additional bits
	@Override
    public String toString(){
		DecimalFormat df=new DecimalFormat("#.00");
		return number + " " + name + " � " +  df.format(salary) + ".";
		// OR
		// return number + " " + name + "\t€" +  String.format("%.2f", salary) + ".";			
	}
	
	public boolean read() {
	    // Create JTextField objects for the employee's details
	    JTextField txtEmpNo = new JTextField(10);
	    // Set the employee number
	    txtEmpNo.setText(String.valueOf(nextNumber));
	    // Make the employee number field non-editable
	    txtEmpNo.setEditable(false); 

	    JComboBox<String> cbTitle = new JComboBox<>(new String[] { "Mr", "Ms", "Mrs", "Miss" });  // Combo box for title

	    JTextField txtFirstName = new JTextField();
	    JTextField txtSurname = new JTextField();
	    JTextField txtPhoneNumber = new JTextField();
	    JTextField txtSalary = new JTextField();

	    // Create an object array to hold the labels and text fields for input
	    Object[] message = {
	        "Employee Number:", txtEmpNo,
	        "Title:", cbTitle,
	        "First Name:", txtFirstName,
	        "Surname:", txtSurname,
	        "Phone Number:", txtPhoneNumber,
	        "Salary:", txtSalary
	    };

	    // Create a dialog window that's on top always
	    JDialog dialog = new JDialog();
	    dialog.setAlwaysOnTop(true);

	    // Show the dialog with the message array of text fields and labels
	    int option = JOptionPane.showConfirmDialog(dialog, message, "ENTER EMPLOYEE DETAILS", JOptionPane.OK_CANCEL_OPTION);

	    // If OK is selected, set the employee details
	    if (option == JOptionPane.OK_OPTION) {
	        // Set the employees name, phone number, and salary based on input
	        this.name.setTitle((String) cbTitle.getSelectedItem());
	        this.name.setFirstName(txtFirstName.getText());
	        this.name.setSurname(txtSurname.getText());
	        this.phoneNumber = txtPhoneNumber.getText();
	        
	        // Try parse the salary and set it. Show an error if invalid
	        try {
	            this.salary = Double.parseDouble(txtSalary.getText());
	        } catch (NumberFormatException e) {
	            // If salary input is invalid, show an error message and return false
	            JOptionPane.showMessageDialog(null, "Invalid salary. Please enter a valid salary.", "Error", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        // Return true if all the details were successfully entered
	        return true;
	    } 
	    else {
	        // If the user cancels, return false
	        return false;
	    }
	}
	
	// Method to get nextNumber used for employee number
	public static int getNextNumber() {
		return nextNumber;
	}
	
	
	public static void setStaticNumber(int newNum) {
		nextNumber = newNum;
	}
	
	// Method to decrement the employee number
	public static int decrementNumber() {
		return nextNumber--;
	}
	
	// Method to get max salary
	public double getMAX_SALARY() {
		return MAX_SALARY;
	}

	// equals() method
	// ==> Called when comparing an object with another object, 
	//     e.g. - if(e1.equals(e2))				
	// ==> Probably sufficient to compare customer numbers as they're unique
	@Override
	public boolean equals(Object obj){
		Employee eObject;
		if (obj instanceof Employee)
		   eObject = (Employee)obj;
		else
		   return false;
		   
	    return(this.number==eObject.number);
	}

	public void setSalary(double salary){
		this.salary=salary;
	}
	public double getSalary(){
		return salary;
	}
	// Get number
	public int getNumber(){
		return number;
	}	
	
	@Override
	public double calculatePay(double taxPercentage) {
		// return the monthly pay as salary/12 less taxPercentage
		double pay=salary/12;
		pay -= (pay * (taxPercentage/100));
		return pay;
	}

	@Override
	public double incrementSalary(double incrementAmount) {
		// add incrementAmount to, and return the new salary
		// salary should not go over a maximum salary of €150,000
		salary += incrementAmount;
		
		if(salary > getMAX_SALARY())
			salary = getMAX_SALARY();
		
		return salary;
	}
}
