package JavaProject;

import java.util.*;
import java.sql.*;

public class Library 
{
	protected String user_ref_id;
	protected String user_name;
	final static protected float premium_fees = 500;
	protected String[] book_list= {"BID-1002","BID-1007","BID-1256","BID-2004","BID-324","BID-604","BID-682","BID-906","BID-1024","BID-1010"};
	Stack<String> book_stack = new Stack<String>();
	
	Library(String user_ref_id,String user_name)
	{
		this.user_ref_id = user_ref_id;
		this.user_name = user_name;
		
		for(int i=0;i<book_list.length;i++)
		{
			book_stack.add(book_list[i]);
		}
		
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
			   
			Statement stmt = conn.createStatement();

			ResultSet myRs = stmt.executeQuery("select * from library_;");
			
			while(myRs.next())
			{
				if(myRs.getBoolean(5)==false)
				{
					boolean res = book_stack.remove(myRs.getString(3));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void borrowbook()
	{
		try
		{
			int bool=0;
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
			   
			Statement stmt = conn.createStatement();

			ResultSet myRs = stmt.executeQuery("select * from library_;");
			
			while(myRs.next())
			{
				if(user_ref_id.equals(myRs.getString(1)))
				{
					if(myRs.getBoolean(5)==false)
					{
						bool=1;
					}
				}
			}
			
			if(bool==1)
			{
				System.out.print("You have not returned the previous book\nKindly return the previous book to boorow a new one");
				return;
			}
			
			System.out.println("List of books available:");
			
			for(int i=0;i<book_stack.size();i++)
			{
				System.out.println((i+1)+". "+book_stack.elementAt(i));
			}
			
			Scanner s = new Scanner(System.in);
			
			System.out.println("Enter the index next to the book id");
			
			int bookch = s.nextInt();
			
			bookch--;
			
			stmt.executeUpdate("insert into library_ values('"+user_ref_id+"','"+user_name+"','"+book_stack.elementAt(bookch)+"',CURRENT_DATE,false);");
			
			System.out.println("Borrow succesfull!!!");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void returnbook()
	{
		try
		{
			Scanner s = new Scanner(System.in);
			
			int bool=0;
			
			String borrowedbook="";
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/mydb","root","Mudeprab@04");
			   
			Statement stmt = conn.createStatement();

			ResultSet myRs = stmt.executeQuery("select * from library_;");
			
			while(myRs.next())
			{
				if(user_ref_id.equals(myRs.getString(1)))
				{
					if(myRs.getBoolean(5)==false)
					{
						bool=1;
						borrowedbook = myRs.getString(3);
					}
				}
			}
			
			if(bool==0)
			{
				System.out.println("You do not have any borrowed book currently");
				return;
			}
			
			System.out.println("Are you sure You need to return the Book: "+borrowedbook);
			System.out.println("Enter: 1-Yes/2-No");
			int userch = s.nextInt();
			
			if(userch==1)
			{
				System.out.println("Book returned Successfully!!!");
				stmt.executeUpdate("update library_ set returned=true where book_id='"+borrowedbook+"';");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}
