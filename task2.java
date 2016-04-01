package p1;
import java.sql.*;

public class task2 {
	public static void main(){
	}
    /**
     * Print out (to the screen) the movies featuring a given star by star ID.
     * 
     * @throws Exception 
     * 
     * @star_ID
     * @connection
     */
	public static void queryStarByID(int star_ID, Connection connection) throws Exception{
		Statement select = connection.createStatement();
		Statement select_from_stars_in_movies = connection.createStatement();	
		ResultSet result_from_stars_in_movies = select_from_stars_in_movies.executeQuery("Select movie_id from stars_in_movies where star_id="+star_ID);
	
		while(result_from_stars_in_movies.next()){   
		ResultSet result = select.executeQuery("Select * from movies where id="+result_from_stars_in_movies.getInt(1));	    
        while (result.next())
     {
                 System.out.println("Id = " + result.getInt(1));
                 System.out.println("Title = " + result.getString(2));
                 System.out.println("Year = " + result.getString(3));
                 System.out.println("Director = " + result.getString(4));
                 System.out.println();
                  }
        
           result.close();
       
        }
      
	}
	
	/**
     * return the star_ID by star's both first_name and last_name.
     * @throws Exception 
     * 
     * @first_name
     * @last_name
     * @connection
     * 
     */
	public static void queryStarByName(String first_name,String last_name,Connection connection) throws Exception{
		Statement select = connection.createStatement();
		
		String select_statement=new String();
		if(!"".equals(first_name)&&!"".equals(last_name)){
			select_statement="Select id from stars where first_name=\""+first_name+"\" and last_name=\""+last_name+"\"";
		}
		else if("".equals(first_name)&&!"".equals(last_name)){
			select_statement="Select id from stars where last_name=\""+last_name+"\"";
		}
		else if(!"".equals(first_name)&&"".equals(last_name)){
			select_statement="Select id from stars where first_name=\""+first_name+"\"";
		}
		else System.out.println("please input valid name!");
		
		ResultSet result = select.executeQuery(select_statement);
		
		while (result.next()){
		     task2.queryStarByID(result.getInt("id"), connection);
        }
		result.close();
		select.close();
	}
	
	/**
     * Insert a new star into the database. 
     * If the star has a single name, add it as his last_name and assign an empty string ("") to first_name.
     * @throws Exception 
     * 
     * @first_name
     * @last_name
     * @connection
     * 
     */
    public static void insertStar(String first_name, String last_name,Connection connection) throws Exception{
		

       if(last_name==null){
    	   last_name=first_name;
    	   first_name="";
       }
       
        // prepare SQL statement template that's to be repeatedly excuted
        String updateString = "INSERT INTO stars (first_name, last_name) VALUES (?,?)";
        PreparedStatement updateStars = connection.prepareStatement(updateString);

        updateStars.setString(1, first_name);
        updateStars.setString(2, last_name);
        //updateStars.setString(3, dob);
        updateStars.executeUpdate();
       
       }
   
    /**
     * Insert a customer into the database. 
     * Do not allow insertion of a customer if his credit card does not exist in the credit card table. 
     * The credit card table simulates the bank records. 
     * If the customer has a single name, add it as his last_name and assign an empty string ("") to first_name. 
     * 
     * @throws Exception 
     * 
     * @first_name
     * @last_name
     * @cc_id
     * @address
     * @email
     * @password
     * @connection
     * 
     */
    public static void insertCustomer(String first_name, String last_name, String cc_id, 
    		String address, String email, String password,Connection connection)throws Exception{
    	
    		String checkCredit="SELECT COUNT(*) FROM creditcards WHERE id=?";
    		PreparedStatement updateCredit = connection.prepareStatement(checkCredit);
    		updateCredit.setString(1, cc_id);
    		ResultSet results = updateCredit.executeQuery();
    		if(results.next()){
    		
    		   //If the customer has a single name
    		   if(last_name==null){
    			   last_name=first_name;
    			   first_name="";
    		   }
    		   
    		   if(first_name==null){
    		   	first_name="";
    		   }
    		   
    		   // prepare SQL statement template that's to be repeatedly excuted
    		   String updateString = "INSERT INTO customers (first_name, last_name, cc_id, " +
    		   		"address, email, password) VALUES(?,?,?,?,?,?)";
    		   PreparedStatement updateCustomers = connection.prepareStatement(updateString);

    		   updateCustomers.setString(1, first_name);
    		   updateCustomers.setString(2, last_name);
    		   updateCustomers.setString(3, cc_id);
    		   updateCustomers.setString(4, address);
    		   updateCustomers.setString(5, email);
    		   updateCustomers.setString(6, password);
               try{
            	   updateCustomers.executeUpdate();
    		       System.out.println("add succeeded");
    		   }
               catch (SQLException e){
            	   System.out.println("credit card does not exist in the credit card table");
            	   System.out.println(e.getMessage());
			       return;
		       }
           }
    }
    
    /**
     * Delete a customer from the database. 
     * @throws Exception 
     * 
     * @customer_ID
     * @connection
     * 
     */
    public static void deleteCustomer(int customer_ID, Connection connection) throws Exception{
	           Statement update = connection.createStatement();
	           System.out.println("delete from customers where id="+customer_ID);
	           update.executeUpdate("delete from customers where id="+customer_ID);
	
    }
    
    /**
     * Provide the metadata of the database; 
     * in particular, print out the name of each table and, for each table, each attribute and its type. 
     * @throws Exception 
     * 
     
     * @connection
     * 
     */
    public static void providemetadata(Connection connection) throws Exception{
        String colName;
        String colType;
        String tabName;
        
    	DatabaseMetaData metadata = connection.getMetaData();
    	ResultSet tableSet = metadata.getTables(null, "%", "%", new String[]{"TABLE"});
    	while(tableSet.next()){
    		tabName = tableSet.getString("TABLE_NAME");
    		System.out.println(tabName+":");
    		ResultSet colSet = metadata.getColumns(null, "%", tabName, "%");
    		while(colSet.next()){
    			colName = colSet.getString("COLUMN_NAME");
    			colType = colSet.getString("TYPE_NAME");
    			System.out.println("  "+colName+" "+colType);
    		}
    	}}

}
