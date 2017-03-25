package Library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Calvin on 3/18/2017.
 */
public final class Purchases {
    private static HashMap<LocalDate, Integer> purchaseHash = new HashMap<>();
    // Date, NumBooksPurchased


    public static String purchase(Integer quantity, ArrayList<Long> isbns) {
        ArrayList<Long> seen = new ArrayList<>();
        for (Long isbn : isbns) {
            if (!Books.getBookHash().containsKey(isbn)) {
                return ("One or more of the book ISBNs are not valid.");
            }
            else if(seen.contains(isbn)){
                return ("Book ISBNs must be unique.");
            }
            else {
                seen.add(isbn);
                Integer prevTotalNumCopies = Books.getBookHash().get(isbn).getTotalNumCopies();
                Integer prevNumAvailableCopies = Books.getBookHash().get(isbn).getNumAvailableCopies();
                Books.getBookHash().get(isbn).setTotalNumCopies(prevTotalNumCopies + quantity);
                Books.getBookHash().get(isbn).setNumAvailableCopies(prevNumAvailableCopies + quantity);
                Books.saveToFile();
                if (!purchaseHash.containsKey(Time.getDate())) {
                    purchaseHash.put(Time.getDate(), quantity);
                } else {
                    Integer oldQuantity = purchaseHash.get(Time.getDate());
                    purchaseHash.put(Time.getDate(), oldQuantity + quantity);
                }
            }
        }
        return ("Success");
    }

    public static HashMap<LocalDate, Integer> getPurchaseHash(){
        return purchaseHash;
    }
}
