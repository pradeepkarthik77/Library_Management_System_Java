package JavaProject;

import java.sql.*;

public class Girl_Hosteller implements Hosteller
{
	protected String roll_no;
	protected String block_no;
	protected String room_no;
	
	Girl_Hosteller(String roll_no)
	{
		this.roll_no = roll_no;
		
		try
		{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
 		   
 		    Statement mystmt = myConn.createStatement();

 		    ResultSet myRs = mystmt.executeQuery("select * from Hosteller;");
 		    
 		    while(myRs.next())
 		    {
 		    	if(roll_no.equals(myRs.getString(1)))
 		    	{
 		    		block_no = myRs.getString(2);
 		    		room_no = myRs.getString(3);
 		    	}
 		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void view_details()
	{
		System.out.println("Roll No: "+roll_no);
		System.out.println("Block No: "+block_no);
		System.out.println("Room No: "+room_no);
		System.out.println("Rules:"+girls_hostel_rules);
	}

}











