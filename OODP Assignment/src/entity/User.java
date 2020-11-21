package entity;
import java.util.ArrayList;

public class User {
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private String salt;
	/**
	 * 
	 */
	private String type;
		
	/**
	 * @return
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * @return
	 */
	public String getPassword() {
		return this.password;
	}
	/**
	 * @return
	 */
	public String getSalt(){ return this.salt; }
	/**
	 * @return
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 */
	public User() {
		
	}

	/**
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	/**
	 * @param username
	 * @param password
	 * @param salt
	 * @param type
	 */
	public User(String username, String password, String salt, String type) {
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.type = type;
	}
	/**
	 * @return
	 */
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
	
	/**
	 * @param username
	 * @return
	 */
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
