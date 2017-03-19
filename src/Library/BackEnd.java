package Library;

import FrontEnd.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.shape.TriangleMesh;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Kevin Bastian on 3/17/2017.
 *
 * This class represents the back end controller for the Library, every action performed in the console goes through a method in this BackEnd
 */
public final class BackEnd {


    private static Boolean debugMode = false;


    public static void setDebugMode(Boolean onOrOff){
        debugMode = onOrOff;
    }

    public static Boolean isDebugMode(){
        return debugMode;
    }

    ///

    ////// BOOKS MENU ACTIONS //////

    ///
    /**
     * Searches in books for the all the books that match the query
     * @param params The params of search
     * @return The response of results
     */
    static public Response LibraryBookSearch(ArrayList<Parameter> params){
        //TODO: It needs actual implementation, this is just a dummy example
        //Test
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

        ArrayList<Book> books = new ArrayList<>();
        books.add(newBook);books.add(newBook4);books.add(newBook1);books.add(newBook2);books.add(newBook3);

        //This is cheating, it should actually pass the string of BookPrint to a response, instead what I am doing is
        // printing the books and passing and empty response
        Books.bookPrint(books);
        // Example of a response with a view to be changed to, only necessary when the response needs to take the user to a different view
        return new Response("",4);
    }

    /**
     * Searches in books store for the all the books that match the query
     * @param params The params of search
     * @return The response of results
     */
    static public Response BookStoreSearch(ArrayList<Parameter> params){
        return null;//TODO
    }

    /**
     * Buys an specific book given by an id in the query
     * @param params The params of search
     * @return The response of results
     */
    static public Response BookPurchase(ArrayList<Parameter> params){
        return null;//TODO
    }


    /////

    ////// VISIT MENU ACTIONS //////

    /////
    /**
     * Registers a visitor by adding a new Visitor to Visitors
     * @param params The info of the visitor to be registered
     * @return The response of whether the registration was successful or not
     */
    static public Response RegisterVisitor(ArrayList<Parameter> params){

        return null;


    }

    /**
     * Begins an active visit given a visitor id and sets such visit as the visitor active visit
     * @param params The visitor id
     * @return The response of whether the visit was created successfully
     */
    static public Response BeginVisit(ArrayList<Parameter> params){
        return null;//TODO
    }

    /**
     * End an active visit by getting the visitor's active visit and setting it to non-active. Finally it adds it
     * to the visitor past visits hash.
     * @param params The visitor id
     * @return The response of results
     */
    static public Response EndVisit(ArrayList<Parameter> params){
        return null;//TODO
    }

    /////

    ////// TRANSACTION MENU ACTIONS //////

    /////
    /**
     * Creates a new Transaction, modifies the given book's availability and finally adds the book to the
     * Visitor's borrowed books (or list of transactions)
     * @param params The book id to be borrowed
     * @return The response of whether the book was able to be borrowed
     */
    static public Response BorrowBook(ArrayList<Parameter> params){
        //TODO
        return new Response("Borrowed book! (not really)");
    }

    /**
     * Gets given user's transactions books
     * @param params The visitor id
     * @return The response list of borrowed books.
     */
    static public Response FindBorrowedBooks(ArrayList<Parameter> params){
        return null;//TODO
    }

    /**
     * Gets the user's transactions, finds the transaction that matches the given id, and finally calculates the fee
     * if applicable
     * @param params The visitor id and the book id to be returned
     * @return The response of whether the book was successfully returned and a fee message if it exists.
     */
    static public Response ReturnBook(ArrayList<Parameter> params){
        return null;//TODO
    }

    /**
     * Gets a given user transactions and reset the fine if payment is bigger than cost
     * @param params The visitor id and the amount to be paid
     * @return The response of whether amount paid was enough to pay the fine.
     */
    static public Response PayFine(ArrayList<Parameter> params){
        return null;//TODO
    }

    /////

    ////// TIME MENU ACTIONS //////

    /////
    /**
     * Advances current time by a given amount
     * @param params The amount of time to be changed
     * @return The response of whether the change was made or not
     */
    static public Response AdvanceTime(ArrayList<Parameter> params){
        return new Response("Advanced time (Not really)");//TODO
    }

    /**
     * Gets the current date and time from the Time class
     * @param params empty query
     * @return The response with the current date and time
     */
    static public Response CurrentDateTime(ArrayList<Parameter> params){
        return new Response(params.get(0) + "," + Time.getDate() + "," + Time.getTime());
    }

    /////

    ////// STATS MENU ACTIONS //////

    /////

    /**
     * Returns the stats for the last given days
     * @param params The amount of days from today and before
     * @return The response of stats
     */
    static public Response LibraryStatsReport(ArrayList<Parameter> params){
        return null;//TODO
    }



    /// UTILITY METHODS ///

    static public Response exit(ArrayList<Parameter> params){
        return new Response("Good bye!").toggleEndResponse();
    }


}
