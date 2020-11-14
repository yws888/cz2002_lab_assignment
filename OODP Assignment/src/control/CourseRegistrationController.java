package control;
import java.util.ArrayList;

import entity.Course;
import entity.CourseIndex;
import entity.Student;

public class CourseRegistrationController {
	
	//checks if the course code is valid
	public boolean checkCourseCode(String courseCode){
	    //Using this temp code for the lists first
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		for(int i=0;i<courseList.size();i++) {
			//get courseList
			if (courseList.get(i).getCourseCode()== courseCode)
				return true;	
		}
		
		return false;
		
	}
	
	//prints the indexes for the particular course and vacancies for users to choose from
	public int showCourseIndexNVacancies(String courseCode) {
		
		ArrayList<CourseIndex> indexList = new ArrayList<CourseIndex>();
		for(int i=0;i<indexList.size();i++) {
			//Print index number 
			System.out.print("("+ (i+1)+"):" + indexList.get(i).getIndexNo() + ", Vacancies: " + indexList.get(i).getNoOfVacancies());
		}
		
		return indexList.size();		
		
	}
	
	//Adds the student into the StudentRegister array
	public void registerStudentforCourse(String courseCode,int i,Student student){
		ArrayList<CourseIndex> indexList = new ArrayList<CourseIndex>();
		if (indexList.get(i-1).getNoOfVacancies()!=0) {
			indexList.get(i-1).addStudentList(student);
			indexList.get(i-1).setNoofVacancies(-1); // Remove 1 vacancy
			System.out.print("Registration is successful.");
		}
		else {
			//Add student into Waitlist Array
			System.out.print("Course Index has 0 vacancies, you have been placed on waitlist");
			indexList.get(i-1).addWaitList(student);
		}
	}
		
	//Check if the student is already registered in that course
	public boolean checkIfRegistered(String courseCode,Student student) {
		ArrayList<CourseIndex> indexList = new ArrayList<CourseIndex>();
		for(int i=0; i<indexList.size();i++) {
			ArrayList<Student> studentList= indexList.get(i).getStudentList();
			if (studentList.contains(student)) {
				return true;
			}
		}
		return false;
	}

}
