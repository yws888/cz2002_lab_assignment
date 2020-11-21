package exception;

/**
 * Exception when there is no user found in the database.
 */
public class UserNotFoundException extends Exception {

  /**
   * The exception message.
   */
  private String message;

  /**
   * The constructor of the exception.
   */
  public UserNotFoundException() {
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
