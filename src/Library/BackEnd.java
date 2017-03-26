package Library;

import FrontEnd.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.shape.TriangleMesh;
import org.omg.CORBA.INTERNAL;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

/**
 * Created by Kevin Bastian on 3/17/2017.
 *
 * This class represents the back end controller for the Library, every action performed in the console goes through a method in this BackEnd
 */
public final class BackEnd {


    private static Boolean debugMode;


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
        ArrayList<Book> results = Books.info(bookSearchParams[0],bookSearchParams[1],bookSearchParams[2],bookSearchParams[3],bookSearchParams[4]);
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
        ArrayList<Book> results = Books.search(bookSearchParams[0],bookSearchParams[1],bookSearchParams[2],bookSearchParams[3],bookSearchParams[4]);
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
        ArrayList<Long> booksIds = new ArrayList<>();
        for(int i = 1; i < params.size(); i++){
            booksIds.add(Long.parseLong((String) params.get(i).getParam()));
        }

//        ArrayList<Long> booksIds = new ArrayList<>();
//            for (String id : (ArrayList<String>)params.get(1).getParam()){
//                booksIds.add((Long.parseLong(id.replace("{","").replace("}",""))));
//            }
        return new Response("buy,"+Purchases.purchase(Integer.parseInt((String)params.get(0).getParam()),booksIds));
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
        ArrayList<String> registerParams = new ArrayList<>();
        for(Parameter parameter : params){
            registerParams.add((String) parameter.getParam());
        }
        return new Response("register,"+Visitors.register(registerParams.get(0),registerParams.get(1),registerParams.get(2),registerParams.get(3)));



    }

    /**
     * Begins an active visit given a visitor id and sets such visit as the visitor active visit
     * @param params The visitor id
     * @return The response of whether the visit was created successfully
     */
    static public Response BeginVisit(ArrayList<Parameter> params){
        return new Response("arrive," + Visits.visit((String) params.get(0).getParam()));
    }

    /**
     * End an active visit by getting the visitor's active visit and setting it to non-active. Finally it adds it
     * to the visitor past visits hash.
     * @param params The visitor id
     * @return The response of results
     */
    static public Response EndVisit(ArrayList<Parameter> params){
        return new Response("depart," + Visits.leave((String) params.get(0).getParam()));
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
        ArrayList<Long> booksIds = new ArrayList<>();
        for(int i = 1; i < params.size(); i++){
            booksIds.add(Long.parseLong((String) params.get(i).getParam()));
        }
        return new Response("borrow,"+Transactions.borrow((String)params.get(0).getParam(),booksIds));
    }

    /**
     * Gets given user's transactions books
     * @param params The visitor id
     * @return The response list of borrowed books.
     */
    static public Response FindBorrowedBooks(ArrayList<Parameter> params){
        return new Response("borrowed," + Transactions.findBooks((String)params.get(0).getParam()));
    }

    /**
     * Gets the user's transactions, finds the transaction that matches the given id, and finally calculates the fee
     * if applicable
     * @param params The visitor id and the book id to be returned
     * @return The response of whether the book was successfully returned and a fee message if it exists.
     */
    static public Response ReturnBook(ArrayList<Parameter> params){

        ArrayList<String> returnBookParams = new ArrayList<>();
        ArrayList<Long> booksIds = new ArrayList<>();
        int i = 0;
        for(Parameter parameter : params){
            if(parameter.getParam() instanceof Collection){
                for (String id : (ArrayList<String>)parameter.getParam()){
                    booksIds.add((Long.parseLong(id.replace("{","").replace("}",""))));
                }
            }
            if(i <= 1){
                returnBookParams.add((String) parameter.getParam());
            }
            i++;
        }
        return new Response("return,"+Transactions._return(Integer.parseInt(returnBookParams.get(0)),booksIds));
    }

    /**
     * Gets a given user transactions and reset the fine if payment is bigger than cost
     * @param params The visitor id and the amount to be paid
     * @return The response of whether amount paid was enough to pay the fine.
     */
    static public Response PayFine(ArrayList<Parameter> params){

        ArrayList<String> payFineParams = new ArrayList<>();
        for(Parameter parameter : params){
            payFineParams.add((String)parameter.getParam());
        }
        return new Response("pay,"+Transactions._pay(Integer.parseInt(payFineParams.get(0)),Integer.parseInt(payFineParams.get(1))));
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
        Integer[] daysAndHours = new Integer[2]; //Index 0 holds days, Index 1 holds hours
        int i = 0;
        for (Parameter param : params){
            daysAndHours[i] = Integer.parseInt((String)param.getParam());
            i++;
        }
        if (daysAndHours[1] == null){
            daysAndHours[1] = 0;
        }

        return new Response("advance,"+Time.incTime(daysAndHours[0],daysAndHours[1]));
    }

    /**
     * Gets the current date and time from
     * the Time class
     * @param params empty query
     * @return The response with the current date and time
     */
    static public Response CurrentDateTime(ArrayList<Parameter> params){
        return new Response("datetime," +  Time.getDate() + "," + Time.getTime());
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

        int days =0;
        if((params.get(0).getParam()).equals("report")){
            days = Integer.MAX_VALUE;
        }else{
            days = Integer.parseInt((String)params.get(0).getParam());
        }
        return new Response("report,\n"+Stats.report(days));
    }



    /// UTILITY METHODS ///

    static public Response exit(ArrayList<Parameter> params){
        for(Visitor visitor: Visitors.getVisitorMap().values()){
            if(visitor.getActiveVisit() != null) {
                visitor.getActiveVisit().setDeparture(Time.getDateTime());
            }
        }
        Visitors.save();
        return new Response("Good bye!").toggleEndResponse();
    }

    static public String[] getBookSearchParams(ArrayList<Parameter> params){
        // A list of strings with the parameters without including the authors
        String[] bookSearchParams = new String[5];
        String authors = "";//Initial authors string, it will stay like that if there are no authors

        for(int i = 0; i<bookSearchParams.length; i++){

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
                if(i==4){
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
        if(bookSearchParams[1] == null || !bookSearchParams[1].equals("*")){
            bookSearchParams[1] = finished_authors;
        }
        return bookSearchParams;
    }

}
