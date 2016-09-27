package fproject;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class tlogin extends JFrame implements ActionListener {

	public static void main(String args[]) {
		tlogin gst = new tlogin();
		gst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gst.pack();

	}//closes main

	private String userName = null;
	private String password = null;
	private String newUserName = null;
	private String newPassword = null;

	private	JMenu file = new JMenu("File");
	JMenuItem ItemExit;

	//JLABLES 
	JLabel userLabel = new JLabel("Username: ");
	JLabel passLabel = new JLabel("Password: ");
	JLabel Credentialtitle = new JLabel("Enter the credentials you want");
	JLabel ticketIDLabel = new JLabel("What ticket number would you like to update?");
	JLabel ticketName = new JLabel("Ticket Name: ");
	JLabel ticketDesc = new JLabel("Ticket Description: ");
	JLabel ticketPriority = new JLabel("Ticket Priority (high, medium, low): ");

	//JBUTTONS
	JButton loginButton = new JButton("Login");
	JButton exitButton = new JButton("EXIT");
	JButton registerButton = new JButton("Register");
	JButton newUserButton = new JButton("New User");
	//jbuttons to update, create, view, and delete ticket
	JButton createButton = new JButton("Create Ticket");
	JButton viewButton = new JButton("View open tickets");
	JButton updateButton = new JButton("Update a Ticket");
	JButton deleteButton = new JButton("Delete a Ticket");
	JButton newTicketCreateButton = new JButton("Submit New Ticket");
	JButton viewTicketButton = new JButton("View Ticket");
	JButton viewAllTicketButton = new JButton("View All Tickets");
	JButton updateTicketButton = new JButton("Update Ticket");
	JButton deleteTicketButton = new JButton("Delete Ticket");
	JButton yesButton = new JButton("Yes");
	JButton noButton = new JButton("No");
	JButton okButton = new JButton("OK");
	JButton wrongCredentialsButton = new JButton("OK");
	JButton closedButton = new JButton("Console Output");
	

	//JFRAMES
	JFrame frame = new JFrame("Ticket Details");
	JFrame newuserframe = new JFrame("New User Frame");
	JFrame loginFrame = new JFrame("Login Frame");
	JFrame wrongCredentialsFrame = new JFrame("Wrong Credentials Frame");
	JFrame menuFrame = new JFrame("Menu Frame");
	JFrame createTicketFrame = new JFrame("Create Ticket Frame");
	JFrame viewTicketFrame = new JFrame("View Tickets Frame");
	JFrame updateTicketFrame = new JFrame("Update Ticket Frame");
	JFrame deleteTicketFrame = new JFrame("Delete Tickets Frame");
	JFrame verifyDeleteFrame = new JFrame("Verification to delete");
	JFrame SQLResultsFrame = new JFrame("SQL Results Frame");


	//JTEXTAREA
	JTextArea wrongCredentialsTextArea = new JTextArea("You have entered the wrong credentials");
	JTextArea ticketIDArea = new JTextArea("What is the ticket number?");
	JTextArea ticketNameArea = new JTextArea("What is the name of the ticket?");
	JTextArea ticketDescArea = new JTextArea("What is the description of the ticket?");
	JTextArea ticketPrioritytArea = new JTextArea("What is the Priority of the ticket?");

	//Jtext fields
	JTextField userTextField = new JTextField();
	JPasswordField passTextField = new JPasswordField();
	JTextField newUserTextField = new JTextField();
	JPasswordField newPassTextField = new JPasswordField();
	JTextField ticketIDField = new JTextField();
	JTextField ticketNameField = new JTextField();
	JTextField ticketDescField = new JTextField();
	JTextField ticketPriorityField = new JTextField();
	JTextField viewTickeField = new JTextField();
	JTextField deleteTicketField = new JTextField();

	//Text fields to get from jframes
	String gTicketName = null;
	String gTicketDesc = null;
	String gTicketPriority = null;
	int gViewTicketID = 0;
	int gUpdateTicketID = 0;
	String gUpdateTicketName = null;
	String gUpdateTicketDesc = null;
	String gUpdateTicketPriority = null;
	String view = null;
	int gDeleteTicketID = 0;

	//sql static data
	static Connection conn = null;
	static Statement stmt = null;

	//SQL Results
	String rsID = null;
	String rsName = null;
	String rsDesc = null;
	String rsPriority = null;
	String results = null;

	//JTABLE
	JTable table = new JTable();

	public tlogin() {
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = loginFrame.getContentPane();

		//new JPanel object
		JPanel userPanel = new JPanel(new BorderLayout());
		//create user lable and textbox
		userLabel.setLabelFor(userTextField);
		userPanel.add(userLabel, BorderLayout.WEST);
		userPanel.add(userTextField, BorderLayout.CENTER);

		JPanel passPanel = new JPanel(new BorderLayout());
		//create password label and password text box
		passLabel.setLabelFor(passTextField);
		passPanel.add(passLabel, BorderLayout.WEST);
		passPanel.add(passTextField, BorderLayout.CENTER);

		//creates all buttons in the window
		JPanel loginPanel = new JPanel(new BorderLayout());
		loginPanel.add(exitButton, BorderLayout.WEST);
		loginPanel.add(loginButton, BorderLayout.CENTER);
		loginPanel.add(newUserButton, BorderLayout.EAST);

		//adds user/pass panels to one panel
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(userPanel, BorderLayout.NORTH);
		panel.add(passPanel, BorderLayout.SOUTH);

		//adds user/pass panel and butons to jFrame
		content.add(panel, BorderLayout.NORTH);
		content.add(loginPanel, BorderLayout.SOUTH);

		//Set the size of the Jframe and make window visible
		loginFrame.setSize(600, 200);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setVisible(true);

		//adds action listener to each button
		loginButton.addActionListener(this);
		exitButton.addActionListener(this);
		newUserButton.addActionListener(this);

	}//closes constructor

	private void newUser(){
		newuserframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//sets size and visiability for this jframe
		Container content = newuserframe.getContentPane();
		newuserframe.setSize(600, 200);
		newuserframe.setLocationRelativeTo(null);
		newuserframe.setVisible(true);

		//creates username label and text box
		JPanel userPanel = new JPanel(new BorderLayout());
		userLabel.setLabelFor(newUserTextField);
		userPanel.add(Credentialtitle, BorderLayout.PAGE_START);
		userPanel.add(userLabel, BorderLayout.WEST);
		userPanel.add(newUserTextField, BorderLayout.CENTER);

		//creates password label and password box
		JPanel passPanel = new JPanel(new BorderLayout());
		passLabel.setLabelFor(newPassTextField);
		passPanel.add(passLabel, BorderLayout.WEST);
		passPanel.add(newPassTextField, BorderLayout.CENTER);

		//creates register button
		JPanel registerPanel = new JPanel(new BorderLayout());
		registerPanel.add(registerButton, BorderLayout.NORTH);
		registerPanel.add(exitButton, BorderLayout.SOUTH);

		//adds userand password panel to one panel
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(userPanel, BorderLayout.NORTH);
		panel.add(passPanel, BorderLayout.SOUTH);

		//adds user/pass and register button panel to jframe
		content.add(panel, BorderLayout.NORTH);
		content.add(registerPanel, BorderLayout.SOUTH);

		//adds action listener to register button
		registerButton.addActionListener(this);
		exitButton.addActionListener(this);
	}

	private void wrongCredentials(){

		wrongCredentialsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = wrongCredentialsFrame.getContentPane();
		wrongCredentialsFrame.setSize(600, 200);
		wrongCredentialsFrame.setLocationRelativeTo(null);
		wrongCredentialsFrame.setVisible(true);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(wrongCredentialsTextArea, BorderLayout.NORTH);
		panel.add(wrongCredentialsButton, BorderLayout.SOUTH);

		content.add(panel, BorderLayout.CENTER);

		wrongCredentialsButton.addActionListener(this);
	}

	public void menu(){
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = menuFrame.getContentPane();
		//sets size and visiability for this jframe
		menuFrame.setSize(600, 200);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setVisible(true);

		JLabel welcomeLabel = new JLabel("Welcome to the menu. Select what you would like to do.");

		//creates username label and text box
		JPanel welcomePanel = new JPanel(new BorderLayout());
		welcomePanel.add(welcomeLabel, BorderLayout.PAGE_START);

		//creates view, update, create option buttons
		JPanel optionsPanel = new JPanel(new BorderLayout());
		optionsPanel.add(createButton, BorderLayout.WEST);
		optionsPanel.add(viewButton, BorderLayout.CENTER);
		optionsPanel.add(updateButton, BorderLayout.EAST);

		//creates delete, exit button
		JPanel deletePanel = new JPanel(new BorderLayout());
		deletePanel.add(deleteButton, BorderLayout.WEST);
		deletePanel.add(closedButton, BorderLayout.CENTER);
		deletePanel.add(exitButton, BorderLayout.EAST);


		//adds welcomePanel, optionsPanel, and deletePanel to jframe
		content.add(welcomePanel, BorderLayout.NORTH);
		if(userName.equals("admin")){
			System.out.println("Admin User Logged in " +userName);
			content.add(optionsPanel, BorderLayout.CENTER);
			content.add(deletePanel, BorderLayout.SOUTH);		}
		else{
			System.out.println("User Logged in " +userName);
			content.add(createButton, BorderLayout.CENTER);
			content.add(exitButton, BorderLayout.SOUTH);
		}

		//adds action listener to register button
		createButton.addActionListener(this);
		viewButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		closedButton.addActionListener(this);
		exitButton.addActionListener(this);

	}

	public void createTicket(){
		createTicketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = createTicketFrame.getContentPane();

		createTicketFrame.setSize(600, 200);
		createTicketFrame.setLocationRelativeTo(null);
		createTicketFrame.setVisible(true);

		JLabel ticketName = new JLabel("Ticket Name: ");
		JLabel ticketDesc = new JLabel("Ticket Description: ");
		JLabel ticketPriority = new JLabel("Ticket Priority (high, medium, low): ");

		JPanel ticketNamePanel = new JPanel(new BorderLayout());
		ticketName.setLabelFor(ticketNameField);
		ticketNamePanel.add(ticketName, BorderLayout.WEST);
		ticketNamePanel.add(ticketNameField, BorderLayout.CENTER);

		JPanel ticketDescPanel = new JPanel(new BorderLayout());
		ticketDesc.setLabelFor(ticketDescField);
		ticketDescPanel.add(ticketDesc, BorderLayout.WEST);
		ticketDescPanel.add(ticketDescField, BorderLayout.CENTER);

		JPanel ticketPriorityPanel = new JPanel(new BorderLayout());
		ticketPriority.setLabelFor(ticketPriorityField);
		ticketPriorityPanel.add(ticketPriority, BorderLayout.WEST);
		ticketPriorityPanel.add(ticketPriorityField, BorderLayout.CENTER);

		JPanel ticketInfoPanel = new JPanel(new BorderLayout());
		ticketInfoPanel.add(ticketNamePanel, BorderLayout.NORTH);
		ticketInfoPanel.add(ticketDescPanel, BorderLayout.CENTER);
		ticketInfoPanel.add(ticketPriorityPanel, BorderLayout.SOUTH);

		JPanel createTicketSubmitButtonPanel = new JPanel(new BorderLayout());
		createTicketSubmitButtonPanel.add(newTicketCreateButton, BorderLayout.NORTH);
		createTicketSubmitButtonPanel.add(exitButton, BorderLayout.SOUTH);

		content.add(ticketInfoPanel, BorderLayout.NORTH);
		content.add(createTicketSubmitButtonPanel, BorderLayout.SOUTH);

		exitButton.addActionListener(this);
		newTicketCreateButton.addActionListener(this);


	}

	public void viewTicket(){
		viewTicketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = viewTicketFrame.getContentPane();
		viewTicketFrame.setSize(600, 200);
		viewTicketFrame.setLocationRelativeTo(null);
		viewTicketFrame.setVisible(true);

		JLabel viewTicketLabel = new JLabel("What ticket number (Integer only) would you like to view?");

		JPanel viewTickePanel = new JPanel(new BorderLayout());
		viewTicketLabel.setLabelFor(viewTickeField);
		viewTickePanel.add(viewTicketLabel, BorderLayout.WEST);
		viewTickePanel.add(viewTickeField, BorderLayout.CENTER);

		JPanel viewTicketButtonPanel = new JPanel(new BorderLayout());
		viewTicketButtonPanel.add(viewTicketButton, BorderLayout.NORTH);
		viewTicketButtonPanel.add(viewAllTicketButton, BorderLayout.SOUTH);

		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(viewTicketButtonPanel, BorderLayout.NORTH);
		buttonPanel.add(exitButton, BorderLayout.SOUTH);

		content.add(viewTickePanel, BorderLayout.NORTH);
		content.add(buttonPanel, BorderLayout.SOUTH);

		exitButton.addActionListener(this);
		viewTicketButton.addActionListener(this);
		viewAllTicketButton.addActionListener(this);


	}

	public void updateTicket(){
		updateTicketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = updateTicketFrame.getContentPane();

		updateTicketFrame.setSize(600, 200);
		updateTicketFrame.setLocationRelativeTo(null);
		updateTicketFrame.setVisible(true);

		JLabel ticketID = new JLabel("Enter the Ticket ID you wish to update: ");
		JLabel ticketName = new JLabel("Update Ticket Name: ");
		JLabel ticketDesc = new JLabel("Update Ticket Description: ");
		JLabel ticketPriority = new JLabel("Update Ticket Priority (high, medium, low): ");

		JPanel ticketIDPanel = new JPanel(new BorderLayout());
		ticketIDPanel.add(ticketID, BorderLayout.WEST);
		ticketIDPanel.add(ticketIDField, BorderLayout.CENTER);

		JPanel ticketNamePanel = new JPanel(new BorderLayout());
		ticketName.setLabelFor(ticketNameField);
		ticketNamePanel.add(ticketName, BorderLayout.WEST);
		ticketNamePanel.add(ticketNameField, BorderLayout.CENTER);

		JPanel ticketDescPanel = new JPanel(new BorderLayout());
		ticketDesc.setLabelFor(ticketDescField);
		ticketDescPanel.add(ticketDesc, BorderLayout.WEST);
		ticketDescPanel.add(ticketDescField, BorderLayout.CENTER);

		JPanel ticketPriorityPanel = new JPanel(new BorderLayout());
		ticketPriority.setLabelFor(ticketPriorityField);
		ticketPriorityPanel.add(ticketPriority, BorderLayout.WEST);
		ticketPriorityPanel.add(ticketPriorityField, BorderLayout.CENTER);

		JPanel ticketInfoPanel = new JPanel(new BorderLayout());
		ticketInfoPanel.add(ticketNamePanel, BorderLayout.NORTH);
		ticketInfoPanel.add(ticketDescPanel, BorderLayout.CENTER);
		ticketInfoPanel.add(ticketPriorityPanel, BorderLayout.SOUTH);

		JPanel createTicketSubmitButtonPanel = new JPanel(new BorderLayout());
		createTicketSubmitButtonPanel.add(updateTicketButton, BorderLayout.NORTH);
		createTicketSubmitButtonPanel.add(exitButton, BorderLayout.SOUTH);

		content.add(ticketIDPanel, BorderLayout.NORTH);
		content.add(ticketInfoPanel, BorderLayout.CENTER);
		content.add(createTicketSubmitButtonPanel, BorderLayout.SOUTH);

		exitButton.addActionListener(this);
		updateTicketButton.addActionListener(this);


	}

	public void deleteTicket(){
		deleteTicketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = deleteTicketFrame.getContentPane();
		deleteTicketFrame.setSize(600, 200);
		deleteTicketFrame.setLocationRelativeTo(null);
		deleteTicketFrame.setVisible(true);

		JTextArea deleteTicketArea = new JTextArea("What ticket number would you like to delete?");

		JPanel deleteTicketPanel = new JPanel(new BorderLayout());
		deleteTicketPanel.add(deleteTicketArea, BorderLayout.WEST);
		deleteTicketPanel.add(deleteTicketField, BorderLayout.CENTER);

		JPanel deleteTicketButtonPanel = new JPanel(new BorderLayout());
		deleteTicketButtonPanel.add(deleteTicketButton, BorderLayout.NORTH);
		deleteTicketButtonPanel.add(exitButton, BorderLayout.SOUTH);

		content.add(deleteTicketPanel, BorderLayout.NORTH);
		content.add(deleteTicketButtonPanel, BorderLayout.SOUTH);

		exitButton.addActionListener(this);
		deleteTicketButton.addActionListener(this);

	}

	public void verifyDelete(){
		verifyDeleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = verifyDeleteFrame.getContentPane();
		verifyDeleteFrame.setSize(600, 200);
		verifyDeleteFrame.setLocationRelativeTo(null);
		verifyDeleteFrame.setVisible(true);

		String verify ="Are you sure you want to delete ticket number : "+ gDeleteTicketID;

		JTextArea VerifydeleteArea = new JTextArea(verify);

		JPanel deleteTicketPanel = new JPanel(new BorderLayout());
		deleteTicketPanel.add(VerifydeleteArea, BorderLayout.NORTH);

		JPanel deleteTicketButtonPanel = new JPanel(new BorderLayout());
		deleteTicketButtonPanel.add(yesButton, BorderLayout.EAST);
		deleteTicketButtonPanel.add(noButton, BorderLayout.WEST);
		content.add(deleteTicketPanel, BorderLayout.NORTH);
		content.add(deleteTicketButtonPanel, BorderLayout.SOUTH);

		yesButton.addActionListener(this);
		noButton.addActionListener(this);	

	}

	public void SQLResults(){
		SQLResultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = SQLResultsFrame.getContentPane();
		SQLResultsFrame.setSize(900, 200);
		SQLResultsFrame.setLocationRelativeTo(null);
		SQLResultsFrame.setVisible(true);

		String resultsTitle ="Results from action performed";

		JTextArea VerifydeleteArea = new JTextArea(resultsTitle);
		JTextArea resultsArea = new JTextArea(results);

		JPanel ResultsPanel = new JPanel(new BorderLayout());
		ResultsPanel.add(VerifydeleteArea, BorderLayout.NORTH);
		ResultsPanel.add(resultsArea, BorderLayout.SOUTH);

		content.add(ResultsPanel, BorderLayout.NORTH);
		content.add(okButton, BorderLayout.SOUTH);

		okButton.addActionListener(this);

	}

	//method to get the button that was clicked
	public void actionPerformed(ActionEvent e){

		// exitButton = ALL methods()
		if(e.getSource() == exitButton){
			System.out.println("Exited program");
			System.exit(0);
		}
		else if(e.getSource() == okButton){
			System.out.println("OK button pressed");
			SQLResultsFrame.dispose();
			menu();
		}

		//loginButton = tlogin() * calls new user function to create new user and enter them into sql database
		else if(e.getSource() == loginButton){
			userName = userTextField.getText();
			password = new String(passTextField.getPassword());
			//calls method to check credentials
			logUserIn();
		}

		//newUserButton = tlogin() * calls new user function to create new user and enter them into sql database
		else if(e.getSource() == newUserButton) {
			newUser();
		}

		//
		else if (e.getSource() == wrongCredentialsButton) {
			System.out.println("User entered wrong credentials. Button bring back to login screen");
			wrongCredentialsFrame.dispose();
			main(null);
		}

		//registerButton = newUser()
		else if(e.getSource() == registerButton){
			//closes the new user frame
			newuserframe.dispose();
			newUserName = newUserTextField.getText();
			newPassword = new String(newPassTextField.getPassword());
			//clears fields
			newUserTextField.setText(null);
			newPassTextField.setText(null);
			//launches method to insert user in database
			createUserSQL();
			//launches the login menu again
			main(null);
		}

		//createButton = menu()
		else if(e.getSource() == createButton){
			System.out.println("createbutton clicked");
			//closes menu frame
			menuFrame.dispose();
			createTicket();
		}

		//viewButton = menu()
		else if(e.getSource() == viewButton){
			System.out.println("viewButton clicked");
			//call method to display to JFrame to say what tickets you want to view
			//closes menu frame
			menuFrame.dispose();
			viewTicket();

		}

		//updateButton = menu()
		else if(e.getSource() == updateButton){
			System.out.println("updateButton clicked");
			//closes menu frame
			menuFrame.dispose();
			updateTicket();		
		}

		//deleteButton = menu()
		else if(e.getSource() == deleteButton){
			System.out.println("deleteButton clicked");
			//closes menu frame
			menuFrame.dispose();
			deleteTicket();
		}
		//yesButon = verifydelete()
		else if(e.getSource() == yesButton){
			System.out.println("yesButton clicked");
			//closes menu frame
			verifyDeleteFrame.dispose();
			deleteTicketSQL();
			SQLResults();
		}
		//noButon = verifydelete()
		else if(e.getSource() == noButton){
			System.out.println("noButton clicked");
			//closes menu frame
			verifyDeleteFrame.dispose();
			menu();
		}

		//newTicketCreateButton = createTicket()
		else if(e.getSource() == newTicketCreateButton){
			createTicketFrame.dispose();
			System.out.println("newTicketCreateButton clicked");
			gTicketName = ticketNameField.getText();
			gTicketDesc = ticketDescField.getText();
			gTicketPriority = ticketPriorityField.getText();

			//clears fields
			ticketNameField.setText("");
			ticketDescField.setText("");
			ticketPriorityField.setText("");

			//call method to create sql insert into
			createTicketSQL();
			SQLResults();
		}

		//viewTicketButton = viewTicket()
		else if(e.getSource() == viewTicketButton){
			System.out.println("viewTicketButton clicked");
			gViewTicketID = Integer.parseInt(viewTickeField.getText());
			//clears fields
			viewTickeField.setText(null);
			viewTicketFrame.dispose();
			view = ("SELECT * FROM cdoeticket WHERE ticket_id='" +gViewTicketID+"'");
			jframe();
			menu();
		}

		//viewAllTicketButton = viewTicket()
		else if(e.getSource() == viewAllTicketButton){
			System.out.println("viewAllTicketButton clicked");
			viewTicketFrame.dispose();
			//test
			view ="SELECT * FROM cdoeticket";
			jframe();
			menu();
		}

		//updateTicketButton = updateTicket()
		else if(e.getSource() == updateTicketButton){
			System.out.println("updateTicketButton clicked");
			gUpdateTicketID = Integer.parseInt(ticketIDField.getText());
			gUpdateTicketName = ticketNameField.getText();
			gUpdateTicketDesc = ticketDescField.getText();
			gUpdateTicketPriority = ticketPriorityField.getText();

			//clears fields
			ticketIDField.setText(null);
			ticketNameField.setText(null);
			ticketDescField.setText(null);
			ticketPriorityField.setText(null);

			updateTicketFrame.dispose();
			updateTicketSQL();
			SQLResults();
		}

		//deleteTicketButton = deleteTicket()
		else if(e.getSource() == deleteTicketButton){
			System.out.println("deleteButton clicked");
			gDeleteTicketID = Integer.parseInt(deleteTicketField.getText());
			//clears field
			deleteTicketField.setText(null);
			deleteTicketFrame.dispose();
			verifyDelete();
		}
		else if(e.getSource() == closedButton){
			
			//TODO
			System.out.println("ItemExit clicked");
			closeTicketSQL();
		}
		else if(e.getSource() == ItemExit){
			System.out.println("ItemExit clicked");
			menu();
		}

		//catch everything else
		else{
			System.out.println("ERROR");		
		}//closes else block
	}//closes action performed

	private void logUserIn(){
		loginFrame.dispose(); //you can't see me!

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
					+ "user=fp411&password=411");

			//Excute Querry
			stmt = conn.createStatement();
			//execute querry to see is given userName and password are in the table
			String sql = ("SELECT * FROM cdoelogin WHERE user_name='" + userName + 
					"' AND password='" + password + "'");
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Checked if user: " + userName + " exist in table.");

			//get results
			if(rs.next()){
				System.out.println("Login Suscessful");
				menu();
			}
			//if login not sucessful send user back to login screen
			else{
				System.out.println("Login Not sucessful");
				wrongCredentials();
			}
			//close connection/statement object  
			stmt.close();
			conn.close();
		} //closes try block
		catch (Exception e) {
			System.out.println(e.getMessage());  
		} //closes catch 
	}//closes login user

	private void createUserSQL(){

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
					+ "user=fp411&password=411");

			//Excute Querry
			stmt = conn.createStatement();
			//execute querry to see is given userName and password are in the table
			String sql = "INSERT INTO cdoelogin (user_name, password)" + 
					"Values ('"+newUserName+"', '"+newPassword+"')";
			stmt.executeUpdate(sql);
			System.out.println("Inserted User: " + newUserName + " into table.");

			//close connection/statement object  
			stmt.close();
			conn.close();
		} //closes try block
		catch (Exception e) {
			System.out.println(e.getMessage());
			results = e.getMessage();
			SQLResults();
		} //closes catch 	
	}//closes create user

	private void createTicketSQL(){

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
					+ "user=fp411&password=411");

			//insert global variable into sql
			stmt = conn.createStatement();
			//execute querry to see is given userName and password are in the table
			String sql = ("INSERT INTO cdoeticket (ticket_name, ticket_description, ticket_priority) "
					+ "VALUES ('"+gTicketName+"', '"+gTicketDesc+"', '"+gTicketPriority+"')");
			stmt.executeUpdate(sql);

			//querry to get ticket ID Autoassigned in server
			String idNum = ("Select ticket_id FROM cdoeticket WHERE ticket_name='" + gTicketName+"'");
			ResultSet rs = stmt.executeQuery(idNum);
			while(rs.next()){
				rsID = String.valueOf(rs.getInt("ticket_id"));
			}
			//output all info to console
			System.out.println("Ticket Created. ID: " + rsID + "Name:" +  gTicketName +
					"Description: " + gTicketDesc + "Priority: " + gTicketPriority);

			results = ("Ticket Created. ID: " + rsID + "\nName: " +  gTicketName +
					"\nDescription: " + gTicketDesc + "\nPriority: " + gTicketPriority);

			//close connection/statement object  
			stmt.close();
			conn.close();
		} //closes try block
		catch (Exception e) {
			System.out.println(e.getMessage()); 
			results = e.getMessage();
			SQLResults();
		} //closes catch 
	}//closes createTicketSQL

	private void updateTicketSQL(){

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
					+ "user=fp411&password=411");

			//Excute Querry
			stmt = conn.createStatement();
			//execute querry to see is given userName and password are in the table

			String sql = ("UPDATE cdoeticket SET ticket_name='" + gUpdateTicketName +"', ticket_description='" +
					gUpdateTicketDesc+"', ticket_priority='" + gUpdateTicketPriority+"' WHERE ticket_id='" + gUpdateTicketID+"'");
			stmt.executeUpdate(sql);

			String updateRs = ("SELECT * FROM cdoeticket WHERE ticket_id='" +gUpdateTicketID+"'");
			ResultSet rs = stmt.executeQuery(updateRs);

			//assign results to global variables
			while (rs.next()) {
				rsID = String.valueOf(rs.getInt("ticket_id"));
				rsName = rs.getString("ticket_name");
				rsDesc = rs.getString("ticket_description");
				rsPriority = rs.getString("ticket_priority");

				System.out.println("Ticket Updated. ID: "+ rsID + "Name: " + rsName +
						"Description: " + rsDesc + "Priority: " + rsPriority);

			}

			results = ("Ticket ID: "+ rsID + "Name: " + rsName +
					"Description: " + rsDesc + "Priority: " + rsPriority);


			//close connection/statement object  
			stmt.close();
			conn.close();
		} //closes try block
		catch (Exception e) {
			System.out.println(e.getMessage()); 
			results = e.getMessage();
			SQLResults();
		} //closes catch 
	}//closes createTicketSQL

	private void closeTicketSQL(){

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
					+ "user=fp411&password=411");

			//Excute Querry
			stmt = conn.createStatement();
			//execute querry to see is given userName and password are in the table

			
			String Rs = ("SELECT * FROM cdoeticket WHERE ticket_priority='closed'");
			ResultSet rs = stmt.executeQuery(Rs);

			ArrayList<String> numClosed = new ArrayList<String>();
			//assign results to global variables
			while (rs.next()) {
				String rsID = rs.getString("ticket_name");
				numClosed.add(rsID);
			}
			for(int i=0; i<numClosed.size(); i++)
			System.out.println("Ticket with high priority: "+ numClosed.size());
			
			String Rs2 = ("SELECT * FROM cdoeticket WHERE ticket_priority!='closed'");
			ResultSet rs2 = stmt.executeQuery(Rs2);

			ArrayList<String> numClosed2 = new ArrayList<String>();
			//assign results to global variables
			while (rs2.next()) {
				String rsID = rs2.getString("ticket_name");
				numClosed2.add(rsID);
			}
			
			System.out.println("Ticket with non-high priority: "+ numClosed2.size());
					
			
			
			String updateRs = ("SELECT * FROM cdoeticket WHERE ticket_priority='high'");
			ResultSet rs3 = stmt.executeQuery(updateRs);

			ArrayList<String> numClosed3 = new ArrayList<String>();
			//assign results to global variables
			while (rs3.next()) {
				String rsID = rs3.getString("ticket_name");
				numClosed.add(rsID);
			}
			for(int i=0; i<numClosed.size(); i++)
			System.out.println("Ticket with high priority: "+ numClosed3.get(i)+"\n");

			//close connection/statement object  
			stmt.close();
			conn.close();
		} //closes try block
		catch (Exception e) {
			System.out.println(e.getMessage()); 
			results = e.getMessage();
			SQLResults();
		} //closes catch 
	}//closes createTicketSQL

	
	private void deleteTicketSQL(){
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
					+ "user=fp411&password=411");

			//Excute Querry
			stmt = conn.createStatement();
			String sql = "DELETE FROM cdoeticket WHERE ticket_id ='"+gDeleteTicketID+"'";
			stmt.executeUpdate(sql);

			//output all info to console
			System.out.println("Ticket:" +gDeleteTicketID+ " deleted from table");

			results = ("Ticket:" +gDeleteTicketID+ " deleted from table");

			//close connection/statement object  
			stmt.close();
			conn.close();
		} //closes try block
		catch (Exception e) {
			System.out.println(e.getMessage());
			results = e.getMessage();
			SQLResults();
		} //closes catch 
	}

	public void jframe(){

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			conn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
					+ "user=fp411&password=411");
			Statement st = conn.createStatement();

			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			Vector<String> column = new Vector<String>();
			try {
				st = conn.createStatement();
				ResultSet res = st.executeQuery(view);
				ResultSetMetaData metaData = res.getMetaData();
				int columns = metaData.getColumnCount();

				//get column names from table!
				String cols = ""; 

				for (int i = 1; i <= columns ; i++) {
					cols = metaData.getColumnName(i);
					column.add(cols);
				}
				//get row data from table!
				while (res.next()) {
					Vector<Object> row = new Vector<Object>(columns);

					for (int i = 1; i <= columns; i++) {
						row.addElement(res.getObject(i));
					} 
					data.addElement(row);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			JTable table = new JTable(data,column);
			JScrollPane scrollPane = new JScrollPane( table );
			getContentPane().add( scrollPane );

			//JPanel buttonPanel = new JPanel();
			getContentPane().add( okButton );

			ItemExit = new JMenuItem("Exit");
			ItemExit.setMnemonic('x');
			file.add(ItemExit);
			JMenuBar bar = new JMenuBar();
			bar.add(file);

			frame.setSize(700, 200);
			frame.add(new JScrollPane(table));
			frame.setJMenuBar(bar);
			frame.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
			view = null;
		}
		catch (ClassNotFoundException e) {
			System.err.println("Class not found!");
		}

		catch (SQLException e) {
			System.err.println("SQL Error! " + e.getMessage() );
		} 
		ItemExit.addActionListener(this);
	}
}//closes class