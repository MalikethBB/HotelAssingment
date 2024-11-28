package ie.atu.serialize;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import ie.atu.hotel.Employee;

public class EmployeeSerializer {
	private ArrayList<Employee> employees;
	
	private final String FILENAME = "employees.bin";	
	private File employeesFile;	
	
	// Default Constructor
	public EmployeeSerializer(){
		// Construct Employee ArrayList
		employees = new ArrayList<Employee>();

		// TODO Construct employeesFile and physically create the File
		employeesFile = new File(FILENAME);
		
		// If the file doesn't exist yet, create the file
		if(employeesFile.exists() == false) {
			// Try to create the new file
			try {
				employeesFile.createNewFile();
			}
			// Exception handler 
			catch(IOException e) {
				JOptionPane.showMessageDialog(null, "There was an error creating the employee file ", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}	

	/////////////////////////////////////////////////////////////
	// Method Name : add()								       //
	// Return Type : void								       //
	// Parameters : None					                   //
	// Purpose : Reads one Employee record from the user       //
	//           and adds it to the ArrayList called employees //
	/////////////////////////////////////////////////////////////
	public void add() {
	    // Get the current value of nextNumber to display it
	    int currentEmployeeNumber = Employee.getNextNumber();

	    // Create an Employee object
	    Employee employee = new Employee();

	    // Set the employee number to the current value of nextNumber before it's incremented
	    employee.setStaticNumber(currentEmployeeNumber);

	    // Check if the read() method isn't invalid and return if it is
	    if (!employee.read()) {
	    	// Decrement the static nextNumber variable
	        EmployeeSerializer.decrementNextNumber();
	        JOptionPane.showMessageDialog(null, "The employee details input was invalid or cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
	        return; 
	    }
	    
	    // Check if the salary exceeds the maximum salary. Return if it's over
	    if (employee.getSalary() > employee.getMAX_SALARY()) {
	        JOptionPane.showMessageDialog(null, "Invalid salary amount. Maximum salary is " + employee.getMAX_SALARY(), "Invalid Salary", JOptionPane.ERROR_MESSAGE);
	        return; 
	    }

	    // Add the employee to the list if employee details are valid
	    employees.add(employee);
	    
	    // Increment nextNumber now that employee details are valid and successfully created
	    Employee.setStaticNumber(currentEmployeeNumber + 1);

	    // Message to show that employee was created 
	    JOptionPane.showMessageDialog(null, "Employee created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

	    // Serialize the employee list to save the new employee to it
	    serializeEmployees();
	}

	
	
	// Decrement method that will make sure the NextNumber is correct if user cancels the creation of an employee
	public static void decrementNextNumber() {
		// Accessing the static employee number that it can't go below 10000
	    if (Employee.getNextNumber() > 10000) {
	    	// Decrement the employee number when cancelled so that next employee will start at correct number
	        Employee.decrementNumber();
	    } 
	    else {
	        JOptionPane.showMessageDialog(null, "Employee number is unable to go below 10000", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}



	
	///////////////////////////////////////////////////////
	// Method Name : list()						        //
	// Return Type : void					   	        //
	// Parameters : None					            //
	// Purpose : Lists all Employee records in employees //
	///////////////////////////////////////////////////////		
	public void list() {
	    StringBuilder employeesToList = new StringBuilder();

	    if (!employees.isEmpty()) {
	        // Loop through all employees and add their formatted details to employeesToList
	        for (Employee tmpEmployee : employees) {
	            // Format the employee details and append them to the list string
	            String employeeDetails = String.format(
	                "Employee Number: %d\nTitle: %s\nName: %s %s\nPhone: %s\nSalary: €%.2f\n\n",
	                tmpEmployee.getNumber(),
	                tmpEmployee.getName().getTitle(),
	                tmpEmployee.getName().getFirstName(),
	                tmpEmployee.getName().getSurname(),
	                tmpEmployee.getPhoneNumber(),
	                tmpEmployee.getSalary()
	            );
	            employeesToList.append(employeeDetails);
	        }

	        // Display all the employee details in a message dialog
	        JOptionPane.showMessageDialog(null, employeesToList.toString(), "Employee List", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        // If there are no employees, show a message saying the list is empty
	        JOptionPane.showMessageDialog(null, "No Employees to list.", "Employee List", JOptionPane.INFORMATION_MESSAGE);
	    }
	}


	////////////////////////////////////////////////////////////////
	// Method Name : view()								          //
	// Return Type : Employee								      //
	// Parameters : None								          //
	// Purpose : Displays the required Employee record on screen  //
	//         : And returns it, or null if not found             //   
	////////////////////////////////////////////////////////////////		
	public Employee view() {
	    // Ask the user to input an employee number
	    String employeeToViewAsString = JOptionPane.showInputDialog("Enter the employee number to view:");

	    // Check if the user cancelled the input (i.e., entered nothing or closed the input dialog)
	    if (employeeToViewAsString == null || employeeToViewAsString.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No employee number entered.", "Error", JOptionPane.ERROR_MESSAGE);
	        return null;  // Return false if no input was provided
	    }

	    try {
	        // Try to convert the input string to an integer (employee number)
	        int employeeToView = Integer.parseInt(employeeToViewAsString);

	        // Loop through the employees list to find the employee by number
	        for (Employee tmpEmp : employees) {
	            // If we find the employee with the matching number, display their details
	            if (tmpEmp.getNumber() == employeeToView) {
	                // Format the employee details in a more readable way
	                String employeeDetails = String.format(
	                    "Employee Number: %d\nTitle: %s\nName: %s %s\nPhone: %s\nSalary: €%.2f",
	                    tmpEmp.getNumber(),
	                    tmpEmp.getName().getTitle(),
	                    tmpEmp.getName().getFirstName(),
	                    tmpEmp.getName().getSurname(),
	                    tmpEmp.getPhoneNumber(),
	                    tmpEmp.getSalary()
	                );

	                // Show employee details in a message dialog
	                JOptionPane.showMessageDialog(null, employeeDetails, "Employee Details", JOptionPane.INFORMATION_MESSAGE);
	                return tmpEmp;  // Return the employee if found and displayed
	            }
	        }

	        // If we reach here, the employee with the entered number was not found
	        JOptionPane.showMessageDialog(null, "Employee with number " + employeeToView + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
	        return null;  // Return false if employee is not found
	    } catch (NumberFormatException e) {
	        // Handle the case where the input was not a valid integer
	        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for the employee number.", "Error", JOptionPane.ERROR_MESSAGE);
	        return null;
	    }
	}
	
	///////////////////////////////////////////////////////////////////
	// Method Name : delete()							        	 //
	// Return Type : void								        	 //
	// Parameters : None									         //
	// Purpose : Deletes the required Employee record from employees //
	///////////////////////////////////////////////////////////////////	
	public void delete(){
		// TODO - Write the code for delete()
		Employee empToDelete= view();
		// If the employee is not null, delete
		if(empToDelete != null) {
			employees.remove(empToDelete);	
			JOptionPane.showMessageDialog(null, "Employee details were deleted.");
			// Serialize so it's not in bin anymore after deletion
			serializeEmployees();
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid input. Emoloyee details could not be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	///////////////////////////////////////////////////////////////
	// Method Name : edit()			  					         //
	// Return Type : void								    	 //
	// Parameters : None								     	 //
	// Purpose : Edits the required Employee record in employees //
	///////////////////////////////////////////////////////////////		
	public void edit() {
	    // Find the employee to edit using the view() method
	    Employee empToEdit = view();

	    // If empToEdit is not null, proceed with editing
	    if (empToEdit != null) {
	        // Create JTextFields with the current values for the employee's details
	        JTextField txtEmpNo = new JTextField(String.valueOf(empToEdit.getNumber()));
	        txtEmpNo.setEditable(false);  // Make the employee number field non-editable

	        JComboBox<String> cbTitle = new JComboBox<>(new String[] { "Mr", "Ms", "Mrs", "Miss" });
	        cbTitle.setSelectedItem(empToEdit.getName().getTitle());  // Set the selected title

	        JTextField txtFirstName = new JTextField(empToEdit.getName().getFirstName());
	        JTextField txtSurname = new JTextField(empToEdit.getName().getSurname());
	        JTextField txtPhoneNumber = new JTextField(empToEdit.getPhoneNumber());
	        JTextField txtSalary = new JTextField(String.valueOf(empToEdit.getSalary()));

	        // Create an Object array to hold the labels and text fields for input
	        Object[] message = {
	            "Employee Number:", txtEmpNo,
	            "Title:", cbTitle,
	            "First Name:", txtFirstName,
	            "Surname:", txtSurname,
	            "Phone Number:", txtPhoneNumber,
	            "Salary:", txtSalary
	        };

	        // Create a dialog window that will always be on top
	        JDialog dialog = new JDialog();
	        dialog.setAlwaysOnTop(true);

	        // Show the dialog with the message array (labels and text fields)
	        int option = JOptionPane.showConfirmDialog(dialog, message, "EDIT EMPLOYEE DETAILS", JOptionPane.OK_CANCEL_OPTION);

	        // If OK is selected, update the employee details
	        if (option == JOptionPane.OK_OPTION) {
	            // Update the employee's details based on user input
	            empToEdit.getName().setTitle((String) cbTitle.getSelectedItem());
	            empToEdit.getName().setFirstName(txtFirstName.getText());
	            empToEdit.getName().setSurname(txtSurname.getText());
	            empToEdit.setPhoneNumber(txtPhoneNumber.getText());

	            // Ensure salary is equal to or below 150,000
	            try {
	                double newSalary = Double.parseDouble(txtSalary.getText());
	                if (newSalary <= 150000) {
	                    empToEdit.setSalary(newSalary);
	                } 
	                // Return if salary is above 150,000
	                else {
	        	        JOptionPane.showMessageDialog(null, "Invalid salary amount. Maximum salary is 150,000", "Invalid Salary", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	            } 
	            // Return if the salary is invalid
	            catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(null, "Invalid salary. Please enter a valid salary.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Show that employee was updated successfully
	            JOptionPane.showMessageDialog(null, "Employee details have been updated.", "Success", JOptionPane.INFORMATION_MESSAGE);

	            // Serialize the updated employee and the list of all employees
	            serializeEmployees();

	            // Display the employees
	            String updatedEmployeeDetails = String.format(
	                "Employee Number: %d\nTitle: %s\nName: %s %s\nPhone: %s\nSalary: €%.2f",
	                empToEdit.getNumber(),
	                empToEdit.getName().getTitle(),
	                empToEdit.getName().getFirstName(),
	                empToEdit.getName().getSurname(),
	                empToEdit.getPhoneNumber(),
	                empToEdit.getSalary()
	            );

	            // Show that the details are updated
	            JOptionPane.showMessageDialog(null, updatedEmployeeDetails, "Updated Employee Details", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	}
	
	// This method will serialize the employees ArrayList when called, 
	// i.e. it will write employees to a file called employees.bin
	public void serializeEmployees(){
		 // TODO - Write the code for serializeEmployees()
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(employeesFile))) {
			// Write the Employees array list to the file
	        os.writeObject(employees);
	    } 
		catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Error saving employee data", "Error", JOptionPane.ERROR_MESSAGE);
	    }	
	}

	// This method will deserialize the Employees ArrayList when called, 
	// i.e. it will restore the employees ArrayList from the file employees.bin	
	public void deserializeEmployees() {
		try {
			FileInputStream fis = new FileInputStream(employeesFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			employees = (ArrayList<Employee>) ois.readObject();
			ois.close();
			if(!employees.isEmpty()) {
				int maxNum = employees.stream()
								.mapToInt(Employee::getNumber)
								.max()
								.orElse(10000);
				Employee.setStaticNumber(maxNum + 1);
			}
			System.out.println("Employees have been deseralized from the file.");
		}
		catch(FileNotFoundException fNFE){
			System.out.print("File not found.");
		}
		catch(IOException ioE) {
			System.out.println("Cannot read from " + employeesFile.getName() + ".");
		}
		catch(ClassNotFoundException cnfE) {
			System.out.println("Class not found.");
		}
	}
}