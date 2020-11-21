package exception;

/**
 * Exception thrown when the time period is not the
 * user has included is not possible.
 */
public class IllegalTimePeriodException extends Exception {

  /**
   * When the user inputs a wrong time period.
   */
  private String message;

  /**
   * The constructor of the exception.
   * 
   */
  public IllegalTimePeriodException() {
    super();
    this.message = "Error updating time period! Inputted start time and end time is not valid!";
  }

  /**
   * Getter to the exception message.
   * @return the message as a string
   */
  public String getMessage() {
    return message;
  }
}
