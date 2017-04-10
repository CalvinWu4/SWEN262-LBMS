package Library.Database;

import Library.Database.Database;
import Library.Database.Time;
import Library.Database.Visitors;
import Library.Visit;
import Library.Visitor;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


/**
 * Created by Calvin on 3/19/2017.
 */
public final class Visits extends Database {
    private static HashMap<LocalDate, ArrayList<Visit>> map;
    private static final File FILE = new File("visits.ser");


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

    public static String visit(Integer visitorID){
        TreeMap<Integer, Visitor> visitorMap = Visitors.getMap();
        Visitor visitor = visitorMap.get(visitorID);
        if(!visitorMap.containsKey(visitorID)){
            return("The specified visitor ID is not a valid ID or has not been assigned to any " +
                    "registered visitor.");
        }
        if(visitor.getActiveVisit() != null){
            return("A visitor with the ID is already visiting the library");
        }
        Visit visit = new Visit(visitorID, Time.getDateTime());
        visitor.setActiveVisit(visit);
        return ("Visitor ID:" + visitor.getIdString() + " has begun his visit on " + Time.getDate() + " at " + Time.getTime());
    }

    public static String leave(Integer visitorID, LocalTime time) {
        TreeMap<Integer, Visitor> visitorMap = Visitors.getMap();
        Visitor visitor = visitorMap.get(visitorID);
        Visit activeVisit = visitor.getActiveVisit();
        if(!visitorMap.containsKey(visitorID)){
            return("The specified visitor ID is not a valid ID or has not been assigned to any " +
                    "registered visitor.");
        }
        if(activeVisit == null){
            return("A visitor with the ID is not currently visiting the library.");
        }
        activeVisit.setDeparture(activeVisit.getArrival().with(time));
        if(map.get(visitorID) == null){
            ArrayList<Visit> visits = new ArrayList<>();
            visits.add(activeVisit);
            map.put(Time.getDate(), visits);
        }
        else{
            map.get(Time.getDate()).add(activeVisit);
        }
        visitor.setActiveVisit(null);
        return ("Visitor ID:" + visitor.getIdString() + " has ended his visit at " + time + " which lasted for " +
                Duration.between(activeVisit.getArrival(), activeVisit.getDeparture()).toHours() + " hours.");
    }

    public static HashMap<LocalDate, ArrayList<Visit>> getMap(){
        return map;
    }
}
