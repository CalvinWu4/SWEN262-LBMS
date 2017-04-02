package Library;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by Calvin on 3/5/2017.
 *
 */
public class Transaction implements Serializable{
    private Long isbn;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;
    private static Fine fine;

    Transaction(Long isbn, LocalDate dateBorrowed, LocalDate dueDate){
        this.isbn = isbn;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dateBorrowed.plusWeeks(1);
    }

    public Integer calculateFee(){
        fine = new Fine();
        if((Time.getDate().isBefore(this.getDueDate()))){
            return fine.getBalance();
        }
        else{
            fine.incBalance(ChronoUnit.WEEKS.between(this.getDueDate(), Time.getDate()));
            return fine.getBalance();
        }
    }

    // Setters
    public void setIsbn(Long isbn){
        this.isbn = isbn;
    }
    public void setDateBorrowed(LocalDate dateBorrowed){
        this.dateBorrowed = dateBorrowed;
    }
    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    // Getters
    public Long getIsbn(){
        return this.isbn;
    }
    public LocalDate getDateBorrowed(){
        return this.dateBorrowed;
    }
    public LocalDate getDueDate(){
        return this.dueDate;
    }
    public Fine getFine(){
        return fine;
    }
}