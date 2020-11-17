package control;

import entity.Course;
import entity.PasswordManager;
import entity.Student;

import java.io.IOException;
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
                    if(!student.hasClashingSchedule(course)){
                        String message = "";
                        if(course.courseIndexVacancy(courseIndex) > 0){
                            //added to course, status ACCEPTED
                            message = student.enrollStudent(course, "ACCEPTED");
                        }else{
                            //0 vacancy, put in waitlist, status WAITLIST
                            message = student.enrollStudent(course, "WAITLIST");
                        }

                        System.out.println(message);
                        System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
                        sc.nextLine();
                        return;
                    }else{
                        //clashing schedule
                        System.out.println("\nCourse adding failed. Selected course clashes with your schedule!! Press the \"ENTER\" key to be directed back to the previous menu!");
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
            System.out.println("\nThere are no records of course code entered. Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;

        }
//
//
//        //if index has no vacancy, put on waitlist, else enter, do vacancy with math
//        System.out.println("Please enter student's matric number:");
//        matriculationNumber = sc.nextLine();
//        if(matriculationNumber.toLowerCase().equals("cancel")){
//            return;
//        }
//
//        while(!(gender.equals("Male") || gender.equals("Female"))) {
//            System.out.println("Please enter student's gender: ('Male' or 'Female')");
//            gender = sc.nextLine();
//            if(gender.toLowerCase().equals("cancel")){
//                return;
//            }
//        }
//        System.out.println("Please enter student's nationality:");
//        nationality = sc.nextLine();
//        byte[] salt = PasswordManager.getRandomSalt();
//        char[] passwordEntered = password.toCharArray();
//        byte[] hashedpassword = PasswordManager.hash(passwordEntered, salt);
//        Student student = new Student();
//        student.createStudent(username, PasswordManager.hexEncoder(hashedpassword), PasswordManager.hexEncoder(salt), name, matriculationNumber, gender, nationality);
//        System.out.println(student.printStudentList());
//        System.out.println("Add Student Operation Completed!! Press the \"ENTER\" key to be directed back to STARS main menu!");
//        sc.nextLine();

    }

    public static void dropCourse(Student student) {
    }

    public static void printRegisteredCourses(Student student) {
    }

    public static void checkCourseVacancy() {
    }

    public static void changeIndex(Student student) {
    }

    public static void swapIndex(Student student) {
    }
}
