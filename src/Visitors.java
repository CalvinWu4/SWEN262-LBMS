import javax.lang.model.type.NullType;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by cxw7054 on 3/10/2017.
 */
public class Visitors {
    private HashMap<String, Visitor> visitorHash = new HashMap<>();
    private static Integer count;
    DecimalFormat df = new DecimalFormat("0000000000"); // 10 zeroes

    public void register(String firstName, String lastName, String address, Integer phone){
        String visitorId = df.format(count);
        Visitor visitor = new Visitor(firstName, lastName, address, phone, visitorId);
        visitorHash.put(visitor.getId(), visitor);
        count++;
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
