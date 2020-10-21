import java.util.Scanner;

public class LoginUI {

	
	public static void main(String[] args) {
		LoginController logincontroller = new LoginController();
		String username, password;
		int userinput;
		boolean verifylogin;
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
				System.out.println("Password:");
				password = sc.next();
				//System.out.println("Username: "+username+ " Password: "+password);
				System.out.println("Processing....");
				verifylogin = logincontroller.verifylogin(username,password);
				if(verifylogin == true) {
					int mode = logincontroller.getLoginMode(username);
					switch(mode) {
						case 1: //Student
							StudentUI studentUI = new StudentUI(logincontroller.getStudent(username));
							System.out.println("Login Successful.");
							studentUI.initStudentUI();
							break;
						case 2: //Staff
							StaffUI staffUI = new StaffUI(logincontroller.getStaff(username));
							System.out.println("Login Successful.");
							staffUI.initStaffUI();
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
				System.exit(0);
				break;
			}
			
			
			
		}
		
	}

}
