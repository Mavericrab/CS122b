package DBConnect;

import java.sql.*;

public class insertCustomer {
	// Incorporate MySQL driver
	public static void insertCustomer(String first_name, String last_name, String cc_id, String address, String email, String password,Connection connection)throws Exception{
    Class.forName("com.mysql.jdbc.Driver").newInstance();

   // Connect to the test database
   connection =
       DriverManager.getConnection("jdbc:mysql:///moviedb", "root", "liudl015");
   
   //if his credit card does not exist in the credit card table
   try{
   String checkcredit="SELECT COUNT(*) FROM creditcards WHERE id=?";
   PreparedStatement updatecredit = connection.prepareStatement(checkcredit);
   updatecredit.setString(1, cc_id);
   ResultSet results = updatecredit.executeQuery();
	results.next();
	
	if (results.getInt(1) < 0) return;
	
	}
   catch (SQLException e)
	{
		System.out.println("credit card does not exist in the credit card table");
		return;
	}
   
   //If the customer has a single name
   if(last_name==null){
	   last_name=first_name;
	   first_name="";
   }
   if(first_name==null){
   	first_name="";
   }
   // prepare SQL statement template that's to be repeatedly excuted
   String updateString = "INSERT INTO customers (first_name, last_name, cc_id, address, email, password) VALUES(?,?,?,?,?,?)";
   PreparedStatement updateCustomers = connection.prepareStatement(updateString);

   
   updateCustomers.setString(1, first_name);
   updateCustomers.setString(2, last_name);
   updateCustomers.setString(3, cc_id);
   updateCustomers.setString(4, address);
   updateCustomers.setString(5, email);
   updateCustomers.setString(6, password);

           
           updateCustomers.executeUpdate();
           System.out.println("add succeeded");
       }
}
