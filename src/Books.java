import java.util.HashMap;

/**
 * Created by Anyhony Perez on 3/5/17.
 */
public class Books {
    private HashMap<Integer, Book> bookHash;

    public Books(){
        //go through file where books for the library are stored and add each book to the "bookHash".
    }

    public void display(){
        //loop through the "bookHash" and display all books.
    }

    //check class diagram for missing parameter
    public void search(String _query, String _field){
        //loop through the "bookHash" and search for the "_query" in the "_field".
    }

    public void add(Book _book){
        //add "_book" to "booksHash".
    }

    public void saveToFile(){
        //save and overwrite the file where library books are stored.
    }
    public void displayAvailable(){
        //loop through the "bookHash" and display all books that are available.
    }

}
