package entity;
import java.util.ArrayList;

/**
 * User represents a user in the database.
 *
 */

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
	 * @return username of the user
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * @return password of the user
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
	 * Checks if the student or staff login details are correct
	 *
	 * @return false if login verification is wrong.
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
	 * Gets the user by their user name
	 *
	 * @param username
	 * @return null if username is not found.
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
