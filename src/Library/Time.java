package Library;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

/**
 * Created by Calvin on 3/15/2017.
 */
public class Time {
    private static LocalDateTime dateTime;
    private static boolean isOpen;

    public static void checkIsOpen(){
        if(dateTime.isAfter(ChronoLocalDateTime.from(LocalTime.of(7,59))) &&
                dateTime.isBefore(ChronoLocalDateTime.from(LocalTime.of(19,0)))){
            isOpen = true;
        }
        else {
            isOpen = false;
            for(Visitor v: Visitors.getVisitorHash().values()){
                for(Visit v2: v.getVisits()){
                    if(v2.getDeparture().equals(null)){
                        v2.setDeparture(LocalTime.of(8,0));
                    }
                }
            }
        }
    }
    public static void incDate(String days){
        dateTime.plusDays(Integer.parseInt(days));
        for(Visitor v: Visitors.getVisitorHash().values()){
            for(Visit v2: v.getVisits()){
                if(v2.getDeparture().equals(null)){
                    v2.setDeparture(LocalTime.of(8,0));
                }
            }
        }
    }
    public static void incHr(String hours){
        dateTime.plusHours(Integer.parseInt(hours));
        checkIsOpen();
    }
    public static void incMin(String hours){
        dateTime.plusMinutes(Integer.parseInt(hours));
        checkIsOpen();
    }

    //Setters
    public static void setDate(String _date){
        String [] dateArray = _date.split("/");
        Integer year = Integer.parseInt(dateArray[2]);
        Integer month = Integer.parseInt(dateArray[0]);
        Integer day = Integer.parseInt(dateArray[1]);
        dateTime = LocalDateTime.of(year, month, day, dateTime.getHour(), dateTime.getMinute());
        // Catch parseInt and LocalDate.of exceptions
    }
    public static void setTime(String _time){
        String [] timeArray = _time.split(":");
        Integer hour = Integer.parseInt(timeArray[0]);
        Integer minute = Integer.parseInt(timeArray[1]);
        dateTime = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), hour, minute);
        // Catch parseInt and LocalTime.of exceptions
    }

    // Getters
    public static LocalDate getDate(){
        return dateTime.toLocalDate();
    }
    public static LocalTime getTime(){
        return dateTime.toLocalTime();
    }
    public static boolean getIsOpen(){
        return isOpen;
    }
}
