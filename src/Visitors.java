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
        Visitor newVisitor = new Visitor(firstName, lastName, address, phone, count++);
        visitorHash.put(newVisitor.getId(), newVisitor);
    }

    public void visit(Integer visitorId){
        Visitor visitor = visitorHash.get(visitorId);
        visitor.addVisit(visitorId, new Date(),null);
    }
}
