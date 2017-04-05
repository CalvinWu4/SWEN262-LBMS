package Library.Users;

import Library.Time;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;


/**
 * Created by Jp on 4/5/17.
 */
public final class Users {


    private static TreeMap<Integer, User> map; // visitorId, Visitor
    public static final File FILE = new File("visitors.ser");



    public Users() {
        map = new TreeMap<>();
        try {
            if (!FILE.createNewFile()) {
                load();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static String create(String username, String password, String role, String visitorID){
        for(User user: map.values()) {
//            if (user.getFirstName().equals(firstName) && visitor.getLastName().equals(lastName) &&
//                    user.getAddress().equals(address) && visitor.getPhone().equals(phone)) {
//                return("A visitor with the same name, address, and phone number is already registered.\n");
//            }
        }
        Integer visitorIdInt;
        try{
            visitorIdInt = Integer.parseInt(visitorID);
            if(!Visitors.getMap().containsKey(visitorIdInt)){
                return "Visitor ID given does not exist";

            }



        }catch (Exception e){

            return "Given visitor ID is not a valid id number";
        }
        User newUser = new User(username, password, role, visitorIdInt);


        Integer userID;

        if(!map.isEmpty()){
            userID = map.lastKey() + 1;
            map.put(userID, newUser);
        }else{
            userID = 1;
            map.put(userID, newUser);

        }
        return("User ID:"+ userID + " has been registered on " +
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
            map = (TreeMap<Integer, User>) ois.readObject();
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

    public static TreeMap<Integer, User> getMap(){
        return map;
    }

}
