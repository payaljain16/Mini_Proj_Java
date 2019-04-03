import assignment.Manager;
import assignment.Programmer;
import assignment.Clerk;
import assignment.Employee;
import assignment.InvalidChoiceException;
import assignment.NameSorter;
import assignment.AgeSorter;
import assignment.SalarySorter;
import assignment.DesignationSorter;
import assignment.*;
import java.util.*;


public class EmpAssignment
{
	public static void main(String args[])
	{
		
		int ch1=0,ch2=0,ch3=0;
		do
		{
			System.out.println("-----------------");
			System.out.println("1. CREATE ");
			System.out.println("2. DISPLAY ");
			System.out.println("3. RAISE SALARY ");
			System.out.println("4. DELETE ");
			System.out.println("5. EXIT ");

			try
			{
			System.out.println("Enter choice:-");
			ch1=new Scanner(System.in).nextInt();

			if(ch1<1 || ch1>4)
				throw new InvalidChoiceException();
			
			}
		
			catch(InputMismatchException|InvalidChoiceException ex)
			{
				ch1=new InvalidChoiceException().readEmpChoice();
			}
			if(ch1==1)
			{
				do
				{
		
					System.out.println("-----------------------------");
					System.out.println("1. Clerk ");
					System.out.println("2. Manager ");
					System.out.println("3. Programmer ");
					System.out.println("4. Exit ");
					System.out.println("Enter sub choice:-");
					ch2=new Scanner(System.in).nextInt();
					switch(ch2)
					{
						case 1:new Clerk();
							break;
						case 2:new Programmer();
							break;
						case 3:new Manager();
							break;
					}
				}while(ch2!=4);
			}
			if(ch1==2)
			{
			
				Employee.display();


			}
			if(ch1==3)
			{
				//for(int i=0;i<e.size();i++)
				//e.get(i).raiseSalary();
				Employee.raiseSalary();				
				
			}

			if(ch1==4)
			{
				Employee.delete();

			}			

		}while(ch1!=5);


	}
}

