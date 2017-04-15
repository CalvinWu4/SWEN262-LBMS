package Library.BookQuery;

import Library.Book;

import java.util.ArrayList;

/**
 * Created by Calvin Wu on 3/14/2017.
 * Search by Publisher
 */
public class PublisherQuery implements BookQuery {
    @Override
    public ArrayList<Book> search(ArrayList<Book> books, String searchParam){
        ArrayList<Book> newBooks = new ArrayList<>();
        for(Book book: books){
            if(book.getPublisher().equals(searchParam)){
                newBooks.add(book);
            }
        }
        return newBooks;
    }
    //TODO: TYLER incorporate this class to also do a Query using googleBooks

}
