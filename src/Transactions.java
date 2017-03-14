/**
 * Created by Calvin on 3/13/2017.
 */
public class Transactions {
    public void checkout(Book book){
        if (book.isAvailable() && book.getNumAvailableCopies() > 0){
            book.setNumAvailableCopies(book.getNumAvailableCopies() - 1);
        }
    }
    public void _return(Book book){
        book.setNumAvailableCopies(book.getNumAvailableCopies() + 1);
    }
    // Instantiate fine if overdue

}
