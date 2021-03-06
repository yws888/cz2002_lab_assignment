package entity;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import helper.EmailManager;
import helper.NotificationManager;

import java.lang.NullPointerException;

/**
  * Student represents a student in the school.
 *
 */
public class Student extends User{
	  /**
	   * The name of the student.
	   */
	private String name;
	
	  /**
	   * The matriculation number of the student as a string.
	   */
	
	private String matricnumber;
	
	/**
   * The gender of the Student. Either Male or Female.
   */
	
	private String gender;
	
	/**
   * The nationality of the Student. 
   */
	
	private String nationality;

	/**
	 * The email of the Student.
	 */

	private String email;

	/**
	   * Getter method for name of the student.
	   * 
	   * @return name of the student
	   */
	public String getName() {
		return name;
	}
	/**
	   * Getter method for matriculation number of the student.
	   * 
	   * @return matriculation number of the student
	   */

	public String getMatricnumber() {
		return matricnumber;
	}
	/**
	   * Getter method for gender of the student.
	   * 
	   * @return gender of the student
	   */
	public String getGender() {
		return gender;
	}
	/**
	   * Getter method for nationality of the student.
	   * 
	   * @return nationality of the student
	   */

	public String getNationality() {
		return nationality;
	}

	/**
	 * Getter method for email of the student.
	 *
	 * @return email of the student
	 */

	public String getEmail() { return email; }

	/**
	 * Default Constructor for Student object.
	 */
	public Student() {
	}
	
	  /**
	   * Constructor for Student object.
	   * 
	   * @param username                         username of the student
	   * @param name                             name of the student
	   * @param matricnumber                     matric number of the student
	   * @param gender                           gender of the student
	   * @param nationality                      nationality of the student
	   * @param email                            email of the student
	   */	
	
	public Student(String username, String name, String matricnumber, String gender, String nationality, String email) {
		super.setUsername((username));
		this.name = name;
		this.matricnumber = matricnumber;
		this.gender = gender;
		this.nationality = nationality;
		this.email = email;
	}
	
	/**
	   * retrieves and returns Student object from corresponding username 
	   * by reading and matching from text file
	   * @param username		username of student
	   * @return                          Student object
	   */

