package boundary;
import java.util.Scanner;

import control.LoginController;
import entity.AccessPeriod;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * User Interface for Users to Login  
 *
 */

public class LoginUI {

	/**
	 * Main Function to run in Console
	 * @param args
	 * @throws InterruptedException
	 */
	
	public static void main(String[] args) throws InterruptedException {
		LoginController logincontroller = new LoginController();
		String username, password;
		int userinput = -1;
		
		boolean verifylogin;


		UserUI userUI;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("====MySTARS====");
			System.out.println("1. Login");
			System.out.println("2. Exit");
			System.out.println("3. Admin tester"); 			//to be removed later, for ease of testing now
		    			
				try {
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
										DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
										System.out.println("\nCurrent date and time is \t" + LocalDateTime.now().format(formatter));
										System.out.println("\nYour access period starts on \t" +AccessPeriod.retrieveAccessPeriod().get(0).getap() + " " +  AccessPeriod.retrieveAccessPeriod().get(0).getapt());
										System.out.println("Your access period ends on \t" +AccessPeriod.retrieveAccessPeriod().get(1).getap() + " " +  AccessPeriod.retrieveAccessPeriod().get(1).getapt());
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
							System.out.println("Login Unsuccessful.");
							System.out.println("Username or password is incorrect or it is not found in the system");
							//break;
						}
						
						break;
					//exit system
					case 2:
						sc.close();
						System.out.println("Thank you for using MyStars, goodbye!");
						System.exit(0);
						break;
						//remove this portion before submission
					case 3:
						userUI = new StaffUI(logincontroller.getStaff("Admin"));
						((StaffUI) userUI).initStaffUI();
						break;
						//end portion removal here					
					//default error input message
					default: 
						System.out.println("Pls select option 1 or 2");
						System.out.println();
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Pls select option 1 or 2");
					}
				//break;
		} while(true);

	}

}
