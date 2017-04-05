package Library;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Calvin on 3/18/2017.
 */
public final class Purchases implements Serializable{
    private static HashMap<LocalDate, Integer> purchaseHash = new HashMap<>(); // Date, NumBooksPurchased


    public static String purchase(Integer quantity, ArrayList<Long> isbns) {
        String result = "success\n";
        ArrayList<Long> seen = new ArrayList<>();
        for (Long isbn : isbns) {
            Book book = Books.getBookHash().get(isbn);
            if (!Books.getBookHash().containsKey(isbn)) {
                return ("One or more of the book ISBNs are not valid.");
            }
            else if(seen.contains(isbn)){
                return ("Book ISBNs must be unique.");
            }
            else {
                Integer prevTotalNumCopies = book.getTotalNumCopies();
                Integer prevNumAvailableCopies = book.getNumAvailableCopies();
                book.setTotalNumCopies(prevTotalNumCopies + quantity);
                book.setNumAvailableCopies(prevNumAvailableCopies + quantity);
                Books.saveToFile();
                if (!purchaseHash.containsKey(Time.getDate())) {
                    purchaseHash.put(Time.getDate(), quantity);
                } else {
                    Integer oldQuantity = purchaseHash.get(Time.getDate());
                    purchaseHash.put(Time.getDate(), oldQuantity + quantity);
                }
                seen.add(isbn);
                result += book.getIsbn() + "," + book.getTitle() + "," + "{" + book.getAuthorsString() + "}" + "," +
                        book.getPublishedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "," +
                        book.getTotalNumCopies() + "\n";
            }
        }
        return result;
    }

    public static HashMap<LocalDate, Integer> getPurchaseHash(){
        return purchaseHash;
    }
}
