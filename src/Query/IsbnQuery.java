package Query;

import Library.Book;

import java.util.ArrayList;

/**
 * Created by Calvin Wu on 3/14/2017.
 */
public class IsbnQuery implements BookQuery {
    @Override
    public ArrayList<Book> search(ArrayList<Book> books, String searchParam){
        ArrayList<Book> newBooks = new ArrayList<>();
        books.get(Integer.parseInt(searchParam));
        for(Book book: books){
            if(book.getIsbn().equals(Integer.parseInt(searchParam))){
                newBooks.add(book);
            }
        }
        return newBooks;
    }
}

