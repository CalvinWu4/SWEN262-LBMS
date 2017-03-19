package Library;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    //check class diagram for missing parameter
//    public void search(String _query, String _field){
//        ArrayList<Book> books = new ArrayList<>();
//        switch (_field) {
//            case "title":
//                for(Book book : bookHash.values()){
//                    String title = book.getTitle();
//                    Pattern pattern = Pattern.compile(_query);
//                    Matcher matcher = pattern.matcher(title);
//                    if(matcher.find()){
//                        books.add(book);
//                    }
//                }
//                break;
//
//            case "author":
//                for(Book book : bookHash.values()){
//                    String author = book.getAuthorsString();
//                    Pattern pattern = Pattern.compile(_query);
//                    Matcher matcher = pattern.matcher(author);
//                    if(matcher.find()){
//                        books.add(book);
//                    }
//                }
//                break;
//
//            case "publisher":for(Book book : bookHash.values()){
//                    String publisher = book.getPublisher();
//                    Pattern pattern = Pattern.compile(_query);
//                    Matcher matcher = pattern.matcher(publisher);
//                    if(matcher.find()){
//                        books.add(book);
//                    }
//                }
//
//                break;
//        }
//        bookPrint(books);
//    }

    public void search(String _query){
        List<String> queryList = new ArrayList<>(Arrays.asList(_query.split(",")));
        ArrayList<Book> searchResults = new ArrayList<>();

        for(String arg : queryList){
            if(!arg.equals("*")){
                switch(queryList.indexOf(arg)){
                    case 0:
                        //BookQuery query = new TitleQuery(;
                        //Collection c = query.search(bookHash,queryList.get(0));
                        break;
                    case 1:
                        for(Book book : bookHash.values()){
                            String author = book.getAuthorsString();
                            Pattern pattern = Pattern.compile(queryList.get(1));
                            Matcher matcher = pattern.matcher(author);
                            if(matcher.find()){
                                searchResults.add(book);
                            }
                        }
                        break;
                    case 2:
                        for(Book book : bookHash.values()){
                            Integer isbn = book.getIsbn();
                            Pattern pattern = Pattern.compile(queryList.get(3));
                            Matcher matcher = pattern.matcher(isbn.toString());
                            if(matcher.find()){
                                searchResults.add(book);
                            }
                        }
                        break;
                    case 3:
                        for(Book book : bookHash.values()){
                            String publisher = book.getPublisher();
                            Pattern pattern = Pattern.compile(queryList.get(3));
                            Matcher matcher = pattern.matcher(publisher);
                            if(matcher.find()){
                                searchResults.add(book);
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Sorting by _");
                        break;
                }
            }
        }

        ArrayList<Book> books = removeDuplicates(searchResults);

        if(books.isEmpty()){
            System.out.println("No results matches your search of: "+_query);
        }
        else {
            System.out.println("Number of Books found in search: "+books.size());
            bookPrint(books);
        }
    }

    public ArrayList<Book> removeDuplicates(ArrayList<Book> _booksList){
        ArrayList<Book> nonDuplicateList = new ArrayList<>();
        ArrayList<Integer> List = new ArrayList<>();

        for(Book book : _booksList){
            if(!List.contains(book.getIsbn())){
                nonDuplicateList.add(book);
                List.add(book.getIsbn());
            }
        }
        return nonDuplicateList;
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

    public void bookPrint(ArrayList<Book> _books){

        int idLength = 2;
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

        for(int i=0; i<=idLength; i++){
            if(i==idLength){
                line += "+";
            }
            else{
                line += "-";
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
        System.out.format("|%-"+idLength+"s|%-"+isbnLength+"s|%-"+titleLength+"s|%-"+authorsLength+"s|%-"+
                        publisherLength+"s|%-"+dateLength+"s|%-"+numOfCopies+"s|%-"+availableCopies+"s|\n",
                "ID","ISBN","Book Title", "Author(s)", "Publisher", "Date Published", "Total # Of Copies",
                "# Of AvailableCopies");
        System.out.println(line);
        for(Book book: _books){
            System.out.format("|%-"+idLength+"s|%-"+isbnLength+"s|%-"+titleLength+"s|%-"+authorsLength+"s|%-"+
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
