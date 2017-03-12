import java.util.Date;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Visit {
    private Date arrival;
    private Date departure;


    Visit(int visitId, Date arrival, Date departure){
        this.arrival = arrival;
        this.departure = departure;
    }

    // Setters
    public void setArrival(Date arrival){
        this.arrival = arrival;
    }
    public void setDeparture(Date departure){
        this.departure = departure;
    }

    // Getters
    public Date getArrival(){
        return this.arrival;
    }
    public Date getDeparture(){
        return this.departure;
    }
}
