package Library;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Calvin Wu on 3/5/2017.
 *
 * The Visit class for all visitors. It creates one visit every time a visitor visits the Library.
 *
 */
public class Visit implements Serializable{
    private Integer visitorID;
    private LocalDateTime arrival;
    private LocalDateTime departure;


    Visit(Integer visitorID, LocalDateTime arrival){
        this.visitorID = visitorID;
        this.arrival = arrival;
    }

    // Setters
    public void setVisitorID(Integer visitorID) {
        this.visitorID = visitorID;
    }
    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }
    public void setDeparture(LocalDateTime departure){
        this.departure = departure;
    }

    // Getters
    public Integer getVisitorID(){
        return this.visitorID;
    }
    public LocalDateTime getArrival(){
        return this.arrival;
    }
    public LocalDateTime getDeparture(){
        return this.departure;
    }
}
