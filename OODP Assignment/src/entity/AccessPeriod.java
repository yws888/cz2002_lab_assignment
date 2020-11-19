package entity;
import java.io.*;
import java.util.ArrayList;
public class AccessPeriod {
	
	private String ap;
	private String apt;
	public String getap() {
		return ap;
	}
	
	public String getapt() {
		return apt;
	}
	public void setap(String ap) {
		this.ap = ap;
	}
	public void setapt(String apt) {
		this.apt = apt;
	}
	public AccessPeriod() {
	}
	public AccessPeriod(String ap,String apt) {
		this.setap(ap);
		this.setapt(apt);
	}
	
	public void createAccessPeriod(String ap, String apt) {
		try {

			File file = new File(System.getProperty("user.dir") + "/src/StudentAccessPeriod");    //creates a new file instance
			Writer output;
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
			pw.println(ap +";" +apt);
			pw.close();
		}catch(Exception ex){

		}
	}
	public static ArrayList<AccessPeriod> retrieveAccessPeriod() { 		
		ArrayList<AccessPeriod> aparray = new ArrayList<AccessPeriod>();
		try {
			File file=new File(System.getProperty("user.dir")+"/src/StudentAccessPeriod");    //creates a new file instance
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
			String line;  
			while((line=br.readLine())!=null)  
			{
			String[] entry = line.split(";");
			aparray.add(new AccessPeriod(entry[0],entry[1])); 
			}  
			fr.close();   
			}  
		catch(IOException e){  
			e.printStackTrace();  
			}  
		return aparray;
	}
	
	
	
}