package Library.Database;

import Library.Book;

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
    public String search(String title, String authors, String isbn, String publisher,
                         String sortOrder, String location, boolean service) {
        if (realBooks == null){
            realBooks = new RealBooks();
        }
        return realBooks.search(title,authors,isbn,publisher,sortOrder,location, service);
    }

    @Override
    public HashMap<Long, Book> getMap(){
        if (realBooks == null){
            realBooks = new RealBooks();
        }
        return realBooks.getMap();

    }
}
