package JavaProject;

import java.sql.*;

public class ug_student extends Student
{
	protected String roll_no;
	protected int no_of_cert_courses;
	
	ug_student(String roll_no)
	{
		super(roll_no);
		this.roll_no = roll_no;
		
		try
		{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
		   
		Statement stmt = conn.createStatement();

		ResultSet myRs = stmt.executeQuery("select * from ug_student");
		
		while(myRs.next())
		{
			if(roll_no.equals(myRs.getString(1)))
			{
				no_of_cert_courses = myRs.getInt(2);
			}
		}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void view_student_details()
	{
		super.view_student_details();
		System.out.println("No of certified courses done: "+no_of_cert_courses);
	}
}
