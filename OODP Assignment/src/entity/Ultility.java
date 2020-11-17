package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ultility {
    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
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
            // ex.printStackTrace();
        }
        return date != null;
    }

    public static boolean isValidTimeInput(String timeStart, String timeEnd) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.format(date);
        try {
            if(dateFormat.parse(timeEnd).after(dateFormat.parse(timeStart)))
            {
                return true;
            }else{
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
