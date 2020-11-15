package entity;
import java.util.ArrayList;
import java.util.HashMap;

import enumeration.SessionType;


public class Course {
	
	private String courseName;
	private String courseCode;
	private String faculty; //or enum?
	private HashMap<Integer, CourseIndex> courseIndexes;
	// private ArrayList<Integer> indexNos = new ArrayList<Integer>();
	private int noOfAUs;
	private SessionType sessionType;
	
	//test method
	public Course() {
		this.setCourseCode("CZ2002");
		this.setCourseName("OODP");
		this.faculty = "SCSE";
		this.sessionType = SessionType.LAB;
		this.noOfAUs = 3;
		courseIndexes = new HashMap<Integer, CourseIndex>();

		CourseIndex courseIndex1 = new CourseIndex(10198, 35, "SS8", this);
		courseIndexes.put(10198, courseIndex1);

	}
	
	public Course(String courseName, SessionType courseType, int noOfAUs) {
		this.setCourseName(courseName);
		this.courseType = courseType;
		this.courseCode = courseCode;
		this.noOfAUs = noOfAUs;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	//check if course index no. exists for that course
	public boolean checkCourseIndex(int index) {
		for (int courseIndexNo : courseIndexes.keySet()) {
			if (index == courseIndexNo)
				return true;
		}
			return false;
	}
	
	//get course index object based on index no.
	public CourseIndex getCourseIndexObject(int index) {
		return courseIndexes.get(index);
	}
	
	//test method
	/*public addIndexes() {
		indexNos.add(e)
	}*/
	
	
	
	

}
