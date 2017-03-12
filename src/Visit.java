import java.util.Date;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Visit {
    private int visitId;
    private Date arrival;
    private Date departure;


    Visit(int visitId, Date arrival, Date departure){
        this.visitId = visitId;
        this.arrival = arrival;
        this.departure = departure;
    }

    // Setters
    public void setVisitorID(int visitorID){
        this.visitId = visitId;
    }
    public void setArrival(Date arrival){
        this.arrival = arrival;
    }
    public void setDeparture(Date departure){
        this.departure = departure;
    }

    // Getters
    public int getVisitorID(){
        return this.visitId;
    }
    public Date getArrival(){
        return this.arrival;
    }
    public Date getDeparture(){
        return this.departure;
    }
}