	public Student retrieveStudentInfoByUsername(String username) {
		Student student = new Student();
		
		try {
			File file=new File("src/Students");    
			
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  
			String line;
			boolean entryfound = false;
			while((line=br.readLine())!=null || entryfound == false)
			{
				String[] entry = line.split(";");
				if(entry[0].equals(username)){
					student = new Student(entry[0],entry[4],entry[5],entry[6],entry[7],entry[8]);
					entryfound = true;

				}
			}
			fr.close();    
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return student;
		
	}
	
		/**
	   * Checks if username exists in database
	   * by reading and matching from text file
	   * 
	   * @param	username	username of the student
	   * @return true if username is in database, else return false
	   */
	public boolean isUsernameTaken(String username) {
		
		try {
			File file=new File("src/Students");    
			
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  
			String line;
			boolean entryfound = false;
			while((line=br.readLine())!=null || entryfound == false)
			{
				String[] entry = line.split(";");
				if(entry[0].equals(username)){
					br.close();
					return true;
				}
			}
			
			fr.close();    
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch (NullPointerException e) {};
		
		return false;
		
	}
	
	/**
	   * Checks if matriculation number exists in database
	   * by reading and matching from text file
	   * 
	   * @param	matriculationNumber	matriculation number of the student
	   * @return true if matriculation number is in database, else return false
	   */
	public boolean isMatricNumberTaken(String matriculationNumber) {
		
		try {
			File file=new File("src/Students");    
			
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  
			String line;
			boolean entryfound = false;
			while((line=br.readLine())!=null || entryfound == false)
			{
				String[] entry = line.split(";");
				if(entry[5].equals(matriculationNumber)){
					br.close();
					return true;
				}
			}
			fr.close();    
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch (NullPointerException e) {};
		
		return false;
		
	}
	
	/**
	   * retrieves and returns Student object regarding their login details 
	   * in an array.
	   * 
	   * @return array containing username, password, is salt and is type.
	   */
	public ArrayList<User> retrieveStudentLoginDetails() { 		
		ArrayList<User> userarray = new ArrayList<User>();
		try {
			File file=new File("src/Students");    
			
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  
			String line;  
			while((line=br.readLine())!=null)  
			{
			String[] entry = line.split(";");
			userarray.add(new User(entry[0], entry[1], entry[2], entry[3])); //0 is username, 1 is password, 2 is salt, 3 is type
			}  
			fr.close();      
			}  
		catch(IOException e){  
			e.printStackTrace();  
			}  
		return userarray;
	}
	
	/**
	   * Retrieves and returns Student object regarding their information
	   * such as username, matriculation number, and name.
	   * 
	   * @return the student list as a string.
	   */
	public String printStudentList(){
		StringBuilder sb = new StringBuilder();
		sb.append("====================Student List====================\n");
		try {
			File file=new File("src/Students");    
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
				sb.append("Username: "+entry[0]+" Matriculation No: "+entry[5]+" Name: "+entry[4]+"\n");
			}
			fr.close();    
		}
		catch(IOException e){
			sb.append("Error Printing Student List!!");
		}
		return sb.toString();
	}
	
	
	   /**
	   * Creates a new student object with its details written to the Students file in the database.
	   * @param username                        username of the student
	   * @param password						password of student
	   * @param salt							salt used in hashing
	   * @param name                             name of the student
	   * @param matriculationNumber              matric number of the student
	   * @param gender                           gender of the student
	   * @param nationality                      nationality of the student
	   * @param email                            email of the student
	   */
	
	public void createStudent(String username, String password, String salt, String name, String matriculationNumber, String gender, String nationality, String email) {
		try {

			File file = new File("src/Students");    
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
			pw.println(username+";"+password+";"+salt+";student;"+name+";"+matriculationNumber+";"+gender+";"+nationality+";"+email);
			pw.close();
		}catch(Exception ex){
			System.out.println("an error occurred");

		}
	}
	
	

	/**
	 * checks if course Index input is already taken By Student
	 *
	 * @param courseIndex		course Index
	 * @return true 			if course Index input is already taken By Student, false otherwise
	 * @throws IOException		if I/O exception occurs
	 */
	public boolean courseIndexTakenByStudent(String courseIndex) throws IOException {
		String matriculation_no = this.matricnumber;
		File file=new File("src/registeredRecords");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[1].equals(matriculation_no) && entry[6].equals(courseIndex)){

				br.close();
				return true;
			}

		}
		fr.close();    
		return false;

	}
	
