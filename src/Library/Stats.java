package Library;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Calvin on 3/12/2017.
 */
public class Stats {


    public ArrayList<Integer> report(Integer days) {
        if (days > 0) {
            ArrayList<Integer> reportArray = new ArrayList<>();
            Integer numBooksTotal = 0;
            Integer numVisitorsTotal = 0;
            Integer visitAvg = 0;
            Integer numPurchasedTotal = 0;
            Integer finesCollectedTotal = 0;
            Integer finesOutstandingTotal = 0;
            for (int i = 0; i < days; i++) {
                LocalDate queriedDate = Time.getDate().minusDays(i);
                // Number of Books
                Integer numBooks = 0;
                for (Book book : Books.getBookHash().values()) {
                    numBooks += book.getTotalNumCopies();
                }
                reportArray.add(numBooks);
                // Number of Visitors
                for (Visitor visitor : Visitors.getVisitorHash().values()) {
                    if (visitor.getRegDate().equals(queriedDate)) {
                        numVisitorsTotal++;
                    }
                }
                reportArray.add(numVisitorsTotal);
                // Average Length of Visit
                Duration totalHrs = Duration.ofHours(0);
                int numHrs = 0;
                for (Visit visit : Visits.getVisitHash().get(queriedDate)) {
                    numHrs++;
                    totalHrs.plusHours(Duration.between(visit.getArrival(), visit.getDeparture()).toHours());
                }
                visitAvg = (int) totalHrs.toHours() / numHrs;
                reportArray.add(visitAvg);
                // Number of Books Purchased
                Integer numPurchased = Purchases.getPurchaseHash().get(queriedDate);
                if (numPurchased != null && numPurchased > 0) {
                    numPurchasedTotal += numPurchased;
                }
                reportArray.add(numPurchased);
                // Fines Collected
                // Fines Outstanding
            }
            return reportArray;
        } else {
            System.out.println("Invalid number of days.");
            return null;
        }
    }
}
