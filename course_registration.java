package JavaProject;

import java.sql.*;
import java.util.*;

public class course_registration 
{
	String roll_no;
	static String[] course_id_list = {"CSE-203","CSE-205","CSE-201","ECE-204","CSE-204","MEE-181","CSE-202","EEE-301","PHY-401","CUL-101"};
	protected HashMap<String, String> course_map = new HashMap<>();
	
	course_registration(String roll_no)
	{
		this.roll_no = roll_no;
		
		course_map.put(course_id_list[0],"CS Essentials");
		course_map.put(course_id_list[1],"UI design");
		course_map.put(course_id_list[2],"Advanced Programming");
		course_map.put(course_id_list[3],"Digital Electronics");
		course_map.put(course_id_list[4],"Object Oriented Programming");
		course_map.put(course_id_list[5],"Manufacturing Practices");
		course_map.put(course_id_list[6],"DBMS");
		course_map.put(course_id_list[7],"EEE");
		course_map.put(course_id_list[8],"Engineering Physics");
		course_map.put(course_id_list[9],"Cultural Education");
		
	}
	
	public void delete_prev_course()
	{
		Scanner s = new Scanner(System.in);
		
		try
		{
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
		   
		Statement mystmt = myConn.createStatement();
		
		System.out.println("Do you want to un-enroll from the previous courses?(1 for Yes/2 for No)");
		int ch= s.nextInt();
		
		if(ch==1)
		{
			mystmt.executeUpdate("DELETE FROM COURSE_REGISTRATION WHERE ROLL_NO='"+roll_no+"';");
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void add_new_course()
	{
		Scanner s= new Scanner(System.in);
		
		
		try
		{
		
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
			   
		Statement mystmt = myConn.createStatement();
		
		ResultSet Rs = mystmt.executeQuery("select * from course_registration");
		
		while(Rs.next())
		{
			if(roll_no.equals(Rs.getString(1)))
			{
				System.out.println("Un-enroll from the previous courses");
				return;
			}
		}
		
		System.out.println("List of courses available:");
		for(int i=0;i<course_id_list.length;i++)
		{
			System.out.println((i+1)+". for "+course_map.get(course_id_list[i]));
		}
		
		System.out.println("Enter the number associated with the course name to enroll in that course(Enter 5 valid numbers)");
		
		int coursech=0;
		
		for(int i=0;i<5;i++)
		{
			System.out.println("Enter the value for Course: "+(i+1));
			coursech=s.nextInt();
			coursech=coursech-1;
			mystmt.executeUpdate("Insert into course_registration values('"+roll_no+"','"+course_id_list[coursech]+"');");
		}
		
		System.out.println("Course registration successfull!!!");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void view_courses()
	{
		try
		{
		
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
			   
		Statement mystmt = myConn.createStatement();
		
		ResultSet myRs = mystmt.executeQuery("Select * from course_registration where roll_no='"+roll_no+"';");
		
		while(myRs.next())
		{
			if(roll_no.equals(myRs.getString(1)))
			{
				System.out.println(myRs.getString(2)+" "+course_map.get(myRs.getString(2)));
			}
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}


















