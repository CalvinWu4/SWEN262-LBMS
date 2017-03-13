import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.HashMap;

/**
 * Created by cxw7054 on 3/10/2017.
 */
public class Visitors {
    private HashMap<String, Visitor> visitorHash = new HashMap<>();
    private static Integer count;
    DecimalFormat tenDigit = new DecimalFormat("0000000000");

    public void register(String firstName, String lastName, String address, Integer phone){
        String visitorId = tenDigit.format(count);
        Visitor visitor = new Visitor(firstName, lastName, address, phone, visitorId);
        visitorHash.put(visitor.getId(), visitor);
        count++;
    }

    public void visit(Integer visitorId){
        Visitor visitor = visitorHash.get(visitorId);
        LocalTime localTime = LocalTime.now();
        Visit visit = new Visit(visitorId, localTime,null);
        visitor.getVisits().add(visit);
    }

    public void leave(Integer visitorId){
        Visitor visitor = visitorHash.get(visitorId);
        for (Visit v: visitor.getVisits()) {
            if(v.getDeparture() == null){
                LocalTime localTime = LocalTime.now();
                v.setDeparture(localTime);
            }
        }
    }
}
