package Library;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Visitor implements Serializable{
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private Integer id;
    private LocalDate regDate;
    private Visit activeVisit;

    Visitor(String firstName, String lastName, String address, String phone, Integer id, LocalDate regDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.id = id;
        this.regDate = regDate;
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
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public void setRegDate(LocalDate date) {
        this.regDate = regDate;
    }
    public void setActiveVisit(Visit activeVisit){
        this.activeVisit = activeVisit;
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
    public String getPhone(){
        return this.phone;
    }
    public Integer getId(){
        return this.id;
    }
    public String getIdString(){
        return new DecimalFormat("0000000000").format(id);
    }
    public LocalDate getRegDate(){
        return this.regDate;
    }
    public Visit getActiveVisit(){
        return this.activeVisit;
    }

}