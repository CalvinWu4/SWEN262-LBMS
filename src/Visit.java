import java.time.LocalTime;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Visit {
    private LocalTime arrival;
    private LocalTime departure;


    Visit(LocalTime arrival, LocalTime departure){
        this.arrival = arrival;
        this.departure = departure;
    }

    // Setters
    public void setArrival(LocalTime arrival){
        this.arrival = arrival;
    }
    public void setDeparture(LocalTime departure){
        this.departure = departure;
    }

    // Getters
    public LocalTime getArrival(){
        return this.arrival;
    }
    public LocalTime getDeparture(){
        return this.departure;
    }
}
