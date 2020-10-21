
public class StudentUI {

	private Student student;
	private Student getStudent() {
		return this.student;
	}
	private void setStudent(Student student) {
		this.student = student;
	}
	public StudentUI(Student student) {
		this.student = student;
	}
	public void initStudentUI() {
		System.out.println("Welcome to STARS (Student): "+this.student.getUsername());
		System.exit(0);//temp code
	}

}
