package Library.BookQuery;

import Library.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Calvin Wu on 3/14/2017.
 * Search by Author(s)
 */
public class AuthorsQuery implements BookQuery {
    @Override
    public ArrayList<Book> search(ArrayList<Book> books, String searchParam) {
        ArrayList<Book> newBooks = new ArrayList<>();
        for (Book book : books) {
            String replace = searchParam.replace("{", "");
            String replace1 = replace.replace("}", "");
            List<String> searchList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
            // Returns true if searchParam's authorlist is a sublist of book's authorlist
            if (Collections.indexOfSubList(book.getAuthors(), searchList) != -1) {
                newBooks.add(book);
            }
        }
        return newBooks;
    }
}
