package Library.Database;

import Library.Book;

import java.util.HashMap;

/**
 * Created by Calvin on 4/15/2017.
 */
public interface Books {
    void save();
    String search(String title, String authors, String isbn, String publisher, String sortOrder, boolean isLibrary, boolean isGoogle);
    HashMap<Long, Book> getMap();

}
