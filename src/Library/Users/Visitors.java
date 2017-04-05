package Library.Users;

import Library.Time;

import java.io.*;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * A Database for the visitor Object
 * Created by cxw7054 on 3/10/2017.
 *
 *
 */
public final class Visitors {

    private static TreeMap<Integer, Visitor> map; // visitorId, Visitor
    public static final File FILE = new File("visitors.ser");



    public Visitors() {
        map = new TreeMap<>();
        try {
            if (!FILE.createNewFile()) {
                load();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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
                Time.getDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + ".");
    }

    public static void save() {
        try
        {
            FileOutputStream fos =
                    new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static void load() {
        try {
            FileInputStream fis = new FileInputStream(FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (TreeMap<Integer, Visitor>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    static public void exitActiveVisitors(){
        for(Visitor visitor: Visitors.getMap().values()){
            if(visitor.getActiveVisit() != null) {
                visitor.getActiveVisit().setDeparture(Time.getDateTime());
            }
        }
    }

    public static TreeMap<Integer, Visitor> getMap(){
        return map;
    }
}
