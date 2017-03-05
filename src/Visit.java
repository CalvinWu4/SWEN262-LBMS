import java.util.Date;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Visit {
    private int visitorId;
    private Date arrival;
    private Date departure;


    Visit(int visitorId, Date arrival, Date departure){
        this.visitorId = visitorId;
        this.arrival = arrival;
        this.departure = departure;
    }

    // Setters
    public void setVisitorID(){
        this.visitorId = visitorId;
    }
    public void setArrival(){
        this.arrival = arrival;
    }
    public void setDeparture(){
        this.departure = departure;
    }

    // Getters
    public int getVisitorID(){
        return this.visitorId;
    }
    public Date getArrival(){
        return this.arrival;
    }
    public Date getDeparture(){
        return this.departure;
    }
}
