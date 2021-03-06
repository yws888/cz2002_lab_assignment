package entity;

/**
 * Course carries the persistent information of the Course object
 * in the system.
 * 
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import exception.ExistingCourseException;


public class Course {
	
	  /**
	   * The name of the course, e.g. "Object Oriented Design and Programming"
	   */
	
	private String courseName;
	
	  /**
	   * The code of the course, e.g. CZ2002
	   */
	
	private String courseCode;
	
	  /**
	   * The school offering the course.
	   */
	
	private String school;
	
	  /**
	   * The number of AUs of the course.
	   */
	
	private int noOfAUs;
	
	/**
	   * The course Index number, e.g. 18001, 18002, etc.
	   */
	
	private String courseIndex;
	
	/**
	   * Maximum number of vacancies (not current vacancies)
	   */

	private int vacancy;

	/**
	 * String containing lab Schedule details, if any
	 */
	private String labSchedule;
	/**
	 * String containing tutorial Schedule details, if any
	 */
	private String tutorialSchedule;
	
	/**
	 * String containing lecture Schedule details
	   */
	
	private String lectureSchedule;


	/**
	   * Getter method for course name.
	   * 
	   * @return course name
	   */

	public String getCourseName() {
		return courseName;
	}


	/**
	   * Getter method for course code.
	   * 
	   * @return course code
	   */
	

	public String getCourseCode() {
		return courseCode;
	}

	/**
	   * Getter method for school offering the course.
	   * 
	   * @return school
	   */

	public String getSchool() {
		return school;
	}


	/**
	   * Getter method for no of AUs.
	   * 
	   * @return no Of AUs
	   */

	public int getNoOfAUs() {
		return noOfAUs;
	}

	
	/**
	   * Getter method for courseIndex
	   * 
	   * @return courseIndex
	   */

	public String getCourseIndex() {
		return courseIndex;
	}

	
	/**
	   * Getter method for Maximum number of vacancies (not current vacancies)
	   * 
	   * @return max vacancies
	   */


	public int getVacancy() {
		return vacancy;
	}

	/**
	 * Setter method for Maximum number of vacancies (not current vacancies)
	 * 
	 * @param vacancy			max vacancies
	 */
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}


	/**
	   * Getter method for String containing lab Schedule details, if any
	   * 
	   * @return labSchedule
	   */


	public String getLabSchedule() {
		return labSchedule;
	}

	
	/**
	 * Setter method for String containing lab Schedule details, if any, for the course object
	 * 
	 * @param labSchedule		lab Schedule
	 */
	public void setLabSchedule(String labSchedule) {
		this.labSchedule = labSchedule;
	}
	

	/**
	   * Getter method for String containing tutorial Schedule details, if any
	   * 
	   * @return tutorialSchedule
	   */

	public String getTutorialSchedule() {
		return tutorialSchedule;
	}
	
	

	/**
	 * Setter method for String containing tutorial Schedule details, if any, for the course object
	 * 
	 * @param tutorialSchedule			tutorial Schedule
	 */
	public void setTutorialSchedule(String tutorialSchedule) {
		this.tutorialSchedule = tutorialSchedule;
	}
	

	/**
	   * Getter method for String containing lecture Schedule details, if any
	   * 
	   * @return lectureSchedule
	   */

	public String getLectureSchedule() {
		return lectureSchedule;
	}
	
	/**
	 * Setter method for String containing lecture Schedule details for the course object
	 * 
	 * @param lectureSchedule	lecture Schedule
	 */

	public void setLectureSchedule(String lectureSchedule) {
		this.lectureSchedule = lectureSchedule;
	}

	/**
	 * Default Constructor for Course object
	 */
	public Course(){

	}
	
	

	/**
	 * Constructor for Course object
	 * 
	 * @param courseCode					course Code
	 * @param courseName					course Name
	 * @param school						school
	 * @param noOfAUs						number Of AUs
	 * @param courseIndex					course Index
	 * @param vacancy						vacancy
	 * @param labSchedule					lab Schedule
	 * @param tutorialSchedule				tutorial Schedule
	 * @param lectureSchedule				lecture Schedule
	 */
	public Course(String courseCode, String courseName, String school, int noOfAUs, String courseIndex, int vacancy, String labSchedule, String tutorialSchedule, String lectureSchedule) {
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.school = school;
		this.noOfAUs = noOfAUs;
		this.courseIndex = courseIndex;
		this.vacancy = vacancy;
		this.labSchedule = labSchedule;
		this.tutorialSchedule = tutorialSchedule;
		this.lectureSchedule = lectureSchedule;
	}
	

	/**
	 * Finds if course index is taken by the student.
	 * 
	 * @param courseIndex	Check if course index exists in database.
	 * 
	 * @return 				true if course index is found in database, 
	 * 						else return false.
	 */
	public boolean isIndexTaken(String courseIndex){
		try {
			File file=new File("src/Courses");    
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr); 
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
					if(entry[4].equals(courseIndex.trim())){
						br.close();
						return true;
				}

			}
			fr.close();    
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * check if CourseCode input exists in database
	 * @param courseCode  input course code
	 * @return true if input course code is in database 
	 */
	
	public boolean existingCourseCode(String courseCode){
		try {
			File file=new File("src/Courses");    
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr); 
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
					if(entry[0].equals(courseCode.trim())){
						br.close();
						return true;
				}

			}
			fr.close();    
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	
	

	/**
	 * Add course to the student's database only if the course
	 * index is not already taken.
	 * 
	 * @param courseCode					course Code
	 * @param courseName					course Name
	 * @param school						school
	 * @param noOfAUs						number Of AUs
	 * @param courseIndex					ArrayList of {@link String}, each element containing a course Index
	 * @param vacancy						ArrayList of {@link String}, each element containing the vacancy for the corresponding course Index
	 * 
	 * @return true if course has been added. Else return error in 
	 * adding course explaining that the index is not available.
	 * @throws ExistingCourseException 		 Exception thrown when user has registered for the same course / course index.
	 * 
	 */
	
	public String addCourses(String courseCode, String courseName, String school, String noOfAUs, ArrayList<String> courseIndex, ArrayList<String> vacancy) throws ExistingCourseException{
		boolean indexIsTaken = false;
		for (String course : courseIndex) {
			if(isIndexTaken(course)) {
				indexIsTaken = true;
				break;
			}
		}
		
		
		if(!indexIsTaken){
			try {
				File file = new File("src/Courses");    
				PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
				for(int i = 0;i<courseIndex.size();i++){
					pw.println(courseCode+";"+courseName+";"+school+";"+noOfAUs+";"+courseIndex.get(i)+";"+vacancy.get(i)+";NONE;NONE;NONE");
				}
				pw.close();
				return "Courses added successfully.";
			}catch(Exception ex){
				return "Error adding courses.";
			}
		}
		throw new ExistingCourseException(courseIndex);

	}
	
	/**
	   * Prints out the whole list of courses in the database with information regarding
	   * its course code, course name and its course index.
	   * 
	   * @return course list as course code, course name as well as its course index.
	   * 
	   * @throws IOException			If I/O error occurs
	   */

	public String retrieveCourse() throws IOException {
		
		String courselist = "";
		File file=new File("src/Courses");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr); 
		String line;
		try {
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
				courselist += "Course Code: " + entry[0] + ", Course Name: " + entry[1] + ", Course Index: " + entry[4] + "\n";
			}
			br.close();
			fr.close();
		}
		catch (NullPointerException e) {
			System.out.println("an error occurred");
		}; 
		
		return courselist;
		}
	
	  /**
	   * Finds the course object based on course index.
	   * 
	   * @param courseIndex               course Index 
	   * 
	   * @return 						  course as course object
	   * 
	   * @throws IOException  			 If I/O error occurs
	   */

	public Course retrieveCourseByIndex(String courseIndex) throws IOException {
		Course course = new Course();
			File file=new File("src/Courses");    
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr); 
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
				if(entry[4].equals(courseIndex)){
					course = new Course(entry[0],entry[1],entry[2],Integer.parseInt(entry[3]),entry[4],Integer.parseInt(entry[5]),entry[6],entry[7],entry[8]);
					br.close();
					return course;
				}

			}
			fr.close();    
		return course;
		}
	
	/**
	   * Finds the course object based on course Code.
	   * 
	   * @param courseCode                course Code 
	   * 
	   * @return 						  course as course object
	   * 
	   * @throws IOException  				If I/O error occurs
	   */

	public Course retrieveCourseByCourseCode(String courseCode) throws IOException {
		Course course = new Course();
		File file=new File("src/Courses");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr); 
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[0].equals(courseCode)){
				course = new Course(entry[0],entry[1],entry[2],Integer.parseInt(entry[3]),entry[4],Integer.parseInt(entry[5]),entry[6],entry[7],entry[8]);
				br.close();
				return course;
			}

		}
		fr.close();    
		return course;
	}

	/**
	 * Prints the schedule based on the days it is available
	 * as well as its location.
	 * 
	 * @param schedule		day, time and location of the course index
	 * 
	 * @return				result with the information regarding the 
	 * 						course index's day, time and location.
	 */
	public String printSchedule(String schedule){
		if(schedule.equals("NONE")){
			return "No schedule found.";
		}
		String[] scheduleArray = schedule.split(",");
		String day="", result;
		switch(Integer.parseInt(scheduleArray[1])){
			case 1:
				day = "Monday   ";
				break;
			case 2:
				day = "Tuesday  ";
				break;
			case 3:
				day = "Wednesday";
				break;
			case 4:
				day = "Thursday ";
				break;
			case 5:
				day = "Friday   ";
				break;
			case 6:
				day = "Saturday ";
				break;
			case 7:
				day = "Sunday   ";
				break;
		}
		result = "Week:"+scheduleArray[0]+"\t"+day+" \t"+scheduleArray[2]+" - "+scheduleArray[3]+"\t Location:"+scheduleArray[4];
		return result;

	}
	/**
	 * Update Course details in the database.
	 * 
	 * @return 		"Courses updated successfully." if Courses were updated successfully, return "Error updating courses." otherwise
	 */
	
	public String updateCourse() {
			try {

				File file = new File("src/Courses");    
				Scanner scanner = new Scanner(file);
				ArrayList<String> textcontent = new ArrayList<String>();
				while (scanner.hasNextLine()){
					textcontent.add(scanner.nextLine());
				}
				scanner.close();
				for(int i =0;i<textcontent.size();i++){
					if(this.courseIndex.equals(textcontent.get(i).split(";")[4])){
						textcontent.set(i, this.courseCode+";"+this.courseName+";"+this.school+";"+this.noOfAUs+";"+this.courseIndex+";"+this.vacancy+";"+this.labSchedule+";"+this.tutorialSchedule+";"+this.lectureSchedule);
					}
				}
				PrintWriter pw = new PrintWriter(new FileOutputStream(file));
				for(int i=0; i<textcontent.size();i++){
					pw.println(textcontent.get(i));
				}
				pw.close();
				return "Courses updated successfully.";
			}catch(Exception ex){
				ex.printStackTrace();
				return "Error updating courses.";
			}
	}
	
	/**
	 * Returns number of vacancies for a given course Index.
	 * 
	 * @param courseIndex		course index
	 * @return 					number of vacancies for a given course Index	
	 */
	public int courseIndexVacancy(String courseIndex){
		//count lines which has index
		//vacancy - count = result
		Course course = null;
		int count = 0;
		try {
			course = retrieveCourseByIndex(courseIndex);
			int vacancy = course.getVacancy();

			File file=new File("src/registeredRecords");    
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr); 
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
				if(entry[6].equals(courseIndex) && entry[7].equals("ACCEPTED")){
					count++;
				}

			}
			fr.close();    

			return (vacancy-count);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	
	/**
	 *  checks if course Code input has been added in the system
	 * @param courseCode		course Code
	 * @return true 			if course Code input already exists, false otherwise
	 */
	public boolean isCourseTaken(String courseCode) {
		try {
			File file=new File("src/Courses");    
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr); 
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
				if(entry[0].equals(courseCode.trim())){
					br.close();
					return true;
				}

			}
			fr.close();    
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}

}
