package entity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Staff extends User{

	public Staff() {
	}
	public Staff(String username) {
		this.setUsername(username);
		
	}
	
	public Staff retrieveStaffInfo(String username) {
		Staff staff = new Staff();
		ArrayList<User> stafflist = retrieveStaffLoginDetails();
		for(int i = 0;i < stafflist.size();i++){
			if(stafflist.get(i).getUsername().equals(username)) {
				staff.setUsername(stafflist.get(i).getUsername());
				return staff;
			}
		}
		return staff;
		
	}
	public ArrayList<User> retrieveStaffLoginDetails() { 
		ArrayList<User> userarray = new ArrayList<User>();
		try {
			File file=new File(System.getProperty("user.dir")+"/src/AdminStaff");    //creates a new file instance

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


}
