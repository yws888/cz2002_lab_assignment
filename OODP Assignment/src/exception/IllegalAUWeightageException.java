package exception;

/**
 * Exception thrown when the total AU taken  
 * exceeds 21 AUs.
 */
public class IllegalAUWeightageException extends Exception {

  /**
   * When the user adds over 21 AUs.
   */
  private String message;

  /**
   * The constructor of the exception.
   * 
   */
  public IllegalAUWeightageException() {
    super();
    this.message = "\nCourse adding failed. Maximum number of AUs possible exceeded!! Press the \"ENTER\" key to be directed back to the previous menu!";
  }

  /**
   * Getter to the exception message.
   * @return the message as a string
   */
  public String getMessage() {
    return message;
  }
}
