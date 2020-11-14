package boundary;
import java.util.Scanner;

import control.CourseMgr;
import entity.Staff;

public class StaffUI {

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
	
	
	public void initStaffUI() {
		
		int choice;


		Scanner sc = new Scanner(System.in);
		
		do {
		System.out.println("Welcome to STARS (Staff): "+this.staff.getUsername());
		System.out.println("1. Edit student access period");
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
			
			break;
		case 2: 
			break;
		case 3: 
			break;
		case 4: 
			
			break;
		case 5: 
			break;

		case 6:
			System.out.println("Pls enter course code (e.g. CZ2002)");
			String code = sc.next();
			//System.out.println("Pls enter course index no. (e.g. 10198)");
			// int index = sc.nextInt();
			
			printslist.printStudentList(code);
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
