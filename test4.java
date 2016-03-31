package DBConnect;
import java.sql.*;

public class test4 {

	public static void main(String[] args) {

	}
	
	public static void delete1customer(int star_ID, Connection connection) throws Exception{
	Statement update = connection.createStatement();
	StringBuffer updatecommand=new StringBuffer();
	
	updatecommand=updatecommand.append("delete from customers where id=");
	updatecommand=updatecommand.append(star_ID);

	System.out.println(updatecommand.toString());
	//ResultSet result = update.executeQuery(updatecommand.toString());
	int retID = update.executeUpdate(updatecommand.toString());
	//System.out.println("retID = "+retID);

    System.out.println("complete");
}

}
