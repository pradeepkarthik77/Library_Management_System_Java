package JavaProject;

import java.sql.*;
import java.util.*;

public class support_staff extends staff
{
	protected String staff_id;
	protected float overtime_pay;;
	
	support_staff(String staff_id)
	{
		super(staff_id);
		this.staff_id = staff_id;
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
			   
			Statement stmt = conn.createStatement();

			ResultSet myRs = stmt.executeQuery("select * from support_staff");
			
			while(myRs.next())
			{
				if(staff_id.equals(myRs.getString(1)))
				{
					overtime_pay = myRs.getFloat(2);
				}
			}	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
	  }
	
	 public void view_staff_details()
	 {
		 super.view_staff_details();
		 System.out.println("Overtime Pay: "+overtime_pay);
	 }
}
