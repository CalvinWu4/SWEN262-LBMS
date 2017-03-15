import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Created by cxw7054 on 3/10/2017.
 */
public final class Visitors {
    private static HashMap<String, Visitor> visitorHash = new HashMap<>();
    private static Integer count;
    static DecimalFormat tenDigit = new DecimalFormat("0000000000");

    public static void register(String firstName, String lastName, String address, Integer phone){
        String visitorId = tenDigit.format(count);
        Visitor visitor = new Visitor(firstName, lastName, address, phone, visitorId);
        visitorHash.put(visitor.getId(), visitor);
        count++;
    }

    public static void visit(Visitor visitor){
        Visit visit = new Visit(visitor.getId(), Time.getDate(), Time.getTime(), null);
        visitor.getVisits().add(visit);
    }

    public static void leave(Visitor visitor){
        for (Visit v: visitor.getVisits()) {
            if(v.getDeparture() == null){
                v.setDeparture(Time.getTime());
            }
        }
    }

    public static HashMap<String, Visitor> getVisitorHash(){
        return visitorHash;
    }
}
