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
    private boolean availability;

    public Book(int _isbn, String _title, ArrayList<String> _authors, String _publisher, Date _publishedDate, int _totalNumCopies, int _numAvailableCopies, boolean _availability){
        this.isbn = _isbn;
        this.title = _title;
        this.authors = _authors;
        this.publisher = _publisher;
        this.publishedDate = _publishedDate;
        this.totalNumCopies = _totalNumCopies;
        this.numAvailableCopies = _numAvailableCopies;
        this.availability = _availability;
    }

    public int getIsbn(){
        return isbn;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthors(){
        String auths = "";
        for(String author : authors){
            auths += author+", ";
        }
        auths = auths.replaceAll(", $","");
        return auths;
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
    public boolean isAvailable() {
        return availability;
    }

    public void setIsbn(int _isbn){
        this.isbn = _isbn;
    }
    public void setTitle(String _title){
        this.title = _title;
    }
    public void setAuthors(ArrayList<String> _authors){
        this.authors = _authors;
    }
    public void setPublisher(String _publisher){
        this.publisher = _publisher;
    }
    public void setPublishedDate(Date _publishedDate){
        this.publishedDate = _publishedDate;
    }
    public void setTotalNumCopies(int _totalNumCopies){
        this.totalNumCopies = _totalNumCopies;
    }
    public void setNumAvailableCopies(int _numAvailableCopies){
        this.numAvailableCopies = _numAvailableCopies;
    }
    public void setAvailability(boolean _availability) {
        this.availability = _availability;
    }
}
