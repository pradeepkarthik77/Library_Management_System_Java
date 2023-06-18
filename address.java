package JavaProject;

import java.util.*;

public class address 
{
	private String street;
	private String city;
	private int pincode;
	
	address()
	{
		street="";
		city="";
		pincode=-1;
	}
	
	public String get_street()
	{
		return street;
	}
	
	public String get_city()
	{
		return city;
	}
	
	public int get_pincode()
	{
		return pincode;
	}
	
	public void set_street(String street)
	{
		this.street = street;
	}
	
	public void set_city(String city)
	{
		this.city = city;
	}
	
	public void set_pincode(int pincode)
	{
		this.pincode = pincode;
	}
	
	public void modify_address()
	{
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter your new Address:");
		
		System.out.println("Enter your street:");
		street = s.next();
		
		System.out.println("Enter your city");
		city = s.next();
		
		System.out.println("Enter your pincode");
		pincode = s.nextInt();
	}
	
}

















