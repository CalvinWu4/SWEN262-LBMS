package Library.Database;

import Library.Book;
import Library.Parser;
import Library.BookQuery.*;
import Library.BookSort.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Anthony Perez on 3/5/17.
 */
public final class RealBooks extends Database implements Books{
    private static HashMap<Long, Book> map;
    private static File file;

    private String url = "https://www.googleapis.com/books/v1/volumes/?langRestrict=en&q=";
    private String authCode = "&maxResults=40&key=AIzaSyBgIxRv3oqcXzLTH0JpIVoDutzOL7yf5k4";

    private String authorParam="";
    private String titleParam="";
    private String isbnParam="";
    private String publisherParam="";

    public RealBooks() {
            file = new File("books.ser");


        if(map == null) {
            map = new HashMap<>();
            load();
        }
    }

    public void load(){
        if(read(file) != null){
            map = (HashMap<Long, Book>)read(file);
        }
        else{
            new Parser();
        }
    }

    @Override
    public void save(){
        write(map, file);
    }

    // Search Library/Book Store
    @Override
    public String search(String title, String authors, String isbn, String publisher, String sortOrder,
                         String location, boolean service){
        ArrayList<Book> searchResults = new ArrayList<Book>();
        Collection<Book> bookCollection = map.values();

        ArrayList<Book> bookList = new ArrayList<>();

        // Choose which books to query
        if(location.equals("bookstore")){
            for(Book book: bookCollection){
                bookList.add(book);
            }
        }
        else{
            for(Book book: bookCollection){
                if(book.getTotalNumCopies() >= 1){
                    bookList.add(book);
                }
            }
        }

        // Check for wildcards
        if((title.equals("*") || authors.equals("*") || isbn.equals("*") || publisher.equals("*")) && !service){
            searchResults = bookList;
        }
        if(title.equals("*") && authors.equals("*") && isbn.equals("*") && publisher.equals("*")){
            if (service){
                return "Search is too broad, please be more specific.";
            }
        }
        // Title Query
        if(!title.equals("*")){
            if(!service){
                TitleQuery query = new TitleQuery();
                if (searchResults.isEmpty()) {
                    searchResults = new ArrayList<Book>(query.search(bookList, title));
                } else {
                    searchResults.retainAll(query.search(bookList, title));
                }
            }
            else{
                titleParam = "intitle%3A" + title.replace("\"","").replace(" ","%20") + "+";

            }
        }
        // Authors Query
        if(!authors.equals("*")){
            if(!service){
                AuthorsQuery query = new AuthorsQuery();
                if (searchResults.isEmpty()) {
                    searchResults = new ArrayList<Book>(query.search(bookList, authors));
                } else {
                    searchResults.retainAll(query.search(bookList, authors));
                }
            }
            else{
                authorParam = "inauthor%3A" + authors.replace("{","").replace("}","").replace(" ","%20") + "+";
            }
        }
        // ISBN Query
        if(!isbn.equals("*")){
            if(!service) {
                IsbnQuery query = new IsbnQuery();
                if (searchResults.isEmpty()) {
                    searchResults = new ArrayList<Book>(query.search(bookList, isbn));
                } else {
                    searchResults.retainAll(query.search(bookList, isbn));
                }
            }
            else{
                isbnParam = "isbn%3A" + isbn + "+";
            }
        }
        // Publisher Query
        if(!publisher.equals("*")){
            if(!service) {
                PublisherQuery query = new PublisherQuery();
                if (searchResults.isEmpty()) {
                    searchResults = new ArrayList<Book>(query.search(bookList, publisher));
                } else {
                    searchResults.retainAll(query.search(bookList, publisher));
                }
            }
            else {
                publisherParam ="inpublisher%3A" + publisher.replace(" ","%20") + "+";
            }
        }
        // Sort Order Query
        if(!sortOrder.equals("*")) {
            if (sortOrder.equals("title")) {
                TitleSort sorter = new TitleSort();
                sorter.sort(searchResults);
            } else if (sortOrder.equals("publish-date")) {
                PubDateSort sorter = new PubDateSort();
                sorter.sort(searchResults);

            }
            else if (location.equals("library") && sortOrder.equals("book-status")) {
                NumAvailSort sorter = new NumAvailSort();
                sorter.sort(searchResults);
            }
            else {
                System.out.println("The specified sort order doesn't match one of the expected values.");
                return null;
            }
        }

        if(service){
            String requestResponse= "";

            String responseTitle;
            String responsePublisher = "";
            String responsePublished = "";
            Integer responsePageCount = 0;
            String responseIsbn = "";

            System.out.println(url + authorParam + titleParam + isbnParam + publisherParam + authCode);
            try {

                URL GoogleURL = new URL(url + authorParam + titleParam + isbnParam + publisherParam + authCode);
                HttpURLConnection con = (HttpURLConnection) GoogleURL.openConnection();

                // Set the HTTP Request type method to GET (Default: GET)
                con.setRequestMethod("GET");
                con.setConnectTimeout(10000);
                con.setReadTimeout(10000);

                // Created a BufferedReader to read the contents of the request.
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                JSONObject obj = new JSONObject(response.toString());
                JSONArray arr = obj.getJSONArray("items");

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject objInfo = arr.getJSONObject(i).getJSONObject("saleInfo");

                    ArrayList<String> responseAuthors = new ArrayList<>();

                    if (objInfo.getString("country").equals("US") && objInfo.getString("saleability").equals("FOR_SALE")) {
                        JSONObject volumeInfoObj = arr.getJSONObject(i).getJSONObject("volumeInfo");
                        JSONObject saleInfoObj = arr.getJSONObject(i).getJSONObject("saleInfo");

                        responseTitle = "\""+volumeInfoObj.getString("title")+"\"";

                        if (volumeInfoObj.has("authors")) {
                            JSONArray _authors = volumeInfoObj.getJSONArray("authors");
                            for(int j=0; j<_authors.length();j++){
                                responseAuthors.add(_authors.getString(j));
                            }
                        }
                        if (volumeInfoObj.has("publisher")) {
                            responsePublisher = volumeInfoObj.getString("publisher");
                        }
                        if (volumeInfoObj.has("publishedDate")) {
                            responsePublished = volumeInfoObj.getString("publishedDate");
                        }
                        if (volumeInfoObj.has("pageCount")) {
                            responsePageCount = (volumeInfoObj.getInt("pageCount"));
                        }
                        if (volumeInfoObj.has("industryIdentifiers")) {
                            JSONArray isbnArr = volumeInfoObj.getJSONArray("industryIdentifiers");
                            for (int j = 0; j < isbnArr.length(); j++) {
                                if (isbnArr.getJSONObject(j).has("identifier")) {
                                    String _isbn = isbnArr.getJSONObject(j).getString("identifier");
                                    if (_isbn.length() > 12) {
                                        responseIsbn = _isbn;
                                    }
                                }
                            }
                        }
//                        if (saleInfoObj.has("country")) {
//                            String country = saleInfoObj.getString("country");
//                            requestResponse += (country+"|");
//                        }
//                        if (saleInfoObj.has("saleability")) {
//                            String saleability = saleInfoObj.getString("saleability");
//                            requestResponse += (saleability+"|\n");
//                        }

                        //Long _isbn, String _title, ArrayList<String> _authors, String _publisher,
                        //LocalDate _publishedDate, Integer _pageCount, Integer _totalNumCopies, int _numAvailableCopies
                        searchResults.add(new Book(Long.parseLong(responseIsbn),responseTitle,responseAuthors,responsePublisher,LocalDate.parse(responsePublished),responsePageCount,0,0));
                        //responseAuthors.clear();
                    }
                }
                in.close();
            }
            catch (IOException ioe){
                System.out.println("IO Error: "+ioe);
            }
            catch (JSONException je) {
                System.out.println("JSON Error: " + je);
            }
        }

