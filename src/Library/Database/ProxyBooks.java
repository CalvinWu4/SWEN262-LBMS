package Library.Database;

import Library.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Calvin on 4/15/2017.
 */
public class ProxyBooks implements Books{
    private RealBooks realBooks;

    @Override
    public void save(){
        if (realBooks == null){
            realBooks = new RealBooks();
        }
        realBooks.save();
    }

    @Override
    public ArrayList<Book> search(String title, String authors, String isbn, String publisher,
                            String sortOrder, boolean isLibrary, boolean isGoogle) throws IOException{
        if (realBooks == null){
            realBooks = new RealBooks();
        }
        return realBooks.search(title, authors, isbn, publisher, sortOrder, isLibrary, isGoogle);
    }

    @Override
    public String getFormattedResults(String title, String authors, String isbn, String publisher, String sortOrder, boolean isLibrary,
                                      boolean isGoogle){
        if (realBooks == null){
            realBooks = new RealBooks();
        }
        return realBooks.getFormattedResults(title, authors, isbn, publisher, sortOrder, isLibrary, isGoogle);
    }


    @Override
    public HashMap<Long, Book> getMap(){
        if (realBooks == null){
            realBooks = new RealBooks();
        }
        return realBooks.getMap();

    }
}
