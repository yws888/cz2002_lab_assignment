package control;

import entity.Course;
import entity.PasswordManager;
import entity.Student;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import boundary.StudentUI;

public class StudentController {
    public static void addCourse(Student student) {
        Scanner sc = new Scanner(System.in);
        String courseIndex="";
        Course course = new Course();
        System.out.println("\nStarting Add Course Process: (Enter \"cancel\" to cancel process) ");
        System.out.println("Please enter course index:");
        courseIndex = sc.nextLine();
        if(courseIndex.toLowerCase().equals("cancel")){
            System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }
        if(course.isIndexTaken(courseIndex)){
            String input;
            boolean fieldIsValid;
            try {
                course = course.retrieveCourseByIndex(courseIndex);
                System.out.println("=====SUMMARY=====");
                System.out.print("Course Code:\t\t"+course.getCourseCode());
                System.out.print("\nCourse Name:\t\t"+course.getCourseName());
                System.out.print("\nAU:\t\t"+course.getNoOfAUs());
                System.out.print("\nSchool:\t\t"+course.getSchool());
                System.out.print("\nIndex:\t\t"+course.getCourseIndex());
                System.out.print("\n---Lecture Schedule---");
                System.out.print("\n"+course.printSchedule(course.getLectureSchedule()));
                System.out.print("\n---Lab Schedule---");
                System.out.print("\n"+course.printSchedule(course.getLabSchedule()));
                System.out.print("\n---Tutorial Schedule---");
                System.out.print("\n"+course.printSchedule(course.getTutorialSchedule()));
                System.out.print("\n=============================");
                fieldIsValid = false;
                while(!fieldIsValid) {
                    System.out.println("\nAre you sure to proceed with adding course Info? ('Y' or 'N')");
                    input = sc.nextLine();
                    if (input.toLowerCase().equals("cancel")) {
                        System.out.println("\nAdding Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                        sc.nextLine();
                        return;
                    }
                    if (input.toUpperCase().equals("Y")) {
                        fieldIsValid = true;
                    }else if (input.toUpperCase().equals("N")){
                        System.out.println("\nAdding Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                        sc.nextLine();
                        return;

                    }else {
                        System.out.println("\nInvalid Input please try again!");
                    }
                }
                //check if index is already taken
                if(!student.courseIndexTakenByStudent(courseIndex)){
                    //check if course code taken
                    if(!student.courseCodeTakenByStudent(courseIndex)) {
                        //check if clashes with schedule
                        if (!student.hasClashingSchedule(course)) {
                            //AU must not be > 21
                            if ((student.retrieveTotalAUTaken() + course.getNoOfAUs()) <= 21) {
                                String message = "";
                                if (course.courseIndexVacancy(courseIndex) > 0) {
                                    //added to course, status ACCEPTED
                                    message = student.enrollStudent(course, "ACCEPTED");
                                } else {
                                    //0 vacancy, put in waitlist, status WAITLIST
                                    message = student.enrollStudent(course, "WAITLIST");
                                }

                                System.out.println(message);
                                System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
                                sc.nextLine();
                                return;
                            } else {
                                //AU Exceeded
                                System.out.println("\nCourse adding failed. Maximum number of AUs possible exceeded!! Press the \"ENTER\" key to be directed back to the previous menu!");
                                sc.nextLine();
                                return;
                            }
                        }else{
                            //clashing schedule
                            System.out.println("\nCourse adding failed. Selected course clashes with your schedule!! Press the \"ENTER\" key to be directed back to the previous menu!");
                            sc.nextLine();
                            return;
                        }
                    }else{
                        //course taken by you already
                        System.out.println("\nCourse adding failed. Selected course is taken by you!! Press the \"ENTER\" key to be directed back to the previous menu!");
                        sc.nextLine();
                        return;
                    }
                }else{
                    //index already taken by student
                    System.out.println("\nCourse adding failed. Selected course is taken by you!! Press the \"ENTER\" key to be directed back to the previous menu!");
                    sc.nextLine();
                    return;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("\nThere are no records of course index entered. Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;

        }

    }

    public static void dropCourse(Student student) {
    	Scanner sc = new Scanner(System.in);
        String courseIndex="";
        Course course = new Course();
        System.out.println("\nStarting Drop Course Process: (Enter \"cancel\" to cancel process) ");
        System.out.println("Please enter course index:");
        courseIndex = sc.nextLine();
        if(courseIndex.toLowerCase().equals("cancel")){
            System.out.println("\nDrop Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }
                    
        try {
        	String input;
            boolean fieldIsValid;
        	//check if the student takes the course
            if(student.courseIndexTakenByStudent(courseIndex)){
                //check if course index taken by student
                if(student.courseCodeTakenByStudent(courseIndex)) {
                	course = course.retrieveCourseByIndex(courseIndex);
                    System.out.println("=====SUMMARY=====");
                    System.out.print("Course Code:\t\t"+course.getCourseCode());
                    System.out.print("\nCourse Name:\t\t"+course.getCourseName());
                    System.out.print("\nAU:\t\t"+course.getNoOfAUs());
                    System.out.print("\nSchool:\t\t"+course.getSchool());
                    System.out.print("\nIndex:\t\t"+course.getCourseIndex());
                    System.out.print("\n---Lecture Schedule---");
                    System.out.print("\n"+course.printSchedule(course.getLectureSchedule()));
                    System.out.print("\n---Lab Schedule---");
                    System.out.print("\n"+course.printSchedule(course.getLabSchedule()));
                    System.out.print("\n---Tutorial Schedule---");
                    System.out.print("\n"+course.printSchedule(course.getTutorialSchedule()));
                    System.out.print("\n=============================");
                    fieldIsValid = false;
                    while(!fieldIsValid) {
                        System.out.println("\nAre you sure to proceed with dropping this course index? ('Y' or 'N')");
                        input = sc.nextLine();
                        if (input.toLowerCase().equals("cancel")) {
                            System.out.println("\nDropping Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                            sc.nextLine();
                            return;
                        }
                        if (input.toUpperCase().equals("Y")) {
                            fieldIsValid = true;
                        }else if (input.toUpperCase().equals("N")){
                            System.out.println("\nDropping Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                            sc.nextLine();
                            return;

                        }else {
                            System.out.println("\nInvalid Input please try again!");
                        }
                    }
                    //remove the course
                    String message = "";                            
                    message = student.dropStudentCourse(course);
                    System.out.println(message);
                    System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
                    sc.nextLine();
                    return; 
                }
                else {
                	//course is not taken by you
                    System.out.println("\nCourse dropping failed. Selected course is not taken by you!! Press the \"ENTER\" key to be directed back to the previous menu!");
                    sc.nextLine();
                    return;
                }
        	}
            else {
            	System.out.println("\nThere are no records of course index entered. Press the \"ENTER\" key to be directed back to the previous menu!");
                sc.nextLine();
                return;
            }	
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void printRegisteredCourses(Student student) {
        try{
            Scanner sc = new Scanner(System.in);
            String registeredCourses = student.printCourseList();
            System.out.println("=====Registered Courses=====");
            System.out.print(registeredCourses);
            System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkCourseVacancy() {
        Scanner sc = new Scanner(System.in);
        String courseIndex="";
        Course course = new Course();
        System.out.println("\nStarting Check Course Vacancy Process: (Enter \"cancel\" to cancel process) ");
        System.out.println("Please enter course index:");
        courseIndex = sc.nextLine();
        if(courseIndex.toLowerCase().equals("cancel")){
            System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }
        if(course.isIndexTaken(courseIndex)){
            int vacancy = course.courseIndexVacancy(courseIndex);
            System.out.println("Course vacancy of index '"+courseIndex+"' is "+vacancy);
            System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }else{
            System.out.println("\nThere are no records of course index entered. Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }
    }

    public static void changeIndex(Student student) {
        Scanner sc = new Scanner(System.in);
        String courseIndex, newCourseIndex ="";
        Course course = new Course();
        System.out.println("\nStarting Change Index Number of Course Process: (Enter \"cancel\" to cancel process) ");
		try {
	        String registeredCourses;
			registeredCourses = student.printCourseList();
			System.out.println("=====Registered Courses=====");
	        System.out.print(registeredCourses);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        System.out.println("\nPlease enter current index no.:");
        courseIndex = sc.nextLine();
        
        if(courseIndex.toLowerCase().equals("cancel")){
            System.out.println("\n Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }
            //checks if index exists in system, if true, course index is in system

        	try {
				if(student.courseIndexTakenByStudent(courseIndex)){
					System.out.println("Current index: " + courseIndex);
					System.out.println("Please enter new index no. to change to:");
					newCourseIndex = sc.nextLine();
				    
				    if(newCourseIndex.toLowerCase().equals("cancel")){
				        System.out.println("\nProcess Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
				        sc.nextLine();
				        return;
				    }

					if(!student.courseIndexTakenByStudent(newCourseIndex) &&  (course.retrieveCourseByIndex(courseIndex).getCourseCode().equals(course.retrieveCourseByIndex(newCourseIndex).getCourseCode()))){ 
						// add condition that both course indexes must have the same course code
						 
						System.out.println("New index: " + newCourseIndex);
						// check if index has vacancies & check if new index timetable clashes
						try {
							if ((course.courseIndexVacancy(newCourseIndex) > 0) && (!student.hasClashingSchedule(course.retrieveCourseByIndex(newCourseIndex))) ) {
								Course tempCourse1 = course.retrieveCourseByIndex(courseIndex);
								System.out.println("\nIndex " + courseIndex);
								System.out.print("\n---Lecture Schedule---");
				                System.out.print("\n"+tempCourse1.printSchedule(tempCourse1.getLectureSchedule()));
				                System.out.print("\n---Lab Schedule---");
				                System.out.print("\n"+tempCourse1.printSchedule(tempCourse1.getLabSchedule()));
				                System.out.print("\n---Tutorial Schedule---");
				                System.out.print("\n"+tempCourse1.printSchedule(tempCourse1.getTutorialSchedule()));
				                
								Course tempCourse2= course.retrieveCourseByIndex(newCourseIndex);
								System.out.println("\nIndex " + newCourseIndex);
								System.out.print("\n---Lecture Schedule---");
				                System.out.print("\n"+tempCourse2.printSchedule(tempCourse2.getLectureSchedule()));
				                System.out.print("\n---Lab Schedule---");
				                System.out.print("\n"+tempCourse2.printSchedule(tempCourse2.getLabSchedule()));
				                System.out.print("\n---Tutorial Schedule---");
				                System.out.print("\n"+tempCourse2.printSchedule(tempCourse2.getTutorialSchedule()));
				                
								System.out.println("\nPress Y to confirm");
								char c = sc.next().charAt(0);
						        sc.nextLine();
								if (c == 'Y'||c == 'y') {
									System.out.println("\nChanging index no...");
									course = course.retrieveCourseByIndex(newCourseIndex);
									course.setCourseIndex(newCourseIndex);
							        String message = student.changeIndexForStudent(course);
							        System.out.println(message);
							        System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
							        sc.nextLine();
							        return;
									}
								else {
									System.out.println("\nProcess cancelled. Press the \"ENTER\" key to be directed back to the previous menu!");
									sc.nextLine();
									return;
									}
							}
							else if((course.courseIndexVacancy(newCourseIndex) <= 0)) {
							    System.out.println("\nIndex has no vacancies");
							    System.out.println("\nProcess cancelled. Press the \"ENTER\" key to be directed back to the previous menu!");
							    sc.nextLine();
							    return;
							}
							else if(student.hasClashingSchedule(course.retrieveCourseByIndex(newCourseIndex))) {
							    System.out.println("\nSchedule clashes");
							    System.out.println("\nProcess cancelled. Press the \"ENTER\" key to be directed back to the previous menu!");
							    sc.nextLine();
							    return;
							}
						} catch (IOException e) {
							e.printStackTrace();
						}

					}

					System.out.println("Either Course index entered is taken already by the student or course index is for a different course code");
				    System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
				    sc.nextLine();
				    return;
				}else{
				    System.out.println("\nEither student is not registered for that course index, course index is for a different course code, or new course index does not exist. \nPress the \"ENTER\" key to be directed back to the previous menu!");
				    sc.nextLine();
				    return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
}

    public static void swapIndex(Student student) {
    	LoginController logincontroller = new LoginController();
		String username, password;
		boolean verifylogin;
		String courseIndex1="";
		String courseIndex2="";
    	Scanner sc = new Scanner(System.in);
    	
    	
    try{System.out.println("\nSwapping Index with classmate...(Enter \"cancel\" to cancel process) ");
    	System.out.println("\nPlease enter YOUR index to be swapped: ");
    	courseIndex1 = sc.nextLine();
    	
    	if (courseIndex1.toLowerCase().equals("cancel")) {
    		System.out.println("\nSwapping Index Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
    		sc.nextLine();
    		return;
    	}
    	
    	System.out.println("Enter Peer's Username:");
		username = sc.next();
		Console con = System.console();
		if (con == null) { //eclipse doesnt support input masking
			System.out.println("Enter Peer's Password:");
			password = sc.next();
	    } 
		else {
			char[] ch = con.readPassword( "Enter Peer's password: ");
			password = new String(ch);
		}
		verifylogin = logincontroller.verifylogin(username,password);
		
		if(verifylogin == true) {
			System.out.println("Verified.");
			System.out.println("\nPlease enter PEER's index to be swapped: ");
	    	courseIndex2 = sc.next();
	    	
			StudentUI studentUI = new StudentUI(logincontroller.getStudent(username));
			Student student2= new Student();
			student2=studentUI.getStudent();
			Course course1 = new Course();
			Course course2 = new Course();
			course1 = course1.retrieveCourseByIndex(courseIndex1);//CourseIndexTakenByFirstStudent
			course2 = course2.retrieveCourseByIndex(courseIndex2); //CourseIndexTakenBySecondStudent
			
			if(student.courseIndexTakenByStudent(courseIndex1)&& student2.courseIndexTakenByStudent(courseIndex2)){
				if (course1.getCourseCode().equals(course2.getCourseCode())) {
					//Swapping of index happens here
					System.out.println("\n\nStudent: " + student.getName() + "," + student.getMatricnumber());
					System.out.println("Index " + courseIndex1);
					System.out.print("\n---Lecture Schedule---");
	                System.out.print("\n"+course1.printSchedule(course1.getLectureSchedule()));
	                System.out.print("\n---Lab Schedule---");
	                System.out.print("\n"+course1.printSchedule(course1.getLabSchedule()));
	                System.out.print("\n---Tutorial Schedule---");
	                System.out.print("\n"+course1.printSchedule(course1.getTutorialSchedule()));
	                
	                System.out.println("\n\nStudent: " + student2.getName() + "," + student2.getMatricnumber());
					System.out.println("Index " + courseIndex2);
					System.out.print("\n---Lecture Schedule---");
	                System.out.print("\n"+course2.printSchedule(course2.getLectureSchedule()));
	                System.out.print("\n---Lab Schedule---");
	                System.out.print("\n"+course2.printSchedule(course2.getLabSchedule()));
	                System.out.print("\n---Tutorial Schedule---");
	                System.out.print("\n"+course2.printSchedule(course2.getTutorialSchedule()));
	                
	                
	                System.out.println("\nConfirm to swap index number? Press 'Y' to confirm");
	            	char c = sc.next().charAt(0);
			        sc.nextLine();
					if (c == 'Y'||c == 'y') {
						String message1=student.changeIndexForStudent(course2);
						String message2=student2.changeIndexForStudent(course1);
						if (message1=="index changed successfully." && message2=="index changed successfully.") {
							System.out.print("\n"+ student.getMatricnumber()+ ", CourseIndex: "+ courseIndex1 + " is swapped with " + student2.getMatricnumber() + ",Course Index:"+ courseIndex2 );
							System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
							sc.nextLine();
							return;
						}
						else {System.out.println("FileNotFound.");}
					}
					else {
						System.out.println("\nProcess cancelled. Press the \"ENTER\" key to be directed back to the previous menu!");
						sc.nextLine();
						
						}
				}
					
			
				else {
					System.out.println("Two indexes do not belong to same course. Please try again.");
					swapIndex(student);
					}
			}
			else {
				System.out.println("Please enter indexes that you and your peer are registered in.Please try again");
				swapIndex(student);
			}
		}
		else {
			System.out.println("Login Failed. Please try again.");
			swapIndex(student);
			}
		
    	
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
