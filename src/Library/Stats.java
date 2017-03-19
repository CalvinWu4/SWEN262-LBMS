package Library;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

/**
 * Created by Calvin on 3/12/2017.
 */
public class Stats {


    public String report(Integer days) {
        if (days > 0) {
            Integer numBooksTotal = 0;
            Integer numVisitorsTotal = 0;
            Integer visitAvg = 0;
            Integer numPurchasedTotal = 0;
            Integer finesCollectedTotal = 0;
            Integer finesOutstandingTotal = 0;
            for (int i = 0; i < days; i++) {
                // Number of Books
                Integer numBooks = 0;
                for(Book book: Books.getBookHash().values()){
                    numBooks += book.getTotalNumCopies();
                }
                // Number of Books Purchased
                Integer numPurchased = Purchases.getPurchaseHash().get(Time.getDate().minusDays(i));
                if (numPurchased != null && numPurchased > 0) {
                    numPurchasedTotal += numPurchased;
                }
            }
            String report = "Number of Books Purchased:" + numBooksTotal;
            return report;
        } else {
            return ("Invalid number of days.");
        }
    }
}
