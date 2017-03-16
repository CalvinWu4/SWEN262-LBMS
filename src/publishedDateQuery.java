import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Created by Calvin Wu on 3/14/2017.
 */
public class publishedDateQuery implements BookQuery {
    @Override
    public ArrayList<Book> search(ArrayList<Book> books, String searchParam){
        ArrayList<Book> newBooks = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        for(Book book: books){
            if(DateToString.convert(book.getPublishedDate()).equals(searchParam)){
                newBooks.add(book);
            }
        }
        return newBooks;
    }
}
