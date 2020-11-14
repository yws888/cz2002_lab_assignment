import java.util.ArrayList;
import java.util.Scanner;
//function 2: Just add a course dont care about components
//check if it has any exisiting of such courses

public class AddCourseUI{
	
	static Scanner sc = new Scanner(System.in);
	
	// rename accordingly to consistency [change if there is a need to]
	private ArrayList<String> facultyNameList;
	private ArrayList<String> courseCodeList;
	private ArrayList<String> courseNameList;
	private ArrayList<String> profNameList;
	private ArrayList<Integer> tutGroups;
	
	
	// get for to-be-added Course details
	public void getCourseDetails() {
		
		String newCourseCode, newCourseName, facultyName;
		int courseVacancy = 10;
	
		
		System.out.println("-------Current faculties are-------");
		// Discuss where faculty to be placed in course.java then change accordingly
		// ++facultyNameList = getFacultyNameList();
		// Printing of faculty name
		for(String fName : facultyNameList) {
			System.out.println(fName);
		}
		while(true)
		{
			System.out.println("Enter faculty name: ");
			facultyName = sc.nextLine().toUpperCase();
			
			//check if input data of faculty is correct
			if(checkFacName(facultyName)) {
				facultyNameList.add(facultyName);
				break;
			}
			// else continue back to asking for correct input
			else System.out.println("Wrong faculty Name");
		}
		
		// Discuss where faculty to be placed in course.java then change accordingly
		// ++ courseCodeList = getCourseCodeList(facultyName);
		// Print out current courses
		System.out.println("The current course codes are ");
		for(String currentCourseCode : courseCodeList) {
			System.out.println(currentCourseCode);
		}
		
		// Add new course code number
		System.out.println("Enter course code: ");
		// Following the format of NTU course code
		newCourseCode = sc.nextLine().toUpperCase();
		
		// Ensure course code used is not repeated
		if(checkCourseCode(newCourseCode)) {
			System.out.println("Course code already exists. \n Please try again"); 
		}else courseCodeList.add(newCourseCode);
		
		// Discuss where faculty to be placed in course.java then change accordingly
		// ++ courseNameList = getCourseNameList(facultyName);
		System.out.println("The current course names are ");
		for(String currentCourseName : courseNameList) {
			System.out.println(currentCourseName);
		}
		
		// Input new course name
		System.out.println("Enter course name: ");
		// Set according to NTU course name standard
		newCourseName = sc.nextLine().toUpperCase();
		
		// Ensure that new course name added is not the same as previous course names
		if(checkCourseName(newCourseName)) {
			System.out.println("Course name already exists. \n Please try again");
		}else courseNameList.add(newCourseName);
		
		
		// Adding a professor to the new course
		
		System.out.println("Choose the professor for the course");
		// Discuss where professor to be placed in course.java then change accordingly
		// ++ profNameList = getProfessorNameList(facultyName);
		// Print list of prof names
		for(String p : profNameList) {
			System.out.println(p);
		}
		
		
		
		// Adding the name of the professor to the course
		while(true)
		{
			System.out.println("Enter the professor's name: ");
			String ProfName = sc.nextLine();
			
			// Check if input is a valid professor name
			if(CheckProfName(ProfName)) {
				break;
			}
			// else continue back to asking for correct input
			else System.out.println("Enter correct professor.");
		}
		
		// Input Course Vacancy
		System.out.println("Enter course Vacancy");
		
		// Error handling if input is not a number
		if(sc.hasNextInt()) {
			courseVacancy = sc.nextInt();
		}else System.out.println("Enter only integers");
		
		// dummy scan changes from sc int to string
		String dummy = sc.nextLine(); 
		
		
		while(true) {
			// Ask if course is lectures only
			System.out.println("Lectures only? Y for Yes / N for No");
			char lectureInput = sc.nextLine().charAt(0);
	
			if(lectureInput == 'Y' || lectureInput == 'y') {
				// creation of course object
				// To return the course code, name vacancy as well as the professor assigned to it
				// for that specific course under the faculty it is assgined to
				// ++ passCourseDetails(facultyName, courseCode, courseName, courseVacancy, ProfName); 
				break;
			}
			
			// if not check tutorial
			else if (lectureInput == 'N' || lectureInput == 'n') {
				
				while(true) {
					int tutVacancy = 0;
					
					System.out.println("Is there tutorial? Y for Yes / N for No");
					char tutorialInput = sc.nextLine().charAt(0);
					
					if (tutorialInput == 'Y'|| tutorialInput == 'y') {
						
						System.out.println("What is the tutorial vacancy for each group?");	
						if(sc.hasNextInt()) {
							tutVacancy =  sc.nextInt(); // total tutorial vacancy > lecture size OK!
						}
						
						// Add the tutorial vacancy to the tutGroups array
						tutGroups = addTutorial(tutVacancy);
					
						dummy = sc.nextLine();
						
						// Check if there is lab for this course
						while(true) {
							System.out.println("Is there Lab? Y for Yes / N for No");
							char labInput = sc.nextLine().charAt(0);
							
							if(labInput == 'Y'|| labInput == 'y') {
								System.out.println("Lab group is same as Lecture group");
								// creation of course object, with tutorials and lab group
								// Same as the lectures only details but with the additional return value of
								// Tutorial groups and vacancies as well as has lab
								// ++passCourseDetailsWithTutorial(courseCode, courseName, facultyName,
								// courseVacancy, ProfName, tutGroups, true, tutVacancy);++
								break;
						
							}
							else if(labInput == 'N'|| labInput == 'n') {
								// creation of course object, with tutorials only
								// Pass the right values accordingly
								// Same as the lectures only details but with the additional return value of
								// Tutorial groups and vacancies as well as no lab
								// ++ passCourseDetailsWithTutorial(facultyName, courseCode, courseName, 
								// courseVacancy, ProfName, tutGroups, false, tutVacancy); ++
								break;
							}
							// Inform user to input Y/N only
							else System.out.println("Please Enter Y or N only");
						}
					}
					// Inform user to input Y/N only
					else System.out.println("Please Enter Y or N only");
				}
			}
			// Inform user to input Y/N only
			else System.out.println("Please Enter Y or N only");
		}
		
		// Finalized details - Print Output with newly added course and details
		System.out.println("Course added to System");
		System.out.println("The current Lists of courses in "+ facultyName + " is");
		printUpdatedCourses();
	}
	
	
	
