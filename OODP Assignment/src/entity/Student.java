package entity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Student extends User{

	public Student() {
	}
	public Student(String username) {
		this.setUsername(username);
	}
	public Student retrieveStudentInfo(String username) {
		Student student = new Student();
		ArrayList<User> studentlist = retrieveStudentLoginDetails();
		for(int i = 0;i < studentlist.size();i++){
			if(studentlist.get(i).getUsername().equals(username)) {
				student.setUsername(studentlist.get(i).getUsername());
				return student;
			}
		}
		return student;
		
	}
	public ArrayList<User> retrieveStudentLoginDetails() { 		
		ArrayList<User> userarray = new ArrayList<User>();
		try {
			File file=new File(System.getProperty("user.dir")+"/OODP Assignment/src/Students");    //creates a new file instance  
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
			String line;  
			while((line=br.readLine())!=null)  
			{
			String[] entry = line.split(";");
			userarray.add(new User(entry[0], entry[1], entry[2])); //0 is username, 1 is password
			}  
			fr.close();    //closes the stream and release the resources  
			}  
		catch(IOException e){  
			e.printStackTrace();  
			}  
		return userarray;
	}
	public static void main(String args[]) {
		Student student = new Student();
		System.out.println(student.retrieveStudentLoginDetails());
	}

}
