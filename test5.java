package DBConnect;
import java.sql.*;

public class test5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
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
	}
		/*	Statement select = connection.createStatement();
	StringBuffer selectcommand=new StringBuffer();
	selectcommand=selectcommand.append("show tables");
	System.out.println(selectcommand.toString());
	ResultSet result = select.executeQuery(selectcommand.toString());	
    while (result.next())
 {
                  System.out.println(result);


               }*/
    System.out.println("complete");
}
}
