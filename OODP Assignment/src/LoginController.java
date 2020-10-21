
public class LoginController {
	public boolean verifylogin(String username, String password) {
		User user = new User(username, password);
		//if login true
		if(user.verifylogin() == true) {
			return true;
		}else {
			return false;
		}
	}
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
	
	public Staff getStaff(String username) {
		Staff staff = new Staff();
		return staff.retrieveStaffInfo(username);
		
	}
	public Student getStudent(String username) {
		Student student = new Student();
		return student.retrieveStudentInfo(username);
	}
}
