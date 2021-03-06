package control;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import entity.AccessPeriod;
import entity.Staff;
import entity.Student;
import entity.User;

/**
 * Logincontroller contains the logic to validate the Users during the login processes
 */
public class LoginController {
	/**
	 * Checks for user in database and verifies login.
	 * @param username       User's username
	 * @param password       User's password
	 * @return true if user exist in database, false if user does not exist in database
	 */
	public boolean verifylogin(String username, String password) {
		User user = new User(username, password);
		
		if(user.verifylogin() == true) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the mode of login (Staff/Student) based on the user type.
	 * @param username    User's username
	 * @return mode of login (integer form), 1 for Student mode, 2 for Staff mode, 3 if user type is not identified. 
	 */
	public int getLoginMode(String username) {
		String type;
		User user = new User();
		user = user.getUserbyUsername(username);
		
		type = user.getType();
		if(type.equals("student")) {
			return 1;
		}else if(type.equals("staff")) {
			return 2;
		}
		return 3;
	}
	
	/** 
	 * Gets the Staff object based on username.
	 * @param username  		Staff's username
	 * @return staff object with Username, Password and Type
	 */
	
	public Staff getStaff(String username) {
		Staff staff = new Staff();
		return staff.retrieveStaffInfo(username);
		
	}
	
	/** 
	 * Gets the Student object based on username.
	 * @param username  		Student's username
	 * @return student object with Username, name, matric number, gender and nationality
	 */
	
	public Student getStudent(String username) {
		Student student = new Student();
		return student.retrieveStudentInfoByUsername(username);
	}

	
	/**
	 * Checks if time of access by student is within the access period. 
	 * @return true if time of access is within access period, false if time of access is not within access period
	 */
	public static  boolean isValidAccessTime() {
		AccessPeriod accessPeriod = AccessPeriod.retrieveAccessPeriod();
		
		String currentDate =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		String currentTime =LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
		    
		if(currentDate.compareTo( accessPeriod.getStartDate() ) >= 0 && currentDate.compareTo( accessPeriod.getEndDate() ) <= 0
				&& currentTime.compareTo( accessPeriod.getStartTime() ) >= 0 && currentTime.compareTo( accessPeriod.getEndTime() ) <= 0) {	
			return true;

		}
		return false;
	}
	
	
	
}
