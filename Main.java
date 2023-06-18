package JavaProject;

import java.sql.*;
import java.util.*;

public class Main extends Exception
{
	Main() {}
	
	Main(String str) {super(str);}
	
   public static void main(String[] args)
   {
	   Main loge;
	   
	   System.out.println("\t\t\t\t\t\tWelcome To Our College Management System");
	   
	   Scanner s = new Scanner(System.in);
	   
	   String user,passw,user_type= "";
	   
	   String inresult="";
	   
	   int logch,loginbool=0,userch=0;
	   
	   try
	   {
	   System.out.println("Enter\n1. For Sign In\n2. for Sign up");
	   
	   logch = s.nextInt();
	   
	   if(logch!=1 && logch!=2)
	   {
		   loge = new Main("Enter a valid input for Sign in/up");
		   throw loge;
	   }
	   
	   do {
	   System.out.println("Enter your Username: ");
	   user = s.next();

	   System.out.println("Enter your Password: ");
	   passw = s.next();
	   
	   System.out.println("Choose your User type:\n1. Student\n2. Staff\n3. Admin");
	   userch = s.nextInt();
	   
	   int bool=0;
	   
	   switch(userch)
	   {
	   		case 1: user_type = "Student";break;
	   		case 2: user_type = "Faculty";break;
	   		case 3: user_type = "Admin";break;
	   		default: bool=1;
	   }
	   
	   if(bool==1)
	   {
		   loge = new Main("Enter a valid input for User type");
		   throw loge;
	   }
	   
	   Login log = new Login(user,passw,user_type);
	   
	   if(logch==1)
	   {
		   inresult = log.signin();
		   
		   if(inresult!="")
		   {
			   System.out.println("Login Successfull!!!");
			   loginbool=1;
			   break;
		   }
		   
		   else
		   {
			   System.out.println("Enter a valid Username and Password");
		   } 
		   
	   }
	   
	   if(logch==2)
	   {
		   log.signup();
		   break;
	   }
	   
	   }while(true);
	   
	   
	   if(loginbool==1)
	   {
		   Student std;
		   
		   int studentch=0;
		   
		   if("STD".equals(inresult.substring(0,3)))
		   {
			   
			   if("STDUG".equals(inresult.substring(0,5)))
				   std = new ug_student(inresult);
		   
			   else
				   std = new pg_student(inresult);
			   
			   do
			   {
			   System.out.print("Enter:\n1. for Paying your fees\n2. for Viewing Marks\n3. for Viewing your personal details\n4. for Accessing the Library\n5. for Viewing your house LeaderBoard\n6. for Allocating a new house(Works only for newcomers)\n7. for Modifying your address\n8. for accessing course registration\n");
			   int stdch = s.nextInt();
			   
			   address a=new address();
			   
			   switch(stdch)
			   {
			   case 1: std.payfees();break;
			   case 2: std.view_marks();break;
			   case 3: std.view_student_details();std.load_address(a,1);break;
			   case 4: std.access_library();break;
			   case 5: std.sh.view_leader_board();break;
			   case 6: std.allocate_house();break;
			   case 7: std.load_address(a,2);break;
			   case 8: std.access_course_reg();break;
			   default: System.out.println("Invalid Input");
			   }
			   
			   System.out.println("\n\nDo you want to perform any other functions?(1-yes,2-no)");
			   studentch = s.nextInt();
			   
			   }while(studentch==1);
		   }

			   staff stf;
			   
			   int staffch=0;
			   
			   if("STF".equals(inresult.substring(0,3)))
			   {
				   
				   if("STFT".equals(inresult.substring(0,4)))
					   stf = new teaching_staff(inresult);
			   
				   else
					   stf = new support_staff(inresult);
				   
				   do
				   {
				   System.out.print("Enter:\n1. for Allocating Marks\n2. for Accessing Leaderboard\n3. for Viewing your personal details\n4. for accessing library\n5. for modifying your address\n");
				   int stfch = s.nextInt();
				   
				   address a=new address();
				   
				   switch(stfch)
				   {
				   case 1: stf.allocate_marks();break;
				   case 2: stf.access_leaderboard();break;
				   case 3: stf.view_staff_details();stf.load_address(a,1);break;
				   case 4: stf.access_library();break;
				   case 5: stf.load_address(a,2);break;
				   default: System.out.println("Invalid Input");
				   }
				   
				   System.out.println("\n\nDo you want to perform any other functions?(1-yes,2-no)");
				   staffch = s.nextInt();
				   
				   }while(staffch==1);
			   }
			   
			   if("ADM".equals(inresult.substring(0,3)))
			   {
				   admin adm;
				   
				   adm = new admin(inresult);
				   
				   int admch=0;
				   
				   int adminch=0;
				   
				   do {
				   System.out.println("Enter:\n1. for Viewing Details\n2. for Accessing Hosteller");
				   admch = s.nextInt();
				   
				   switch(admch)
				   {
				   case 1: adm.view_all_details();break;
				   case 2: adm.access_hosteller();break;
				   }
				   
				   System.out.println("Do you want to perform any other function(1-yes/2-no)");
				   adminch = s.nextInt();
				   
				   }while(adminch==1);
			   }
	   }
	   
	   System.out.println("Thank You!!!");
	   }
	   catch(Main e)
	   {
		   e.printStackTrace();
	   }
   }
   
   
}



