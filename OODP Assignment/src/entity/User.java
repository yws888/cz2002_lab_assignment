package entity;
import java.util.ArrayList;

/**
 * User represents a user in the database.
 *
 */

public class User {
	/**
	 * username of the user
	 */
	private String username;
	/**
	 * password of the user
	 */
	private String password;
	/**
	 * 
	 */
	private String salt;
	/**
	 * 	 type of the user (e.g. student, staff)
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
	 * @return type of the user (e.g. student, staff)
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
	 * Default constructor for User object
	 */
	public User() {
		
	}

	/**
	 * 	 Constructor for User object, initialising the following attributes:
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	/**
	 * 	 Constructor for User object, initialising the following attributes:
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
	 * @return false if login verification is wrong, true if user and password are found in the system
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
	 * Gets the user object by their user name
	 *
	 * @param username			user name
	 * @return null if username is not found, otherwise it returns the user object
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
