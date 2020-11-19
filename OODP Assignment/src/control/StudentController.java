package control;

import entity.Course;
import entity.PasswordManager;
import entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("Please enter current index no.:");
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

					if(!student.courseIndexTakenByStudent(newCourseIndex)){
						
						System.out.println("New index: " + newCourseIndex);
						// check if index has vacancies & check if new index timetable clashes
						try {
							if ((course.courseIndexVacancy(newCourseIndex) > 0) && (!student.hasClashingSchedule(course.retrieveCourseByIndex(newCourseIndex))) ) {
								Course tempCourse1 = course.retrieveCourseByIndex(courseIndex);
								System.out.println("Index " + courseIndex);
								System.out.print("\n---Lecture Schedule---");
				                System.out.print("\n"+tempCourse1.printSchedule(tempCourse1.getLectureSchedule()));
				                System.out.print("\n---Lab Schedule---");
				                System.out.print("\n"+tempCourse1.printSchedule(tempCourse1.getLabSchedule()));
				                System.out.print("\n---Tutorial Schedule---");
				                System.out.print("\n"+tempCourse1.printSchedule(tempCourse1.getTutorialSchedule()));
				                
								Course tempCourse2= course.retrieveCourseByIndex(newCourseIndex);
								System.out.println("Index " + newCourseIndex);
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					System.out.println("Course index entered is taken already by the student");
				    System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
				    sc.nextLine();
				    return;
				}else{
				    System.out.println("\nEither student is not registered for that course index or course index does not exist. \nPress the \"ENTER\" key to be directed back to the previous menu!");
				    sc.nextLine();
				    return;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
}

    public static void swapIndex(Student student) {
    }
}
