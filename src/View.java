import java.util.*;

/**
 * Created by Brandon and Kevin on 2/27/2017.
 */



public class View {

    /** The top and bottom borders of the UI **/
    static private String border = "=======================";

    /** The options currently available for the view **/
    private TreeMap<String,MenuOption> options;

    /** The ID of the View **/
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
     * View format : new View(OptionsArray, Id of view (eg: LBMS view is 0, Book is 1, etc.), "Header Message")
     */
    static public void initalizeViews(){

        views = new HashMap<>();
        //LBMS view's options
        TreeMap<String,MenuOption> LBMSoptions =  new TreeMap<>();
        MenuOption books = new MenuOption("1","Books",1);
        MenuOption stats = new MenuOption("2","Stats for Nerds",2);
        MenuOption visitors = new MenuOption("3","Visitors" ,3);
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

        //Books
        TreeMap<String,MenuOption> BookOptions =  new TreeMap<>();
        BookOptions.put("1",back);
        BookOptions.put("2",dummy);
        BookOptions.put("3",dummy);
        BookOptions.put("4",dummy);
        View Books = new View(BookOptions,1,"Books: ");


        //Stats
        TreeMap<String,MenuOption> StatsOptions =  new TreeMap<>();
        StatsOptions.put("1",back);
        StatsOptions.put("2",dummy);
        StatsOptions.put("3",dummy);
        StatsOptions.put("4",dummy);
        View Stats = new View(StatsOptions,2,"Stats: ");

        //Visitors
        //Option to go back to the LBMS view
        //TODO add correct commands, so far they just go to the visitors view
        MenuOption register = new MenuOption("2","Register a New Visitor",3);
        MenuOption record_visit = new MenuOption("3","Record a visit",3);
        MenuOption record_departure = new MenuOption("4","Record a Departure",3);
        TreeMap<String,MenuOption> VisitorOptions =  new TreeMap<>();
        VisitorOptions.put("1",back);
        VisitorOptions.put("2",register);
        VisitorOptions.put("3",record_visit);
        VisitorOptions.put("4",record_departure);
        View Visitors = new View(VisitorOptions,3,"Visitors: ");

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
        //Select the view from the array
        view.printUI();
        Exchange newExchange = new Exchange(view);
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

    public TreeMap<String, MenuOption> getOptions(){
        return this.options;
    }

    public void setOptions(TreeMap<String, MenuOption> options) {
        this.options = options;
    }


    public static void main(String[] args){
        initalizeViews();
        setView(views.get(0));
    }
}
