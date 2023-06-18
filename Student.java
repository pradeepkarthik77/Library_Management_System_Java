package JavaProject;

import java.sql.*;
import java.util.*;

public abstract class Student 
{
	protected String roll_no;
	protected String name;
	protected String std_section;
	protected static String std_campus="Coimbatore";
	course_registration course;
	public sports_house sh;
	public Library lib;
	
	Student(String roll_no)
	{
		this.roll_no = roll_no;
		
		course = new course_registration(roll_no);
		
		sh = new sports_house();
		
		try
		{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
		   
		Statement stmt = conn.createStatement();

		ResultSet myRs = stmt.executeQuery("select * from student");
		
		while(myRs.next())
		{
			if(roll_no.equals(myRs.getString(2)))
			{
				name = myRs.getString(1);
				std_section = myRs.getString(3);
			}
		}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		lib = new Library(roll_no,name);
	}
	
	public void view_student_details()
	{
		System.out.println("Roll no: "+roll_no);
	    System.out.println("Name: "+name);
	    System.out.println("Section: "+std_section);
	    System.out.println("Campus: "+std_campus);
	}
	
	public void payfees()
	{
		
		Scanner s = new Scanner(System.in);
		
		int bool=0;
		
		try
		{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
		   
		Statement stmt = conn.createStatement();

		ResultSet myRs = stmt.executeQuery("select * from student");
		
		Statement st = conn.createStatement();
		
		ResultSet rest = st.executeQuery("select * from login");
		
		boolean boo = false;
		
		while(myRs.next())
		{
			if(roll_no.equals(myRs.getString(2)))
			{
				boo = myRs.getBoolean(5);
			}
		}
		
		if(boo)
		{
			System.out.println("You have already paid the fees!!!");
			return;
		}
		
		String passw="";
		
		while(rest.next())
		{
			if(roll_no.equals(rest.getString(3)))
			{
				passw = rest.getString(2);
			}
		}
		
		System.out.println("Before Fees payment, Enter your Password:");
		
		do
		{
		String password = s.next();
		if(password.equals(passw))
			bool=1;
		else
			System.out.println("Enter the correct password");
		}while(bool==0);
		
		System.out.println("Your tuition fees is: 2,00,000");
		
		System.out.println("Enter your Card Number:");
		long card_no = s.nextLong();

		
		System.out.println("Enter your cardholder name");
		String card_holder = s.next();
		
		System.out.println("Enter your Pin number:");
		int pin = s.nextInt();
		
		System.out.println("Do you want to proceed to payment?(yes/no)");
		String ch = s.next();
		
		
		if(ch.equals("yes"))
		{
			
			System.out.print("Payment Successfull\nTransaction details:\nCard Number:"+card_no+"\nCard Holder:"+card_holder+"\nFees paid: 2,00,000");
			
			stmt.executeUpdate("update student set fees_paid=true where roll_no='"+roll_no+"';");
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void access_course_reg()
	{
		Scanner s = new Scanner(System.in);
		
		System.out.print("Enter:\n1. for Removing previously registered courses\n2. for Registering new courses\n3. for viewing already registered courses\n");
		int coursech = s.nextInt();
		
		if(coursech==1)
		{
			course.delete_prev_course();
		}
		else if(coursech==2)
		{
			course.add_new_course();
		}
		
		else if(coursech==3)
		{
			course.view_courses();
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
				if(roll_no.equals(myRs.getString(1)))
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
			stmt.executeUpdate("Update address set street='"+a.get_street()+"',city='"+a.get_city()+"',pincode="+a.get_pincode()+" where reference_id='"+roll_no+"';");
			System.out.println("Update Succesfull!!!");
		}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void allocate_house()
	{
		Random rand = new Random();
		
		int rand_house = rand.nextInt(4);
		
		String house_id=null;
		
		switch(rand_house)
		{
		case 0: house_id = "HD01";
		case 1: house_id = "HD02";
		case 2: house_id = "HD03";
		case 3: house_id = "HD04";
		}
		
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
			   
			Statement stmt = conn.createStatement();
			
			ResultSet myRs = stmt.executeQuery("select * from alloted_house;");
			
			while(myRs.next())
			{
				if(roll_no.equals(myRs.getString(1)))
				{
					if(myRs.getString(2)!=null)
					{
						System.out.println("You are already allocated to a house");
						return;
					}
				}
			}
			
			stmt.executeUpdate("update alloted_house set house_id='"+house_id+"' where roll_no='"+roll_no+"';");
			
			System.out.print("Allocation Successfull!!!\nYour allocated house is: "+house_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void access_library()
	{
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter\n1. If you want to Borrow Book\n2. If you want to Return book");
		int libch = s.nextInt();
		
		if(libch==1)
		{
			lib.borrowbook();
		}
		
		else if(libch==2)
		{
			lib.returnbook();
		}
	}
	
	public void view_marks()
	{
		Stack<String> stack = new Stack<String>();
		
		float[] marks = new float[5];
		
		try
		{
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
				System.out.println("You have not enrolled in any of the courses");
				return;
			}
			
			ResultSet Rs = stmt.executeQuery("select * from marks;");
			
			while(Rs.next())
			{
				if(roll_no.equals(Rs.getString(1)))
				{
					for(int i=0;i<5;i++)
						marks[i] = Rs.getFloat(i+2);
				}
			}
			
			for(int i=0;i<5;i++)
			{
				System.out.println("Your Mark in the Subject: "+stack.elementAt(i)+" is: "+marks[i]);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


















