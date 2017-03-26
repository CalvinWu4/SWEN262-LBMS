package Library;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;

/**
 * A Database for the visitor Object
 * Created by cxw7054 on 3/10/2017.
 *
 *
 */
public final class Visitors {
    private static HashMap<String, Visitor> visitorHash = new HashMap<>(); // visitorId, Visitor
    private static final String VISITORSFILE = "libraryVisitors.csv";
    private static Integer count = 1;


    public static String register(String firstName, String lastName, String address, String phone){
        for(Visitor visitor: visitorHash.values()) {
            if (visitor.getFirstName().equals(firstName) && visitor.getLastName().equals(lastName) &&
                    visitor.getAddress().equals(address) && visitor.getPhone().equals(phone)) {
                return("A visitor with the same name, address, and phone number is already registered.\n");
            }
        }
        DecimalFormat tenDigit = new DecimalFormat("0000000000");
        String visitorId = tenDigit.format(count);
        Visitor newVisitor = new Visitor(firstName, lastName, address, phone, visitorId, Time.getDate());
        visitorHash.put(newVisitor.getId(), newVisitor);
        count++;
        saveToFile();
        return("Visitor ID:"+ visitorId + " has been registered on " +
                Time.getDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + ".");
    }

    public static void saveToFile(){
        try {
            FileWriter fw = new FileWriter(VISITORSFILE);
            PrintWriter pw = new PrintWriter(fw,true);

            for(Visitor visitor: visitorHash.values()) {
                pw.write(visitor.getFirstName() + "," + visitor.getLastName() + "," +
                        visitor.getAddress() + "," + visitor.getPhone() + "," + visitor.getId() + "," + visitor.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n");
            }
            fw.flush();
            pw.close();
            fw.close();
        }
        catch (IOException ioe){
            System.out.println("Error writing to file.");
        }
    }


    public static HashMap<String, Visitor> getVisitorHash(){
        return visitorHash;
    }
}
