package Library;

import java.time.LocalDate;
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

//    public Integer calculateFee(){
//        if(!(this.isAvailable())){
//            LocalDate present = LocalDate.now();
//            if((present.isBefore(this.getDueDate()))){
//                return fine.getCost();
//            }
//            else{
//                long weeksInYear = ChronoUnit.WEEKS.between(this.getDueDate(), present);
//                fine.addCost(weeksInYear);
//                return fine.getCost();
//            }
//        }
//        return fine.getCost();
//    }

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
}