package entity;
import java.io.*;

/**
 * AccessPeriod is the class representing the Access Period for students
 * All students registered share the same Access Period
 */

public class AccessPeriod {
	
	  /**
		Start Date of access period 	   
		*/
	
	private String startDate;
	
	  /**
		End Date of access period 	   
		*/
	private String endDate;
	  /**
		Start Time of access period 	   
		*/
	private String startTime;
	  /**
		End Time of access period 	   
		*/
	private String endTime;
	/**
	   * Getter method for Start Date of access period
	   * 
	   * @return Start Date of access period
	   */

	public String getStartDate() {
		return startDate;
	}
	/**
	   * Getter method for End Date of access period
	   * 
	   * @return End Date of access period
	   */
	public String getEndDate() {
		return endDate;
	}
	/**
	   * Getter method for Start Time of access period
	   * 
	   * @return Start Time of access period
	   */

	public String getStartTime() {
		return startTime;
	}
	/**
	   * Getter method for End Time of access period
	   * 
	   * @return End Time of access period
	   */

	public String getEndTime() {
		return endTime;
	}
	
	  /**
	   * Constructor for AccessPeriod class.
	   * 
	   * @param startDate                   start Date
	   * @param endDate                  	end Date
	   * @param startTime               	start Time
	   * @param endTime           			end Time
	   */

	public AccessPeriod(String startDate, String endDate, String startTime, String endTime) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	  /**
	   * create/modify Access Period by (over)writing the text file.
	   * 
	   * @param startDate                   start Date
	   * @param endDate                  	end Date
	   * @param startTime               	start Time
	   * @param endTime           			end Time
	   */
	
	public static void createAccessPeriod(String startDate,  String endDate,String startTime, String endTime) {
		try {
			File file = new File("src/StudentAccessPeriod");    //creates a new file instance
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, false)); //changed to false to overwrite the previous one,
			pw.println(startDate +";" + endDate +";" + startTime +";" +endTime);
			pw.close();
		}catch(Exception ex){
				System.out.println("an error occurred");
		}
	}
	/**
	   * retrieves and returns AccessPeriod object by reading from text file
	   * 
	   * @return                          course object
	   */
	
	
	public static AccessPeriod retrieveAccessPeriod() { 		
		AccessPeriod accessPeriod = null;
		try {
			File file=new File("src/StudentAccessPeriod");    //creates a new file instance
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
			String line;  
			while((line=br.readLine())!=null)  
			{
			String[] entry = line.split(";");
			accessPeriod = new AccessPeriod(entry[0],entry[1], entry[2], entry[3]); 
			}  
			fr.close();   
			}  
		catch(IOException e){  
			e.printStackTrace();  
			}  
		return accessPeriod;
	}
	
	
	
}
