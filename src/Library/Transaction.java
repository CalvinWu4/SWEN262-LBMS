package Library;

import java.time.LocalDate;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Transaction {
    //we need a transaction id
    private Integer transactionID;
    private Integer bookID;
    private Visitor visitor;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;

    Transaction(Integer book, Visitor visitor, LocalDate dateBorrowed){
        this.bookID = book;
        this.visitor = visitor;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dateBorrowed.plusDays(7);
    }

    // Setters
    public void setVisitorID(String visitorID){
        this.visitor = visitor;
    }
    public void setDateBorrowed(LocalDate dateBorrowed){
        this.dateBorrowed = dateBorrowed;
    }
    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    // Getters
    public Integer getTransactionID(){
        return this.transactionID;
    }
    public Integer getBookID(){
        return this.bookID;
    }
    public Visitor getVisitorID(){
        return this.visitor;
    }
    public LocalDate getDateBorrowed(){
        return this.dateBorrowed;
    }
    public LocalDate getDueDate(){
        return this.dueDate;
    }
}