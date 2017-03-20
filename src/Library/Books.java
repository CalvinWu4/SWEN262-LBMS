package Library;

import Query.*;
import Sort.*;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;

/**
 * Created by Anthony Perez on 3/5/17.
 */
public final class Books {
    private static HashMap<Integer, Book> bookHash = new HashMap<>();
    private static final String BOOKSFILE = "libraryBooks.csv";


    public Books(){
        //test for class to work
        ArrayList<String> authors = new ArrayList<>();
        authors.add("testAuthor1");
        authors.add("testAuthor2");

        ArrayList<String> authors2 = new ArrayList<>();
        authors2.add("testAuthor3");
        LocalDate date = LocalDate.now();
        Book newBook = new Book(1, "Harry Potter and the Deathly Hallows",authors,"publisherName", date,1000,10,5);
        Book newBook4 = new Book(2,"Harry Potter and the Prisoner of Azkaban",authors,"publisherName", date,1000,10,5);
        Book newBook1 = new Book(3,"Catching Fire (The Second Book of the Hunger Games)",authors2,"publisherName", date,1000,10,5);
        Book newBook2 = new Book(4,"Harry Potter Coloring Book",authors,"publisherName", date,1000,5,10);
        Book newBook3 = new Book(5,"The Hunger Games",authors,"RIT", date,1000,10,5);
        //go through file where books for the library are stored and add each book to the "bookHash".\
        LocalDate pastDate = LocalDate.now();
        //pastDate = pastDate.minusDays(1);
        pastDate = pastDate.minusWeeks(2);
        //newBook4.setDueDate(pastDate); Transaction

        bookHash.put(newBook.getIsbn(),newBook);
        bookHash.put(newBook1.getIsbn(),newBook1);
        bookHash.put(newBook2.getIsbn(),newBook2);
        bookHash.put(newBook3.getIsbn(),newBook3);
        bookHash.put(newBook4.getIsbn(),newBook4);

        try{
            BufferedReader br = new BufferedReader(new FileReader(BOOKSFILE));

            String tempLine = "";

            while ((tempLine = br.readLine()) != null){
                //TODO: Parse file with library books and add them to the bookHash.
            }
        }
        catch (IOException ioe){
            System.out.println("Error reading the file");
        }

        display();
        //search("Harry,*,*,*");
        //saveToFile();
        //displayAvailable();
    }

    public void display(){
        ArrayList<Book> books = new ArrayList<>();
        for(Book book : bookHash.values()){
            books.add(book);
            //System.out.println("Library.Book fee cost: "+book.calculateFee()); Library.Transaction
        }
        bookPrint(books);
    }

    public ArrayList<Book> info(String title, String authors, String isbn, String publisher,
                                  String sortOrder){

        ArrayList<Book> searchResults = new ArrayList<Book>();
        Collection<Book> bookCollection = bookHash.values();
        ArrayList<Book> bookList = new ArrayList<Book>(bookCollection);

        if(!title.equals("*")){
            TitleQuery query = new TitleQuery();
            searchResults = new ArrayList<Book>(query.search(bookList, title));
            }
        if(!authors.equals("*")){
            AuthorsQuery query = new AuthorsQuery();
            if(searchResults.isEmpty()) {
                searchResults = new ArrayList<Book>(query.search(bookList, authors));
            }
            else{
                 searchResults.retainAll(query.search(bookList, authors));
            }
        }
        if(!isbn.equals("*")){
            IsbnQuery query = new IsbnQuery();
            if(searchResults.isEmpty()) {
                searchResults = new ArrayList<Book>(query.search(bookList, isbn));
            }
            else{
                searchResults.retainAll(query.search(bookList, isbn));
            }

        }
        if(!publisher.equals("*")){
            PublisherQuery query = new PublisherQuery();
            if(searchResults.isEmpty()) {
                searchResults = new ArrayList<Book>(query.search(bookList, publisher));
            }
            else{
                searchResults.retainAll(query.search(bookList, publisher));
            }
        }
        if(sortOrder.equals("title")){
            TitleSort sorter = new TitleSort();
            sorter.sort(searchResults);
            return searchResults;
        }
        else if(sortOrder.equals("publish-date")){
            PubDateSort sorter = new PubDateSort();
            sorter.sort(searchResults);
            return searchResults;
        }
        else if(sortOrder.equals("book-status")){
            NumAvailSort sorter = new NumAvailSort();
            sorter.sort(searchResults);
            for (Book book : searchResults) {
                if (book.getNumAvailableCopies() < 1) {
                    searchResults.remove(book);
                }
            }
            return searchResults;
        }
        else{
            System.out.println("The specified sort order doesn't match one of the expected values.");
            return null;
        }
    }
    public ArrayList<Book> search(String title, String authors, String isbn, String publisher,
                                  String sortOrder){

        ArrayList<Book> searchResults = new ArrayList<Book>();
        Collection<Book> bookCollection = bookHash.values();
        ArrayList<Book> bookList = new ArrayList<Book>(bookCollection);

        if(!title.equals("*")){
            TitleQuery query = new TitleQuery();
            searchResults = new ArrayList<Book>(query.search(bookList, title));
        }
        if(!authors.equals("*")){
            AuthorsQuery query = new AuthorsQuery();
            if(searchResults.isEmpty()) {
                searchResults = new ArrayList<Book>(query.search(bookList, authors));
            }
            else{
                searchResults.retainAll(query.search(bookList, authors));
            }
        }
        if(!isbn.equals("*")){
            IsbnQuery query = new IsbnQuery();
            if(searchResults.isEmpty()) {
                searchResults = new ArrayList<Book>(query.search(bookList, isbn));
            }
            else{
                searchResults.retainAll(query.search(bookList, isbn));
            }

        }
        if(!publisher.equals("*")){
            PublisherQuery query = new PublisherQuery();
            if(searchResults.isEmpty()) {
                searchResults = new ArrayList<Book>(query.search(bookList, publisher));
            }
            else{
                searchResults.retainAll(query.search(bookList, publisher));
            }
        }
        if(sortOrder.equals("title")){
            TitleSort sorter = new TitleSort();
            sorter.sort(searchResults);
            return searchResults;
        }
        else if(sortOrder.equals("publish-date")){
            PubDateSort sorter = new PubDateSort();
            sorter.sort(searchResults);
            return searchResults;
        }
        else{
            System.out.println("The specified sort order doesn't match one of the expected values.");
            return null;
        }
    }

    public void add(Book _book){
        bookHash.put(_book.getIsbn(),_book);
    }

    public void saveToFile(){
        try {
            FileWriter fw = new FileWriter(BOOKSFILE);
            PrintWriter pw = new PrintWriter(fw,true);

            for(Book book : bookHash.values()){
                pw.write(book.getIsbn()+",\""+book.getTitle()+"\",\""+book.getAuthors()+"\",\""+
                        book.getPublisher()+"\","+book.getPublishedDate()+","+book.getTotalNumCopies()+
                        ","+book.getNumAvailableCopies()+"\n");
            }
            fw.flush();
            pw.close();
            fw.close();
        }
        catch (IOException ioe){
            System.out.println("Error writting to file.");
        }
    }
    public void displayAvailable(){
        //loop through the "bookHash" and display all books that are available.
        ArrayList<Book> availableBooks = new ArrayList<>();
        for(Book book : bookHash.values()){
            if(book.getTotalNumCopies() > 0){
               availableBooks.add(book);
            }
        }
        bookPrint(availableBooks);
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

    public static HashMap<Integer, Book> getBookHash(){
        return bookHash;
    }

}
