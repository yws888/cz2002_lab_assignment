package exception;

/**
 * Exception when the user inputs an invalid course code
 */
public class CourseCodeNotFoundException extends Exception {

  /**
   * message shown when no such course code is found.
   */
  private String message;

  /**
   * Constructor for the exception.
   * 
   *  @param courseCode  The course code that does not exists in database
   */
  public CourseCodeNotFoundException(String courseCode) {
    super();
    this.message = "\nThere are no records of course code " + courseCode
    		+ ". Press the \"ENTER\" key to be directed back to the previous menu!";
  }

  /**
   * Getter function for message.
   * 
   * @return the string of message
   */
  public String getMessage() {
    return message;
  }
}
