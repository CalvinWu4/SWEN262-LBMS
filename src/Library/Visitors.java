package Library;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * A Database for the visitor Object
 * Created by cxw7054 on 3/10/2017.
 *
 *
 */
public final class Visitors extends Database{

    private static TreeMap<Integer, Visitor> map; // visitorId, Visitor
    private static final File FILE = new File("visitors.ser");


    public Visitors() {
        map = new TreeMap<>();
        load();
    }

    public static void load(){
        if(read(FILE) != null){
            map = (TreeMap<Integer, Visitor>)read(FILE);
        }
    }

    public static void save(){
        write(map, FILE);
    }

    public static String register(String firstName, String lastName, String address, String phone){
        for(Visitor visitor: map.values()) {
            if (visitor.getFirstName().equals(firstName) && visitor.getLastName().equals(lastName) &&
                    visitor.getAddress().equals(address) && visitor.getPhone().equals(phone)) {
                return("A visitor with the same name, address, and phone number is already registered.\n");
            }
        }
        DecimalFormat tenDigit = new DecimalFormat("0000000000");
        if(map.containsKey(1)) {
            Visitor newVisitor = new Visitor(firstName, lastName, address, phone, map.lastKey() + 1, Time.getDate());
            map.put(map.lastKey() + 1, newVisitor);
        }
        else{
            Visitor newVisitor = new Visitor(firstName, lastName, address, phone, 1, Time.getDate());
            map.put(1,newVisitor);
        }
        return("Visitor ID:"+ tenDigit.format(map.lastKey()) + " has been registered on " +
                Time.getDate() + ".");
    }

    public static TreeMap<Integer, Visitor> getMap(){
        return map;
    }
}
