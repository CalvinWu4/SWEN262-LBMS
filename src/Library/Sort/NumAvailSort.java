package Library.Sort;

import Library.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Calvin Wu on 3/19/2017.
 */
public class NumAvailSort implements BookSort {

    private Comparator<Book> NumAvailComparator = new Comparator<Library.Book>() {

        @Override
        public int compare(Library.Book book1, Library.Book book2) {
            return book1.getNumAvailableCopies().compareTo(book2.getNumAvailableCopies());
        }
    };

    @Override
    public void sort(ArrayList<Book> books) {
        Collections.sort(books, NumAvailComparator);
    }
}
