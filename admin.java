package JavaProject;

import java.sql.*;
import java.util.*;

public class admin 
{
	protected String admin_id;
	protected String admin_name;
	protected Boy_Hosteller boy;
	protected Girl_Hosteller girl;
	
	admin(String admin_id)
	{
		this.admin_id = admin_id;
		
		try
		{
    		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
  		   
 		    Statement mystmt = myConn.createStatement();

 		    ResultSet myRs = mystmt.executeQuery("select * from admin_;");
 		    
 		    while(myRs.next())
 		    {
 		    	if(admin_id.equals(myRs.getString(1)))
 		    	{
 		    		admin_name = myRs.getString(2);
 		    	}
 		    }
 		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void view_all_details()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter:\n1. for Student Detail\n2. for Staff details\n3. for Hosteller Details");
		int admch= sc.nextInt();
		
		String ref_id;
		
		int contich=0;
		
		if(admch==1)
		{
			do
			{
			System.out.println("Enter the roll_no of the student");
			ref_id = sc.next();
			
			if("STDUG".equals(ref_id.substring(0,5)))
			{
				ug_student ug = new ug_student(ref_id);
				
				ug.view_student_details();
				
			}
			
			else if("STDPG".equals(ref_id.substring(0,5)))
			{
				pg_student pg = new pg_student(ref_id);
				
				pg.view_student_details();
			}
			
			System.out.println("Do you want to see the details of any other student?(1-Yes/2-No)");
			contich = sc.nextInt();
			
			}while(contich==1);
		}
		
		else if(admch==2)
		{
			do
			{
			System.out.println("Enter the staff id of the staff");
			ref_id = sc.next();
			
			if("STFT".equals(ref_id.substring(0,4)))
			{
				teaching_staff ts = new teaching_staff(ref_id);
				
				ts.view_staff_details();
				
			}
			
			else if("STFS".equals(ref_id.substring(0,4)))
			{
				support_staff ss = new support_staff(ref_id);
				
				ss.view_staff_details();
			}
			
			System.out.println("Do you want to see the details of any other staff?(1-Yes/2-No)");
			contich = sc.nextInt();
			
			}while(contich==1);
		}
		
		else if(admch==3)
		{
			
			try
			{
    		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
    		   
 		    Statement mystmt = myConn.createStatement();

 		    ResultSet myRs = mystmt.executeQuery("select * from Hosteller;");
 		    
 		    String gen="";
 		    
			do
			{
			System.out.println("Enter the roll_no of the Hosteller");
			ref_id = sc.next();
			
			while(myRs.next())
			{
				if(ref_id.equals(myRs.getString(1)))
				{
					gen = myRs.getString(5);
				}
			}
			
			if("M".equals(gen))
			{
				Boy_Hosteller bo = new Boy_Hosteller(ref_id);
				bo.view_details();
			}
			
			else if("F".equals(gen))
			{
				Girl_Hosteller gl = new Girl_Hosteller(ref_id);
				gl.view_details();
			}
			
			System.out.println("Do you want to see the details of any other Hosteller?(1-Yes/2-No)");
			contich = sc.nextInt();
			
			}while(contich==1);
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void access_hosteller()
	{
		Scanner s = new Scanner(System.in);
		
		System.out.println("1. for Check_out\n2. for Check_in\n3. for Viewing whether the Hosteller is In/Out");
		int hostch = s.nextInt();
		
		String roll_no="";
		
		int cho=0;
		
		try
		{
    		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
 		   
 		    Statement mystmt = myConn.createStatement();
			
		if(hostch==1)
		{
		
			do
			{
				System.out.println("Enter the roll_no of the Hosteller for check out:");
				roll_no = s.next();
				
				mystmt.executeUpdate("insert into check_in_out values('"+roll_no+"',CURRENT_TIMESTAMP,null)");
				
				System.out.println("Check out successfull!!!");
				
				System.out.println("Do you want to enter check in details for any other Hosteller?(1-yes/2-no)");
				cho = s.nextInt();
				
			}while(cho==1);
			
		}
		
		else if(hostch==2)
		{
			int bool;
			
			do
			{
			bool=0;
			System.out.println("Enter the roll_no of the Hosteller for check in:");
			roll_no = s.next();
			
			ResultSet myRs = mystmt.executeQuery("select * from check_in_out;");
			
			while(myRs.next())
				if(roll_no.equals(myRs.getString(1)))
					if(myRs.getTime(3)==null)
						bool=1;
			
			if(bool==0)
			{
				System.out.println("The Hosteller is currently checked in already!!!");
				break;
			}
			
			System.out.println("Do you want to check roll no: "+roll_no+" in? (1-yes/2-no)");
			cho = s.nextInt();
			
			if(cho==1)
			{
				mystmt.executeUpdate("update check_in_out set check_out_time=CURRENT_TIMESTAMP");
				System.out.println("Update Succesfull!!!");
			}
			
			System.out.println("Do you want to check in any other Hostellers?(1-Yes/2-No)");
			cho = s.nextInt();
			
			}while(cho==1);
		}
		
		else if(hostch==3)
		{
			int bool;
			
			do
			{
			bool=0;
			System.out.println("Enter the roll_no of the Hosteller for checking whether the Hosteller is In/Out");
			roll_no = s.next();
			
			ResultSet myRs = mystmt.executeQuery("select * from check_in_out;");
			
			while(myRs.next())
				if(roll_no.equals(myRs.getString(1)))
					if(myRs.getTime(3)==null)
						bool=1;
			
			if(bool==1)
			{
				System.out.println("The Hosteller is currently checked out ");
			}
			
			else if(bool==0)
			{
				System.out.println("The Hosteller is currently checked In");
			}
			
			System.out.println("Do you want to Continue?(1-yes/2-no)");
			cho = s.nextInt();
			
			}while(cho==1);
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}













