package exception;


/**
 * Exception when the user inputs an invalid course index
 */
public class CourseIndexNotFoundException extends Exception {

  /**
   * When no such course index is found.
   */
  private String message;

  /**
   * Constructor for the exception.
   *  
   * @param courseIndex  The course index that does not exists in database
   */
  public CourseIndexNotFoundException(String courseIndex) {
	  super();
	  
	
      this.message = "\nThere are no records of course index " + courseIndex
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
