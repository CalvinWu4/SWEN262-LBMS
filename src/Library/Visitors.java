package Library;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Created by cxw7054 on 3/10/2017.
 */
public final class Visitors {
    private static HashMap<String, Visitor> visitorHash = new HashMap<>(); // visitorId, Visitor
    private static Integer count;


    public static void register(String firstName, String lastName, String address, Integer phone){
        for(Visitor visitor: visitorHash.values()) {
            if (visitor.getFirstName().equals(firstName) && visitor.getLastName().equals(lastName) &&
                    visitor.getAddress().equals(address) && visitor.getPhone().equals(phone)) {
                System.out.println("A visitor with the same name, address, and phone number is already registered.\n");
                break;
            }
        }
        DecimalFormat tenDigit = new DecimalFormat("0000000000");
        String visitorId = tenDigit.format(count);
        Visitor newVisitor = new Visitor(firstName, lastName, address, phone, visitorId);
        visitorHash.put(newVisitor.getId(), newVisitor);
        count++;
        System.out.println("Visitor ID:"+ visitorId + " has been registered on " +
                Time.getDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + ".");
    }

    public static HashMap<String, Visitor> getVisitorHash(){
        return visitorHash;
    }
}
