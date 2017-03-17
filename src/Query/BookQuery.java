package Query;
import Library.Book;
import java.util.ArrayList;

/**
 * Created by Calvin on 3/14/2017.
 */
public interface BookQuery {
    public ArrayList<Book> search(ArrayList<Book> books, String searchParam);
// Sort Function
// public class Query.TitleQuery{
//
//    Comparator<Library.Book> TitleComparator = new Comparator<Library.Book>() {
//
//        @Override
//        public int compare(Library.Book book1, Library.Book book2) {
//            return book1.getTitle().compareTo(book2.getTitle());
//        }
//    };
//
//    @Override
//    public void sort(ArrayList<Library.Book> books){
//        Collections.sort(books, TitleComparator);
//    }
//}
}
