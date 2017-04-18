package Library.Database;

/**
 * Created by Calvin on 4/15/2017.
 */
public interface Books {
    void save();
    String search(String title, String authors, String isbn, String publisher, String sortOrder, String location, boolean service);
}
