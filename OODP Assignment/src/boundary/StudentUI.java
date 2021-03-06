package boundary;
import java.util.Scanner;

import control.StudentController;
import entity.Student;
import exception.CourseClashingException;
import exception.CourseIndexFullException;
import exception.CourseIndexNotFoundException;
import exception.ExistingCourseException;
import exception.IllegalAUWeightageException;
import exception.IllegalCourseChangeException;

/**
 * Boundary Class that limits the user to carry out only Student functions 
 * A subclass of the UserUI class
 */

public class StudentUI implements UserUI {

	/**
	 *  Student object of current User(Student) 
	 */ 
	private Student student;
	
	/**
	 * Get Student object details
	 * 
	 * @return Student object
	 */
	public Student getStudent() {
		return this.student;
	}
	
	
	/**
	 * Constructor for StudentUI Object 
	 * @param student   student object 
	 */
	public StudentUI(Student student) {
		this.student = student;
	}
	
	/**
	 * User Interface for Student 
	 */
	public void initUI() {
		int choice = -1;

		Scanner sc = new Scanner(System.in);
		
		do {
				System.out.println("\nWelcome to STARS (Student): "+this.student.getName());
				System.out.println("1. Add Course");
				System.out.println("2. Drop Course");
				System.out.println("3. Check/Print Courses Registered");
				System.out.println("4. Check Vacancies Available");
				System.out.println("5. Change Index Number of Course");
				System.out.println("6. Swap Index Number with Another Student");
				System.out.println("0. Exit");
				System.out.print("\nEnter the number of your choice: "); 
				try {
					choice = Integer.parseInt(sc.nextLine());
					
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
			} 
			catch (NumberFormatException e) {
			System.out.println("Please select a valid numeric option");
			System.out.println();
			} catch (CourseIndexNotFoundException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (CourseClashingException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (CourseIndexFullException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (ExistingCourseException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (IllegalAUWeightageException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (IllegalCourseChangeException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} 
		} while (choice != 0);

		return;
	}

}
