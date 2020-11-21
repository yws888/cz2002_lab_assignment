package entity;
import java.io.*;
import java.util.ArrayList;
public class AccessPeriod {
	
	private String startDate;
	private String endDate;

	private String startTime;
	private String endTime;


	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public AccessPeriod(String startDate, String endDate, String startTime, String endTime) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public AccessPeriod() {
	}

	
	public static void createAccessPeriod(String startDate,  String endDate,String startTime, String endTime) {
		try {
			File file = new File(System.getProperty("user.dir") + "/src/StudentAccessPeriod");    //creates a new file instance
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, false)); //changed to false to overwrite the previous one,
			pw.println(startDate +";" + endDate +";" + startTime +";" +endTime);
			pw.close();
		}catch(Exception ex){

		}
	}
	public static AccessPeriod retrieveAccessPeriod() { 		
		AccessPeriod accessPeriod = null;
		try {
			File file=new File(System.getProperty("user.dir")+"/src/StudentAccessPeriod");    //creates a new file instance
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
