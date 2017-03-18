package FrontEnd;

import java.util.*;

/**
 * Created by Brandon and Kevin on 2/27/2017.
 */



public class View {

    /** The top and bottom borders of the UI **/
    static private String border = "=======================";

    /** The options currently available for the view **/
    private TreeMap<String,MenuOption> options;

    /** The ID of the FrontEnd.View **/
    private int id;

    /** The message of the view to be displayed **/
    private String headerMessage;

    /** The final and only HashMap of views **/
    static public HashMap<Integer,View> views;


    /**
     * Initialize all views with the options
     *
     * Option format : new Option("Trigger keyword or number" , "Name to display" , Id of view to display)
     *   or
     * Option format : new Option("Trigger keyword or number" , "Name to display" , [list of commands])
     *
     * FrontEnd.View format : new FrontEnd.View(OptionsArray, Id of view (eg: LBMS view is 0, Library.Book is 1, etc.), "Header Message")
     */
    static public void initalizeViews(){

        views = new HashMap<>();
        //LBMS view's options
        TreeMap<String,MenuOption> LBMSoptions =  new TreeMap<>();
        MenuOption books = new MenuOption("1","Library.Books",1);
        MenuOption stats = new MenuOption("2","Stats for Nerds",2);
        MenuOption visitors = new MenuOption("3","Library.Visitors" ,3);
        MenuOption date = new MenuOption("4","Edit Date", 4);

        //Save LMBS options in Map
        LBMSoptions.put("1",books);
        LBMSoptions.put("2",stats);
        LBMSoptions.put("3",visitors);
        LBMSoptions.put("4",date);


        View LBMS = new View(LBMSoptions,0,"Welcome to the LBMS System!");

        //Option to go back to the LBMS view
        MenuOption back = new MenuOption("1","Go Back",0);

        //Dummy option for the other clients
        MenuOption dummy = new MenuOption("2","Dummy Option",0);

        //Library.Books
        TreeMap<String,MenuOption> BookOptions =  new TreeMap<>();
        BookOptions.put("1",back);
        BookOptions.put("2",dummy);
        BookOptions.put("3",dummy);
        BookOptions.put("4",dummy);
        View Books = new View(BookOptions,1,"Library.Books: ");


        //Stats
        TreeMap<String,MenuOption> StatsOptions =  new TreeMap<>();
        StatsOptions.put("1",back);
        StatsOptions.put("2",dummy);
        StatsOptions.put("3",dummy);
        StatsOptions.put("4",dummy);
        View Stats = new View(StatsOptions,2,"Stats: ");

        //Library.Visitors
        //Option to go back to the LBMS view
        //TODO add correct commands, so far they just go to the visitors view
        MenuOption register = new MenuOption("2","Register a New Library.Visitor",3);
        MenuOption record_visit = new MenuOption("3","Record a visit",3);
        MenuOption record_departure = new MenuOption("4","Record a Departure",3);
        TreeMap<String,MenuOption> VisitorOptions =  new TreeMap<>();
        VisitorOptions.put("1",back);
        VisitorOptions.put("2",register);
        VisitorOptions.put("3",record_visit);
        VisitorOptions.put("4",record_departure);
        View Visitors = new View(VisitorOptions,3,"Library.Visitors: ");

        //Dates
        TreeMap<String,MenuOption> DateOptions =  new TreeMap<>();
        DateOptions.put("1",back);
        DateOptions.put("2",dummy);
        DateOptions.put("3",dummy);
        DateOptions.put("4",dummy);
        View Dates = new View(DateOptions,4,"Dates: ");

        //Finally add views to the HashMap
        views.put(LBMS.id,LBMS);
        views.put(Books.id,Books);
        views.put(Stats.id,Stats);
        views.put(Visitors.id,Visitors);
        views.put(Dates.id,Dates);
    }

    public View(TreeMap<String,MenuOption> options, int id, String headerMessage){
        this.options = options;
        this.id = id;
        this.headerMessage = headerMessage;
    }


    static public void setView(View view){
        //Find view in the hashMap of views, if found, print the UI and start the exchange wit
        view.printUI();
        Response response;
        //Keep printing the same UI and setting exchange  until the response is not an error
        while (((response = Exchange.setExchangeView(view)) instanceof ErrorResponse)){
            System.out.println(response.getResponseMessage());
            view.printUI();
        }



    }

    static public View findView(int viewId){
        return views.get(viewId);
    }

    public void printUI(){
        System.out.println(border);
        System.out.println(headerMessage);

        Collection c = options.keySet();

        for (MenuOption option : options.values()){
            System.out.println(option.getKeyWord() + ") " + option.getMessage());
        }
        System.out.println(border);

    }

    public int getId(){
        return this.id;
    }

    public TreeMap<String, MenuOption> getOptions(){
        return this.options;
    }

    public void setOptions(TreeMap<String, MenuOption> options) {
        this.options = options;
    }


    public static void main(String[] args){
        initalizeViews();
        setView(findView(0));
    }
}
