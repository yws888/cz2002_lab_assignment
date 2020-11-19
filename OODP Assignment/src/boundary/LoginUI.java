package boundary;
import java.util.Scanner;

import control.LoginController;

import java.io.*;

public class LoginUI {
	
	
	public static void main(String[] args) throws InterruptedException {
		LoginController logincontroller = new LoginController();
		String username, password;
		int userinput;
		boolean verifylogin;
		UserUI userUI;
		Scanner sc = new Scanner(System.in);	
		while(true) {
			System.out.println("====MySTARS====");
			System.out.println("1. Login");
			System.out.println("2. Exit");

			userinput = sc.nextInt();
			switch(userinput) {
			case 1:
				System.out.println("Username:");
				username = sc.next();
				Console cnsl = System.console();
				if (cnsl == null) { //eclipse doesnt support input masking
					System.out.println("Password:");
					password = sc.next();
			    } 
				else {
					char[] ch = cnsl.readPassword( "Enter password : ");
					password = new String(ch);
				}
				//System.out.println("Username: "+username+ " Password: "+password);
				System.out.println("Processing....");
				verifylogin = logincontroller.verifylogin(username,password);
				if(verifylogin == true) {
					int mode = logincontroller.getLoginMode(username);
					switch(mode) {
						case 1: //Student
							userUI = new StudentUI(logincontroller.getStudent(username));
							System.out.println("Login Successful.");
							((StudentUI) userUI).initStudentUI();
							break;
						case 2: //Staff
							userUI = new StaffUI(logincontroller.getStaff(username));
							System.out.println("Login Successful.");
							((StaffUI) userUI).initStaffUI();
							break;
						case 3: //error
							System.out.println("Error, could not find user entry.");
							break;
					}
				}else {
					System.out.println("Login Failed.");
				}
				
				break;
			case 2:
				sc.close();
				System.exit(0);
				break;

			}

		}
		
	}

}
