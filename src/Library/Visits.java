package Library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Calvin on 3/19/2017.
 */
public class Visits {
    private static HashMap<LocalDate, ArrayList<Visit>> visitHash = new HashMap<>();

    public static void visit(String visitorID){
        if(!Visitors.getVisitorHash().containsKey(visitorID)){
            System.out.println("The specified visitor ID is not a valid ID or has not been assigned to any " +
                    "registered visitor.");
        }
        else {
                for (ArrayList<Visit> visits : visitHash.values()) {
                    for(Visit visit: visits){
                        if (visit.getDeparture() == null) {
                            System.out.println("A visitor with the id is already visiting the library.");
                            break;
                        }
                    }
            }
            Visit newVisit = new Visit(visitorID, Time.getDate(), Time.getTime(), null);
            if(visitHash.get(Time.getDate()).isEmpty()){
                ArrayList<Visit> visits = new ArrayList<>();
                visits.add(newVisit);
            }
            else{
                visitHash.get(Time.getDate()).add(newVisit);
            }
        }
    }

    public static void leave(String visitorID) {
        for (ArrayList<Visit> visits : visitHash.values()) {
            for (Visit visit : visits) {
                if (visit.getDeparture() == null) {
                    visit.setDeparture(Time.getTime());
                }
            }
        }
    }

    public static HashMap<LocalDate, ArrayList<Visit>> getVisitHash(){
        return visitHash;
    }
}
