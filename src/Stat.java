import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

/**
 * Created by Calvin on 3/12/2017.
 */
public class Stat {
    private int numBooksOwned;
    private int numVisitors;
    private Duration avgVisitTime;
    private ArrayList<Book> booksPurchased;
    private BigDecimal amtBookFines;

    Stat(int numBooksOwned, int numVisitors, Duration avgVisitTime, ArrayList<Book> booksPurchased,
         BigDecimal amtBookFines) {
        this.numBooksOwned = numBooksOwned;
        this.numVisitors = numVisitors;
        this.avgVisitTime = avgVisitTime;
        this.booksPurchased = booksPurchased;
        this.amtBookFines = amtBookFines;
    }

    // Setters
    public void setNumBooksOwned(int numBooksOwned) {
        this.numBooksOwned = numBooksOwned;
    }

    public void setNumVisitors(int numVisitors) {
        this.numVisitors = numVisitors;
    }

    public void setAvgVisitTime(Duration avgVisitTime) {
        this.avgVisitTime = avgVisitTime;
    }

    public void setBooksPurchased(ArrayList<Book> booksPurchased) {
        this.booksPurchased = booksPurchased;
    }

    public void setAmtBookFines(BigDecimal amtBookFines) {
        this.amtBookFines = amtBookFines;
    }


    // Getters
    public int getNumBooksOwned() {
        return this.numBooksOwned;
    }

    public int getNumVisitors() {
        return this.numVisitors;
    }

    public Duration getAvgVisitTime() {
        return this.avgVisitTime;
    }

    public ArrayList<Book> getBooksPurchased() {
        return this.booksPurchased;
    }

    public BigDecimal getAmtBookFines() {
        return this.amtBookFines;
    }
}
