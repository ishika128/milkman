package routineentry;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection
{
	public static Connection doConnect()
	{Connection con = null ;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
	con=		DriverManager.getConnection("jdbc:mysql://localhost/milkmandb","root","");
	System.out.println("connected****" );
			
			
			
		} 
		
		catch (Exception e)
		{
			
			e.printStackTrace();
		}
		return con ;
		
		
	}
	
	
	
	

	public static void main(String[] args)
	{
		 doConnect();
	}

}
