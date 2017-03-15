import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Calvin on 3/15/2017.
 */
public class Time {
    private static LocalDate date;
    private static LocalTime time;
    private static boolean isOpen;

    public static void setDate(String _date){
        String [] dateArray = _date.split("/");
        Integer year = Integer.parseInt(dateArray[2]);
        Integer month = Integer.parseInt(dateArray[0]);
        Integer day = Integer.parseInt(dateArray[1]);
        date = LocalDate.of(year, month, day);
        // Catch parseInt and LocalDate.of exceptions
    }
    public static void setTime(String _time){
        String [] timeArray = _time.split(":");
        Integer hour = Integer.parseInt(timeArray[0]);
        Integer minute = Integer.parseInt(timeArray[1]);
        time = LocalTime.of(hour, minute);
        if(time.isAfter(LocalTime.of(7,59)) && time.isBefore(LocalTime.of(19,0))){
            isOpen = true;
        }
        else {
            isOpen = false;
        }
        // Catch parseInt and LocalTime.of exceptions
    }

    public static LocalDate getDate(){
        return date;
    }
    public static LocalTime getTime(){
        return time;
    }
    public static boolean getIsOpen(){
        return isOpen;
    }
}
