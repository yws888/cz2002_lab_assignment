package boundary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import control.StaffController;
import entity.Course;
import entity.Staff;
import entity.Student;

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

	/**
	 * Initialises Course and Student to be used in the
	 * user interface later.
	 */
    Course course;
    Student student;

	/**
	 * Initialises User Interface for Staff Member
	 */
	
	public void initStaffUI()  {
		int choice = -1;
		int choice2 = -1;

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

				choice = Integer.parseInt(sc.nextLine());
				
	
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
					            choice2 = Integer.parseInt(sc.nextLine());


					            switch (choice2) {
					                case 1:
					                	StaffController.addCourse();
					                	
										
										try { 
											 Course course = new Course();
											 System.out.println(course.retrieveCourse()); 
											 
										} catch (IOException e) {} 
										 catch (NullPointerException e) {};
										
					                	
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
					        } while (choice2 != 0);
						break;
					case 4:
						StaffController.checkAvailableSlot();
						break;
					case 5:
				        //name gender nationality only
				        String courseIndex="";
				        course = new Course();
				        student = new Student();
				        System.out.println("Please enter the course index to print student list:");
				        
				        courseIndex = sc.nextLine();
				        if(courseIndex.toLowerCase().equals("cancel")){
				            System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
				            sc.nextLine();
				            break;
				        }
				        if(course.isIndexTaken(courseIndex)){
				            ArrayList<Student> studentList = new ArrayList<Student>();
				            try {
				                studentList = student.studentListByIndex(courseIndex);
				            } catch (IOException e) {
				                e.printStackTrace();
				            }
				            System.out.print("=====Student list=====");
				            //print list
				            if(studentList.size() > 0){
				                for(int i=0;i<studentList.size();i++){
				                    System.out.print("\nName:"+studentList.get(i).getName()+"\tGender:"+studentList.get(i).getGender()+"\tNationality:"+studentList.get(i).getNationality());
				                }

				            }else{
				                System.out.print("\nNo student list found.");
				            }
				            System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
				            sc.nextLine();
				        }else{
				            System.out.println("\nThere are no records of course index entered. Press the \"ENTER\" key to be directed back to the previous menu!");
				            sc.nextLine();
				        }
				        break;
					case 6:
					        //name gender nationality only
					        String courseCode="";
					        course = new Course();
					        student = new Student();
					        System.out.println("Please enter the course code to print student list:");
					        courseCode = sc.nextLine();
					        if(courseCode.toLowerCase().equals("cancel")){
					            System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
					            sc.nextLine();
					            break;
					        }
					        if(course.isCourseTaken(courseCode)){
					            ArrayList<Student> studentList = new ArrayList<Student>();
					            try {
					                studentList = student.studentListByCourseCode(courseCode);
					            } catch (IOException e) {
					                e.printStackTrace();
					            }
					            System.out.print("=====Student list====="); //print list
					            if(studentList.size() > 0){
					                for(int i=0;i<studentList.size();i++){
					                    System.out.print("\nName:"+studentList.get(i).getName()+"\tGender:"+studentList.get(i).getGender()+"\tNationality:"+studentList.get(i).getNationality());
					                }
					            }else{
					                System.out.print("\nNo student list found.");
					            }
					            System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
					            sc.nextLine();
					        }else{
					            System.out.println("\nThere are no records of course code entered. Press the \"ENTER\" key to be directed back to the previous menu!");
					            sc.nextLine();
					        }
				            break;

					case 0: //quit
						break;
					default:
						System.out.println("Please select an option from 0-6");
						System.out.println();
						break;
				}
			} 
			catch (NumberFormatException e) {
				System.out.println("Please select a valid numeric option");
				System.out.println();
			}
			catch (InterruptedException e) {}
						
		} while (choice != 0);

		return;
	}
}

