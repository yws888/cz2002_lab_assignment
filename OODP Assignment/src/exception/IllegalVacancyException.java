package exception;

/**
 * Exception when the target vacancy is less than the
 * previous vacancy size for the course index.
 */
public class IllegalVacancyException extends Exception {
  
  /**
   * When the user inputs a smaller target vacancy.
   */
  private String message;
  
  /**
   * The constructor of the exception.
   * 
   */
  public IllegalVacancyException() {
    super();
    this.message = "Input vacancy must be more than the current set vacancy, unable to update vacancy."
    		+ "\nPress the \"ENTER\" key to be directed back to the previous menu!";
  }

  /**
   * Getter to the exception message.
   * @return the message as a string
   */
  public String getMessage() {
    return message;
  }
}