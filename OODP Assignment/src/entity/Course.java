package entity;

import java.io.*;
//import java.lang.reflect.Array;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Scanner;


public class Course {
	//datetime standard
	// private String dateformat = "HH:mm";
	
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

//	public void setCourseName(String courseName) {
//		this.courseName = courseName;
//	}

	/**
	   * Getter method for course code.
	   * 
	   * @return course code
	   */
	
	public String getCourseCode() {
		return courseCode;
	}

//	public void setCourseCode(String courseCode) {
//		this.courseCode = courseCode;
//	}


	public String getSchool() {
		return school;
	}

//	public void setSchool(String school) {
//		this.school = school;
//	}

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
	
	
	public boolean isIndexTaken(ArrayList<String> courseIndex){
		try {
			File file=new File(System.getProperty("user.dir")+"/src/Courses");    //creates a new file instance
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
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
			fr.close();    //closes the stream and release the resources
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}

	public boolean isIndexTaken(String courseIndex){
		try {
			File file=new File(System.getProperty("user.dir")+"/src/Courses");    //creates a new file instance
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
					if(entry[4].equals(courseIndex.trim())){
						br.close();
						return true;
				}

			}
			fr.close();    //closes the stream and release the resources
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}

	public String addCourses(String courseCode, String courseName, String school, String noOfAUs, ArrayList<String> courseIndex, ArrayList<String> vacancy){
		if(!isIndexTaken(courseIndex)){
			try {
				File file = new File(System.getProperty("user.dir") + "/src/Courses");    //creates a new file instance
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


	public Course retrieveCourseByIndex(String courseIndex) throws IOException {
		Course course = new Course();
			File file=new File(System.getProperty("user.dir")+"/src/Courses");    //creates a new file instance
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
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
			fr.close();    //closes the stream and release the resources
		return course;
		}

	public Course retrieveCourseByCourseCode(String courseCode) throws IOException {
		Course course = new Course();
		File file=new File(System.getProperty("user.dir")+"/src/Courses");    //creates a new file instance
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
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
		fr.close();    //closes the stream and release the resources
		return course;
	}

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
	public String updateCourse() {
			try {

				File file = new File(System.getProperty("user.dir") + "/src/Courses");    //creates a new file instance
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
	public int courseIndexVacancy(String courseIndex){
		//count lines which has index
		//vacancy - count = result
		Course course = null;
		int count = 0;
		try {
			course = retrieveCourseByIndex(courseIndex);
			int vacancy = course.getVacancy();

			File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    //creates a new file instance
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
				if(entry[6].equals(courseIndex)){
					count++;
				}

			}
			fr.close();    //closes the stream and release the resources

			return (vacancy-count);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean isCourseTaken(String courseCode) {
		try {
			File file=new File(System.getProperty("user.dir")+"/src/Courses");    //creates a new file instance
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
				if(entry[0].equals(courseCode.trim())){
					br.close();
					return true;
				}

			}
			fr.close();    //closes the stream and release the resources
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}

}
