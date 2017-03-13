import java.time.LocalDate;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Transaction {
    private int isbn;
    private int visitorId;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;

    Transaction(int isbn, int visitorID, LocalDate dateBorrowed, LocalDate dueDate){
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
    public LocalDate getDateBorrowed(){
        return this.dateBorrowed;
    }
    public LocalDate getDueDate(){
        return this.dueDate;
    }
}
