package control;

import entity.Course;
import entity.Student;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * StudentController contains the logic to realise use case of the options
 * given to the student in the StudentUI.
 */
public class StudentController {
    
	/**
	   * Adds a new course not found in the student's record
	   * by asking the user for course index as input.
	   * Ensures that the course either exists or does not
	   * clash with student's schedule.
	   * 
	   * @param student				student object
	   */
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
	
	/**
	   * Drops an existing course found in the student's record
	   * by asking the user for course index as input.
	   * Updates the database according to the student's request.
	   * 
	   * @param student				student object
	   */
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
    
    /**
	   * Prints out registered courses under the student
	   * found in the database
	   * 
	   * @param student				student object
	   */
    public static void printRegisteredCourses(Student student) {
        try{
            Scanner sc = new Scanner(System.in);
            String registeredCourses = student.printCourseList();
            System.out.println("=====Registered Courses=====");
            System.out.print(registeredCourses);
            System.out.println("\n=============================");
            ArrayList<Course> courses = student.retrieveRegisteredCourses();
            for (Course course :courses) {
            	System.out.println("\nCourse code: " + course.getCourseCode() + " Course index: " + course.getCourseIndex());
            	System.out.println();
            	System.out.print("\n---Lecture Schedule---");
                System.out.print("\n"+course.printSchedule(course.getLectureSchedule()));
                System.out.print("\n---Lab Schedule---");
                System.out.print("\n"+course.printSchedule(course.getLabSchedule()));
                System.out.print("\n---Tutorial Schedule---");
                System.out.print("\n"+course.printSchedule(course.getTutorialSchedule()));
                System.out.println("\n=============================");
            }
            System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
	   * Checks the number of vacancies in a course.
	   * 
	   */
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
    
    /**
	   * Changing of existing index found in the student's
	   * database and ensure that the new index the student
	   * is changing to, belongs to the same course code.
	   * Ensure that the new course index do not clash with
	   * the student's schedule as well.
	   * 
	   * @param student				student object
	   */
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
								System.out.println("\nIndex " + courseIndex + ":");
								System.out.print("\n---Lecture Schedule---");
				                System.out.print("\n"+tempCourse1.printSchedule(tempCourse1.getLectureSchedule()));
				                System.out.print("\n---Lab Schedule---");
				                System.out.print("\n"+tempCourse1.printSchedule(tempCourse1.getLabSchedule()));
				                System.out.print("\n---Tutorial Schedule---");
				                System.out.print("\n"+tempCourse1.printSchedule(tempCourse1.getTutorialSchedule()));
				                System.out.println();
				                
								Course tempCourse2= course.retrieveCourseByIndex(newCourseIndex);
								System.out.println("\nIndex " + newCourseIndex + ":");
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
									//course.setCourseIndex(newCourseIndex);
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

					System.out.println("Either Course index entered is taken already by the student or course index is for a different course code or course index doesnt exist");
				    System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
				    sc.nextLine();
				    return;
				}else{
				    System.out.println("\nStudent is not currently registered for that course index.  \nPress the \"ENTER\" key to be directed back to the previous menu!");
				    //Course index could be for a different course code, or new course index does not exist.
				    sc.nextLine();
				    return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
}
    
    /**
	   * Swaps index with an existing student found in the database.
	   * Done by keying in the other student's username and password.
	   * Ensure that both the index are registered by both students 
	   * and that the index belong to the same course code.
	   * 
	   * @param student				student object
	   */
    public static void swapIndex(Student student) {
    	LoginController logincontroller = new LoginController();
		String username, password;
		boolean verifylogin;
		String courseIndex1="";
		String courseIndex2="";
    	Scanner sc = new Scanner(System.in);
    	
    	
	    try{System.out.println("\nSwapping Index with classmate...(Enter \"cancel\" to cancel process) ");
	    	System.out.println("\nPlease enter YOUR index to be swapped: ");
	    	
	    	while (true) {
	    		courseIndex1 = sc.nextLine();
	    		if (courseIndex1.toLowerCase().equals("cancel")) {
		    		System.out.println("\nSwapping Index Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
		    		break;
		    	}
	    		else if (courseIndex1.isBlank()) {
		    		System.out.println("Please do not leave blank entries, please enter course index again");
		    		continue;
		    	}
	    		else if (student.isCourseIndexAccepted(courseIndex1) != true) {
	    			if (student.isCourseIndexOnWaitlist(courseIndex1) == true) {
	        			System.out.println("You are currently on waitlist for this index. Please enter indexes that you are already accepted in.");
	    				continue;
	        		}
	    			else {
	    				System.out.println("Please enter index that you are registered in. Please try again!");
	    				continue;
	    			}
	    		}
	    		break;
	    	}
	    	
	    	if (courseIndex1.toLowerCase().equals("cancel")) {
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
			
			
			//Checks if the user trys to login to his/her own account again
			if (!username.equals(student.getUsername())) {
				verifylogin = logincontroller.verifylogin(username,password);
			}
			
			else { verifylogin=false; }
			
			if(verifylogin == true) {
				System.out.println("Verified.");
				
			
				Student student2= logincontroller.getStudent(username);
				Course course1 = new Course();
				Course course2 = new Course();
				
				System.out.println("\nPlease enter PEER's index to be swapped: ");
		    	
		    	while (true) {
		    		sc = new Scanner(System.in);
		    		courseIndex2 = sc.nextLine();
		    		if (courseIndex2.toLowerCase().equals("cancel")) {
			    		System.out.println("\nSwapping Index Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
			    		break;
			    	}
		    		else if (courseIndex2.isBlank()) {
			    		System.out.println("Please do not leave blank entries, please enter course index again");
			    		continue;
			    	}
		    		else if (student2.isCourseIndexAccepted(courseIndex2) != true) {
		    			if (student2.isCourseIndexOnWaitlist(courseIndex2) == true) {
		        			System.out.println("Your peer is currently on waitlist for this index. Please enter indexes that your peer is already accepted in.");
		    				continue;
		        		}
		    			else {
		    				System.out.println("Please enter index that your peer is registered in. Please try again!");
		    				continue;
		    			}
		    		}
		    		break;
		    	}
		    	if (courseIndex2.toLowerCase().equals("cancel")) {
		    		return;
		    	}
				
				course1 = course1.retrieveCourseByIndex(courseIndex1);//CourseIndexTakenByFirstStudent
				course2 = course2.retrieveCourseByIndex(courseIndex2); //CourseIndexTakenBySecondStudent
				
				//Make sure that 2 courseIndexes are not the same 
				if (courseIndex1.compareTo(courseIndex2)!=0) {
					if(student.courseIndexTakenByStudent(courseIndex1)&& student2.courseIndexTakenByStudent(courseIndex2)){
						if (course1.getCourseCode().equals(course2.getCourseCode())) {
							if (!student.hasClashingSchedule(course2) && !student2.hasClashingSchedule(course1)){
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
								System.out.println("Swap in indexes will result in clashes in schedule with other courses.Please check and try again.");
								swapIndex(student);
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
					System.out.println("The course indexes are the same.Please try again.");
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
