package entity;
import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private String salt;
	private String type;
		
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public String getSalt(){ return this.salt; }
	public String getType() {
		return this.type;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setType(String type) {
		this.type = type;
	}

	public User() {
		
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User(String username, String password, String salt, String type) {
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.type = type;
	}
	public boolean verifylogin() {
		Student student = new Student();
		Staff staff = new Staff();
		ArrayList<User> userlist = new ArrayList<User>();
		userlist.addAll((student.retrieveStudentLoginDetails()));
		userlist.addAll((staff.retrieveStaffLoginDetails()));
		for(int i=0;i<userlist.size();i++) {
			if(this.username.equals(userlist.get(i).getUsername())){
				return PasswordManager.PasswordValidation(this.password.toCharArray(), PasswordManager.hexDecoder(userlist.get(i).getSalt()),PasswordManager.hexDecoder(userlist.get(i).getPassword()));

			}
		}
		return false;
	}
	
	public User getUserbyUsername(String username) {
		Student student = new Student();
		Staff staff = new Staff();
		ArrayList<User> userlist = new ArrayList<User>();
		userlist.addAll((student.retrieveStudentLoginDetails()));
		userlist.addAll((staff.retrieveStaffLoginDetails()));
		for(int i=0;i<userlist.size();i++) {
			if(userlist.get(i).getUsername().equals(username)){
				return userlist.get(i);
			}
		}
		return null;
	}
}
