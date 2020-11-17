package boundary;

import control.CourseMgr;
import entity.Course;
import entity.CourseIndex;

public class CourseUI {
	public void getCourseInput() {

	}

	CourseMgr courseMgr;


	public CourseUI() {
		courseMgr = new CourseMgr();
	}


	public void printVacancies(int index) {

		Course course = courseMgr.retrieveCourseFromIndex(index);
		//CourseIndex courseIndex = course.getCourseIndexObject(index);
		// int vacancies = courseMgr.checkVacancies(course.getCourseCode(), courseIndex);

		//System.out.println("Index Number: " + courseIndex.getIndexNo() + " \t\t Course: " + course.getCourseCode());
		//to insert a table
		//System.out.println("Vacancies Available: " + courseIndex.getNoOfVacancies() + " \t Waitlist Length: " + courseIndex.getNoOfVacancies());


	}
}

