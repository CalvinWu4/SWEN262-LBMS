package Library;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private LocalDate regDate;
    private Visit activeVisit;

    Visitor(String firstName, String lastName, String address, int phone, String id, LocalDate regDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.id = id;
    }


    public void startVisit(){
        if(this.activeVisit == null){
            this.activeVisit = new Visit(this.id, Time.getDateTime());
        }else{
            System.out.println("The given user already has an active visit");
        }

    }

    public void endVisit(){


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
    public void setRegDate(LocalDate date) {
        this.regDate = regDate;
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
    public Visit getActiveVisit(){
        return this.activeVisit;
    }
    public LocalDate getRegDate(){
        return this.regDate;
    }

}