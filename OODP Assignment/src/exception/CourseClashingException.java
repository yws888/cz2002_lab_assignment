package exception;
 
/**
 * Exception thrown when the course is clashing with another course in student's schedule
 */
public class CourseClashingException extends Exception {

  /**
   * When users tries to include a course that has a
   * schedule clashing with the user's current schedule.
   */
  private String message;

  /**
   * The constructor of the exception.
   * 
   * @param courseIndex  The course index to which the registration is being made
   */
  public CourseClashingException(String courseIndex) {
    super();
    this.message = "Course adding failed " + courseIndex + " clashes with your schedule!! Press the \"ENTER\" key to be directed back to the previous menu!";
  }

  /**
   * Getter to the exception message.
   * @return the message as a string
   */
  public String getMessage() {
    return message;
  }
}
