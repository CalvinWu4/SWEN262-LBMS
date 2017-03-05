import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Anthony Perez on 3/5/17.
 */
public class Book {
    private int isbn;
    private String title;
    private ArrayList<String> authors;
    private String publisher;
    private Date publishedDate;
    private int totalNumCopies;
    private int numAvailableCopies;

    public Book(int _isbn, String _title, ArrayList<String> _authors, String _publisher, Date _publishedDate, int _totalNumCopies, int _numAvailableCopies){
        isbn = _isbn;
        title = _title;
        authors = _authors;
        publisher = _publisher;
        publishedDate = _publishedDate;
        totalNumCopies = _totalNumCopies;
        numAvailableCopies = _numAvailableCopies;
    }

    public int getIsbn(){
        return isbn;
    }
    public String getTitle(){
        return title;
    }
    public ArrayList<String> getAuthors(){
        return authors;
    }
    public String getPublisher(){
        return publisher;
    }
    public Date getPublishedDate(){
        return publishedDate;
    }
    public int getTotalNumCopies(){
        return totalNumCopies;
    }
    public int getNumAvailableCopies(){
        return numAvailableCopies;
    }

    public void setIsbn(int _isbn){
        isbn = _isbn;
    }
    public void setTitle(String _title){
        title = _title;
    }
    public void setAuthors(ArrayList<String> _authors){
        authors = _authors;
    }
    public void setPublisher(String _publisher){
        publisher = _publisher;
    }
    public void setPublishedDate(Date _publishedDate){
        publishedDate = _publishedDate;
    }
    public void setTotalNumCopies(int _totalNumCopies){
        totalNumCopies = _totalNumCopies;
    }
    public  void setNumAvailableCopies(int _numAvailableCopies){
        numAvailableCopies = _numAvailableCopies;
    }
}
