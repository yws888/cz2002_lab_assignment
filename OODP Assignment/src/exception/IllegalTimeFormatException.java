package exception;

/**
 * Exception thrown when the time formatting is not
 * according to the specified format.
 */
public class IllegalTimeFormatException extends Exception {

  /**
   * When the user inputs a wrong time format.
   */
  private String message;

  /**
   * The constructor of the exception.
   * 
   */
  public IllegalTimeFormatException() {
    super();
    this.message = "Wrong format try again!";
  }

  /**
   * Getter to the exception message.
   * @return the message as a string
   */
  public String getMessage() {
    return message;
  }
}
