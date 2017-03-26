package Library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Calvin on 3/19/2017.
 */
public final class Visits {
    private static HashMap<LocalDate, ArrayList<Visit>> visitHash = new HashMap<>();

    public static String visit(String visitorID){
        if(!Visitors.getVisitorMap().containsKey(visitorID)){
            return("The specified visitor ID is not a valid ID or has not been assigned to any " +
                    "registered visitor.");
        }else{
            return Visitors.getVisitorMap().get(visitorID).startVisit();

        }
    }

    public static String leave(String visitorID) {
        if(!Visitors.getVisitorMap().containsKey(visitorID)){
            return("The specified visitor ID is not a valid ID or has not been assigned to any " +
                    "registered visitor.");
        }else{
            return Visitors.getVisitorMap().get(visitorID).endVisit();
        }
    }

    public static HashMap<LocalDate, ArrayList<Visit>> getVisitHash(){
        return visitHash;
    }
}
