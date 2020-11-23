package boundary;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import control.LoginController;
import entity.AccessPeriod;


/**
 * User Interface Boundary Class for Users to Login  
 *
 */

public class LoginUI {
	
	  /**
	   * Main function that are run in the console.
	   * 
	   * @param args the default parameter for main function, not used in this case
	   * @throws InterruptedException
	   */
	
	public static void main(String[] args) throws InterruptedException  {
		LoginController logincontroller = new LoginController();
		String username, password;
		int userinput;
		
		boolean verifylogin;


		UserUI userUI;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("====MySTARS====");
			System.out.println("1. Login");
			System.out.println("2. Exit");
		    			
				try {
					userinput = Integer.parseInt(sc.nextLine());

					switch(userinput) {
					case 1:
						System.out.println("Username:");
						username = sc.nextLine();
						Console cnsl = System.console();
						if (cnsl == null) { //eclipse doesnt support input masking
							System.out.println("Password:");
							password = sc.nextLine();
					    } 
						else {
							char[] ch = cnsl.readPassword( "Enter password : ");
							password = new String(ch);
						}
						
						System.out.println("Processing....");
						verifylogin = logincontroller.verifylogin(username,password);
						if(verifylogin == true) {
							int mode = logincontroller.getLoginMode(username);
							switch(mode) {
								case 1: //Student
									userUI = new StudentUI(logincontroller.getStudent(username));
									
									if (LoginController.isValidAccessTime()==true){
										System.out.println("Login Successful.");
										((StudentUI) userUI).initUI();
										

										}
									else
									{
										System.out.println("Sorry you are not allowed to access the portal now!");
										System.out.println("Please log in at your specified access period!");
										System.out.println("\nCurrent date is \t\t" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

										AccessPeriod accessPeriod = AccessPeriod.retrieveAccessPeriod();
										System.out.println("\nYour access date is from \t" + accessPeriod.getStartDate() + " to " +  accessPeriod.getEndDate());
										System.out.println("\nCurrent time is \t\t" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
										System.out.println("Your access time is from \t" + accessPeriod.getStartTime() + " to " + accessPeriod.getEndTime());
									}
									
									break;
								case 2: //Staff
									userUI = new StaffUI(logincontroller.getStaff(username));
									System.out.println("Login Successful. ");
									((StaffUI) userUI).initUI();
									
									break;
								case 3: //error
									System.out.println("Error, could not find user entry.");
									break;
							}
						}else {
							System.out.println("\nLogin Unsuccessful.");
							System.out.println("Username or password is incorrect or is not registered in the system");
							//break;
						}
						
						break;
					//exit system
					case 2:
						sc.close();
						System.out.println("Thank you for using MyStars, goodbye!");
						System.exit(0);
						break;
										
					default: 
						System.out.println("Pls select option 1 or 2");
						System.out.println();
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Please select a valid numeric option");
					System.out.println();
					}
		} while(true);

	}

}
