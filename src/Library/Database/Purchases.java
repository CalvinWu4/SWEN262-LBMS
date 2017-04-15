package Library.Database;

import Library.Book;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Calvin on 3/18/2017.
 */
public final class Purchases extends Database implements Serializable{
    private static HashMap<LocalDate, Integer> map; // Date, NumBooksPurchased
    private static final File FILE = new File("purchases.ser");

    public Purchases() {
        map = new HashMap<>();
        load();
    }

    public static void load(){
        if(read(FILE) != null){
            map = (HashMap<LocalDate, Integer>)read(FILE);
        }
    }

    public static void save(){
        write(map, FILE);
    }

    public static String purchase(Integer quantity, ArrayList<Long> isbns) {
        String result = "success\n";
        ArrayList<Long> seen = new ArrayList<>();
        for (Long isbn : isbns) {
            Book book = RealBooks.getMap().get(isbn);
            if (!RealBooks.getMap().containsKey(isbn)) {
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
                if (!map.containsKey(Time.getDate())) {
                    map.put(Time.getDate(), quantity);
                } else {
                    Integer oldQuantity = map.get(Time.getDate());
                    map.put(Time.getDate(), oldQuantity + quantity);
                }
                seen.add(isbn);
                result += book.getIsbn() + "," + book.getTitle() + "," + book.getAuthorsString() + "," +
                        book.getPublishedDate() + "," +
                        book.getTotalNumCopies() + "\n";
            }
        }
        return result;
    }


    public static HashMap<LocalDate, Integer> getMap(){
        return map;
    }
}
