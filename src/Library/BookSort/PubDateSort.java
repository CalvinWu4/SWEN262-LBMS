package Library.BookSort;

import Library.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Tyler Lima on 3/5/2017.
 *
 * Sorting by Published Date
 */
public class PubDateSort implements BookSort {

    private Comparator<Book> PubDateComparator = new Comparator<Library.Book>() {

        @Override
        public int compare(Library.Book book1, Library.Book book2) {
            return book1.getPublishedDate().compareTo(book2.getPublishedDate());
        }
    };

    @Override
    public void sort(ArrayList<Book> books) {
        Collections.sort(books, PubDateComparator);
    }
}
