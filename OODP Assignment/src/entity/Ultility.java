package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility is the class containing oft-used utility methods for input validation
 */

public class Ultility {
	
	  /**
	   * Validates whether an input is numeric
	   * 
	   * @param input   input from user
	   * @return        boolean whether input is numeric or not
	   */
	
    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
    
    /**
	   * Validates whether an input  has the correct date / time format
	   * 
	   * @param format   	date / time format
	   * @param value       input value
	   * @return        	boolean whether input  has the correct date / time format
	   */
    
    
    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            return false;
        }
        return date != null;
    }
    
    /**
	   * Validates whether an input  has the correct time start and time end format.
	   * 
	   * @param timeStart   	Starting time in HH:mm
	   * @param timeEnd             Ending time in HH:mm
	   * @return        	        boolean whether input  has the correct start and end time format
	   */
    public static boolean isValidTimeInput(String timeStart, String timeEnd) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.format(date);
        try {
            if(dateFormat.parse(timeEnd).after(dateFormat.parse(timeStart)))
            {
                return true;
            }
			return false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    
   /**
	   * Validates whether an input  has the correct time range.
	   * 
	   * @param dateStart   Starting Date
	   * @param dateEnd     Ending date
	   * @param timeStart	Starting Time
	   * @param timeEnd	Ending Time
	   * @return        	boolean whether input has the correct time range.
	   */
    public static boolean isValidTimeRange(String dateStart, String dateEnd, String timeStart, String timeEnd) {
      //  Date date = new Date();
       // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       // dateFormat.format(date);
    	
            if(dateEnd.compareTo(dateStart)>=0 && timeEnd.compareTo(timeStart)>0)
            {
                return true;
            }
			return false;
            

    }
    

}
