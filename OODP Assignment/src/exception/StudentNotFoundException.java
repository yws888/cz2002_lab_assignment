package exception;

/**
 * Exception when there is no student found in the database.
 */
public class StudentNotFoundException extends Exception {

  /**
   * The exception message.
   */
  private String message;

  /**
   * The constructor of the exception.
   */
  public StudentNotFoundException() {
    super();
    this.message = "Login Failed. Please try again.";
  }

  /**
   * Getter to the exception message.
   * @return the message as a string
   */
  public String getMessage() {
    return message;
  }
}