	private ArrayList<Integer> addTutorial(int vacancy) {
		int groupNumber = 0;
		ArrayList<Integer> tutGroups = new ArrayList<Integer>();
		do {
			System.out.println("Enter tutorial group number, Enter -1 to quit");
			if(sc.hasNextInt()) {
				// Input the vacancy for each group number of the course's tutorial
				groupNumber = sc.nextInt();
				tutGroups.add(groupNumber);
			}
			// Error handling when input is not a number
			else System.out.println("Integers only");
		}while(groupNumber != -1);
		
		// Updated all tutorial goods with the input vacancy
		return tutGroups;
	}
	
	// Printing of updated course names
	public void printUpdatedCourses() {
		System.out.println("-----The current updated course names are-----");
		for(String updatedCourseName : courseNameList) {
			System.out.println(updatedCourseName);
		}
	}
	

	//Check Methods
	
	// Check if professor input is the same as the provided list of professors
	public boolean CheckProfName(String profName) {
		for(String PName: profNameList) {
			if(PName.equals(profName)) {
				return true;
			}
		}
		return false;
	}
	
	// Check if faculty input exists
	public boolean checkFacName(String facultyName) {
		for(String facName: facultyNameList) {
			if (facultyName.equals(facName)) {
				return true;
			}
		}
		return false;
	}
	
	// Check if new course code added exists already
	public boolean checkCourseCode(String courseCode) {
		for(String code: courseCodeList) {
			if(courseCode.equals(code))
				return true;
		}
		return false;
	}
	
	// Check if new course name added exists already
	public boolean checkCourseName(String courseName) {
		for(String name: courseNameList) {
			if(courseName.equals(name)) {
				return true;
			}
		}
		return false;
	}
}