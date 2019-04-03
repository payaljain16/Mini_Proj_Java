import java.sql.*;
import java.io.*;

public class JdbcDemo3
{
	public static void main(String args[])
	{
		try
		{

			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");

			/*CallableStatement cstmt=con.prepareCall("CALL ?");
		
			System.out.println("Enter procedure name:");
			String name=br.readLine();
	
			cstmt.setString(1,name);

			cstmt.execute();

			cstmt.close();
			*/

			Statement stmt=con.createStatement();
			stmt.addBatch("insert into EMP values("ravi",22)");
			stmt.addBatch("insert into EMP values("ravi5",24)");
			stmt.addBatch("insert into EMP values("ravi3",28)");
			stmt.addBatch("insert into EMP values("ravi11",32)");

			System.out.println("Wait for 30 sec..");
			Thread.sleep(30000);
			stmt.executeBatch();
			System.out.println("Now check the table.........");
			stmt.close();
			con.close();

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}