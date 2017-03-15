import java.util.ArrayList;

/**
 * Created by Calvin Wu on 3/14/2017.
 */
public class numAvailCopiesQuery implements BookQuery {
    @Override
    public ArrayList<Book> search(ArrayList<Book> books, String searchParam){
        ArrayList<Book> newBooks = new ArrayList<>();
        for(Book book: books){
            if(book.getNumAvailableCopies().equals(Integer.parseInt(searchParam))){
                newBooks.add(book);
            }
        }
        return newBooks;
    }
}