	/**
	 * checks if student is already accepted for the course index
	 *
	 * @param courseIndex		course Index
	 * @return true 			if student is accepted for the course index, else return false.
	 * @throws IOException		if I/O exception occurs
	 */
	public boolean isCourseIndexAccepted(String courseIndex) throws IOException {
		String matriculation_no = this.matricnumber;
		File file=new File("src/registeredRecords");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[1].equals(matriculation_no) && entry[6].equals(courseIndex) && entry[7].equals("ACCEPTED")){
				br.close();
				return true;
			}

		}
		fr.close();    
		return false;
	}
	
	/**
	 * checks if student is on waitlist for the course index
	 *
	 * @param courseIndex		course Index
	 * @return true 			if student is on waitlist for the course index, else return false.
	 * @throws IOException		if I/O exception occurs
	 */
	public boolean isCourseIndexOnWaitlist(String courseIndex) throws IOException {
		String matriculation_no = this.matricnumber;
		File file=new File("src/registeredRecords");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[1].equals(matriculation_no) && entry[6].equals(courseIndex) && entry[7].equals("WAITLIST")){
				br.close();
				return true;
			}

		}
		fr.close();    
		return false;
	}
	
	

	/**
	 * Checks if there is any time overlap between 2 courses that would affect the schedule.
	 *
	 * @param startTime1		startTime of session 1 
	 * @param endTime1			endTime of session 1 
	 * @param startTime2		startTime of session 2 
	 * @param endTime2			endTime of session 2 
	 * @return true if time overlaps, else return false if time does not overlap.
	 */
	public boolean timeOverlap(String startTime1, String endTime1, String startTime2, String endTime2){
		//ranges overlap if (StartDate1 <= EndDate2) and (StartDate2 <= EndDate1)
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.format(date);
		try {
			if((dateFormat.parse(startTime1).before(dateFormat.parse(endTime2)) || dateFormat.parse(startTime1).equals(dateFormat.parse(endTime2))) 
					&& (dateFormat.parse(startTime2).before(dateFormat.parse(endTime1)) || dateFormat.parse(startTime2).equals(dateFormat.parse(endTime1)))){
				
				if((dateFormat.parse(startTime2).equals(dateFormat.parse(endTime1)) || dateFormat.parse(startTime1).equals(dateFormat.parse(endTime2)))){
					return false;
				}
				return true;
			}
			return false;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * Checks if there is any clashes in the student's current schedule after adding the Course to be added
	 *
	 * @param course			 Course to be added
	 * @return true if there is clash, else return false if there is no clash.
	 * @throws IOException		if I/O exception occurs
	 */
	public boolean hasClashingSchedule(Course course) throws IOException {
		String matriculation_no = this.matricnumber;
		File file=new File("src/registeredRecords");    
		FileReader fr=new FileReader(file);   
		BufferedReader br=new BufferedReader(fr);  
		String line;
		String [] courseSchedule, tempCourseSchedule;
		String courseDay, courseWeek, tempDay, tempWeek;
		Course tempCourse;
		boolean result1=false, result2=false, result3=false, result4=false, result5=false, result6=false, result7=false, result8=false, result9=false;
		while((line=br.readLine())!=null)
		{
			//check if lab and day are same, then compare for optimization purpose
			String[] entry = line.split(";");
			if(entry[1].equals(matriculation_no)){
				//retrieve course By Index
				tempCourse = course.retrieveCourseByIndex(entry[6]);
				//compare lab with all 3
				if(!course.getLabSchedule().equals("NONE")) {
					courseSchedule = course.getLabSchedule().split(",");
					courseWeek = courseSchedule[0];
					courseDay = courseSchedule[1];
					if(!tempCourse.getLabSchedule().equals("NONE")) {
						tempCourseSchedule = tempCourse.getLabSchedule().split(",");
						tempWeek = tempCourseSchedule[0];
						tempDay = tempCourseSchedule[1];
						if((courseWeek.equals(tempWeek) && courseDay.equals(tempDay)) || (courseDay.equals(tempDay) && courseWeek.equals("WEEKLY")) || (courseDay.equals(tempDay) && tempWeek.equals("WEEKLY"))) {
							result1 = timeOverlap(courseSchedule[2], courseSchedule[3], tempCourseSchedule[2], tempCourseSchedule[3]);
						}
					}
					if(!tempCourse.getTutorialSchedule().equals("NONE")) {
						tempCourseSchedule = tempCourse.getTutorialSchedule().split(",");
						tempWeek = tempCourseSchedule[0];
						tempDay = tempCourseSchedule[1];
						if(courseWeek.equals(tempWeek) && courseDay.equals(tempDay)  || (courseDay.equals(tempDay) && courseWeek.equals("WEEKLY")) || (courseDay.equals(tempDay) && tempWeek.equals("WEEKLY"))) {
							result2 = timeOverlap(courseSchedule[2], courseSchedule[3], tempCourseSchedule[2], tempCourseSchedule[3]);
						}
					}
					if(!tempCourse.getLectureSchedule().equals("NONE")) {
						tempCourseSchedule = tempCourse.getLectureSchedule().split(",");
						tempWeek = tempCourseSchedule[0];
						tempDay = tempCourseSchedule[1];
						if(courseWeek.equals(tempWeek) && courseDay.equals(tempDay)  || (courseDay.equals(tempDay) && courseWeek.equals("WEEKLY")) || (courseDay.equals(tempDay) && tempWeek.equals("WEEKLY"))) {
							result3 = timeOverlap(courseSchedule[2], courseSchedule[3], tempCourseSchedule[2], tempCourseSchedule[3]);
						}
					}
				}
				//compare tut with all 3
				if(!course.getTutorialSchedule().equals("NONE")) {
					courseSchedule = course.getTutorialSchedule().split(",");
					courseWeek = courseSchedule[0];
					courseDay = courseSchedule[1];
					if(!tempCourse.getLabSchedule().equals("NONE")) {
						tempCourseSchedule = tempCourse.getLabSchedule().split(",");
						tempWeek = tempCourseSchedule[0];
						tempDay = tempCourseSchedule[1];
						if(courseWeek.equals(tempWeek) && courseDay.equals(tempDay)  || (courseDay.equals(tempDay) && courseWeek.equals("WEEKLY")) || (courseDay.equals(tempDay) && tempWeek.equals("WEEKLY"))) {
							result4 = timeOverlap(courseSchedule[2], courseSchedule[3], tempCourseSchedule[2], tempCourseSchedule[3]);
						}
					}
					if(!tempCourse.getTutorialSchedule().equals("NONE")) {
						tempCourseSchedule = tempCourse.getTutorialSchedule().split(",");
						tempWeek = tempCourseSchedule[0];
						tempDay = tempCourseSchedule[1];
						if(courseWeek.equals(tempWeek) && courseDay.equals(tempDay)  || (courseDay.equals(tempDay) && courseWeek.equals("WEEKLY")) || (courseDay.equals(tempDay) && tempWeek.equals("WEEKLY"))) {
							result5 = timeOverlap(courseSchedule[2], courseSchedule[3], tempCourseSchedule[2], tempCourseSchedule[3]);
						}
					}
					if(!tempCourse.getLectureSchedule().equals("NONE")) {
						tempCourseSchedule = tempCourse.getLectureSchedule().split(",");
						tempWeek = tempCourseSchedule[0];
						tempDay = tempCourseSchedule[1];
						if(courseWeek.equals(tempWeek) && courseDay.equals(tempDay)  || (courseDay.equals(tempDay) && courseWeek.equals("WEEKLY")) || (courseDay.equals(tempDay) && tempWeek.equals("WEEKLY"))) {
							result6 = timeOverlap(courseSchedule[2], courseSchedule[3], tempCourseSchedule[2], tempCourseSchedule[3]);
						}
					}
				}
				//compare lec with all 3
				//if same course code, dont compare since same course but difft index supposed to have the same lecture slots
				if(!course.getLectureSchedule().equals("NONE") && !course.getCourseCode().equals(tempCourse.getCourseCode())) {
					courseSchedule = course.getLectureSchedule().split(",");
					courseWeek = courseSchedule[0];
					courseDay = courseSchedule[1];
					if(!tempCourse.getLabSchedule().equals("NONE")) {
						tempCourseSchedule = tempCourse.getLabSchedule().split(",");
						tempWeek = tempCourseSchedule[0];
						tempDay = tempCourseSchedule[1];
						if(courseWeek.equals(tempWeek) && courseDay.equals(tempDay)  || (courseDay.equals(tempDay) && courseWeek.equals("WEEKLY")) || (courseDay.equals(tempDay) && tempWeek.equals("WEEKLY"))) {
							result7 = timeOverlap(courseSchedule[2], courseSchedule[3], tempCourseSchedule[2], tempCourseSchedule[3]);
						}
					}
					if(!tempCourse.getTutorialSchedule().equals("NONE")) {
						tempCourseSchedule = tempCourse.getTutorialSchedule().split(",");
						tempWeek = tempCourseSchedule[0];
						tempDay = tempCourseSchedule[1];
						if(courseWeek.equals(tempWeek) && courseDay.equals(tempDay)  || (courseDay.equals(tempDay) && courseWeek.equals("WEEKLY")) || (courseDay.equals(tempDay) && tempWeek.equals("WEEKLY"))) {
							result8 = timeOverlap(courseSchedule[2], courseSchedule[3], tempCourseSchedule[2], tempCourseSchedule[3]);
						}
					}
					if(!tempCourse.getLectureSchedule().equals("NONE")) {
						tempCourseSchedule = tempCourse.getLectureSchedule().split(",");
						tempWeek = tempCourseSchedule[0];
						tempDay = tempCourseSchedule[1];
						if(courseWeek.equals(tempWeek) && courseDay.equals(tempDay)  || (courseDay.equals(tempDay) && courseWeek.equals("WEEKLY")) || (courseDay.equals(tempDay) && tempWeek.equals("WEEKLY"))) {
							result9 = timeOverlap(courseSchedule[2], courseSchedule[3], tempCourseSchedule[2], tempCourseSchedule[3]);
						}
					}
				}
			}
			if(result1 || result2 || result3 || result4 || result5 || result6 || result7 || result8 || result9){
				br.close();    
				return true;
			}

		}
		br.close();    
	return false;

	}
	/**
	 * retrieve  Courses Student has Registered for
	 * 
	 * @return ArrayList containing Course objects Student has Registered for 		
	 * @throws IOException				if I/O exception occurs
	 */
	public ArrayList<Course> retrieveRegisteredCourses() throws IOException {
		ArrayList<String> indexList = new ArrayList<String>();
		String matriculation_no = this.matricnumber;
		File file=new File("src/registeredRecords");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[1].equals(matriculation_no)){
				indexList.add((entry[6]));
			}

		}
		fr.close();    

		ArrayList<Course> courseList = new ArrayList<Course>();
		file=new File("src/Courses");    
		fr=new FileReader(file);   //reads the file
		br=new BufferedReader(fr);  
		while((line=br.readLine())!=null)
		{
			for(int i=0;i <indexList.size();i++) {
				String[] entry = line.split(";");
				if (entry[4].equals(indexList.get(i))) {
					courseList.add(new Course(entry[0],entry[1],entry[2],Integer.parseInt(entry[3]),entry[4],Integer.parseInt(entry[5]),entry[6],entry[7],entry[8]));
				}
			}
		}
		fr.close();    
		return courseList;

	}

	/**
	 * Prints the course list found in the database.
	 *
	 * @return course list's information as a string
	 * @throws IOException				if I/O exception occurs
	 */
	public String printCourseList() throws IOException {
		ArrayList<Course> courseList = retrieveRegisteredCourses();
		StringBuilder sb = new StringBuilder();
		String matriculation_no = this.matricnumber;
		File file=new File("src/registeredRecords");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			for(int i = 0;i<courseList.size();i++){
				if(entry[1].equals(matriculation_no) && entry[6].equals(courseList.get(i).getCourseIndex())){
					sb.append("\nCourse:"+courseList.get(i).getCourseCode()+"\tAU:"+courseList.get(i).getNoOfAUs()+"\tIndex No.:"+courseList.get(i).getCourseIndex()+"\tStatus:"+entry[7]);
				}

			}

		}
		fr.close();    
		sb.append("\nTotal AU: "+retrieveTotalAUTaken());
		return sb.toString();

	}

	/**
	 * retrieves Total AUs Taken
	 *
	 * @return Total AUs Taken
	 */
	public int retrieveTotalAUTaken()  {
		ArrayList<Course> registeredCourses = null;
		try {
			registeredCourses = retrieveRegisteredCourses();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int count=0;
		for(int i=0;i<registeredCourses.size();i++){
			count += registeredCourses.get(i).getNoOfAUs();
		}
		return count;
	}
	
	/**
	 * enroll Student in a course by adding a new record to registeredRecords file
	 * 
	 * @param course			{@link Course} object to enroll Student in
	 * @param status			ACCEPTED or WAITLIST
	 * @return					"Courses added successfully." if successful, return "Error adding courses." otherwise
	 */
	public String enrollStudent(Course course, String status){
			try {
				File file = new File("src/registeredRecords");    
				PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
				pw.println(this.getUsername()+";"+this.getMatricnumber()+";"+this.getName()+";"+this.getGender()+";"+course.getCourseCode()+";"+course.getCourseName()+";"+course.getCourseIndex()+";"+status);
				pw.close();
				if(status == "WAITLIST"){
					return "Course Vacancy is full. You have been put on waitlist for the course.";
				}
				return "Courses added successfully.";
			}catch(Exception ex){
				return "Error adding courses.";
			}
	}
	
	/**
	 * method to drop a Course for the Student
	 * 
	 * @param course			{@link Course} object to be dropped
	 * @return					"Course dropped successfully." if successful, return "Error adding courses." otherwise
	 */
	public String dropStudentCourse(Course course) {
    	String courseCodeToRemove = course.getCourseCode();
		String matriculation_no = this.matricnumber;
    	
		//transfer data from old file to temp file without that course info
    	String tempFile = "temp.txt";
    	File oldFile = new File( "src/registeredRecords");
    	File newFile = new File (tempFile);
    	
    	String currentLine;
    	String entry[];
    	try {
    		FileWriter fw = new FileWriter(tempFile,true);
    		BufferedWriter bw = new BufferedWriter(fw);
    		PrintWriter pw = new PrintWriter(bw);
    		
    		FileReader fr = new FileReader("src/registeredRecords");
    		BufferedReader br = new BufferedReader(fr);
    		
    		while((currentLine = br.readLine()) != null)  {
    			entry = currentLine.split(";");
    			//if it does not contain that entry, print the line
    			if(!(entry[1].equals(matriculation_no) && entry[4].equals(courseCodeToRemove))) {
    				pw.println(currentLine);
    			}
    		}
    		
    		pw.flush();
    		pw.close();
    		fr.close();
    		br.close();
    		bw.close();
    		fw.close();
    		
    		oldFile.delete();
    		File dump = new File( "src/registeredRecords");
    		newFile.renameTo(dump);
    		this.removeStudentsFromWaitList(course.getCourseIndex(),1);
    		return "Course dropped successfully.";
    		
    	}
    	catch(Exception ex) {
    		return "Error dropping course.";
    	}
    }

	/**
	 * Checks if course is taken by the student based on the input course index.
	 * @param courseIndex				courseIndex
	 * @return true if the course is found in the database of that student, 
	 * else return false if it is not found in the student's database.
	 */
	public boolean courseCodeTakenByStudent(String courseIndex) {
		try {
			Course course = new Course();
			course = course.retrieveCourseByIndex(courseIndex);
			String courseCodeToTake = course.getCourseCode();

			String matriculation_no = this.matricnumber;
			File file=new File("src/registeredRecords");    
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
				if(entry[1].equals(matriculation_no) && entry[4].equals(courseCodeToTake)){
					br.close();
					return true;
				}

			}
			br.close();    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		return false;


	}

	/**
	 * Returns the list of students studying the course based on course
	 * index as an input.
	 * 
	 * @param courseIndex				courseIndex
	 * @return 							ArrayList of {@link Student} under the course index  input.
	 * @throws IOException				if I/O exception occurs
	 */
	public ArrayList<Student> studentListByIndex(String courseIndex) throws IOException {
		//name gender nationality only
		ArrayList<Student> studentList = new ArrayList<>();
		Student student = new Student();
		File file=new File("src/registeredRecords");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[6].equals(courseIndex) && entry[7].equals("ACCEPTED") ){
				studentList.add(student.retrieveStudentInfoByUsername(entry[0]));
			}

		}
		fr.close();
		return studentList;

	}
	/**
	 * Returns the student lists that are found under that course code.
	 *
	 * @param courseCode				courseCode
	 * @return							ArrayList of {@link Student}  which contains the students under the input course code.
	 * @throws IOException				if I/O exception occurs
	 */
	public ArrayList<Student> studentListByCourseCode(String courseCode) throws IOException {
		//name gender nationality only
		ArrayList<Student> studentList = new ArrayList<>();
		Student student = new Student();
		File file=new File("src/registeredRecords");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[4].equals(courseCode) && entry[7].equals("ACCEPTED")){
				studentList.add(student.retrieveStudentInfoByUsername(entry[0]));
			}

		}
		fr.close();
		return studentList;
	}
	
	
	
	/**
	 * 
	 * changes course Index For the Student in the file
	 * 
	 * @param course		{@link Course} object 
	 * @return				message "index changed successfully." if Index was changed, "index was not changed" or 	"FileNotFoundException" otherwise
	 */
	public String changeIndexForStudent(Course course){
		try {
			File file=new File("src/registeredRecords");      
			String message = "index was not changed";
			Scanner scanner = new Scanner(file);
			ArrayList<String> textcontent = new ArrayList<String>();
			while (scanner.hasNextLine()){
				textcontent.add(scanner.nextLine());
			}
			scanner.close();
			
			for(int i =0;i<textcontent.size();i++){
				if((course.getCourseCode().equals(textcontent.get(i).split(";")[4]) && (this.matricnumber.equals(textcontent.get(i).split(";")[1]))) ){
					textcontent.set(i, this.getUsername()+";"+this.getMatricnumber()+";"+this.getName()+";"+this.getGender()+";"+course.getCourseCode()+";"+course.getCourseName()+";"+course.getCourseIndex()+";"+"ACCEPTED");
					message = "index changed successfully.";
				}
			}
				
			PrintWriter pw = new PrintWriter(new FileOutputStream(file));
			for(int i=0; i<textcontent.size();i++){
					pw.println(textcontent.get(i));
			}
				pw.close();
				return message;
			
		}catch(FileNotFoundException ex){
			return "FileNotFoundException";
		}
}

	/**
	 * Removes student from waitlist if there is a space after another
	 * student has dropped the course.
	 *
	 * @param courseIndex				course Index of course to remove student from 
	 * @param availableSlots			no. of available slots
	 * @return							"Waitlist updated accordingly." if successful, return "Error updating waitlist." otherwise
	 * @throws IOException				if I/O exception occurs
	 */
	public String removeStudentsFromWaitList(String courseIndex, int availableSlots) throws IOException {
		int counter = availableSlots;
		ArrayList<String> emailList = new ArrayList<>();
		ArrayList<String> messageList = new ArrayList<>();
		ArrayList<String> recordList = new ArrayList<>();
		ArrayList<String> waitList = new ArrayList<>();
		Student student = new Student();
		File file=new File("src/registeredRecords");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[6].equals(courseIndex)){
				if(entry[7].equals("WAITLIST") && counter > 0){
					entry[7] = "ACCEPTED";
					recordList.add(String.join(";",entry));
					waitList.add(String.join(";",entry));
					emailList.add(student.retrieveStudentInfoByUsername(entry[0]).getEmail());
					messageList.add(entry[4]+" "+entry[5]+" Index "+entry[6]);
					counter--;
				}else{
					recordList.add(line);
				}
			}else {
				recordList.add(line);
			}

		}
		fr.close();

		try {
			file = new File("src/registeredRecords");
			PrintWriter pw = new PrintWriter(new FileOutputStream(file));
			int x = 0;
			for(int i=0; i<recordList.size();i++){
				pw.println(recordList.get(i));
				if(x< waitList.size() && recordList.get(i).equals(waitList.get(x))) {
					sendMail(emailList.get(x), messageList.get(x));
					x++;
				}
			}
			pw.close();

			return "Waitlist updated accordingly.";
		}catch(Exception ex){
			ex.printStackTrace();
			return "Error updating waitlist.";
		}
	}

	/**
	 * Notification sending to student via email.
	 *
	 * @param email			student's email
	 * @param message		message to be sent in email
     * @return "Sending Email Message to "+email+" success" if successful; return "Sending Email Message to "+email+" failed" otherwise
	 */
	public String sendMail(String email, String message){
		NotificationManager notifMgr = new EmailManager();
		return notifMgr.sendNotification(email, message);
	}

}
