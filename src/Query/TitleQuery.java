package Query;
import Library.Book;
import java.util.ArrayList;
/**
 * Created by Calvin Wu on 3/14/2017.
 *
 */
public class TitleQuery implements BookQuery {
    @Override
    public ArrayList<Book> search(ArrayList<Book> books, String searchParam){
        ArrayList<Book> newBooks = new ArrayList<>();
        for(Book book: books){
            if(book.getTitle().equals(searchParam)){
                newBooks.add(book);
            }
        }
        return newBooks;
    }
}
