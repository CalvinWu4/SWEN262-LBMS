package Library;

import java.time.LocalDate;

/**
 * Created by Calvin on 3/5/2017.
 */
public class Transaction {
    private Integer isbn;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;

    Transaction(Integer isbn, LocalDate dateBorrowed, LocalDate dueDate){
        this.isbn = isbn;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dateBorrowed.plusDays(7);
    }

    // Setters
    public void setIsbn(Integer isbn){
        this.isbn = isbn;
    }
    public void setDateBorrowed(LocalDate dateBorrowed){
        this.dateBorrowed = dateBorrowed;
    }
    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    // Getters
    public Integer getIsbn(){
        return this.isbn;
    }
    public LocalDate getDateBorrowed(){
        return this.dateBorrowed;
    }
    public LocalDate getDueDate(){
        return this.dueDate;
    }
}