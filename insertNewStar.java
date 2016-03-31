package DBConnect;

import java.sql.*;

public class insertNewStar {
	
	public static void insertStar(String first_name, String last_name, String dob,Connection connection) throws Exception{
		
		
		// Incorporate MySQL driver
        Class.forName("com.mysql.jdbc.Driver").newInstance();

       // Connect to the test database
       connection =
           DriverManager.getConnection("jdbc:mysql:///moviedb", "root", "liudl015");

       if(last_name==null){
    	   last_name=first_name;
    	   first_name="";
       }
       // prepare SQL statement template that's to be repeatedly excuted
        try{
       String updateString = "INSERT INTO stars (first_name, last_name, dob) VALUES (?,?,?)";
       PreparedStatement updateStars = connection.prepareStatement(updateString);

       
               updateStars.setString(1, first_name);
               updateStars.setString(2, last_name);
               updateStars.setString(3, dob);
               
               updateStars.executeUpdate();
       }
       catch(SQLException e){
   		System.out.println("added failed");
   		return;
   	}
     }
	
}
