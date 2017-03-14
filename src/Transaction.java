import java.time.LocalDate;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Transaction {
    private Book book;
    private Visitor visitor;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;

    Transaction(Book book, Visitor visitor, LocalDate dateBorrowed, LocalDate dueDate){
        this.book = book;
        this.visitor = visitor;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dueDate;
    }

    // Setters
    public void setIsbn(){
        this.book = book;
    }
    public void setVisitorID(){
        this.visitor = visitor;
    }
    public void setDateBorrowed(){
        this.dateBorrowed = dateBorrowed;
    }
    public void setDueDate(){
        this.dueDate = dueDate;
    }

    // Getters
    public Book getIsbn(){
        return this.book;
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
