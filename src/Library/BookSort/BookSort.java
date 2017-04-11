package Library.BookSort;

import Library.Book;

import java.util.ArrayList;

/**
 * Created by Tyler Lima on 3/5/2017.
 * The Book BookSort Interface, used for the Strategy Design Pattern
 */
public interface BookSort {
    public void sort(ArrayList<Book> books);
}
