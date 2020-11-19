package entity;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Student extends User{
	private String name;
	private String matricnumber;
	private String gender;
	private String nationality;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMatricnumber() {
		return matricnumber;
	}

	public void setMatricnumber(String matricnumber) {
		this.matricnumber = matricnumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Student() {
	}
	public Student(String username, String name, String matricnumber, String gender, String nationality) {
		this.setUsername(username);
		this.setName(name);
		this.setMatricnumber(matricnumber);
		this.setGender(gender);
		this.setNationality(nationality);
	}
	public Student retrieveStudentInfoByUsername(String username) {
		Student student = new Student();
		//ArrayList<User> studentlist = retrieveStudentLoginDetails();
		try {
			File file=new File(System.getProperty("user.dir")+"/src/Students");    //creates a new file instance
			
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
			String line;
			boolean entryfound = false;
			while((line=br.readLine())!=null || entryfound == false)
			{
				String[] entry = line.split(";");
				if(entry[0].equals(username)){
					student = new Student(entry[0],entry[4],entry[5],entry[6],entry[7]);
					entryfound = true;

				}
			}
			fr.close();    //closes the stream and release the resources
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return student;
		
	}
	public ArrayList<User> retrieveStudentLoginDetails() { 		
		ArrayList<User> userarray = new ArrayList<User>();
		try {
			File file=new File(System.getProperty("user.dir")+"/src/Students");    //creates a new file instance
			
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
			String line;  
			while((line=br.readLine())!=null)  
			{
			String[] entry = line.split(";");
			userarray.add(new User(entry[0], entry[1], entry[2], entry[3])); //0 is username, 1 is password, 2 is salt, 3 is type
			}  
			fr.close();    //closes the stream and release the resources  
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
			File file=new File(System.getProperty("user.dir")+"/src/Students");    //creates a new file instance
			FileReader fr=new FileReader(file);   //reads the file
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
			String line;
			while((line=br.readLine())!=null)
			{
				String[] entry = line.split(";");
				sb.append("Username: "+entry[0]+" Matriculation No: "+entry[5]+" Name: "+entry[4]+"\n");
			}
			fr.close();    //closes the stream and release the resources
		}
		catch(IOException e){
			sb.append("Error Printing Student List!!");
		}
		return sb.toString();
	}
	public void createStudent(String username, String password, String salt, String name, String matriculationNumber, String gender, String nationality) {
		try {

			File file = new File(System.getProperty("user.dir") + "/src/Students");    //creates a new file instance
			Writer output;
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
			pw.println(username+";"+password+";"+salt+";student;"+name+";"+matriculationNumber+";"+gender+";"+nationality);
			pw.close();
		}catch(Exception ex){

		}
	}

	public boolean courseIndexTakenByStudent(String courseIndex) throws IOException {
		String matriculation_no = this.matricnumber;
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    //creates a new file instance
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[1].equals(matriculation_no) && entry[6].equals(courseIndex)){

				fr.close();
				return true;
			}

		}
		fr.close();    //closes the stream and release the resources
		return false;

	}

	public boolean timeOverlap(String startTime1, String endTime1, String startTime2, String endTime2){
		//ranges overlap if (StartDate1 <= EndDate2) and (StartDate2 <= EndDate1)
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.format(date);
		try {
			if((dateFormat.parse(startTime1).before(dateFormat.parse(endTime2)) || dateFormat.parse(startTime1).equals(dateFormat.parse(endTime2))) && (dateFormat.parse(startTime2).before(dateFormat.parse(endTime1)) || dateFormat.parse(startTime2).equals(dateFormat.parse(endTime1)))){
				return true;
			}else{
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean hasClashingSchedule(Course course) throws IOException {
		String matriculation_no = this.matricnumber;
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    //creates a new file instance
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
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
				if(!course.getLectureSchedule().equals("NONE")) {
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
				return true;
			}

		}
		fr.close();    //closes the stream and release the resources
	return false;

	}
	public ArrayList<Course> retrieveRegisteredCourses() throws IOException {
		ArrayList<String> indexList = new ArrayList<String>();
		String matriculation_no = this.matricnumber;
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    //creates a new file instance
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[1].equals(matriculation_no)){
				indexList.add((entry[6]));
			}

		}
		fr.close();    //closes the stream and release the resources

		ArrayList<Course> courseList = new ArrayList<Course>();
		file=new File(System.getProperty("user.dir")+"/src/Courses");    //creates a new file instance
		fr=new FileReader(file);   //reads the file
		br=new BufferedReader(fr);  //creates a buffering character input stream
		while((line=br.readLine())!=null)
		{
			for(int i=0;i <indexList.size();i++) {
				String[] entry = line.split(";");
				if (entry[4].equals(indexList.get(i))) {
					courseList.add(new Course(entry[0],entry[1],entry[2],Integer.parseInt(entry[3]),entry[4],Integer.parseInt(entry[5]),entry[6],entry[7],entry[8]));
				}
			}
		}
		fr.close();    //closes the stream and release the resources
		return courseList;

	}

	public String printCourseList() throws IOException {
		ArrayList<Course> courseList = retrieveRegisteredCourses();
		StringBuilder sb = new StringBuilder();
		String matriculation_no = this.matricnumber;
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    //creates a new file instance
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
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
		fr.close();    //closes the stream and release the resources
		sb.append("\nTotal AU: "+retrieveTotalAUTaken());
		return sb.toString();

	}

	public int retrieveTotalAUTaken() throws IOException {
		ArrayList<Course> registeredCourses = retrieveRegisteredCourses();
		int count=0;
		for(int i=0;i<registeredCourses.size();i++){
			count += registeredCourses.get(i).getNoOfAUs();
		}
		return count;
	}
	public String enrollStudent(Course course, String status){
			try {
				File file = new File(System.getProperty("user.dir") + "/src/registeredRecords");    //creates a new file instance
				Writer output;
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

	public boolean courseCodeTakenByStudent(String courseIndex) throws IOException {
		Course course = new Course();
		course = course.retrieveCourseByIndex(courseIndex);
		String courseCodeToTake = course.getCourseCode();

		String matriculation_no = this.matricnumber;
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    //creates a new file instance
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[1].equals(matriculation_no) && entry[4].equals(courseCodeToTake)){
				fr.close();
				return true;
			}

		}
		fr.close();    //closes the stream and release the resources
		return false;

	}

	public ArrayList<Student> studentListByIndex(String courseIndex) throws IOException {
		//name gender nationality only
		ArrayList<Student> studentList = new ArrayList<>();
		Student student = new Student();
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    //creates a new file instance
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
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
	public ArrayList<Student> studentListByCourseCode(String courseCode) throws IOException {
		//name gender nationality only
		ArrayList<Student> studentList = new ArrayList<>();
		Student student = new Student();
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    //creates a new file instance
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
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
	
	public String changeIndexForStudent(Course course){
		try {
			File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    //creates a new file instance
			// File file = new File("registeredRecords");    //creates a new file instance
			// FileReader fr=new FileReader(file);   //reads the file
			//BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
			String message = "oh no";
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

	public String removeStudentsFromWaitList(String courseIndex, int availableSlots) throws IOException {
		//remember trimtosize
		int counter = availableSlots;
		ArrayList<String> recordList = new ArrayList<>();
		Student student = new Student();
		File file=new File(System.getProperty("user.dir")+"/src/registeredRecords");    //creates a new file instance
		FileReader fr=new FileReader(file);   //reads the file
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
		String line;
		while((line=br.readLine())!=null)
		{
			String[] entry = line.split(";");
			if(entry[6].equals(courseIndex)){
				if(entry[7].equals("WAITLIST") && counter > 0){
					entry[7] = "ACCEPTED";
					recordList.add(String.join(";",entry));
				}else{
					recordList.add(line);
				}
			}else {
				recordList.add(line);
			}

		}
		fr.close();

		try {
			file = new File(System.getProperty("user.dir") + "/src/registeredRecords");    //creates a new file instance
			Scanner scanner = new Scanner(file);
			Writer output;
			PrintWriter pw = new PrintWriter(new FileOutputStream(file));
			for(int i=0; i<recordList.size();i++){
				pw.println(recordList.get(i));
			}
			pw.close();
			return "Waitlist updated accordingly.";
		}catch(Exception ex){
			ex.printStackTrace();
			return "Error updating waitlist.";
		}




	}

}
