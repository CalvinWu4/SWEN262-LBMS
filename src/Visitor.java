/**
 * Created by Calvin on 3/5/2017.
 */
public class Visitor {
    private String firstName;
    private String lastName;
    private String address;
    private Integer phone;
    private Integer id;

    Visitor(String firstName, String lastName, String address, int phone, int id){
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
    public void setID(int ID){
        this.id = id;
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
    public int getPhone(){
        return this.phone;
    }
    public int getID(){
        return this.id;
    }
}