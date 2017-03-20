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
        String[] bookSearchParams = getBookSearchParams(params);
        ArrayList<Book> results = Books.info(bookSearchParams[1],bookSearchParams[2],bookSearchParams[3],bookSearchParams[4],bookSearchParams[5]);
        if(results == null ||  results.size() == 0 || (results.get(0) == null)){

            System.out.println("The search returned an empty result");
        }else {
            Books.bookPrint(results);
        }
        // Example of a response with a view to be changed to, only necessary when the response needs to take the user to a different view
        return new Response("");
    }

    /**
     * Searches in books store for the all the books that match the query
     * @param params The params of search
     * @return The response of results
     */
    static public Response BookStoreSearch(ArrayList<Parameter> params){
        String[] bookSearchParams = getBookSearchParams(params);
        ArrayList<Book> results = Books.search(bookSearchParams[1],bookSearchParams[2],bookSearchParams[3],bookSearchParams[4],bookSearchParams[5]);
        if(results == null ||  results.size() == 0 || (results.get(0) == null)){
            System.out.println("The search returned an empty result");
        }else {
            Books.bookPrint(results);
        }
        // Example of a response with a view to be changed to, only necessary when the response needs to take the user to a different view
        return new Response("");
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

        return null;
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
        return new Response(params.get(0).getParam() + "," + Time.getDate() + "," + Time.getTime());
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

    static public String[] getBookSearchParams(ArrayList<Parameter> params){
        // A list of strings with the parameters without including the authors
        String[] bookSearchParams = new String[6];
        String authors = "";//Initial authors string, it will stay like that if there are no authors

        for(int i = 1; i<bookSearchParams.length; i++){

            if(i < params.size()) {
                Parameter param = params.get(i);
                //If the parameter is an author then add it to authors array
                if (param.getParam() instanceof ArrayList){
                    for (String author : (ArrayList<String>) param.getParam()) {
                        authors = authors.concat("," + author);
                    }
                } else {
                    bookSearchParams[i] = (String) param.getParam();
                }
            }else{
                if(i==5){
                    bookSearchParams[i] = "title";
                }else{
                    bookSearchParams[i] = "*";
                }

            }
        }
        //Remove the first comma
        String finished_authors;
        if(!authors.equals("")){
            finished_authors = authors.substring(1);
        }else{
            finished_authors = authors;
        }

        bookSearchParams[2] = finished_authors;
        return bookSearchParams;
    }

}
