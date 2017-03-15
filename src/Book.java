import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Anthony Perez on 3/5/17.
 */
public class Book {
    private Integer isbn;
    private String title;
    private ArrayList<String> authors;
    private String publisher;
    private LocalDate publishedDate;
    private Integer totalNumCopies;
    private Integer numAvailableCopies;
    private boolean availability;

    public Book(Integer _isbn, String _title, ArrayList<String> _authors, String _publisher, LocalDate _publishedDate,
                Integer _totalNumCopies, Integer _numAvailableCopies, boolean _availability){
        this.isbn = _isbn;
        this.title = _title;
        this.authors = _authors;
        this.publisher = _publisher;
        this.publishedDate = _publishedDate;
        this.totalNumCopies = _totalNumCopies;
        this.numAvailableCopies = _numAvailableCopies;
        this.availability = _availability;
    }

    public Integer getIsbn(){
        return isbn;
    }
    public String getTitle(){
        return title;
    }
    public ArrayList<String> getAuthors(){
        return authors;
    }
    public String getAuthors2(){
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
    public LocalDate getPublishedDate(){
        return publishedDate;
    }
    public Integer getTotalNumCopies(){
        return totalNumCopies;
    }
    public Integer getNumAvailableCopies(){
        return numAvailableCopies;
    }
    public boolean isAvailable() {
        return availability;
    }

    public void setIsbn(Integer _isbn){
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
    public void setPublishedDate(LocalDate _publishedDate){
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
