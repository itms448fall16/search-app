package fproject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TicketsTable extends JFrame{


	public TicketsTable() {

		createTable();
		tlogin.main(null);
	}

	private void createTable()
	{	
		//vars. for SQL Query create
		final String createTable = "CREATE TABLE cdoeticket(ticket_id INT AUTO_INCREMENT PRIMARY KEY, ticket_name VARCHAR(30), ticket_description VARCHAR(200), ticket_priority VARCHAR(7))";
		//create login table string
		final String createLoginTable = "CREATE TABLE cdoelogin(user_name VARCHAR(30) PRIMARY KEY NOT NULL, password VARCHAR(30) NOT NULL)";
		Connection connect = null;
		Statement statement = null;

		//try catch to create the ticket table  
		try {

			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
					+ "user=fp411&password=411");

			//see if table is already created	  
			DatabaseMetaData dbm = connect.getMetaData();
			// check if "cdoeticket" table is there
			ResultSet tables = dbm.getTables(null, null, "cdoeticket", null);
			//******* Table exists*******
			if (tables.next()) {

				System.out.println("cdoeticket Table already exists");
			}
			// Table does not exist
			else {
				//create table
				statement = connect.createStatement();
				statement.executeUpdate(createTable);
				System.out.println("Created cdoeticket table in database...");
			}//closes else

			//close connection/statement object  
			statement.close();
			connect.close();
		} //closes try block
		catch (Exception e) {
			System.out.println(e.getMessage());  
		}  

		//try catch to Create the Login Table
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
					+ "user=fp411&password=411");

			//see if table is already created	  
			DatabaseMetaData dbm = connect.getMetaData();
			// check if "cdoeticket" table is there
			ResultSet tables = dbm.getTables(null, null, "cdoelogin", null);
			//******* Table exists*******
			if (tables.next()) {
				System.out.println("cdoelogin Table already exists");
			}
			// Table does not exist
			else {
				//Time to create table!
				statement = connect.createStatement();
				statement.executeUpdate(createLoginTable);
				System.out.println("Created cdoelogin table in given database...");
			}//closes else

			//close connection/statement object  
			statement.close();
			connect.close();
		} //closes try block
		catch (Exception e) {
			System.out.println(e.getMessage());  
		} //closes catch 
	}//closes createTable()

	public static void main(String args[]) throws InterruptedException
	{
		System.out.println("Please wait while we check if the tables are in the database");
		new TicketsTable();
	}

}
