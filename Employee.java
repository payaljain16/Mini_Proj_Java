package assignment;
import java.util.*;
import java.io.*;
import java.sql.*;

public abstract class Employee
{
	int eid;
	String name;
	int age;
	int salary;
	String designation;
	public static int counter=0;

	public Employee(int salary,String desig)
	{
		counter++;
		System.out.println("The no of employees are:"+counter);
		Scanner sc=new Scanner(System.in);
		System.out.println("\nEnter details for desig:" + desig+":-");
		System.out.println("Enter eid:");
		eid=sc.nextInt();
		System.out.println("\nEnter name:");
		name=sc.next();
		
		try
		{
		System.out.println("Enter age:");
		age=sc.nextInt();
		if(age<21 || age>60)
			throw new InvalidAgeException();
		}
		
		catch(InputMismatchException|InvalidAgeException e)
		{
			age=new InvalidAgeException().readEmpAge();
		}

		this.salary=salary;
		this.designation=desig;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password-1");
			PreparedStatement pstmt=con.prepareStatement("insert into EMP values(?,?,?,?,?)");

			
			pstmt.setInt(1,eid);
			pstmt.setString(2,name);
			pstmt.setInt(3,age);
			pstmt.setInt(4,salary);
			pstmt.setString(5,designation);

			pstmt.execute();

			pstmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	public String toString()
	{
		System.out.println("The employee id is:"+ eid);
		System.out.println("The name is:"+ name);
		System.out.println("The age is:"+ age);
		System.out.println("The salary is:"+ salary);
		System.out.println("The designation is:"+ designation);
		return "";
	}

	public static void raiseSalary()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password-1");
			Statement stmt=con.createStatement();

			stmt.executeUpdate("update EMP set SALARY=SALARY+2000 where designation='CLERK'");
			stmt.executeUpdate("update EMP set SALARY=SALARY+5000 where designation='PROGRAMMER'");
			stmt.executeUpdate("update EMP set SALARY=SALARY+10000 where designation='MANAGER'");


			stmt.close();
			con.close();

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static void display()
	{
		try
		{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password-1");
			Statement stmt=con.createStatement();

			ResultSet rs=stmt.executeQuery("select * from EMP");
			ResultSetMetaData rsmd=rs.getMetaData();
			int cols=rsmd.getColumnCount();
			while(rs.next())
			{
				for(int i=1;i<=cols;i++)
				{
					System.out.println(rsmd.getColumnName(i)+":"+rs.getString(i));
				}
			    System.out.println();
			}
			rs.close();
			stmt.close();
			con.close();
		}

		catch(Exception e)
		{
			System.out.println(e);
		}
	
		
	}

	public static void delete()
	{
		try
		{

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password-1");
			Statement stmt=con.createStatement();

			
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter EID to be deleted:");
			int empid=Integer.parseInt(br.readLine());

			ResultSet rs=stmt.executeQuery("select * from EMP where eid="+empid);
			
			if(rs.next())
			{
				
					System.out.println("ID:"+rs.getInt(1));
					System.out.println("Name:"+rs.getString(2));
					System.out.println("Age:"+rs.getInt(3));
					System.out.println("Salary:"+rs.getInt(4));
					System.out.println("Desig:"+rs.getString(5));

				System.out.println("Are you sure you want to delete record:(yes/no)");
				String yn=br.readLine();
				if(yn.equalsIgnoreCase("yes"))
				{
						stmt.executeUpdate("delete from EMP where eid="+empid);
						System.out.println("Successfully record deleted.");
				}
			}
			else
			{
				System.out.println("No record found.");
			}


			

			rs.close();
			stmt.close();
			con.close();
		}

		catch(Exception e)
		{
			System.out.println(e);
		}
	
		
	}


}