        // Book Print
        int isbnLength = 13;
        int titleLength = 10;
        int authorsLength = 10;
        int publisherLength = 9;
        int dateLength = 14;
        int numOfCopies = 17;
        int availableCopies = 20;

        String searchResultsString = "";
        String line="+";

        for(Book book : searchResults){

            if(book.getTitle().length() > titleLength){
                titleLength = book.getTitle().length();
            }
            if(book.getAuthorsString().length() > authorsLength){
                authorsLength = book.getAuthorsString().length();
            }
            if(book.getPublisher().length() > publisherLength){
                publisherLength = book.getPublisher().length();
            }
            if(book.getPublishedDate().toString().length() > dateLength){
                dateLength = book.getPublishedDate().toString().length();
            }
        }

        for(int i=0; i<=isbnLength; i++){
            if(i==isbnLength){
                line += "+";
            }
            else{
                line += "-";
            }
        }

        for(int i=0; i<=titleLength; i++){
            if(i==titleLength){
                line += "+";
            }
            else{
                line += "-";
            }
        }

        for(int i=0; i<=authorsLength; i++){
            if(i==authorsLength){
                line += "+";
            }
            else{
                line += "-";
            }
        }

        for(int i=0; i<=publisherLength; i++){
            if(i==publisherLength){
                line += "+";
            }
            else{
                line += "-";
            }
        }

        for(int i=0; i<=dateLength; i++){
            if(i==dateLength){
                line += "+";
            }
            else{
                line += "-";
            }
        }
        if(location.equals("library")) {
            for (int i = 0; i <= numOfCopies; i++) {
                if (i == numOfCopies) {
                    line += "+";
                } else {
                    line += "-";
                }
            }

            for (int i = 0; i <= availableCopies; i++) {
                if (i == availableCopies) {
                    line += "+";
                } else {
                    line += "-";
                }
            }
        }

        searchResultsString += searchResults.size() + " book(s) found.\n";
        searchResultsString += line + "\n";
        searchResultsString += String.format("|%-" + isbnLength + "s|%-" + titleLength + "s|%-" + authorsLength +
                        "s|%-" + publisherLength + "s|%-" + dateLength +  "s|", "ISBN", "Book Title", "Author(s)",
                "Publisher", "Date Published");
        if(location.equals("library")) {
            searchResultsString += String.format("%-" + numOfCopies + "s|%-" + availableCopies + "s|",
                    "Total # Of Copies",
                    "# Of AvailableCopies");
        }
        searchResultsString += "\n";

        searchResultsString += line + "\n";
        for(Book book: searchResults){
            searchResultsString += String.format("|%-"+isbnLength+"s|%-"+titleLength+"s|%-"+authorsLength+"s|%-"+
                            publisherLength+ "s|%-"+dateLength+"s|", book.getIsbn(),book.getTitle(), book.getAuthorsString(),
                    book.getPublisher(), book.getPublishedDate());

            if(location.equals("library")){
                searchResultsString += String.format("%-"+numOfCopies+"s|%-"+availableCopies+"s|",
                        book.getTotalNumCopies(), book.getNumAvailableCopies());
            }
            searchResultsString += "\n";
        }
        searchResultsString += line + "\n";

        return searchResultsString;
    }

    public HashMap<Long, Book> getMap(){
        return map;
    }

}
