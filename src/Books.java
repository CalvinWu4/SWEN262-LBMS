import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anthony Perez on 3/5/17.
 */
public class Books {
    private HashMap<Integer, Book> bookHash = new HashMap<>();
    private static final String BOOKSFILE = "libraryBooks.csv";

    public Books(){
        //test for class to work
        ArrayList<String> authors = new ArrayList<>();
        authors.add("testAuthor1");
        authors.add("testAuthor2");
        LocalDate date = LocalDate.now();
//        Book newBook = new Book(1,1, "Harry Potter and the Deathly Hallows",authors,"publisherName", date,10,5, true, date);
//        Book newBook4 = new Book(2,2,"Harry Potter and the Prisoner of Azkaban",authors,"publisherName", date,10,5, false, date);
//        Book newBook1 = new Book(3,3,"Catching Fire (The Second Book of the Hunger Games)",authors,"publisherName", date,10,5, true, date);
//        Book newBook2 = new Book(4,4,"Harry Potter Coloring Book",authors,"publisherName", date,10,5, true, date);
//        Book newBook3 = new Book(5, 5,"The Hunger Games",authors,"publisherName", date,10,5, true, date);
        //go through file where books for the library are stored and add each book to the "bookHash".\
        LocalDate pastDate = LocalDate.now();
        //pastDate = pastDate.minusDays(1);
        pastDate = pastDate.minusWeeks(2);
        //newBook4.setDueDate(pastDate); Transaction

//        bookHash.put(newBook.getIsbn(),newBook);
//        bookHash.put(newBook1.getIsbn(),newBook1);
//        bookHash.put(newBook2.getIsbn(),newBook2);
//        bookHash.put(newBook3.getIsbn(),newBook3);
//        bookHash.put(newBook4.getIsbn(),newBook4);

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
        //search("Harry Potter","title");
        //saveToFile();
        //displayAvailable();
    }

    public void display(){
        ArrayList<Book> books = new ArrayList<>();
        for(Book book : bookHash.values()){
            books.add(book);
            //System.out.println("Book fee cost: "+book.calculateFee()); Transaction
        }
        bookPrint(books);
    }

    //check class diagram for missing parameter
    public void search(String _query, String _field){
        ArrayList<Book> books = new ArrayList<>();
        switch (_field) {
            case "title":
                for(Book book : bookHash.values()){
                    String title = book.getTitle();
                    Pattern pattern = Pattern.compile(_query);
                    Matcher matcher = pattern.matcher(title);
                    if(matcher.find()){
                        books.add(book);
                    }
                }
                break;

            case "author":
                for(Book book : bookHash.values()){
                    String author = book.getAuthorsString();
                    Pattern pattern = Pattern.compile(_query);
                    Matcher matcher = pattern.matcher(author);
                    if(matcher.find()){
                        books.add(book);
                    }
                }
                break;

            case "publisher":
                for(Book book : bookHash.values()){
                    String publisher = book.getPublisher();
                    Pattern pattern = Pattern.compile(_query);
                    Matcher matcher = pattern.matcher(publisher);
                    if(matcher.find()){
                        books.add(book);
                    }
                }
                break;
        }
        bookPrint(books);
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
            if(book.isAvailable()){
               availableBooks.add(book);
            }
        }
        bookPrint(availableBooks);
    }

    public void bookPrint(ArrayList<Book> _books){

        int isbnLength = 13;
        int titleLength = 0;
        int authorsLength = 0;
        int publisherLength = 0;
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
        System.out.format("|%-"+isbnLength+"s|%-"+titleLength+"s|%-"+authorsLength+"s|%-"+publisherLength+"s|%-"+
                dateLength+"s|%-"+numOfCopies+"s|%-"+availableCopies+"s|\n","ISBN","Book Title", "Author(s)",
                "Publisher", "Date Published", "Total # Of Copies", "# Of AvailableCopies");
        System.out.println(line);
        for(Book book: _books){
            System.out.format("|%-"+isbnLength+"s|%-"+titleLength+"s|%-"+authorsLength+"s|%-"+publisherLength+
                    "s|%-"+dateLength+"s|%-"+numOfCopies+"s|%-"+availableCopies+"s|\n",book.getIsbn(),book.getTitle(),
                    book.getAuthors(), book.getPublisher(), book.getPublishedDate(), book.getTotalNumCopies(),
                    book.getNumAvailableCopies());
        }
        System.out.println(line);
    }

}
