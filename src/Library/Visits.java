package Library;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Calvin on 3/19/2017.
 */
public final class Visits extends Database{
    private static HashMap<LocalDate, ArrayList<Visit>> map;
    public static final File FILE = new File("visits.ser");


    public Visits() {
        map = new HashMap<>();
        load();
    }

    public static void load(){
        if(read(FILE) != null){
            map = (HashMap<LocalDate, ArrayList<Visit>>)read(FILE);
        }
    }

    public static void save(){
        write(map, FILE);
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

    public static HashMap<LocalDate, ArrayList<Visit>> getMap(){
        return map;
    }
}
