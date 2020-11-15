package entity;
import java.util.ArrayList;
import java.util.HashMap;

import enumeration.SessionType;

public class CourseIndex {
	
	private int indexNo;
	private int noOfVacancies;
	private int totalSize; 
	private String groupName; //e.g. SS8
	private Course course;
	private ArrayList<Student> studentsRegistered;
	private ArrayList<Student> studentsOnWaitList;
	private HashMap<SessionType, Timeslot> sessions;

		
	public CourseIndex(int indexNo, int totalSize, String groupName, Course course) {
		this.indexNo = indexNo;
		this.noOfVacancies = totalSize;
		this.totalSize = totalSize;
		this.groupName = groupName;
		this.course = course;
		this.studentsRegistered = new ArrayList<Student>();
		this.studentsOnWaitList = new ArrayList<Student>();
		
		/*int choice;
		switch(choice) {
		case 1: //lectures only
			sessions = 	new HashMap<SessionType, Timeslot>();
			sessions.put(SessionType.LEC, Timeslot);
			
			break;
		case 2: // lec + tut
			sessions = 	new HashMap<SessionType, Timeslot>();
			sessions.put(SessionType.LEC, Timeslot);
			sessions.put(SessionType.TUT, Timeslot);

			break;
		case 3: // lec, tut, lab
			sessions = 	new HashMap<SessionType, Timeslot>();
			sessions.put(SessionType.LEC, Timeslot);
			sessions.put(SessionType.TUT, Timeslot);
			sessions.put(SessionType.LAB, Timeslot);
			break;
		}*/
	}

	public int getNoOfVacancies() {
		return noOfVacancies;
	}
	
	public void setNoOfVacancies(int noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}
	public int getIndexNo() {
		return indexNo;
	}
	public void setIndexNo(int indexNo) {
		this.indexNo = indexNo;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public int getWaitListLength() {
		return studentsOnWaitList.size();
	}


	public ArrayList<Student> getStudentsOnWaitList() {
		return studentsOnWaitList;
	}

	// Added
	public ArrayList<Student> getStudentsRegistered() {
			return studentsRegistered;
	}
	
	public void addStudentList(Student student) {
		studentsRegistered.add(student);}
	
	public void addWaitList(Student student) {
		studentsOnWaitList.add(student);}
	
	//can just reuse above and change the argument passed in?
	public void setNoofVacancies(int assign) {
		
		noOfVacancies=noOfVacancies+(assign);
		
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	
	



}
