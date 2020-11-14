package boundary;
import java.util.Scanner;

import control.CourseMgr;
import entity.Course;
import entity.CourseIndex;
import entity.Student;

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
		int choice;
		CourseMgr courseMgr = new CourseMgr();
		CourseUI courseUI = new CourseUI();


		Scanner sc = new Scanner(System.in);
		
		do {
		System.out.println("\nWelcome to STARS (Student): "+this.student.getUsername());
		System.out.println("1. Add Course");
		System.out.println("2. Drop Course");
		System.out.println("3. Check/Print Courses Registered");
		System.out.println("4. Check Vacancies Available");
		System.out.println("5. Change Index Number of Course");
		System.out.println("6. Swop Index Number with Another Student");
		System.out.println("0. Exit");
		System.out.print("\nEnter the number of your choice: "); 
		choice = sc.nextInt();
		
		switch (choice) {
		case 1: 
			
			break;
		case 2: 
			break;
		case 3: 
			break;
		case 4: 
			
			// System.out.println("Pls enter course code (e.g. CZ2002)");
			// String code = sc.next();
			
			
			System.out.println("Pls enter course index no. (e.g. 10198)");
			int indexNo = sc.nextInt();
			
			if (courseMgr.validateCourseIndex(indexNo)) {
				courseUI.printVacancies(indexNo);
			}
				
			// int noOfVacancies = courseMgr.checkVacancies(code, index);
			break;
		case 5: 
			break;

		case 6:
			break;

		case 0: //quit
			break;
		default:
			System.out.println("Please select an option from 0-6");
			System.out.println();
			break;
	}
} while (choice != 0);
		

		
		
		

		System.exit(0);//temp code
	}

}
