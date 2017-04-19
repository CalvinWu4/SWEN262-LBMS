package Library.Database;

import Library.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Calvin on 4/15/2017.
 */
public interface Books {
    void save();
    ArrayList<Book> search(String title, String authors, String isbn, String publisher, String sortOrder,
                           boolean isLibrary, boolean isGoogle) throws IOException;
    String getFormattedResults(String title, String authors, String isbn, String publisher, String sortOrder,
                               boolean isLibrary, boolean isGoogle);
    HashMap<Long, Book> getMap();

}
