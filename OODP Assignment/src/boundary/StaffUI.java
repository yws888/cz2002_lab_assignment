package boundary;
import java.util.Scanner;

import control.StaffController;
import entity.Staff;

public class StaffUI implements UserUI {

	private Staff staff;
	private Staff getStaff() {
		return this.staff;
	}
	private void setStaff(Staff staff) {
		this.staff = staff;
	}
	public StaffUI(Staff staff) {
		this.staff = staff;
	}

	public StaffUI(){}
	/*public static void main(String[] args) throws InterruptedException {
		StaffUI staffui = new StaffUI();
		staffui.initStaffUI();
	}*/
	public void initStaffUI() throws InterruptedException {
		Scanner sc = new Scanner(System.in);

		int choice;

		do {
			System.out.println("\nWelcome to STARS (Staff): ");//+this.staff.getUsername());
			System.out.println("1. Edit Student Access Period");
			System.out.println("2. Add a student");
			System.out.println("3. Add/Update a course");
			System.out.println("4. Check available slot for an index number");
			System.out.println("5. Print student list by index number");
			System.out.println("6. Print student list by course");
			System.out.println("0. Exit");
			System.out.print("\nEnter the number of your choice: ");
			choice = sc.nextInt();

			switch (choice) {
				case 1:
					StaffController.editAccessPeriod();
					break;
				case 2:
					StaffController.addStudent();
					break;
				case 3:
					StaffController.courseConfiguration();
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
		} while (choice != 0);

//		switch (choice) {
//		case 1:
//			System.out.println("Pls enter course code (e.g. CZ2002)");
//			String code = sc.next();
//			System.out.println("Pls enter course index no. (e.g. 10198)");
//			int index = sc.nextInt();
//
//			//printslist.printStudentList(code);
//
		return;//temp code
	}
}

