package p1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {
	static Connection connection;
	public static void menu() throws SQLException{
		enter();
		
		boolean open=true;
		while(open==true){
			System.out.println("Choose your command:\n");
			System.out.println("1: Print out the movies featuring a given star.\n" +
			"2: Insert a new star into the database.\n" +
					"3: Insert a customer into the database.\n" +
			"4: Delete a customer from the database.\n"+
					"5: Provide the metadata of the database\n"+
			"6: Enter a valid SELECT/UPDATE/INSERT/DELETE SQL command.\n"+
					"7: Exit the menu. \n"+
			"8: Exit the program.\n");
			Scanner in=new Scanner(System.in);
			int func=in.nextInt();
			in.close();
			if(func==1) queryStarByID();queryStarByName();//there need to be changed!
			if(func==2) insertStar();
			if(func==3);
			if(func==4);
			if(func==5);
			if(func==6) ;
			if(func==7) enter();
			if(func==8) open=false;
		}
		 connection.close();

		   System.out.println("exited");  
	} 
	public static void enter() {
		System.out.println("Welcome to moviedb database");
		
		System.out.println("Enter username:");
		Scanner input=new Scanner(System.in);
		String user=input.next();
		input.close();
		
		System.out.println("Enter password:");
		Scanner input2=new Scanner(System.in);
		input2.close();
		String password=input2.next();
		
		//驱动程序名//不固定，根据驱动
		  String driver = "com.mysql.jdbc.Driver";
		  // URL指向要访问的数据库名******
		  String url = "jdbc:mysql://localhost/moviedb";
		  // MySQL配置时的用户名
				  
		  try {
		  // 加载驱动程序
		  Class.forName(driver);
		  
		  // 连续数据库
		  connection = DriverManager.getConnection(url, user, password);
		 
		  if(!connection.isClosed())
		   System.out.println("Succeeded connecting to the Database!");		 
		   
		  } catch(ClassNotFoundException e) {  
		   System.out.println("Sorry,can`t find the Driver!");  
		     
		  } catch(SQLException e) {
	           System.out.println("Can not connect! Wrong username or password");  
		     enter();
		  } catch(Exception e){
		   System.out.println("failed");  
		  }
		 
	}
	
	
	/**
     * Print out (to the screen) the movies featuring a given star by star ID.
     * 
     * @throws Exception 
     * 
     * @star_ID
     * @connection
     */
	public static void queryStarByID() throws Exception{
		Statement select = connection.createStatement();
		Statement select_from_stars_in_movies = connection.createStatement();
        System.out.println("please input star_ID");
	
		Scanner input=new Scanner(System.in);
		String star_ID_string=input.next();	
		int star_ID=Integer.parseInt(star_ID_string);
		input.close();
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
	public static void queryStarByName() throws Exception{
		Statement select = connection.createStatement();
		System.out.println("please input first_name");
		Scanner input1=new Scanner(System.in);
		String first_name=input1.next();	
		input1.close();
		
		System.out.println("please input last_name");
		Scanner input2=new Scanner(System.in);
		String last_name=input2.next();	
		input2.close();
		
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
    public static void insertStar() throws Exception{
		
    	System.out.println("please input first_name");
		Scanner input1=new Scanner(System.in);
		String first_name=input1.next();	
		input1.close();
		
		System.out.println("please input first_name");
		Scanner input2=new Scanner(System.in);
		String last_name=input2.next();	
		input2.close();

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
    public static void insertCustomer( )throws Exception{
    	System.out.println("please input first_name");
		Scanner input1=new Scanner(System.in);
		String first_name=input1.next();	
		input1.close();
		
		System.out.println("please input last_name");
		Scanner input2=new Scanner(System.in);
		String last_name=input2.next();	
		input2.close();
		
		System.out.println("please input cc_id");
		Scanner input3=new Scanner(System.in);
		String cc_id=input1.next();	
		input3.close();
		
		System.out.println("please input address");
		Scanner input4=new Scanner(System.in);
		String address=input2.next();	
		input4.close();
		
		System.out.println("please input String email");
		Scanner input5=new Scanner(System.in);
		String email=input2.next();	
		input5.close();
		
		System.out.println("please input password");
		Scanner input6=new Scanner(System.in);
		String password=input2.next();	
		input6.close();
		
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
    public static void deleteCustomer() throws Exception{
	           Statement update = connection.createStatement();
	           System.out.println("please input customer_ID");
	
	   		Scanner input=new Scanner(System.in);
	   		String customer_ID_string=input.next();	
	   		int customer_ID=Integer.parseInt(customer_ID_string);
	   		input.close();
	   		
	   		
	   		
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
}
