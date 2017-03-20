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
    public static String checkIsOpenAndClose(){
        if(dateTime.isAfter(ChronoLocalDateTime.from(LocalTime.of(7,59))) &&
                dateTime.isBefore(ChronoLocalDateTime.from(LocalTime.of(19,0)))){
            isOpen = true;
            return("The library is open");
        }else {
            isOpen = false;
            for(Visitor visitor: Visitors.getVisitorHash().values()){
                if(visitor.getActiveVisit() != null) {
                    visitor.getActiveVisit().setDeparture(LocalDateTime.now());
                }
            }
            return("The library has now closed");
        }

    }

    public static String incTime(Integer days, Integer hours) {
        if (days < 0 || days > 7) {
            return ("Invalid number of days entered.");
        } else if (hours < 0 || hours > 23 || days == 0 && hours == 0) {
            return ("Invalid number of hours entered.");
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
