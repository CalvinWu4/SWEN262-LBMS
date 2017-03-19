package Query;
import Library.Book;
import java.util.ArrayList;

/**
 * Created by Calvin on 3/14/2017.
 */
public interface BookQuery {
    public ArrayList<Book> search(ArrayList<Book> books, String searchParam);
}
