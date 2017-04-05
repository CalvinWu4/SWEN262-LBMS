package Library.Users;

import Library.Time;
import Library.Visit;
import Library.Visits;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

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


    public String startVisit(){
        if(this.activeVisit == null){
            this.activeVisit = new Visit(this.id, Time.getDateTime());
            return "Success";
        }else{
            return "The given user already has an active visit";
        }

    }

    /**
     * Ends the active visit for the visitor
     */
    public String endVisit(){
        if(this.activeVisit != null){
            if(Visits.getVisitHash().get(this.activeVisit.getArrival()) == null){
                ArrayList<Visit> newVisits = new ArrayList<>();
                newVisits.add(this.activeVisit);
                Visits.getVisitHash().put(this.activeVisit.getArrival().toLocalDate(),newVisits);
            }
            this.activeVisit = new Visit(this.id, Time.getDateTime());
            return "Success";

        }else{
            return "The given user doesn't have an active visit";
        }

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
    public Visit getActiveVisit(){
        return this.activeVisit;
    }
    public LocalDate getRegDate(){
        return this.regDate;
    }

}