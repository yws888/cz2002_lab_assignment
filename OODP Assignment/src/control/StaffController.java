package control;

import entity.AccessPeriod;
import entity.Course;
import entity.PasswordManager;
import entity.Student;
import entity.Ultility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Staffcontroller is the logic to realise use case of the options
 * given to the admin in the StaffUI.
 */
public class StaffController {
	
	/**
	   * Constructor for student object.
	   *  
	   * Adds a new student not found in the database
	   * hash the password of the student so as to protect data
	   * admin keys in information of new student into database.
	   * 
	   * @throws InterruptedException
	   */
    public static void addStudent() throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        String username, password, name, matriculationNumber, gender = "", nationality;
        System.out.println("\nStarting Add Student Process: (Enter \"cancel\" to cancel process) ");
        System.out.println("Please enter student's account username:");
        username = sc.nextLine();
        if(username.toLowerCase().equals("cancel")){
            return;
        }
        System.out.println("Please enter student's account password:");
        password = sc.nextLine();
        if(password.toLowerCase().equals("cancel")){
            return;
        }
        System.out.println("Please enter student's name:");
        name = sc.nextLine();
        if(name.toLowerCase().equals("cancel")){
            return;
        }
        System.out.println("Please enter student's matric number:");
        matriculationNumber = sc.nextLine();
        if(matriculationNumber.toLowerCase().equals("cancel")){
            return;
        }

