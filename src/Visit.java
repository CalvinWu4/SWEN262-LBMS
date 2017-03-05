import java.util.Date;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Visit {
    private int isbn;
    private int visitorId;
    private Date dateBorrowed;
    private Date dueDate;

    Visit(int isbn, int visitorID, Date dateBorrowed, Date dueDate){
        this.isbn = isbn;
        this.visitorId = visitorID;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dueDate;
    }

    // Setters
    public void setIsbn(){
        this.isbn = isbn;
    }
    public void setVisitorID(){
        this.visitorId = visitorId;
    }
    public void setDateBorrowed(){
        this.dateBorrowed = dateBorrowed;
    }
    public void setDueDate(){
        this.dueDate = dueDate;
    }

    // Getters
    public int getIsbn(){
        return this.isbn;
    }
    public int getVisitorID(){
        return this.visitorId;
    }
    public Date getDateBorrowed(){
        return this.dateBorrowed;
    }
    public Date getDueDate(){
        return this.dueDate;
    }
}
