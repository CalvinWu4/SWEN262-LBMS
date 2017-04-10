package Library;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Anthony Perez on 3/5/17.
 */
public class Book implements Serializable{
    private Long isbn;
    private String title;
    private ArrayList<String> authors;
    private String publisher;
    private LocalDate publishedDate;
    private Integer pageCount;
    private Integer totalNumCopies;
    private Integer numAvailableCopies;

    public Book(Long _isbn, String _title, ArrayList<String> _authors, String _publisher,
                LocalDate _publishedDate, Integer _pageCount, Integer _totalNumCopies, int _numAvailableCopies){
        this.isbn = _isbn;
        this.title = _title;
        this.authors = _authors;
        this.publisher = _publisher;
        this.publishedDate = _publishedDate;
        this.pageCount = _pageCount;
        this.totalNumCopies = _totalNumCopies;
        this.numAvailableCopies = _numAvailableCopies;
    }

    public Long getIsbn(){
        return isbn;
    }
    public String getTitle(){
        return title;
    }
    public ArrayList<String> getAuthors(){
        return authors;
    }
    public String getAuthorsString(){
        return getAuthors().toString().replaceAll("\\[","{").replaceAll("]","}").replaceAll(",  ",", ");
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
    public void setIsbn(Long _isbn){
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
}
