package JavaProject;

import java.sql.*;
import java.util.*;

class Login extends Exception
{
    private String Username;
    private String Password;
    private String Type_of_user;
    
    Login(String Username,String Password,String Type_of_user)
    {
    	this.Username = Username;
    	this.Password = Password;
    	this.Type_of_user = Type_of_user;
    }
    
    Login(String s){super(s);}
    
    public String signin()
    {
    	String ret="";
    	
    	try
    	{
    		
    		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
 		   
 		    Statement mystmt = myConn.createStatement();

 		    ResultSet myRs = mystmt.executeQuery("select * from Login");
 		    
 		    while(myRs.next())
 		    {
 		    	if((Username.equals(myRs.getString(1)))&&(Password.equals(myRs.getString(2)))&&(Type_of_user.equals(myRs.getString(4))))
 		    	{
 		    		ret = myRs.getString(3);
 		    		return ret;
 		        }
 		    }
 		    
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return ret;

    }
    
    public void signup()
    {
    	Scanner s = new Scanner(System.in);
    	
    	Login lo;
    	
    	String ref_id;
    	
    	try
    	{
    		
    	System.out.println("Confirm Password");
    	String cpassword = s.next();
    	
    	if(!Password.equals(cpassword))
    	{
    		lo = new Login("Enter the same password");
    		throw lo;
    	}
    	
    	System.out.println("Enter your reference_id(Student roll_no/ Staff_id/ Admin_id)");
    	ref_id = s.next();
    	
    	try
    	{
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
		   
		Statement mystmt = myConn.createStatement();

		ResultSet myRs = mystmt.executeQuery("select * from Login");
		
		while(myRs.next())
		{
			if(ref_id.equals(myRs.getString(3)))
			{
				lo = new Login("Account already exists");
				throw lo;
			}
		}
		
		mystmt.executeUpdate("insert into login values('"+Username+"','"+Password+"','"+ref_id+"','"+Type_of_user+"');");
		
		if(Type_of_user=="Student")
		{
			final String campus="Coimbatore";
			
			System.out.println("Enter your Name");
			String name = s.next();
			
			System.out.println("Enter your Section Ex: (ECE-A)");
			String section = s.next();
			
			mystmt.executeUpdate("insert into Student values ('"+name+"','"+ref_id+"','"+section+"','"+campus+"',false)");
			
			System.out.println("Enter\n1. for UG student\n2. for PG student");
			int ugch = s.nextInt();
			
			if(ugch!=1 && ugch!=2)
			{
				lo = new Login("Enter a valid option for UG/PG choice");
				throw lo;
			}
			
			if(ugch==1)
			{
				System.out.println("Enter the number of certified courses you have done");
				int cert = s.nextInt();
				mystmt.executeUpdate("insert into UG_Student values ('"+ref_id+"',"+cert+")");
			}
			
			else if(ugch==2)
			{
				System.out.println("Enter the number of research papers you have published");
				int pub = s.nextInt();
				mystmt.executeUpdate("insert into PG_Student values ('"+ref_id+"',"+pub+")");
			}
			
			System.out.print("Sign in Succesfull");
		}
		
		else if(Type_of_user=="Faculty")
		{
			final float incent=5000;
			
			System.out.println("Enter your Name");
			String name = s.next();
			
			System.out.println("Enter your Department Ex: (ECE,CSE)");
			String dept = s.next();
			
			System.out.println("Enter your Salary");
			float staff_pay = s.nextFloat();
			
			mystmt.executeUpdate("insert into Staff values ('"+ref_id+"','"+name+"','"+dept+"',"+staff_pay+","+incent+")");
			
			System.out.println("Enter\n1. for Teaching Staff\n2. for Non-Teaching Staff");
			int staffch = s.nextInt();
			
			if(staffch!=1 && staffch!=2)
			{
				lo = new Login("Enter a valid input for staff choice");
				throw lo;
			}
			
			else if(staffch==1)
			{
				System.out.println("Enter your Subscrption allotement");
				Float subs = s.nextFloat();
				mystmt.executeUpdate("insert into teaching_staff values ('"+ref_id+"',"+subs+")");
			}
			
			System.out.print("Sign in Succesfull");
		}
		
		else if(Type_of_user=="Admin")
		{
			System.out.println("Enter your name");
			String name = s.next();
			
			mystmt.executeUpdate("insert into Admin_ values ('"+ref_id+"','"+name+"')");
		}
		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		
    	}
    	catch(Login e)
    	{
    		e.printStackTrace();
    	}
    	
    }
}