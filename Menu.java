package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
	static Connection connection;
	public static void menu() throws SQLException{
		enter();
		
		int back=1;
		while(back==1){
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
			if(func==1);
			if(func==2);
			if(func==3);
			if(func==4);
			if(func==5);
			if(func==6);
			if(func==7) enter();
			if(func==8) back=2;
		}
		 connection.close();

		   System.out.println("exited");  
	} 
	public static void enter() {
		System.out.println("Welcome to moviedb database");
		
		System.out.println("Enter username:");
		Scanner input=new Scanner(System.in);
		String user=input.next();
		
		System.out.println("Enter password:");
		Scanner input2=new Scanner(System.in);
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
}
