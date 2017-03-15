import java.util.ArrayList;

/**
 * Created by Calvin on 3/14/2017.
 */
public interface BookQuery {
    public ArrayList<Book> search(ArrayList<Book> books, String searchParam);
// Sort Function
// public class TitleQuery{
//
//    Comparator<Book> TitleComparator = new Comparator<Book>() {
//
//        @Override
//        public int compare(Book book1, Book book2) {
//            return book1.getTitle().compareTo(book2.getTitle());
//        }
//    };
//
//    @Override
//    public void sort(ArrayList<Book> books){
//        Collections.sort(books, TitleComparator);
//    }
//}
}
