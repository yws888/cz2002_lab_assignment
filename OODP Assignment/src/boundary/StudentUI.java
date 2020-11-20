package boundary;
import java.util.Scanner;

//import control.CourseMgr;
import control.StudentController;
import entity.Student;

public class StudentUI implements UserUI {

	private Student student;
	public Student getStudent() {
		return this.student;
	}
//	private void setStudent(Student student) {
//		this.student = student;
//	}
	public StudentUI(Student student) {
		this.student = student;
	}
	public void initStudentUI() {
		int choice = -1;
//		CourseMgr courseMgr = new CourseMgr();
//		CourseUI courseUI = new CourseUI();


		Scanner sc = new Scanner(System.in);
		
		do {
		System.out.println("\nWelcome to STARS (Student): "+this.student.getUsername());
		System.out.println("1. Add Course");
		System.out.println("2. Drop Course");
		System.out.println("3. Check/Print Courses Registered");
		System.out.println("4. Check Vacancies Available");
		System.out.println("5. Change Index Number of Course");
		System.out.println("6. Swap Index Number with Another Student");
		System.out.println("0. Exit");
		System.out.print("\nEnter the number of your choice: "); 
		try {
			choice = Integer.parseInt(sc.next());
			
			switch (choice) {
			case 1:
				StudentController.addCourse(student);
				break;
			case 2:
				StudentController.dropCourse(student);
				break;
			case 3:
				StudentController.printRegisteredCourses(student);
				break;
			case 4:
				StudentController.checkCourseVacancy();
				break;
			case 5:
				StudentController.changeIndex(student);
				break;
			case 6:
				StudentController.swapIndex(student);
				break;
			case 0: //quit
				break;
			default:
				System.out.println("Please select an option from 0-6");
				System.out.println();
				break;
}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Please select an option from 0-6");
		}
} while (choice != 0);

		return;//temp code
	}

}
