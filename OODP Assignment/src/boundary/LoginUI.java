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
		boolean isChoice2 = false;
		UserUI userUI;
		
		
		while(!isChoice2) {
			
			System.out.println("====MySTARS====");
			System.out.println("1. Login");
			System.out.println("2. Exit");
			
			Scanner sc = new Scanner(System.in);
			
			if(sc.hasNextInt()) {
				
				userinput = Integer.parseInt(sc.next());
						
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
								
								if (LoginController.isValidAccessTime()==true){
									System.out.println("Login Successful.");
									((StudentUI) userUI).initStudentUI();
									}
								else
								{
									System.out.println("Sorry you are not allowed to access the portal now!");
									System.out.println("Please log in at your specified access period!");
									System.out.println("Your access period is on " +LoginController.retriveAccessperiod());
								}
								
								break;
							case 2: //Staff
								userUI = new StaffUI(logincontroller.getStaff(username));
								System.out.println("Login Successful. ");
								((StaffUI) userUI).initStaffUI();							
								break;
							case 3: //error
								System.out.println("Error, could not find user entry.");
								break;
						}
					}else {
						System.out.println("Login Failed.");
						System.out.println();
						break;
					}
					
					break;
				//exit system
				case 2:
					sc.close();
					System.out.println("Thank you for using MyStars, goodbye!");
					System.exit(0);
					isChoice2 = true;
					break;
				
				//default error input message
				default: 
					System.out.println("Please select option 1 or 2");
					System.out.println();
					break;
				}
							
			} else {
			System.out.println("Please select option 1 or 2");
			System.out.println();
			continue;
			}
		}
		
	}

}
