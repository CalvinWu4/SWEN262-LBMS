package Library;

import FrontEnd.State.OpenClosedContext;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by Calvin on 3/15/2017.
 */
public final class Time implements Serializable{
    private final static LocalDateTime startDateTime = LocalDateTime.of(2017, 3, 20, 2, 0);
    private static LocalDateTime dateTime = LocalDateTime.of(2017, 3, 20, 2, 0);
    private static final int MINDAYINC = 0;
    private static final int MAXDAYINC = 7;
    private static final int MINHRINC = 0;
    private static final int MAXHRINC = 23;
    /** Context field that will check the state of the library for open or closed **/
    static private OpenClosedContext context = new OpenClosedContext();
    private static final File FILE = new File("time.ser");


    public Time() {
        try {
            if (!FILE.createNewFile()) {
                load();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Helper function for incTime.
     */
    public static void checkIsOpenAndClose(){
        if(dateTime.isAfter(LocalDateTime.of(dateTime.getYear(),dateTime.getMonth(),dateTime.getDayOfMonth(),7,59)) &&
                dateTime.isBefore(LocalDateTime.of(dateTime.getYear(),dateTime.getMonth(),dateTime.getDayOfMonth(),19,0))){
            context.toggleOpenClosed();
        }else {
            context.toggleOpenClosed();
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
            checkIsOpenAndClose();
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
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }

    public static void load() {
        try {
            FileInputStream fis = new FileInputStream(FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            dateTime = (LocalDateTime) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    public static void save() {
        try
        {
            FileOutputStream fos =
                    new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dateTime);
            oos.close();
            fos.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    static public void exitActiveVisitors(){
        for(Visitor visitor: Visitors.getMap().values()){
            if(visitor.getActiveVisit() != null) {
                visitor.getActiveVisit().setDeparture(Time.getDateTime());
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