        while(!(gender.equals("Male") || gender.equals("Female"))) {
            System.out.println("Please enter student's gender: ('Male' or 'Female')");
            gender = sc.nextLine();
            if(gender.toLowerCase().equals("cancel")){
                return;
            }
        }
        System.out.println("Please enter student's nationality:");
        nationality = sc.nextLine();
        byte[] salt = PasswordManager.getRandomSalt();
        char[] passwordEntered = password.toCharArray();
        byte[] hashedpassword = PasswordManager.hash(passwordEntered, salt);
        Student student = new Student();
        student.createStudent(username, PasswordManager.hexEncoder(hashedpassword), PasswordManager.hexEncoder(salt), name, matriculationNumber, gender, nationality);
        System.out.println(student.printStudentList());
        System.out.println("Add Student Operation Completed!! Press the \"ENTER\" key to be directed back to STARS main menu!");
        sc.nextLine();

    }
    
    /**
	   * Edits students access period in terms of the access date
	   * as well as access time.
	   * 
	   */
    public static void editAccessPeriod() {
    	AccessPeriod accessPeriod = AccessPeriod.retrieveAccessPeriod();
    	System.out.println("Access period Dates are: " +accessPeriod.getStartDate() + " to " +  accessPeriod.getEndDate());
		System.out.println("Access period Time is: " +accessPeriod.getStartTime() + " to " +  accessPeriod.getEndTime());
        Scanner sc = new Scanner(System.in);
		String startDate, startTime;
		String endDate, endTime;
		while(true) {
			System.out.println("Please enter Access Period Start Date: ");
			System.out.println("type 'cancel' to exit");
			System.out.println("(Enter in this format yyyy-MM-dd)");
			System.out.println("e.g. 2020-08-08");
			startDate = sc.nextLine();
			if(startDate.toLowerCase().equals("cancel")){
	            System.out.println("\n Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
	            sc.nextLine();
	            //sc.close();
	            return;
	        }
			
			System.out.println("Please enter Access Period End Date: ");
			System.out.println("(Enter in this format yyyy-MM-dd)");
			System.out.println("e.g. 2020-08-28");

			endDate = sc.nextLine();
			if(endDate.toLowerCase().equals("cancel")){
	            System.out.println("\n Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
	            sc.nextLine();
	            //sc.close();
	            return;
	        }
			
			System.out.println("Please enter Access Period Start Time: ");
			System.out.println("(Enter in this format  HH:mm)");
			System.out.println("e.g. 08:08");

			startTime = sc.nextLine();
			if(startTime.toLowerCase().equals("cancel")){
	            System.out.println("\n Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
	            sc.nextLine();
	            //sc.close();
	            return;
	        }

			System.out.println("Please enter Access Period End Time: ");
			System.out.println("(Enter in this format  HH:mm)");
			System.out.println("e.g. 18:08");

			endTime = sc.nextLine();
			if(endTime.toLowerCase().equals("cancel")){
	            System.out.println("\n Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
	            sc.nextLine();
	            //sc.close();
	            return;
	        }
			    
			/*if (startDate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})") && startTime.matches("([0-9]{2}):([0-9]{2})") &&
					endDate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})") && endTime.matches("([0-9]{2}):([0-9]{2})")	)*/
			
				if (Ultility.isValidFormat("HH:mm", startTime) && Ultility.isValidFormat("HH:mm", endTime)&& 
						Ultility.isValidFormat("yyyy-MM-dd", startDate) && Ultility.isValidFormat("yyyy-MM-dd", endDate)
						) {
					if (Ultility.isValidTimeRange(startDate,endDate,startTime,endTime) && Ultility.isValidTimeInput(startTime, endTime)) {
						AccessPeriod.createAccessPeriod(startDate, endDate, startTime,  endTime);
						System.out.println("Access time is updated Successfully!\n Press ENTER to return to previous menu");
						sc.nextLine();
						return;}
					
					else {System.out.println("Starting Access Date is later than Ending Access Date. Please try again.");}
					
				}
				
				else 
					System.out.println("Wrong format try again");
    }
		
    }
    
    /**
	   * Adds a new course not found in the database
	   * and set the course's vacancy size as well as
	   * the additional information of the new course.
	   * 
	   */
    public static void addCourse(){
        System.out.println("\nStarting Add Course Process: (Enter \"cancel\" to cancel process) ");
        Scanner sc = new Scanner(System.in);
        String course_code,course_name, au="", school, index, input="";
        ArrayList<String> indexList = new ArrayList<String>(), vacancyList = new ArrayList<String>();
        //int vacancy;
        boolean validation1=true, validation2=true;

        System.out.println("Please enter the course code:");
        course_code = sc.nextLine();
        if(course_code.toLowerCase().equals("cancel")){
            System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }
        System.out.println("Please enter the name for this course:");
        course_name = sc.nextLine();
        if(course_name.toLowerCase().equals("cancel")){
            System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }

        System.out.println("Please enter the number of AUs for this course:");
        while(!Ultility.isNumeric(au.trim())) {
            au = sc.nextLine();
            if (au.toLowerCase().equals("cancel")) {
                System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                sc.nextLine();
                return;
            }
            if(!Ultility.isNumeric(au)){
                System.out.println("Invalid Input, please enter again" );
            }
        }

        System.out.println("Please enter School of the Course:");
        school = sc.nextLine();
        if(school.toLowerCase().equals("cancel")){
            System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }
        input = "";
        while(validation1) {
            System.out.println("Please enter the list of indexes that the course has (Eg. '64001,64003'):");
            index = sc.nextLine();
            if (index.toLowerCase().equals("cancel")) {
                System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                sc.nextLine();
                return;
            }
            index = index.replaceAll("\\s+","");
            indexList = new ArrayList<>(Arrays.asList(index.split(",")));
            System.out.println("You have entered the following indexes:");
            for(int i = 0; i<indexList.size();i++){
                System.out.println(indexList.get(i));
            }
            validation2 = true;
            while(validation2) {
                System.out.println("Are you sure to proceed with these indexes? ('Y' or 'N')");
                input = sc.nextLine();
                if (input.toLowerCase().equals("cancel")) {
                    System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                    sc.nextLine();
                    return;
                }
                if (input.toUpperCase().equals("Y")) {
                    validation1 = false;
                    validation2 = false;
                }
                if (input.toUpperCase().equals("N")){
                    validation2 = false;
                }
            }
        }
        System.out.println("Please enter vacancy space for each class index:");

        for(int i = 0; i<indexList.size();i++){
            String vacancyInput = "";
            while(!Ultility.isNumeric(vacancyInput.trim())) {
                System.out.println("Class vacancy for index number " + indexList.get(i) + " :");
                vacancyInput = sc.nextLine();
                if (vacancyInput.toLowerCase().equals("cancel")) {
                    System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                    sc.nextLine();
                    return;
                }
                if(Ultility.isNumeric(vacancyInput.trim())) {
                    vacancyList.add(vacancyInput.trim());
                }else{
                    System.out.println("Invalid Input, please enter again" );
                }
            }
        }
        //Summary
        System.out.println("=====SUMMARY=====");
        System.out.print("Course Code:\t\t"+course_code);
        System.out.print("\nCourse Name:\t\t"+course_name);
        System.out.print("\nAU:\t\t"+au);
        System.out.print("\nSchool:\t\t"+school);
        System.out.print("\n---Index List---");
        for(int i = 0; i<indexList.size();i++){
            System.out.print("\n"+(i+1)+". "+indexList.get(i)+"\t\t"+"Vacancy: "+vacancyList.get(i));
        }
        validation2 = true;
        input = "";
        while(validation2) {
            System.out.println("\nAre you sure to proceed with adding course? ('Y' or 'N')");
            input = sc.nextLine();
            if (input.toLowerCase().equals("cancel")) {
                System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                sc.nextLine();
                return;
            }
            if (input.toUpperCase().equals("Y")) {
                validation1 = false;
                validation2 = false;
            }
            if (input.toUpperCase().equals("N")){
                System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                sc.nextLine();
                return;

            }
        }
        Course course = new Course();
        String message = course.addCourses(course_code,course_name,school,au,indexList,vacancyList);
        System.out.println(message);
        System.out.println("\nPress the \"ENTER\" key to be directed back to STARS main menu!");
        sc.nextLine();
    }
    
    
    /**
	   * Update the class schedule for both existing and
	   * new courses in terms of lecture, lab and tutorial
	   * Ensures that the time format follows the right format.
	   * 
	   */
    public static void updateClassSchedule(){
        Scanner sc = new Scanner(System.in);
        Course course = new Course();
        String input="";
        System.out.println("\nStarting Update Course Class Schedule Process: (Enter \"cancel\" to cancel process) ");
        System.out.println("Please enter Course Index you want to update: ");
        boolean fieldIsValid = false;
        input = sc.nextLine();
        if(input.toLowerCase().equals("cancel")){
            System.out.println("\nUpdate Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }
        //one for exists, one for doesn't exist
        if(course.isIndexTaken(input)){
            //instantiate course object
            try {
                course = course.retrieveCourseByIndex(input);
            } catch (IOException e) {
                System.out.println("\nError Retrieving Course!! Press the \"ENTER\" key to be directed back to the previous menu!");
                sc.nextLine();
                return;
            }
            //Print Summary of the schedule
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
            System.out.print("\nUpdate and Change:");
            System.out.print("\n1. Lecture Schedule");
            System.out.print("\n2. Lab Schedule");
            System.out.print("\n3. Tutorial Schedule");
            System.out.println("\nPlease select an option:");
            input = sc.nextLine();
            int update_option;
            while(true){
                if(Ultility.isNumeric(input)) {
                    update_option = Integer.parseInt(input); //remember this option
                    if (update_option >= 1 && update_option <= 3){
                        String timeStart="", timeEnd="", week, classType="", classVenue;
                        int day = 0;
                        week = "";
                        while (!(week.equals("ODD") || week.equals("EVEN") || week.equals("WEEKLY"))) {
                            System.out.println("Please enter whether this class is on 'ODD' week, 'EVEN' week, or 'WEEKLY'(Case Sensitive): ");
                            week = sc.nextLine();
                            if (!(week.equals("ODD") || week.equals("EVEN") || week.equals("WEEKLY"))) {
                                System.out.println("Invalid Input, please try again!");
                            }
                        }

                        System.out.println("Please enter which day of the week this class will be on (eg. Enter '1' for Monday, '7' for Sunday): ");
                        fieldIsValid = false;
                        while (!fieldIsValid) {
                            input = sc.nextLine();
                            if (Ultility.isNumeric(input)) {
                                day = Integer.parseInt(input);
                                if (day >= 1 && day <= 7) {
                                    fieldIsValid = true;
                                } else {
                                    System.out.println("Invalid Input, please try again!");
                                }
                            } else {
                                System.out.println("Invalid Input, please try again!");
                            }
                        }
                        fieldIsValid = false;
                        while (!fieldIsValid) {
                            System.out.println("Please enter what time the class will start: (eg. 18:00, 09:00)");
                            String pattern = "HH:mm";
                            timeStart = sc.nextLine();
                            if (Ultility.isValidFormat(pattern, timeStart)) {
                                fieldIsValid = true;
                            } else {
                                System.out.println("Invalid Input, please try again!");
                            }
                        }
                        fieldIsValid = false;
                        while (!fieldIsValid) {
                            System.out.println("Please enter what time the class will end: (eg. 18:00, 09:00)");
                            String pattern = "HH:mm";
                            timeEnd = sc.nextLine();
                            if (Ultility.isValidFormat(pattern, timeEnd)) {
                                fieldIsValid = true;
                            } else {
                                System.out.println("Invalid Input, please try again!");
                            }
                        }
                        System.out.println("Please enter the class venue: ");
                        classVenue = sc.nextLine();
                        Course updatedCourse = course;
                        String scheduleParameters = week+","+day+","+timeStart+","+timeEnd+","+classVenue;
                        switch(update_option){
                            case 1: classType = "Lecture";
                                updatedCourse.setLectureSchedule(scheduleParameters);
                                break;
                            case 2: classType = "Lab";
                                updatedCourse.setLabSchedule(scheduleParameters);
                                break;
                            case 3: classType = "Tutorial";
                                updatedCourse.setTutorialSchedule(scheduleParameters);
                                break;
                        }

                        System.out.println("=====SUMMARY=====");
                        System.out.print("Class Type:\t\t" + classType);
                        System.out.print("\nClass Frequency:\t\t" + week);
                        System.out.print("\nDay of the week:\t\t" + day);
                        System.out.print("\nStart Time:\t\t" + timeStart);
                        System.out.print("\nEnd Time:\t\t" + timeEnd);
                        System.out.print("\nClass Venue:\t\t" + classVenue);

                        fieldIsValid = false;
                        while(!fieldIsValid) {
                            System.out.println("\nAre you sure to proceed with updating Course Info? ('Y' or 'N')");
                            input = sc.nextLine();
                            if (input.toLowerCase().equals("cancel")) {
                                System.out.println("\nUpdating Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                                sc.nextLine();
                                return;
                            }
                            if (input.toUpperCase().equals("Y")) {
                                fieldIsValid = true;
                            }else if (input.toUpperCase().equals("N")){
                                System.out.println("\nUpdating Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                                sc.nextLine();
                                return;

                            }else {
                                System.out.println("\nInvalid Input please try again!");
                            }
                        }

                        //check whether time is valid
                        if(Ultility.isValidTimeInput(timeStart,timeEnd)){
                            String message = updatedCourse.updateCourse();
                            System.out.println("=====SUMMARY=====");
                            System.out.print("Course Code:\t\t"+updatedCourse.getCourseCode());
                            System.out.print("\nCourse Name:\t\t"+updatedCourse.getCourseName());
                            System.out.print("\nAU:\t\t"+updatedCourse.getNoOfAUs());
                            System.out.print("\nSchool:\t\t"+updatedCourse.getSchool());
                            System.out.print("\nIndex:\t\t"+updatedCourse.getCourseIndex());
                            System.out.print("\n---Lecture Schedule---");
                            System.out.print("\n"+updatedCourse.printSchedule(updatedCourse.getLectureSchedule()));
                            System.out.print("\n---Lab Schedule---");
                            System.out.print("\n"+updatedCourse.printSchedule(updatedCourse.getLabSchedule()));
                            System.out.print("\n---Tutorial Schedule---");
                            System.out.print("\n"+updatedCourse.printSchedule(updatedCourse.getTutorialSchedule()));
                            System.out.print("\n=============================\n");
                            System.out.println(message);
                        }else{
                            System.out.println("\nError Updating Course, inputted start time and end time is not valid! Press the \"ENTER\" key to be directed back to the previous menu!");
                            sc.nextLine();
                            return;
                        }

                        System.out.println("\nPress the \"ENTER\" key to be directed back to STARS main menu!");
                        sc.nextLine();
                        return;


                    }else{
                        System.out.println("Invalid input, please try again!");
                    }
                }else{
                    System.out.println("Invalid input, please try again!");
                }

            }


        }else{
            System.out.println("\nCourse Index not found. Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
        }


    }
    
    /**
	   * Courseconfiguration is the logic to realise use case 
	   * of the options when configuring the course information
	   * 
	   */
    public static void courseConfiguration() {
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n1. Add Course");
            System.out.println("2. Update Class Schedule of a Course");
            System.out.println("3. Update course vacancy:");
            System.out.println("0. Exit");
            System.out.print("\nEnter the number of your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    updateClassSchedule();
                    break;
                case 3:
                    updateCourseVacancy();
                    break;
                case 0: //quit
                    break;
                default:
                    System.out.println("Please select an option from 0-3");
                    System.out.println();
                    break;
            }
        } while (choice != 0);
        return;
    }
    
    /**
	   * Updates existing course vacancy and ensure that the
	   * target course vacancy to change to is larger than before.
	   * 
	   */
    private static void updateCourseVacancy() {
        Scanner sc = new Scanner(System.in);
        String courseIndex="",input="";
        Course course = new Course();
        Student student = new Student();
        System.out.println("\nStarting Update Course Vacancy Process: (Enter \"cancel\" to cancel process) ");
        System.out.println("Please enter course index:");
        courseIndex = sc.nextLine();
        if(courseIndex.toLowerCase().equals("cancel")){
            System.out.println("\nUpdate Course Vacancy Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }
        if(course.isIndexTaken(courseIndex)){
            try {
                course = course.retrieveCourseByIndex(courseIndex);
                int currentVacancy = course.getVacancy();
                System.out.println("Current Vacancy is now set at: "+currentVacancy);
                System.out.println("Please enter vacancy amount to increase to: ");
                input = sc.nextLine();
                while(!Ultility.isNumeric(input)){
                    System.out.println("Invalid input, please enter a valid vacancy amount to increase to:");
                    input = sc.nextLine();
                }
                int targetVacancy = Integer.parseInt(input);
                boolean fieldIsValid = false;
                while(!fieldIsValid) {
                    System.out.println("\nAre you sure to proceed with increasing vacancy amount from "+currentVacancy+" to "+targetVacancy+"? ('Y' or 'N')");
                    input = sc.nextLine();
                    if (input.toLowerCase().equals("cancel")) {
                        System.out.println("\nUpdate Course Vacancy Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                        sc.nextLine();
                        return;
                    }
                    if (input.toUpperCase().equals("Y")) {
                        fieldIsValid = true;
                    }else if (input.toUpperCase().equals("N")){
                        System.out.println("\nUpdate Course Vacancy Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
                        sc.nextLine();
                        return;

                    }else {
                        System.out.println("\nInvalid Input please try again!");
                    }
                }
                if(!(targetVacancy <= currentVacancy)){
                    //update vacancy
                    Course updatedCourse = course;
                    updatedCourse.setVacancy(targetVacancy);
                    String message = updatedCourse.updateCourse();
                    System.out.println(message);
                    //fill up spaces from waitlist
                    int vacancyIncrease = targetVacancy - currentVacancy;
                    message = student.removeStudentsFromWaitList(courseIndex, vacancyIncrease);
                    System.out.println(message);
                    System.out.println("\nUPress the \"ENTER\" key to be directed back to the previous menu!");
                    sc.nextLine();
                    return;
                }else{
                    System.out.println("Input vacancy must be more than the current set vacancy, unable to update vacancy.");
                    System.out.println("\nPress the \"ENTER\" key to be directed back to the previous menu!");
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
	   * Checks the course vacancy size by taking in the course
	   * index and prints out the course vacancy.
	   * 
	   */
    public static void checkAvailableSlot() {
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
            try {
				course = course.retrieveCourseByIndex(courseIndex);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Total size of index '"+courseIndex+"' is "+course.getVacancy());
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
	   * Prints the list of students with the course index
	   * as the input.
	   * 
	   */
    public static void printListByIndex() {
        //name gender nationality only
        Scanner sc = new Scanner(System.in);
        String courseIndex="";
        Course course = new Course();
        Student student = new Student();
        System.out.println("Please enter the course index to print student list:");
        courseIndex = sc.nextLine();
        if(courseIndex.toLowerCase().equals("cancel")){
            System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
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
            return;
        }else{
            System.out.println("\nThere are no records of course index entered. Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }
    }
    
    /**
	   * Prints the list of students with the course code
	   * as the input.
	   * 
	   */
    public static void printListByCourse() {
        //name gender nationality only
        Scanner sc = new Scanner(System.in);
        String courseCode="";
        Course course = new Course();
        Student student = new Student();
        System.out.println("Please enter the course code to print student list:");
        courseCode = sc.nextLine();
        if(courseCode.toLowerCase().equals("cancel")){
            System.out.println("\nAdd Course Process Cancelled!! Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }
        if(course.isCourseTaken(courseCode)){
            ArrayList<Student> studentList = new ArrayList<Student>();
            try {
                studentList = student.studentListByCourseCode(courseCode);
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
            return;
        }else{
            System.out.println("\nThere are no records of course code entered. Press the \"ENTER\" key to be directed back to the previous menu!");
            sc.nextLine();
            return;
        }


    }
}
