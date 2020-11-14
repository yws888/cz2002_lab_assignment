package MySTARS.Entity;
import java.util.ArrayList;
import java.util.HashMap;

public class Course {

	private String courseName;
	private String courseCode;
	private String school; // or enum?
	private HashMap<Integer, CourseIndex> courseIndexes = new HashMap<Integer, CourseIndex>();
	private ArrayList<Integer> indexNos = new ArrayList<Integer>();
	private int noOfAUs;
	private CourseType courseType;

	// test method
	public Course() {
		this.setCourseCode("CZ2002");
		this.setCourseName("OODP");
		this.school = "SCSE";
		this.courseType = CourseType.LAB;
		this.noOfAUs = 3;
		indexNos.add(10198);
		CourseIndex courseIndex1 = new CourseIndex(10198, 35, this);
		courseIndexes.put(10198, courseIndex1);
		// for (int i = 10198; i<=10200; i++ )
		// indexNos.add(i);
		// for (int j = 1; j <= indexNos.size(); j++)
		// courseIndexes =
	}

	public Course(String courseName, CourseType courseType, int noOfAUs) {
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

	// get corresponding index object based on index no.
	public CourseIndex getcourseIndex(int index) {
		return courseIndexes.get(index);
	}

	// test method
	/*
	 * public addIndexes() { indexNos.add(e) }
	 */

}
