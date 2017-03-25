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
    private final static LocalDateTime startDateTime = LocalDateTime.of(2017, 3, 20, 2, 0);
    private static LocalDateTime dateTime = LocalDateTime.of(2017, 3, 20, 2, 0);
    private static boolean isOpen;


    /**
     * Helper function for incTime.
     */
    public static void checkIsOpenAndClose(){
        if(dateTime.isAfter(LocalDateTime.of(dateTime.getYear(),dateTime.getMonth(),dateTime.getDayOfMonth(),7,59)) &&
                dateTime.isBefore(LocalDateTime.of(dateTime.getYear(),dateTime.getMonth(),dateTime.getDayOfMonth(),19,0))){
            isOpen = true;
        }else {
            isOpen = false;
            for(Visitor visitor: Visitors.getVisitorHash().values()){
                if(visitor.getActiveVisit() != null) {
                    visitor.getActiveVisit().setDeparture(LocalDateTime.now());
                }
            }
        }

    }

    public static String incTime(Integer days, Integer hours) {
        if (days < 0 || days > 7) {
            return ("Invalid number of days entered.");
        } else if (hours < 0 || hours > 23 || days == 0 && hours == 0) {
            return ("Invalid number of hours entered.");
        } else {
            dateTime = dateTime.plusDays(days);
            dateTime = dateTime.plusHours(hours);
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
    public static LocalDateTime getStartDateTime(){
        return startDateTime;
    }
    public static boolean getIsOpen(){
        return isOpen;
    }
}
