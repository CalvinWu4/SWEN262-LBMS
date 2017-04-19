package Library.Database;

import Library.Book;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Calvin on 3/18/2017.
 */
public final class Purchases extends Database implements Serializable{
    private static HashMap<LocalDate, Integer> map; // Date, NumBooksPurchased
    private static final File FILE = new File("purchases.ser");
    private static ProxyBooks books = new ProxyBooks();
    private static HashMap<Long, Book> bookMap = books.getMap();

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
            // Check for duplicate book isbns
            if(seen.contains(isbn)){
                return ("Book ISBNs must be unique.");
            }
            // Check if the book exists in the bookstore
            if (!bookMap.containsKey(isbn)) {
                ArrayList<Book> localBookResult = new ArrayList<>();
                ArrayList<Book> googleBookResult = new ArrayList<>();
                try {
                    localBookResult = books.search("*", "*", isbn.toString(), "*", "*", true, false);
                    googleBookResult = books.search("*", "*", isbn.toString(), "*", "*", true, true);
                }
                catch (IOException ioe){    // Won't ever reach here since ISBNs are unique
                }
                // Add book to booksMap if not present
                if (localBookResult.get(0) != null){
                    bookMap.putIfAbsent(isbn, localBookResult.get(0));
                } else {
                    if (googleBookResult.get(0) != null) {
                        bookMap.putIfAbsent(isbn, googleBookResult.get(0));
                    } else {
                        return ("One or more of the book ISBNs are not valid.");
                    }
                }
            }
            // Add book to this map
            Book book = bookMap.get(isbn);
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
        return result;
    }


    public static HashMap<LocalDate, Integer> getMap(){
        return map;
    }
}
