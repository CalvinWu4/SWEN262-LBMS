import java.util.ArrayList;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Visitor {
    private String firstName;
    private String lastName;
    private String address;
    private Integer phone;
    private String id;
    private ArrayList<Visit> visits;

    Visitor(String firstName, String lastName, String address, int phone, String id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.id = id;
    }

    // Setters
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(int phone){
        this.phone = phone;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setVisits (ArrayList<Visit> visitArrayList){
        this.visits = visits;
    }

    // Getters
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getAddress(){
        return this.address;
    }
    public Integer getPhone(){
        return this.phone;
    }
    public String getId(){
        return this.id;
    }
    public ArrayList<Visit> getVisits(){
        return this.visits;
    }
}