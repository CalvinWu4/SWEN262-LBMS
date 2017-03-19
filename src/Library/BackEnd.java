package Library;

import FrontEnd.*;
import javafx.scene.shape.TriangleMesh;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Kevin Bastian on 3/17/2017.
 *
 * This class represents the back end controller for the Library, every action performed in the console goes through a method in this BackEnd
 */
public final class BackEnd {



    ///

    ////// BOOKS MENU ACTIONS //////

    ///
    /**
     * Searches in books for the all the books that match the query
     * @param query The params of search
     * @return The response of results
     */
    static public Response LibraryBookSearch(String query){
        return null;
    }

    /**
     * Searches in books store for the all the books that match the query
     * @param query The params of search
     * @return The response of results
     */
    static public Response BookStoreSearch(String query){
        return null;
    }

    /**
     * Buys an specific book given by an id in the query
     * @param query The params of search
     * @return The response of results
     */
    static public Response BookPurchase(String query){
        return null;
    }


    /////

    ////// VISIT MENU ACTIONS //////

    /////
    /**
     * Registers a visitor by adding a new Visitor to Visitors
     * @param query The info of the visitor to be registered
     * @return The response of whether the registration was successful or not
     */
    static public Response RegisterVisitor(String query){
        return null;
    }

    /**
     * Begins an active visit given a visitor id and sets such visit as the visitor active visit
     * @param query The visitor id
     * @return The response of whether the visit was created successfully
     */
    static public Response BeginVisit(String query){
        return null;
    }

    /**
     * End an active visit by getting the visitor's active visit and setting it to non-active. Finally it adds it
     * to the visitor past visits hash.
     * @param query The visitor id
     * @return The response of results
     */
    static public Response EndVisit(String query){
        return null;
    }

    /////

    ////// TRANSACTION MENU ACTIONS //////

    /////
    /**
     * Creates a new Transaction, modifies the given book's availability and finally adds the book to the
     * Visitor's borrowed books (or list of transactions)
     * @param query The book id to be borrowed
     * @return The response of whether the book was able to be borrowed
     */
    static public Response BorrowBook(String query){
        return null;
    }

    /**
     * Gets given user's transactions books
     * @param query The visitor id
     * @return The response list of borrowed books.
     */
    static public Response FindBorrowedBooks(String query){
        return null;
    }

    /**
     * Gets the user's transactions, finds the transaction that matches the given id, and finally calculates the fee
     * if applicable
     * @param query The visitor id and the book id to be returned
     * @return The response of whether the book was successfully returned and a fee message if it exists.
     */
    static public Response ReturnBook(String query){
        return null;
    }

    /**
     * Gets a given user transactions and reset the fine if payment is bigger than cost
     * @param query The visitor id and the amount to be paid
     * @return The response of whether amount paid was enough to pay the fine.
     */
    static public Response PayFine(String query){
        return null;
    }

    /////

    ////// TIME MENU ACTIONS //////

    /////
    /**
     * Advances current time by a given amount
     * @param query The amount of time to be changed
     * @return The response of whether the change was made or not
     */
    static public Response AdvanceTime(String query){
        System.out.println("Advanced time");
        return null;
    }

    /**
     * Gets the current date and time from the Time class
     * @param query empty query
     * @return The response with the current date and time
     */
    static public Response CurrentDateTime(String query){
        System.out.println(Time.getDate());
        return new Response(Time.getDate() + " " + Time.getTime());
    }

    /////

    ////// STATS MENU ACTIONS //////

    /////

    /**
     * Returns the stats for the last given days
     * @param query The amount of days from today and before
     * @return The response of stats
     */
    static public Response LibraryStatsReport(String query){
        return null;
    }



    /// UTILITY METHODS ///

    static public Response exit(String query){
        return new Response("Good bye!").toggleEndResponse();
    }
}
