package Library;

import Library.Database.ProxyBooks;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Brandon on 3/19/2017.
 */
public class Parser {

    public Parser(){
        CSVParser fileParser = null;
        FileReader BOOKCSV = null;

        final File file = new File("Books.csv"); //Checks to see if any books have been bought
        if(file.exists() && !file.isDirectory()) {
            try {
                BOOKCSV = new FileReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                fileParser = new CSVParser(BOOKCSV, CSVFormat.EXCEL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        HashMap<Long, Book> bookHashMap = new ProxyBooks().getMap();
        assert fileParser != null;
        for(CSVRecord record : fileParser){

            LocalDate _pubDate;

            Long _isbn = new Long(record.get(0)); //Gets the isbn as a long

            String _title = "\"" + record.get(1) + "\""; //Stores the title

            //Splits up potential multiple authors and adds them to an ArrayList
            ArrayList<String> _authors = new ArrayList<>();
            String[] authorArray = record.get(2).split(",");
            for(int i = 0; i < authorArray.length; i++){
                _authors.add(authorArray[i]);
            }

            String _publisher = record.get(3); //Records the publisher
            String fileString = BOOKCSV.toString();
                String[] dateArray = record.get(4).split("-|/"); //formats all potential dates into LocalDates
                if (dateArray.length == 1) {
                    _pubDate = LocalDate.of(Integer.parseInt(dateArray[0]), 1, 1);
                } else if (dateArray.length == 2) {
                    _pubDate = LocalDate.of(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), 1);
                } else {
                    _pubDate = LocalDate.of(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]));
                }

            Integer _pageCount = new Integer(record.get(5)); //Stores the page count

            int _totalNumCopies = 0;
            int _numAvailableCopies = 0;

            Book nextBook = new Book(_isbn,_title,_authors, _publisher,_pubDate,_pageCount, _totalNumCopies, _numAvailableCopies);
            bookHashMap.put(_isbn, nextBook);
        }
    }

}
