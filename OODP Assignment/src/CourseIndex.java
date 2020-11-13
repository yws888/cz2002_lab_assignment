import java.util.ArrayList;

public class CourseIndex {
	
	private int indexNo;
	private int noOfVacancies;
	private Course course;
	private ArrayList<Student> studentsRegistered;
	private ArrayList<Student> studentsOnWaitList;
	//create separate class for venues and timings?
	
	public CourseIndex(int indexNo, int noOfVacancies, Course course) {
		this.indexNo =indexNo;
		this.noOfVacancies = noOfVacancies;
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
	
	// Added
	public ArrayList<Student> getStudentList() {
			return studentsRegistered;
	}
	
	public void addStudentList(Student student) {
		studentsRegistered.add(student);
}
	
	
	public void setNoofVacancies(int assign) {
		
		noOfVacancies=noOfVacancies+(assign);
		
	}
	
	



}
