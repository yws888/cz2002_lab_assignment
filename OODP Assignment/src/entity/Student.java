package entity;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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
	   * by reading & matching from text file
	   * 
	   * @return                          Student object
	   */
	public Student retrieveStudentInfoByUsername(String username) {
		Student student = new Student();
		//ArrayList<User> studentlist = retrieveStudentLoginDetails();
		try {
			File file=new File(System.getProperty("user.dir")+"/src/Students");    
			
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
	public ArrayList<User> retrieveStudentLoginDetails() { 		
		ArrayList<User> userarray = new ArrayList<User>();
		try {
			File file=new File(System.getProperty("user.dir")+"/src/Students");    
			
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

	public String printStudentList(){
		StringBuilder sb = new StringBuilder();
		sb.append("====================Student List====================\n");
		try {
			File file=new File(System.getProperty("user.dir")+"/src/Students");    
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
	 * @param username
	 * @param password
	 * @param salt
	 * @param name
	 * @param matriculationNumber
	 * @param gender
	 * @param nationality
	 * @param email
	 */
	
	public void createStudent(String username, String password, String salt, String name, String matriculationNumber, String gender, String nationality, String email) {
		try {

			File file = new File(System.getProperty("user.dir") + "/src/Students");    
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
			pw.println(username+";"+password+";"+salt+";student;"+name+";"+matriculationNumber+";"+gender+";"+nationality+";"+email);
			pw.close();
		}catch(Exception ex){

		}
	}
	
	

	/**
	 * checks if course Index input is already taken By Student
	 * @param courseIndex		course Index
	 * @return true 			if course Index input is already taken By Student, false otherwise
	 * @throws IOException		
	 */
	public boolean courseIndexTakenByStudent(String courseIndex) throws IOException {
		String matriculation_no = this.matricnumber;
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    
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
	
	//returns true if time overlaps?

	/**
	 * @param startTime1
	 * @param endTime1
	 * @param startTime2
	 * @param endTime2
	 * @return
	 */
	public boolean timeOverlap(String startTime1, String endTime1, String startTime2, String endTime2){
		//ranges overlap if (StartDate1 <= EndDate2) and (StartDate2 <= EndDate1)
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.format(date);
		try {
			if((dateFormat.parse(startTime1).before(dateFormat.parse(endTime2)) || dateFormat.parse(startTime1).equals(dateFormat.parse(endTime2))) 
					&& (dateFormat.parse(startTime2).before(dateFormat.parse(endTime1)) || dateFormat.parse(startTime2).equals(dateFormat.parse(endTime1)))){
				// shld add && (dateFormat.parse(startTime1).before(dateFormat.parse(endTime2)) ?
				if((dateFormat.parse(startTime2).equals(dateFormat.parse(endTime1)) || dateFormat.parse(startTime1).equals(dateFormat.parse(endTime2)))){
					return false;
				}else {
					return true;
				}
			}else{
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * @param course
	 * @return
	 * @throws IOException
	 */
	public boolean hasClashingSchedule(Course course) throws IOException {
		String matriculation_no = this.matricnumber;
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    
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
	 * @return ArrayList<Course> containing Courses Student has Registered for 		
	 * @throws IOException
	 */
	public ArrayList<Course> retrieveRegisteredCourses() throws IOException {
		ArrayList<String> indexList = new ArrayList<String>();
		String matriculation_no = this.matricnumber;
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    
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
		file=new File(System.getProperty("user.dir")+"/src/Courses");    
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
	 * @return
	 * @throws IOException
	 */
	public String printCourseList() throws IOException {
		ArrayList<Course> courseList = retrieveRegisteredCourses();
		StringBuilder sb = new StringBuilder();
		String matriculation_no = this.matricnumber;
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    
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
	 * @return Total AUs Taken
	 * @throws IOException
	 */
	public int retrieveTotalAUTaken() throws IOException {
		ArrayList<Course> registeredCourses = retrieveRegisteredCourses();
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
	 * @return					different messages
	 */
	public String enrollStudent(Course course, String status){
			try {
				File file = new File(System.getProperty("user.dir") + "/src/registeredRecords");    
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
	 * @param course
	 * @return
	 */
	
	public String dropStudentCourse(Course course) {
    	String courseCodeToRemove = course.getCourseCode();
		String matriculation_no = this.matricnumber;
    	
		//transfer data from old file to temp file without that course info
    	String tempFile = "temp.txt";
    	File oldFile = new File(System.getProperty("user.dir")+ "/src/registeredRecords");
    	File newFile = new File (tempFile);
    	
    	String currentLine;
    	String entry[];
    	try {
    		FileWriter fw = new FileWriter(tempFile,true);
    		BufferedWriter bw = new BufferedWriter(fw);
    		PrintWriter pw = new PrintWriter(bw);
    		
    		FileReader fr = new FileReader(System.getProperty("user.dir")+"/src/registeredRecords");
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
    		File dump = new File(System.getProperty("user.dir")+ "/src/registeredRecords");
    		newFile.renameTo(dump);
    		
    		return "Course dropped successfully.";
    		
    	}
    	catch(Exception ex) {
    		return "Error dropping course.";
    	}
    }

	/**
	 * @param courseIndex
	 * @return
	 * @throws IOException
	 */
	public boolean courseCodeTakenByStudent(String courseIndex) throws IOException {
		Course course = new Course();
		course = course.retrieveCourseByIndex(courseIndex);
		String courseCodeToTake = course.getCourseCode();

		String matriculation_no = this.matricnumber;
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    
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
		return false;

	}

	/**
	 * @param courseIndex
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Student> studentListByIndex(String courseIndex) throws IOException {
		//name gender nationality only
		ArrayList<Student> studentList = new ArrayList<>();
		Student student = new Student();
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[6].equals(courseIndex) && entry[7] == "ACCEPTED"){
				studentList.add(student.retrieveStudentInfoByUsername(entry[0]));
			}

		}
		fr.close();
		return studentList;

	}
	/**
	 * @param courseCode
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Student> studentListByCourseCode(String courseCode) throws IOException {
		//name gender nationality only
		ArrayList<Student> studentList = new ArrayList<>();
		Student student = new Student();
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[4].equals(courseCode) && entry[7] == "ACCEPTED"){
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
			File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");      
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
				//br.close();
				return message;
			
		}catch(FileNotFoundException ex){
			return "FileNotFoundException";
		}
}

	/**
	 * @param courseIndex
	 * @param availableSlots
	 * @return
	 * @throws IOException
	 */
	public String removeStudentsFromWaitList(String courseIndex, int availableSlots) throws IOException {
		int counter = availableSlots;
		//System.out.println(counter);
		ArrayList<String> emailList = new ArrayList<>();
		ArrayList<String> messageList = new ArrayList<>();
		ArrayList<String> recordList = new ArrayList<>();
		ArrayList<String> waitList = new ArrayList<>();
		Student student = new Student();
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    
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
			file = new File(System.getProperty("user.dir") + "/src/registeredRecords");
			PrintWriter pw = new PrintWriter(new FileOutputStream(file));
			int x = 0;
			for(int i=0; i<recordList.size();i++){
				pw.println(recordList.get(i));
				if(x< waitList.size() && recordList.get(i).equals(waitList.get(x))) {
					System.out.println(sendMail(emailList.get(x), messageList.get(x)));
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
	 *
	 * @param email
	 * @param message
	 * @return
	 */
	public String sendMail(String email, String message){
		String response;
		EmailManager em = new EmailManager();
		response = em.sendEmail(email, message);
		return response;
	}

}
