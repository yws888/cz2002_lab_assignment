package printslist;
import functionapp;
import datap;


public class printslist {
	
	public class CourseMgr {
		static ArrayList<Course> courseList = datap.courseList;
		static ArrayList<Student> studentList = datap.studentList;
		static ArrayList<CourseReg> registrationList = datap.regList;
		
		
		public static Course getCourseByCode (String courseCode){
			for (Course c : courseList) {
				if (c.getCourseCode().equals(courseCode)) {
					return c;}
			}
			return null;
		}
	
	
}
	
	public static void printStudentList(String courseCode) {
		boolean flag = false;
		Course course = CourseMgr.getCourseByCode (courseCode);
		
		System.out.println();
		System.out.println("Course Code: " + courseCode);
		System.out.println("Username\t Matric Number\t Full Name");
		System.out.println("-----------------------------------------------------------------");
		
		for (CourseReg courseReg : registrationList){
			if(courseReg.getCourse().equals(course)){
				Student student = courseReg.getStudent();
				System.out.print(student.getUsername() + "\t\t ");
				System.out.print(student.getMatricNumber() + "\t ");
				System.out.print(student.getusername());
				System.out.println();
				
				flag = true;
			}
		}
		if (!flag) System.out.println("\nNo record is found!");
	}
	
}
