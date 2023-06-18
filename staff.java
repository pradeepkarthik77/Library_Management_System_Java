package JavaProject;

import java.sql.*;
import java.util.*;

public abstract class staff 
{
	protected String staff_id;
	protected String name;
	protected String staff_dept;
	protected float staff_pay;
	static protected float staff_incentive=5000.0f;
	sports_house sh;
	Library libr;
	
	staff(String staff_id)
	{
		this.staff_id = staff_id;
		
		sh = new sports_house();
		
		try
		{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
		   
		Statement stmt = conn.createStatement();

		ResultSet myRs = stmt.executeQuery("select * from staff");
		
		while(myRs.next())
		{
			if(staff_id.equals(myRs.getString(1)))
			{
				name = myRs.getString(2);
				staff_dept = myRs.getString(3);
				staff_pay = myRs.getFloat(4);
			}
		}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		libr = new Library(staff_id,name);
	}
	
	public void view_staff_details()
	{
		System.out.println("Staff id: "+staff_id);
	    System.out.println("Name: "+name);
	    System.out.println("Department: "+staff_dept);
	    System.out.println("staff Pay: "+staff_pay);
	    System.out.println("staff Incentive: "+staff_incentive);
	}
	
	public void access_library()
	{
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter\n1. If you want to Borrow Book\n2. If you want to Return book");
		int libch = s.nextInt();
		
		if(libch==1)
		{
			libr.borrowbook();
		}
		
		else if(libch==2)
		{
			libr.returnbook();
		}
	}
	
	public void access_leaderboard()
	{
		Scanner s = new Scanner(System.in);
		
		sh.view_leader_board();
		
		System.out.println("Do you want to edit leader board?(1-Yes/2-No)");
		int editch = s.nextInt();
		
		if(editch==1)
		{
			sh.modify_leader_board();
			System.out.println("Updated Leaderboard:");
			sh.view_leader_board();
		}
	}
	
	public void load_address(address a,int choice)
	{
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
			   
			Statement stmt = conn.createStatement();

			ResultSet myRs = stmt.executeQuery("select * from address;");
			
			while(myRs.next())
			{
				if(staff_id.equals(myRs.getString(1)))
				{
					a.set_city(myRs.getString(3));
					a.set_pincode(myRs.getInt(4));
					a.set_street(myRs.getString(2));
				}
			}
		 
		if(choice==1)
		{
			System.out.println("Address: "+a.get_street()+" "+a.get_city()+" "+a.get_pincode());
		}
		
		else if(choice==2)
		{
			a.modify_address();
			stmt.executeUpdate("Update address set street='"+a.get_street()+"',city='"+a.get_city()+"',pincode="+a.get_pincode()+" where reference_id='"+staff_id+"';");
			System.out.println("Update Succesfull!!!");
		}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void allocate_marks()
	{
		try
		{
			int ch=0;
			
		   do
		   {
			   Stack<String> stack = new Stack<String>();
			   
			   float[] marks = new float[5];
			   
			   
			   Scanner sc = new Scanner(System.in);
			   
			   System.out.println("Enter the roll no of the student whose marks you want to enter:");
			   String roll_no = sc.next();
			   
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
				   
				Statement stmt = conn.createStatement();
				
				ResultSet myRs = stmt.executeQuery("select * from course_registration;");
				
				while(myRs.next())
				{
					if(roll_no.equals(myRs.getString(1)))
					{
						boolean aa = stack.add(myRs.getString(2));
					}
				}
				
				if(stack.isEmpty())
				{
					System.out.println("Student has not enrolled in any courses");
				}
				
				else
				{
							for(int i=0;i<5;i++)
							{
								System.out.println("Enter the mark for the subject: "+stack.elementAt(i));
								marks[i] = sc.nextFloat();
							}
					
					stmt.executeUpdate("Update marks set mark1="+marks[0]+" where roll_no='"+roll_no+"';");
					stmt.executeUpdate("Update marks set mark2="+marks[1]+" where roll_no='"+roll_no+"';");
					stmt.executeUpdate("Update marks set mark3="+marks[2]+" where roll_no='"+roll_no+"';");
					stmt.executeUpdate("Update marks set mark4="+marks[3]+" where roll_no='"+roll_no+"';");
					stmt.executeUpdate("Update marks set mark5="+marks[4]+" where roll_no='"+roll_no+"';");
					
					System.out.println("Updated values:");
					
					for(int i=0;i<5;i++)
					{
						System.out.println(roll_no+"'s Mark in the Subject: "+stack.elementAt(i)+" is: "+marks[i]);
					}
				}
				
				System.out.println("Do you want to enter marks for any other student?(1-Yes/2-No)");
				ch = sc.nextInt();
				
		   }while(ch==1);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}



















