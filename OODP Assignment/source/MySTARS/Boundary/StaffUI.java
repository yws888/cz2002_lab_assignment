package MySTARS.Boundary;
import MySTARS.Entity.Staff;

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
		System.out.println("Welcome to STARS (Staff): " + this.staff.getUsername());
		System.exit(0);// temp code
	}

}
