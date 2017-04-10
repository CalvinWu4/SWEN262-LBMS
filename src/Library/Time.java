package Library;

import Library.State.OpenClosedContext;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by Calvin on 3/15/2017.
 */
public final class Time extends Database implements Serializable{
    private final static LocalDateTime startDateTime = LocalDateTime.of(2017, 4, 1, 8, 0);
    private static LocalDateTime dateTime;
    private static final int MINDAYINC = 0;
    private static final int MAXDAYINC = 7;
    private static final int MINHRINC = 0;
    private static final int MAXHRINC = 23;
    private static final LocalTime openingTime = LocalTime.of(7,59);
    private static final LocalTime closingTime = LocalTime.of(19,0);
    // Context field that will check the state of the library for open or closed
    static private OpenClosedContext context = new OpenClosedContext();
    private static final File FILE = new File("time.ser");


    public Time() {
        dateTime = startDateTime;
        context = new OpenClosedContext();
        load();
    }

    public static void load(){
        if(read(FILE) != null){
            dateTime = (LocalDateTime)read(FILE);
        }
    }

    public static void save(){
        write(dateTime, FILE);
    }

    /**
     * Helper function for incTime.
     */
    public static void checkIsOpenAndClose(){
        if(dateTime.isAfter(LocalDateTime.of(dateTime.getYear(),dateTime.getMonth(),dateTime.
                getDayOfMonth(),openingTime.getHour(),openingTime.getMinute())) &&
                dateTime.isBefore(LocalDateTime.of(dateTime.getYear(),dateTime.getMonth(),dateTime.
                        getDayOfMonth(),closingTime.getHour(),closingTime.getMinute()))){
            context.toggleOpenClosed();
        }else {
            context.toggleOpenClosed();
            exitActiveVisitors();
        }
    }

    public static String incTime(Integer days, Integer hours) {
        if (days < MINDAYINC || days > MAXDAYINC) {
            return ("Invalid number of days entered.");
        } else if (hours < MINHRINC || hours > MAXHRINC || days == MINDAYINC && hours == MINHRINC) {
            return ("Invalid number of hours entered.");
        } else {
            dateTime = dateTime.plusDays(days);
            dateTime = dateTime.plusHours(hours);
            if(days > 0){
                exitActiveVisitors();
            }
            if(hours > 0) {
                checkIsOpenAndClose();
            }
            for (ArrayList<Transaction> transactions : Transactions.getMap().values()){
                for(Transaction transaction: transactions){
                    //Only keep calculating fees for transactions if they haven't been returned
                    if(transaction.getReturnedDate() == null)
                        transaction.calculateFee();
                }
            }
        }
        return ("Success");
    }

    static public OpenClosedContext getTimeContext(){
        return context;
    }

    public static String display(){
        return dateTime.toString();
    }

    // Helper function to exit all active visitors when the library closes
    static public void exitActiveVisitors(){
        for(Visitor visitor: Visitors.getMap().values()) {
            if(visitor.getActiveVisit() != null){
                Visits.leave(visitor.getId(), closingTime);
            }
        }
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
}
