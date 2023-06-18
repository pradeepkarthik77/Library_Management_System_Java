package JavaProject;

import java.sql.*;
import java.util.*;

public class sports_house 
{
	protected String[] house_id_list= {"HD01","HD02","HD03","HD04"};
	
	protected HashMap<String, String> house_map = new HashMap<>();
	
	final static float max_points = 100f;
	
	sports_house()
	{
		house_map.put(house_id_list[0],"Amritamayi");
		house_map.put(house_id_list[1],"Anandamayi");
		house_map.put(house_id_list[2],"Chinmayi");
		house_map.put(house_id_list[3],"Jyothimayi");
	}
	
	public void view_leader_board()
	{
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
			   
			Statement stmt = conn.createStatement();
			
			ResultSet myRs = stmt.executeQuery("select * from sports_house order by house_points desc");
			
			int i=0;
			
			while(myRs.next())
			{
				i++;
				System.out.println(i+".   "+myRs.getString(1)+" "+house_map.get(myRs.getString(1))+" "+myRs.getFloat(3));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void modify_leader_board()
	{
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
			   
			Statement stmt = conn.createStatement();
			
			Scanner s = new Scanner(System.in);
			
			int userch=0;
			
			float tempval=0f;
			
			for(int i=0;i<4;i++)
			{
				System.out.println("Do you want to change the points for: "+house_map.get(house_id_list[i])+" 1-Yes/2-No");
				userch = s.nextInt();
				if(userch==1)
				{
					System.out.println("Enter the modified points:");
					tempval = s.nextFloat();
					
					stmt.executeUpdate("update sports_house set house_points="+tempval+" where house_id='"+house_id_list[i]+"';");
				}
			}
			
			System.out.println("Updated Succesfully!!!");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
