package Library.Database;

import Library.Book;
import Library.Parser;
import Library.BookQuery.*;
import Library.BookSort.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Anthony Perez on 3/5/17.
 */
public final class RealBooks extends Database implements Books{
    private static HashMap<Long, Book> map;
    private static final File FILE = new File("books.ser");

    public RealBooks() {
        if(map == null) {
            map = new HashMap<>();
            load();
        }
    }

    public void load(){
        if(read(FILE) != null){
            map = (HashMap<Long, Book>)read(FILE);
        }
        else{
            new Parser();
        }
    }

    @Override
    public void save(){
        write(map, FILE);
    }

    // Search Library/Book Store
    @Override
    public String search(String title, String authors, String isbn, String publisher, String sortOrder,
                         String location){
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
        if(title.equals("*") || authors.equals("*") || isbn.equals("*") || publisher.equals("*")){
            searchResults = bookList;
        }
        // Title Query
        if(!title.equals("*")){
            TitleQuery query = new TitleQuery();
            if(searchResults.isEmpty()) {
                searchResults = new ArrayList<Book>(query.search(bookList, title));
            }
            else{
                searchResults.retainAll(query.search(bookList, title));
            }
        }
        // Authors Query
        if(!authors.equals("*")){
            AuthorsQuery query = new AuthorsQuery();
            if(searchResults.isEmpty()) {
                searchResults = new ArrayList<Book>(query.search(bookList, authors));
            }
            else{
                searchResults.retainAll(query.search(bookList, authors));
            }
        }
        // ISBN Query
        if(!isbn.equals("*")){
            IsbnQuery query = new IsbnQuery();
            if(searchResults.isEmpty()) {
                searchResults = new ArrayList<Book>(query.search(bookList, isbn));
            }
            else{
                searchResults.retainAll(query.search(bookList, isbn));
            }
        }
        // Publisher Query
        if(!publisher.equals("*")){
            PublisherQuery query = new PublisherQuery();
            if(searchResults.isEmpty()) {
                searchResults = new ArrayList<Book>(query.search(bookList, publisher));
            }
            else{
                searchResults.retainAll(query.search(bookList, publisher));
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

    public static HashMap<Long, Book> getMap(){
        return map;
    }

}
