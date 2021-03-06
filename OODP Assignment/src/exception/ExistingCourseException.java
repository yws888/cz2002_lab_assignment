package exception;

import java.util.ArrayList;

/**
 * Exception thrown when user has registered for the same course / course index.
 */
public class ExistingCourseException extends Exception {

  /**
   * When the user adds an existing course either already
   * taken by the user or it is already created by the 
   * admin based on the course index.
   */
  private String message;
  
  /**
   * The constructor for the exception.
   * 
   * @param courseIndex  The course index that already exists in database
   */
  public ExistingCourseException(String courseIndex) {
    super();
    this.message = "Action failed " + courseIndex + " is already registered! Press the \"ENTER\" key to be directed back to the previous menu!";
  }
  
  /**
   * The constructor for the exception.
   * 
   * @param courseIndex  an array of course indexes that already exist in database
   */
  public ExistingCourseException(ArrayList<String> courseIndex) {
	  super();
	    this.message = "Action failed " + courseIndex + " is already registered! Press the \"ENTER\" key to be directed back to the previous menu!";
	  }

/**
   * Getter function to the exception message.
   * 
   * @return the message as a string
   */
  public String getMessage() {
    return message;
  }
}
