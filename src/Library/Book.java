package Library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Created by Anthony Perez on 3/5/17.
 */
public class Book {
    private Integer bookId;
    private Integer isbn;
    private String title;
    private ArrayList<String> authors;
    private String publisher;
    private LocalDate publishedDate;
    private Integer totalNumCopies;
    private Integer numAvailableCopies;
    private Integer pageCount;
    private boolean availability;
    private LocalDate purchasingDate;
    //private Fine fine; Put this in transaction

    public Book(Integer _bookId, Integer _isbn, String _title, ArrayList<String> _authors, String _publisher,
                LocalDate _publishedDate, Integer _pageCount, Integer _totalNumCopies, int _numAvailableCopies,
                boolean _availability, LocalDate _purchasingDate){
        this.bookId = _bookId;
        this.isbn = _isbn;
        this.title = _title;
        this.authors = _authors;
        this.publisher = _publisher;
        this.publishedDate = _publishedDate;
        this.totalNumCopies = _totalNumCopies;
        this.numAvailableCopies = _numAvailableCopies;
        this.availability = _availability;
        this.purchasingDate = _purchasingDate;

        //fine = new Fine();
    }

    public Integer getBookId(){
        return bookId;
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
    public String getAuthorsString(){
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
    public Integer getPageCount(){
        return pageCount;
    }
    public boolean isAvailable() {
        return availability;
    }
    public LocalDate getPurchasingDate(){
        return purchasingDate;
    }
    public void setBookId(Integer _bookId){
        this.bookId = _bookId;
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
    public void setPageCount(Integer _pageCount){
        this.pageCount = _pageCount;
    }
    public void setTotalNumCopies(Integer _totalNumCopies){
        this.totalNumCopies = _totalNumCopies;
    }
    public void setNumAvailableCopies(Integer _numAvailableCopies){
        this.numAvailableCopies = _numAvailableCopies;
    }
    public void setAvailability(boolean _availability) {
        this.availability = _availability;
    }
    public void setPurchasingDate(LocalDate _purchasingDate){
        this.purchasingDate = _purchasingDate;
    }



    /// Put this in Transaction
//    public Integer calculateFee(){
//        if(!(this.isAvailable())){
//            LocalDate present = LocalDate.now();
//            if((present.isBefore(this.getDueDate()))){
//                return fine.getCost();
//            }
//            else{
//                long weeksInYear = ChronoUnit.WEEKS.between(this.getDueDate(), present);
//                fine.addCost(weeksInYear);
//                return fine.getCost();
//            }
//        }
//        return fine.getCost();
//    }
}
