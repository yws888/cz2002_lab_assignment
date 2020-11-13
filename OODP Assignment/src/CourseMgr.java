import java.util.ArrayList;
import java.util.HashMap;

public class CourseMgr {

	private HashMap<String, Course> courses = new HashMap<String, Course>();
	
	public CourseMgr() {
		// temp generator
		Course testCourse = new Course();
		
		courses.put(testCourse.getCourseCode(), testCourse);
	}

	public int checkVacancies(String courseCode, int courseIndex) {
		//assume validation of input done in UI
		return courses.get(courseCode).getcourseIndex(courseIndex).getNoOfVacancies();
		}
	
	
	
}
