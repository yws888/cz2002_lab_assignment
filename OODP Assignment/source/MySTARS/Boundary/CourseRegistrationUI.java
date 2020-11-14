package MySTARS.Boundary;
import java.util.*;

import MySTARS.Control.CourseRegistrationController;
import MySTARS.Entity.Student;

public class CourseRegistrationUI {
	Scanner sc = new Scanner(System.in);

	public void registerforCourse() {
		boolean valid = true;
		// Controller used
		CourseRegistrationController CRC = new CourseRegistrationController();
		Student student = new Student();

		System.out.print("Registering Course---------------");
		while (valid) {
			System.out.print("Enter Course Code:");
			String coursecode = sc.next();

			// Check if coursecode is a valid course code
			if (CRC.checkCourseCode(coursecode) == true && CRC.checkIfRegistered(coursecode, student)) {
				// print Course Indexes list
				int noofIndex = CRC.showCourseIndexNVacancies(coursecode);
				System.out.print("Select index to register:");
				int userinput = sc.nextInt();
				CRC.registerStudentforCourse(coursecode, userinput, student);

			}

			else if (CRC.checkCourseCode(coursecode) == false) {
				System.out.print("Please enter a correct course code.");
				continue;
			} else {
				System.out.print("You are already registered for this course.");
			}
		}

		// Return to StudentUI?

	}

}
