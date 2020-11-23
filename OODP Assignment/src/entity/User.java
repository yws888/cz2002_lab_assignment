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
	 * salt used in password hashing process
	 */
	private String salt;
	/**
	 * 	 type of the user (e.g. student, staff)
	 */
	private String type;
		
	/**
	 * Getter method for username of the user
	 * 
	 * @return username of the user
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * Getter method for password of the user
	 * 
	 * @return password of the user
	 */
	public String getPassword() {
		return this.password;
	}
	/**
	 * Getter method for salt used in password hashing process
	 * @return salt
	 */
	public String getSalt(){ return this.salt; }
	/**
	 * Getter method for type of the user
	 * 
	 * @return type of the user (e.g. student, staff)
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Setter method for username
	 * 
	 * @param username username
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
