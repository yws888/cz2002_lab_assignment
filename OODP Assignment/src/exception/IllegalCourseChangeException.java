package exception;

/**
 * Exception thrown when the course index the user is trying
 * to change to is from a different course code. 
 */
public class IllegalCourseChangeException extends Exception {

  /**
   * When the user adds a course index from a different
   * course code.
   */
  private String message;

  /**
   * The constructor of the exception.
   * 
   */
  public IllegalCourseChangeException() {
    super();
    this.message = "\nCourse index is not from the same course code Press the \"ENTER\" key to be directed back to the previous menu!";
  }

  /**
   * Getter to the exception message.
   * @return the message as a string
   */
  public String getMessage() {
    return message;
  }
}
