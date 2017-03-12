import javax.lang.model.type.NullType;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by cxw7054 on 3/10/2017.
 */
public class Visitors {
    private HashMap<Integer, Visitor> visitorHash = new HashMap<>();
    private static int count;

    public void register(String firstName, String lastName, String address, Integer phone){
        Visitor visitor = new Visitor(firstName, lastName, address, phone, count++);
        visitorHash.put(visitor.getId(), visitor);
    }

    public void visit(Integer visitorId){
        Visitor visitor = visitorHash.get(visitorId);
        Visit visit = new Visit(visitorId, new Date(),null);
        visitor.getVisits().add(visit);
    }

    public void leave(Integer visitorId){
        Visitor visitor = visitorHash.get(visitorId);
        for (Visit v: visitor.getVisits()) {
            if(v.getDeparture().equals(null)){
                v.setDeparture(new Date());
            }
        }
    }
}
