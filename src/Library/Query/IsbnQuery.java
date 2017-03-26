package Library.Query;

import Library.Book;
import Library.Books;

import java.util.ArrayList;

/**
 * Created by Calvin Wu on 3/14/2017.
 *
 * Search by ISBN
 */
public class IsbnQuery implements BookQuery {
    @Override
    public ArrayList<Book> search(ArrayList<Book> books, String searchParam){
        ArrayList<Book> newBooks = new ArrayList<>();
        newBooks.add(Books.getBookHash().get(Long.parseLong(searchParam)));
        return newBooks;
    }
}

