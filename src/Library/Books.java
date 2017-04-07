package Library;

import Library.Query.*;
import Library.Sort.*;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Anthony Perez on 3/5/17.
 */
public final class Books {
    private static HashMap<Long, Book> map;
    public static File FILE;

    public Books(){
        map = new HashMap<>();
        FILE = new File("books.ser");
        load();
    }

    public static void save() {
        try
        {
            FileOutputStream fos =
                    new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static void load() {
        try {
            FileInputStream fis = new FileInputStream(FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap<Long, Book>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    // Search Library
    public static ArrayList<Book> info(String title, String authors, String isbn, String publisher,
                                       String sortOrder){

        ArrayList<Book> searchResults = new ArrayList<Book>();

        Collection<Book> bookCollection = map.values();
        ArrayList<Book> bookList = new ArrayList<>();

        // Query books with at least one available copy
        for(Book book: bookCollection){
            if(book.getTotalNumCopies() >= 1){
                bookList.add(book);
            }
        }

        // Title Query
        if(!title.equals("*")){
            TitleQuery query = new TitleQuery();
            searchResults = new ArrayList<Book>(query.search(bookList, title));
        }
        else{
            searchResults = bookList;
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
        else{
            searchResults = bookList;
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
        else{
            searchResults = bookList;
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
        else{
            searchResults = bookList;
        }
        // Sort Order Query
        if(!sortOrder.equals("*")) {
            if (sortOrder.equals("title")) {
                TitleSort sorter = new TitleSort();
                sorter.sort(searchResults);
            } else if (sortOrder.equals("publish-date")) {
                PubDateSort sorter = new PubDateSort();
                sorter.sort(searchResults);
            } else if (sortOrder.equals("book-status")) {
                NumAvailSort sorter = new NumAvailSort();
                sorter.sort(searchResults);
            } else {
                System.out.println("The specified sort order doesn't match one of the expected values.");
                return null;
            }
        }

        return searchResults;
    }

    // Search Book Store
    public static ArrayList<Book> search(String title, String authors, String isbn, String publisher,
                                         String sortOrder){

        ArrayList<Book> searchResults = new ArrayList<Book>();
        Collection<Book> bookCollection = map.values();

        ArrayList<Book> bookList = new ArrayList<>();

        // Query all books
        for(Book book: bookCollection){
            bookList.add(book);
        }

        // Title Query
        if(!title.equals("*")){
            TitleQuery query = new TitleQuery();
            searchResults = new ArrayList<Book>(query.search(bookList, title));
        }
        else{
            searchResults = bookList;
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
        else{
            searchResults = bookList;
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
        else{
            searchResults = bookList;
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
        else{
            searchResults = bookList;
        }
        // Sort Order Query
        if(!sortOrder.equals("*")) {
            if (sortOrder.equals("title")) {
                TitleSort sorter = new TitleSort();
                sorter.sort(searchResults);
            } else if (sortOrder.equals("publish-date")) {
                PubDateSort sorter = new PubDateSort();
                sorter.sort(searchResults);
            } else {
                System.out.println("The specified sort order doesn't match one of the expected values.");
                return null;
            }
        }

        return searchResults;
    }

    static public void bookPrint(ArrayList<Book> _books){

        int isbnLength = 13;
        int titleLength = 10;
        int authorsLength = 10;
        int publisherLength = 9;
        int dateLength = 14;
        int numOfCopies = 17;
        int availableCopies = 20;

        String line="+";

        for(Book book : _books){

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

        for(int i=0; i<=numOfCopies; i++){
            if(i==numOfCopies){
                line += "+";
            }
            else{
                line += "-";
            }
        }

        for(int i=0; i<=availableCopies; i++){
            if(i==availableCopies){
                line += "+";
            }
            else{
                line += "-";
            }
        }

        System.out.println(line);
        System.out.format("|%-"+isbnLength+"s|%-"+titleLength+"s|%-"+authorsLength+"s|%-"+
                        publisherLength+"s|%-"+dateLength+"s|%-"+numOfCopies+"s|%-"+availableCopies+"s|\n",
                "ISBN","Book Title", "Author(s)", "Publisher", "Date Published", "Total # Of Copies",
                "# Of AvailableCopies");
        System.out.println(line);
        for(Book book: _books){
            System.out.format("|%-"+isbnLength+"s|%-"+titleLength+"s|%-"+authorsLength+"s|%-"+
                            publisherLength+ "s|%-"+dateLength+"s|%-"+numOfCopies+"s|%-"+availableCopies+"s|\n",
                    book.getIsbn(),book.getTitle(), book.getAuthorsString(), book.getPublisher(),
                    book.getPublishedDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), book.getTotalNumCopies(),
                    book.getNumAvailableCopies());
        }
        System.out.println(line);
    }

    public static HashMap<Long, Book> getMap(){
        return map;
    }

}
