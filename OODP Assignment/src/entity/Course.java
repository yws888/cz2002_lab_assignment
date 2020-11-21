package entity;

import java.io.*;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Scanner;

//import scrame.exception.CourseNotFoundException;


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

	private String labSchedule;
	private String tutorialSchedule;
	
	/**
	   * lecture schedule
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

	public void setCourseIndex(String courseIndex) {
		this.courseIndex = courseIndex;
	}

	public int getVacancy() {
		return vacancy;
	}

	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

	public String getLabSchedule() {
		return labSchedule;
	}

	public void setLabSchedule(String labSchedule) {
		this.labSchedule = labSchedule;
	}

	public String getTutorialSchedule() {
		return tutorialSchedule;
	}

	public void setTutorialSchedule(String tutorialSchedule) {
		this.tutorialSchedule = tutorialSchedule;
	}

	public String getLectureSchedule() {
		return lectureSchedule;
	}

	public void setLectureSchedule(String lectureSchedule) {
		this.lectureSchedule = lectureSchedule;
	}

	public Course(){

	}
	
	

	/**
	 * Constructor for Course object
	 * 
	 * @param courseCode
	 * @param courseName
	 * @param school
	 * @param noOfAUs
	 * @param courseIndex
	 * @param vacancy
	 * @param labSchedule
	 * @param tutorialSchedule
	 * @param lectureSchedule
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
	

/*	public boolean isIndexTaken(ArrayList<String> courseIndex){
		try {
			File file=new File(System.getProperty("user.dir")+"/src/Courses");    
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr); 
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
				for(int i=0;i<courseIndex.size();i++){
					if(entry[4].equals(courseIndex.get(i))){
						br.close();
						return true;
					}
				}

			}
			fr.close();    
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}*/

	/**
	 * @param courseIndex
	 * @return
	 */
	public boolean isIndexTaken(String courseIndex){
		try {
			File file=new File(System.getProperty("user.dir")+"/src/Courses");    
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
	 * @param courseCode
	 * @param courseName
	 * @param school
	 * @param noOfAUs
	 * @param courseIndex
	 * @param vacancy
	 * @return
	 */
	
	public String addCourses(String courseCode, String courseName, String school, String noOfAUs, ArrayList<String> courseIndex, ArrayList<String> vacancy){
		boolean indexIsTaken = false;
		for (String course : courseIndex) {
			if(isIndexTaken(course)) {
				indexIsTaken = true;
				break;
			}
		}
		
		// if(!isIndexTaken(courseIndex)){
		if(!indexIsTaken){
			try {
				File file = new File(System.getProperty("user.dir") + "/src/Courses");    
				PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
				for(int i = 0;i<courseIndex.size();i++){
					pw.println(courseCode+";"+courseName+";"+school+";"+noOfAUs+";"+courseIndex.get(i)+";"+vacancy.get(i)+";NONE;NONE;NONE");
				}
				pw.close();
				return "Courses added successfully.";
			}catch(Exception ex){
				return "Error adding courses.";
			}
		}else{
			return "Courses could not be added, one of the index entered is not available.";
		}

	}
	
	  /**
	   * Finds the course object based on course index.
	   * 
	   * @param courseIndex               course Index 
	   * @return                          course object
	   * @throws IOException  			  if no course found (?)
	   */

	public Course retrieveCourseByIndex(String courseIndex) throws IOException {
		Course course = new Course();
			File file=new File(System.getProperty("user.dir")+"/src/Courses");    
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
	   * @return                          course object
	   * @throws IOException  			  if no course found (?)
	   */

	public Course retrieveCourseByCourseCode(String courseCode) throws IOException {
		Course course = new Course();
		File file=new File(System.getProperty("user.dir")+"/src/Courses");    
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
	 * @param schedule
	 * @return
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
	 * update Course details in the file
	 * 
	 * @return 		"Courses updated successfully." if Courses were updated successfully, return "Error updating courses." otherwise
	 */
	
	public String updateCourse() {
			try {

				File file = new File(System.getProperty("user.dir") + "/src/Courses");    
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
	 * returns number of vacancies for a given course Index
	 * 
	 * @param courseIndex		course Index
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

			File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr); 
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
				if(entry[6].equals(courseIndex)){
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
	 *  checks if course Code input already exists
	 * @param courseCode		course Code
	 * @return true 			if course Code input already exists, false otherwise
	 */
	public boolean isCourseTaken(String courseCode) {
		try {
			File file=new File(System.getProperty("user.dir")+"/src/Courses");    
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
