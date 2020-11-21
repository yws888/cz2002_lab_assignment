package control;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import entity.AccessPeriod;
import entity.Staff;
import entity.Student;
import entity.User;

/**
 * Controller to manage the 
 *
 */
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
/*	public static String retriveAccessperiod() {
		
		ArrayList<AccessPeriod> aplist = new ArrayList<AccessPeriod>();
		aplist.addAll((AccessPeriod.retrieveAccessPeriod()));
		for(int i=0;i<aplist.size();i++) {
			if((aplist.size()-1)==i){
				
				return aplist.get(i).getap();
				}
	
		}
		return null;
	}*/
	public static  boolean isValidAccessTime() {
		AccessPeriod accessPeriod = AccessPeriod.retrieveAccessPeriod();
		// LocalDateTime daten =LocalDateTime.now();
		String currentDate =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		/*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = null;
		Date startDate = null;
		Date endDate = null;
		try {
			currentDate = simpleDateFormat.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			startDate = simpleDateFormat.parse(accessPeriod.getStartDate());
			endDate = simpleDateFormat.parse(accessPeriod.getEndDate());
		} catch (ParseException e) {
			//e.printStackTrace();
		}*/
		String currentTime =LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
		

			//if(daten.compareTo(accessPeriodStartDateTime)>=0 && daten.compareTo(accessPeriodEndDateTime)<0){            
			if(currentDate.compareTo( accessPeriod.getStartDate() ) >= 0 && currentDate.compareTo( accessPeriod.getEndDate() ) <= 0
					&& currentTime.compareTo( accessPeriod.getStartTime() ) >= 0 && currentTime.compareTo( accessPeriod.getEndTime() ) <= 0) {	
				return true;

			}
		return false;
	}
	
	
	
}
