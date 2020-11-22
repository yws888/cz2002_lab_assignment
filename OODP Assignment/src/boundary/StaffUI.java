package boundary;
import java.util.Scanner;

import control.StaffController;
import entity.Staff;

/**
 * Boundary Class that limits the user to carry out only Staff functions 
 *  Implements the UserUI interface
 */

public class StaffUI implements UserUI {

	/**
	 *  Staff object of current user (Staff) 
	 */
	private Staff staff;
	
	/**
	 * Constructor for StaffUI
	 * @param staff 	staff object
	 */
	public StaffUI(Staff staff) {
		this.staff = staff;
	}

	//public StaffUI(){}

	/**
	 * Initialises User Interface for Staff Member
	 */
	
	public void initStaffUI()  {
		int choice = -1;

		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("\nWelcome to STARS (Staff): " +this.staff.getUsername());
			System.out.println("1. Edit Student Access Period");
			System.out.println("2. Add a student");
			System.out.println("3. Add/Update a course");
			System.out.println("4. Check available slot for an index number");
			System.out.println("5. Print student list by index number");
			System.out.println("6. Print student list by course");
			System.out.println("0. Exit");
			System.out.print("\nEnter the number of your choice: ");
			
			
			try {

				choice = Integer.parseInt(sc.next());
				
	
				switch (choice) {
					case 1:
						StaffController.editAccessPeriod();
						break;
					case 2:
						StaffController.addStudent();
						break;
					case 3:
					        do {
					            System.out.println("\n1. Add Course");
					            System.out.println("2. Update Class Schedule of a Course");
					            System.out.println("3. Update course vacancy:");
					            System.out.println("0. Exit");
					            System.out.print("\nEnter the number of your choice: ");
									choice = Integer.parseInt(sc.next());


					            switch (choice) {
					                case 1:
					                	StaffController.addCourse();
					                    break;
					                case 2:
					                	StaffController.updateClassSchedule();
					                    break;
					                case 3:
					                	StaffController.updateCourseVacancy();
					                    break;
					                case 0: //quit
					                    break;
					                default:
					                    System.out.println("Please select an option from 0-3");
					                    System.out.println();
					                    break;
					            }
					        } while (choice != 0);
						break;
					case 4:
						StaffController.checkAvailableSlot();
						break;
					case 5:
						StaffController.printListByIndex();
						break;
					case 6:
						StaffController.printListByCourse();
						break;
					case 0: //quit
						break;
					default:
						System.out.println("Please select an option from 0-6");
						System.out.println();
						break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Please select a valid numeric option");
			}catch (InterruptedException e) {
				
			}
			
						
		} while (choice != 0);

		return;//temp code
	}
}

