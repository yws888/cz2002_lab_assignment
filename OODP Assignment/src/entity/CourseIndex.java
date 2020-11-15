package entity;
import java.util.ArrayList;

public class CourseIndex {
	
	private int indexNo;
	private int noOfVacancies;
	private int totalSize; 
	private Course course;
	private ArrayList<Student> studentsRegistered;
	private ArrayList<Student> studentsOnWaitList;
	//create separate class for venues and timings?
	
	public CourseIndex(int indexNo, int totalSize, Course course) {
		this.indexNo =indexNo;
		noOfVacancies = totalSize;
		this.setTotalSize(totalSize);
		this.course = course;
		
	}
	
	public int getNoOfVacancies() {
		return noOfVacancies;
	}
	public void setNoOfVacancies(int noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}
	public int getIndexNo() {
		return indexNo;
	}
	public void setIndexNo(int indexNo) {
		this.indexNo = indexNo;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public int getWaitListLength() {
		return studentsOnWaitList.size();
	}


	public ArrayList<Student> getStudentsOnWaitList() {
		return studentsOnWaitList;
	}

	// Added
	public ArrayList<Student> getStudentsRegistered() {
			return studentsRegistered;
	}
	
	public void addStudentList(Student student) {
		studentsRegistered.add(student);}
	
	public void addWaitList(Student student) {
		studentsOnWaitList.add(student);}
	
	//can just reuse above and change the argument passed in?
	public void setNoofVacancies(int assign) {
		
		noOfVacancies=noOfVacancies+(assign);
		
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	
	



}
