package Library;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;


/**
 * Created by Jp on 4/5/17.
 */
public final class Users extends Database{

    private static TreeMap<Integer, User> map; // visitorId, Visitor
    private static final File FILE = new File("users.ser");


    public Users() {
        map = new TreeMap<>();
        load();
    }


    public static String create(String username, String password, String role, String visitorID){
        for(User user: map.values()) {
            if (user.getUsername().equals(username) && user.getVisitorID().equals(visitorID)) {
                return("A visitor with the same name, address, and phone number is already registered.\n");
            }
        }
        Integer visitorIdInt;
        try{
            visitorIdInt = Integer.parseInt(visitorID.replace(";",""));
            if(!Visitors.getMap().containsKey(visitorIdInt)){
                return "Visitor ID given does not exist";
            }

        }catch (Exception e){

            System.out.println("Error: "+e);

            return "Given visitor ID is not a valid id number";
        }

        Integer userID;

        if(!map.isEmpty()){
            userID = map.lastKey() + 1;
            map.put(userID, new User(userID, username, password, role, visitorIdInt));
        }else{
            userID = 1;
            map.put(userID, new User(userID, username, password, role, visitorIdInt));
        }
        return("User ID:"+ userID + " has been registered on " +
                Time.getDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + ".");
    }

    public static void save() {
        write(map, FILE);
    }

    public static void load() {
        if(read(FILE) != null){
            map = (TreeMap<Integer, User>)read(FILE);

            for(User user : map.values()){
                System.out.println(user.getVisitorID()+"-"+user.getUsername()+"-"+user.getPassword());
            }
        }
    }

    public static TreeMap<Integer, User> getMap(){
        return map;
    }

}
