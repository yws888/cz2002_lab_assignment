package entity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Staff represents a staff in the school.
*
*/

public class Staff extends User{

	public Staff() {
	}
	public Staff(String username) {
		this.setUsername(username);
		
	}
	
	/**
	   * 
	   * returns Staff object given username input by calling retrieveStaffLoginDetails()
	   * and retrieves corresponding object based on username
	   * 
	   * @param username                  username of the Staff
	   * @return                          Staff object
	   */
	
	
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
	
	/**
	   * retrieves and returns all Staffs as objects by reading from text file
	   * 
	   * @return                          ArrayList<User> of Staff object(s)
	   */
	
	public ArrayList<User> retrieveStaffLoginDetails() { 
		ArrayList<User> userarray = new ArrayList<User>();
		try {
			File file=new File("src/AdminStaff");    

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


}
