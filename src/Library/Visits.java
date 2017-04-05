package Library;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Calvin on 3/19/2017.
 */
public final class Visits {
    private static HashMap<LocalDate, ArrayList<Visit>> map = new HashMap<>();
    public static final File FILE = new File("visits.ser");

    public Visits() {
        map = new HashMap<>();
        try {
            if (!FILE.createNewFile()) {
                load();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static String visit(String visitorID){
        if(!Visitors.getMap().containsKey(Integer.parseInt(visitorID))){
            return("The specified visitor ID is not a valid ID or has not been assigned to any " +
                    "registered visitor.");
        }else{
            return Visitors.getMap().get(Integer.parseInt(visitorID)).startVisit();

        }
    }

    public static String leave(String visitorID) {
        if(!Visitors.getMap().containsKey(Integer.parseInt(visitorID))){
            return("The specified visitor ID is not a valid ID or has not been assigned to any " +
                    "registered visitor.");
        }else{
            return Visitors.getMap().get(Integer.parseInt(visitorID)).endVisit();
        }
    }

    public static void save() {
        try
        {
            FileOutputStream fos =
                    new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static void load() {
        try {
            FileInputStream fis = new FileInputStream(FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap<LocalDate, ArrayList<Visit>>) ois.readObject();
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

    public static HashMap<LocalDate, ArrayList<Visit>> getMap(){
        return map;
    }
}
