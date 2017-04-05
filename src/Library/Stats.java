package Library;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Calvin on 3/12/2017.
 */
public class Stats {


    static public String report(Integer days) {
        int daysPassed = (int) Duration.between(Time.getStartDateTime(), Time.getDateTime()).toDays();
        if (days <= 0) {
            return ("Invalid number of days.");
        }
        if (days > daysPassed) {
            days = daysPassed;
        }
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
            // Number of Visitors
            for (Visitor visitor : Visitors.getMap().values()) {
                if (visitor.getRegDate().equals(queriedDate)) {
                    numVisitorsTotal++;
                }
            }
            // Average Length of Visit
            Duration totalHrs = Duration.ofHours(0);
            int numVisits = 0;
            if(Visits.getMap().get(queriedDate) != null) {
                for (Visit visit : Visits.getMap().get(queriedDate)) {
                    numVisits++;
                    totalHrs = totalHrs.plusHours(Duration.between(visit.getArrival(), visit.getDeparture()).toHours());
                }
            }
            if(numVisits > 0) {
                visitAvg = (int) totalHrs.toHours() / numVisits;
            }
            else{
                visitAvg = (int) totalHrs.toHours();
            }
            // Number of Books Purchased
            Integer numPurchased = Purchases.getMap().get(queriedDate);
            if (numPurchased != null && numPurchased > 0) {
                numPurchasedTotal += numPurchased;
            }
            // Fines Collected and Outstanding
            for (ArrayList<Transaction> transactions : Transactions.getMap().values()) {
                for (Transaction transaction : transactions) {
                    Fine fine = transaction.getFine();
                    if (fine != null) {
                        finesCollectedTotal += fine.getPaid();
                        finesOutstandingTotal += fine.getBalance();
                    }
                }
            }
        }
        String reportString = "Number of Books:" + numBooksTotal + "\n" +
                "Number of Visitors:" + numVisitorsTotal + "\n" +
                "Average Length of Visit:" + visitAvg + "\n" +
                "Number of Books Purchased:" + numPurchasedTotal + "\n" +
                "Fines Collected:" + finesCollectedTotal + "\n" +
                "Fines Outstanding:" + finesOutstandingTotal + "\n";
        return reportString;
    }
}
