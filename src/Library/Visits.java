package Library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Calvin on 3/19/2017.
 */
public final class Visits implements DB{
    private static HashMap<LocalDate, ArrayList<Visit>> visitHash = new HashMap<>();

    public static void visit(String visitorID){
        if(!Visitors.getVisitorHash().containsKey(visitorID)){
            System.out.println("The specified visitor ID is not a valid ID or has not been assigned to any " +
                    "registered visitor.");
        }else{
            Visitors.getVisitorHash().get(visitorID).startVisit();
        }
    }

    public static void leave(String visitorID) {
        if(!Visitors.getVisitorHash().containsKey(visitorID)){
            System.out.println("The specified visitor ID is not a valid ID or has not been assigned to any " +
                    "registered visitor.");
        }else{
            Visitors.getVisitorHash().get(visitorID).endVisit();
        }
    }

    public static HashMap<LocalDate, ArrayList<Visit>> getVisitHash(){
        return visitHash;
    }

    @Override
    public Object addValue(Object value) {
        return null;
    }

    @Override
    public Object getValue(Object key) {
        return null;
    }

    @Override
    public boolean hasKey(Object key) {
        return false;
    }

    @Override
    public Collection getAllValues() {
        return null;
    }
}
