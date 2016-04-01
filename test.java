package p1;
import java.sql.*;

public class test {
	 public static void main(String[] arg) throws Exception
	       {
		 try {
		      Class.forName("com.mysql.jdbc.Driver");   
		      System.out.println("Success loading Mysql Driver!");
		    }
		    catch (Exception e) {
		      System.out.print("Error loading Mysql Driver!");
		      e.printStackTrace();
		    }
		 
		      
		 Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb","root", "1");
		          System.out.println("Success connect Mysql server!");
		          task2.queryStarByName("Hilary","Swank", connection);
		          task2.insertStar("Happy","",connection);
		          task2.insertCustomer("Rick", "Carter", " 6831232434544301", "d", "e","f",connection);
		       }
}

