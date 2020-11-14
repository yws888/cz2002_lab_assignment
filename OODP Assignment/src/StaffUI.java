
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
		
		do{
		System.out.println("Welcome to STARS (Staff): "+this.staff.getUsername());
		System.out.println("1. Print Studentlist in the course");	
		choice = sc.nextInt();
		
		switch (choice) {
		case 1: 
			System.out.println("Pls enter course code (e.g. CZ2002)");
			String code = sc.next();
			System.out.println("Pls enter course index no. (e.g. 10198)");
			int index = sc.nextInt();
			
			printslist.printStudentList(code);
			
		System.exit(0);//temp code
	}

}
