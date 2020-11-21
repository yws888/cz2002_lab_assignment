/*package control;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import entity.Course;

public class CourseMgr {

	private HashMap<String, Course> courses = new HashMap<String, Course>();

	
	public CourseMgr() {
		// temp generator
		Course testCourse = new Course();
		
		courses.put(testCourse.getCourseCode(), testCourse);
	}

	
	
	//validate if course index exists
	public boolean validateCourseIndex(int courseIndex) {

	    for (Course course : courses.values()) {
	        //if (course.checkCourseIndex(courseIndex) == true)
	        	return true;
	    }
	    
	    return false;
	 }
	
	//retrieve course object from course index number
	public Course retrieveCourseFromIndex(int courseIndex) {

	    for (Course course : courses.values()) {
	        //if (course.checkCourseIndex(courseIndex) == true)
	        	return course;
	    }
	    
	    return null;
	 }

	
}*/
