package Library;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Brandon on 3/19/2017.
 */
public class Parser {

    private ArrayList<String> bookLine = new ArrayList<>();

    public Parser(){
        CSVParser fileParser = null;
        FileReader BUYBOOKCSV = null;
        try {
            BUYBOOKCSV = new FileReader("Books.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileParser = new CSVParser(BUYBOOKCSV, CSVFormat.EXCEL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<Long, Book> bookHash = Books.getBookHash();
        assert fileParser != null;
        for(CSVRecord record : fileParser){

            LocalDate _pubDate;

            Long _isbn = new Long(record.get(0));
            String _title = record.get(1);
            ArrayList<String> _authors = new ArrayList<>();
            String[] authorArray = record.get(2).split(",");

            for(int i = 0; i < authorArray.length; i++){
                _authors.add(authorArray[i]);
            }

            String _publisher = record.get(3);
            String[] dateArray = record.get(4).split("-|/");
            if(dateArray.length == 1){
                _pubDate = LocalDate.of(Integer.parseInt(dateArray[0]), 1, 1);
            }
            else if(dateArray.length == 2){
                _pubDate = LocalDate.of(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), 1);
            }
            else{
                _pubDate = LocalDate.of(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]));
            }
            Integer _pageCount = new Integer(record.get(5));
            Book nextBook = new Book(_isbn,_title,_authors, _publisher,_pubDate,_pageCount, 0, 0);
            bookHash.put(_isbn, nextBook);
        }
    }

}
