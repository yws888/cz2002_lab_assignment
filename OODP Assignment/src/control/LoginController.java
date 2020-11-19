package control;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import entity.AccessPeriod;
import entity.Staff;
import entity.Student;
import entity.User;

public class LoginController {
	public boolean verifylogin(String username, String password) {
		User user = new User(username, password);
		//if login true
		if(user.verifylogin() == true) {
			return true;
		}else {
			return false;
		}
	}
	public int getLoginMode(String username) {
		String type;
		User user = new User();
		user = user.getUserbyUsername(username);
		
		type = user.getType();
		if(type.equals("student")) {
			return 1;
		}else if(type.equals("staff")) {
			return 2;
		}
		return 3;
	}
	
	public Staff getStaff(String username) {
		Staff staff = new Staff();
		return staff.retrieveStaffInfo(username);
		
	}
	public Student getStudent(String username) {
		Student student = new Student();
		return student.retrieveStudentInfoByUsername(username);
	}
	public static String retriveAccessperiod() {
		
		ArrayList<AccessPeriod> aplist = new ArrayList<AccessPeriod>();
		aplist.addAll((AccessPeriod.retrieveAccessPeriod()));
		for(int i=0;i<aplist.size();i++) {
			if((aplist.size()-1)==i){
				
				return aplist.get(i).getap();
				}
	
		}
		return null;
	}
	public static  boolean isValidAccessTime() {
	
		
		ArrayList<AccessPeriod> aplist = new ArrayList<AccessPeriod>();
		aplist.addAll((AccessPeriod.retrieveAccessPeriod()));
		//String time = "12/11/2020";              //check changing today
		
		String daten =LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String timen =LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
		for(int i=0;i<aplist.size();i++) {
			
			if(daten.equals(aplist.get(i).getap()) && (timen.compareTo(aplist.get(i).getapt()))>0){              //manipulate the today time
				
				
				return true;

			}
		
		}
		
		return false;
	}
	
	
	
}
