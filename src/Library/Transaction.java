package Library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import Library.*;

/**
 * Created by Calvin on 3/5/2017.
 *
 */
public class Transaction {
    private Long isbn;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;
    private Fine fine;

    Transaction(Long isbn, LocalDate dateBorrowed, LocalDate dueDate){
        this.isbn = isbn;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dateBorrowed.plusDays(7);
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

    public Integer calculateFee(Book book){
        fine = new Fine();
        LocalDate present = LocalDate.now();
        if((present.isBefore(this.getDueDate()))){
            return fine.getCost();
        }
        else{
            long weeksInYear = ChronoUnit.WEEKS.between(this.getDueDate(), present);
            fine.addCost(weeksInYear);
            return fine.getCost();
        }
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
        return this.fine;
    }
}