package Library;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Calvin Wu on 3/5/2017.
 *
 * The Visit class for all visitors. It creates one visit every time a visitor visits the Library.
 *
 */
public class Visit {
    private String visitorID;
    private LocalDateTime arrival;
    private LocalDateTime departure;


    Visit(String visitorID, LocalDateTime arrival){
        this.visitorID = visitorID;
        this.arrival = arrival;
    }

    // Setters
    public void setVisitorID(String visitorID) {
        this.visitorID = visitorID;
    }
    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }
    public void setDeparture(LocalDateTime departure){
        this.departure = departure;
    }

    // Getters
    public String getVisitorID(){
        return this.visitorID;
    }
    public LocalDateTime getArrival(){
        return this.arrival;
    }
    public LocalDateTime getDeparture(){
        return this.departure;
    }
}
