package exception;

/**
 * Exception thrown when you try to register to a course index
 * that has no vancancy left.
 */
public class CourseIndexFullException extends Exception {

  /**
   * The exception message.
   */
  private String message;

  /**
   * Constructor for the exception.
   * 
   * @param courseIndex  The course index to which the registration is being made
   */
  public CourseIndexFullException(String courseIndex) {
    super();
    this.message = "Sorry, no vacancy left for course index " +
                   courseIndex + ".\nProcess cancelled. Press the \"ENTER\" key to be directed back to the previous menu!";
  }

  /**
   * Getter of the exception message.
   * 
   * @return the message as a string
   */
  public String getMessage() {
    return message;
  }
}
