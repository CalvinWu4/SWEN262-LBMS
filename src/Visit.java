import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Visit {
    private String visitorID;
    private LocalDate date;
    private LocalTime arrival;
    private LocalTime departure;


    Visit(String visitorID, LocalDate date, LocalTime arrival, LocalTime departure){
        this.arrival = arrival;
        this.departure = departure;
    }

    // Setters
    public void setVisitorID(String visitorID){
        this.visitorID = visitorID;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public void setArrival(LocalTime arrival){
        this.arrival = arrival;
    }
    public void setDeparture(LocalTime departure){
        this.departure = departure;
    }

    // Getters
    public String getVisitorID(){
        return this.visitorID;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public LocalTime getArrival(){
        return this.arrival;
    }
    public LocalTime getDeparture(){
        return this.departure;
    }
}
