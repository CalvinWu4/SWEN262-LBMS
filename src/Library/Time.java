package Library;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Calvin on 3/15/2017.
 */
public final class Time {
    private static LocalDateTime dateTime = LocalDateTime.of(2017, 3, 20, 2, 0);
    private static boolean isOpen;


    /**
     * Helper function for incTime.
     */
    public static void checkIsOpenAndClose(){
        if(dateTime.isAfter(ChronoLocalDateTime.from(LocalTime.of(7,59))) &&
                dateTime.isBefore(ChronoLocalDateTime.from(LocalTime.of(19,0)))){
            isOpen = true;
            System.out.println("The library is open");
        }else {
            isOpen = false;
            for(Visitor visitor: Visitors.getVisitorHash().values()){
                if(visitor.getActiveVisit() != null) {
                    visitor.getActiveVisit().setDeparture(LocalDateTime.now());
                }
            }
            System.out.println("The library has now closed");
        }

    }
    public static String incTime(Integer days, Integer hours) {
        if (days < 0 || days > 7) {
            return ("Error (invalid number of days)");
        } else if (hours < 0 || hours > 23 || days == 0 && hours == 0) {
            return ("Error (invalid number of hours)");
        } else {
            dateTime.plusDays((days));
            dateTime.plusHours(hours);
            checkIsOpenAndClose();
            return ("Success");
        }
    }

    public static String display(){
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }

    //Setters (unneeded)
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
    public static LocalDateTime getDateTime(){
        return dateTime;
    }


    public static boolean getIsOpen(){
        return isOpen;
    }
}
