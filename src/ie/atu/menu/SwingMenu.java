package ie.atu.menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import ie.atu.serialize.EmployeeSerializer;

// JFrame that contains two panels, one to the LEFT of the JFrame, and one to the CENTER
// SwingMenu IS-A JFrame ==> Inheritance
public class SwingMenu extends JFrame{  
   // Instance Variables go here (properties of our JFrame)...
   private static final int FRAME_WIDTH = 300;
   private static final int FRAME_HEIGHT = 340;     
	
   // It has two JPanels...
   //    centerPAnel with a JLabel, and...
   private JPanel centerPanel;
   private JLabel jlblEmployee;	
   private Font f;
	
	// buttonPanel with 6 JButtons
   private JPanel buttonPanel;  
   private JButton jbtAdd, jbtList, jbtView, jbtEdit, jbtDelete, jbtExit;
	
   // EmployeeSerializer object for calling methods in EmployeeSerializer class
   private EmployeeSerializer employeeSerializer;
    
   // Constructor - SetLayout & Add Components here...
   public SwingMenu(){	   
      f = new Font("Gill Sans MT", Font.BOLD, 22);		
	   
      /////
      // Build centerPanel 
      /////
      centerPanel = new JPanel(new BorderLayout());
      centerPanel.setBackground(Color.WHITE);
      // Add an empty border with specified padding (top, left, bottom, right)
      centerPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
      
      jlblEmployee = new JLabel("EMPLOYEE",new ImageIcon("image/employees.jpg"),SwingConstants.CENTER);
      // Center the icon and text and place the text under the icon, and set the Font of jlblEmployeeStore
      jlblEmployee.setHorizontalTextPosition(JLabel.CENTER);
      jlblEmployee.setVerticalTextPosition(JLabel.TOP);
      jlblEmployee.setFont(f);
      centerPanel.add(jlblEmployee,BorderLayout.NORTH);
      jlblEmployee = new JLabel("MAINTENANCE",SwingConstants.CENTER);
      jlblEmployee.setFont(f);
      centerPanel.add(jlblEmployee, BorderLayout.CENTER);
      centerPanel.setBackground(Color.WHITE);

      /////
      // Build buttonPanel
      /////
      buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(6,1,5,5));
      buttonPanel.setBackground(Color.WHITE);
      // Add an empty border with specified padding (top, left, bottom, right)
      buttonPanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
              
      buttonPanel.add(jbtAdd=new JButton("ADD"));
      buttonPanel.add(jbtList=new JButton("LIST"));				
      buttonPanel.add(jbtView=new JButton("VIEW"));		
      buttonPanel.add(jbtEdit=new JButton("EDIT"));				
      buttonPanel.add(jbtDelete=new JButton("DELETE"));	
      buttonPanel.add(jbtExit=new JButton("EXIT"));				

      // setMnemonics on JButtons
      jbtAdd.setMnemonic('A');
      jbtList.setMnemonic('L');
      jbtView.setMnemonic('V');
      jbtEdit.setMnemonic('E');
      jbtDelete.setMnemonic('D');
      jbtExit.setMnemonic('X');

      // Set up Frame
      add(centerPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.WEST);

      // Create a employeeSerializer object to call add(), list(), etc. methods
      employeeSerializer=new EmployeeSerializer();
      // deserializeEmployees() before the JFrame is displayed...
      employeeSerializer.deserializeEmployees();
        
      // Create a Listener for each of the 6 JButtons		
      jbtAdd.addActionListener(e -> {
         employeeSerializer.add();
		});
      jbtList.addActionListener(e -> {
         employeeSerializer.list();
		});				
      jbtView.addActionListener(e -> {
         employeeSerializer.view();
		});
		jbtEdit.addActionListener(e -> {
		   employeeSerializer.edit();
		});
		jbtDelete.addActionListener(e -> {
			employeeSerializer.delete();
		});				
		jbtExit.addActionListener(e -> {
			// Serialize the Employees ArrayList on Exit		
			employeeSerializer.serializeEmployees();
			dispose();
		});
   }
   
   public static void main(String[] args){ 
      SwingMenu frame = new SwingMenu();
		
      // Do whatever to frame object
      // Set frame's title to "EMPLOYEE SECTION"
      frame.setTitle("EMPLOYEE SECTION");

      // Make frame big enough to hold components
      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

      // Add the Default Close operation to frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
      // Center the frame
      frame.setLocationRelativeTo(null);

      // Set frame visible
      frame.setVisible(true);
   }
}