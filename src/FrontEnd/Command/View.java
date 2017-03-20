package FrontEnd.Command;

import java.util.*;

/**
 * Created by Brandon and Kevin on 2/27/2017.
 */



public class View {

    /** The top and bottom borders of the UI **/
    static private String border = "=======================";

    /** The options currently available for the view **/
    private TreeMap<String,MenuOption> options;

    /** The ID of the FrontEnd.Command.View **/
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
     * Option format : new Option("Trigger keyword or number" , "Name to display" , Command)
     *
     *  View IDs:
     *
     *  Main menu: 0
     *  Book menu: 1
     *  Visits menu: 2
     *  Transaction menu: 3
     *  Stats menu: 4
     *  Time menu: 5
     *
     */
    static public void initMenuOptions(){
        //Option to go back to the LBMS menu
        MenuOption back = new MenuOption("1","Go Back",0);

        //Option to exit the program
        MenuOption exit = new MenuOption("quit","Exit program",new BackEndCommand("exit"),0);
        //Dummy option for the other clients
        MenuOption dummy = new MenuOption("2","Dummy Option",0);

        //LBMS view's options
        TreeMap<String,MenuOption> LBMSoptions =  new TreeMap<>();
        MenuOption books = new MenuOption("1","Books",1);
        MenuOption visitors = new MenuOption("2","Visitors" ,2);
        MenuOption transactions = new MenuOption("3","Transactions" ,3);
        MenuOption stats = new MenuOption("4","Stats for Nerds",4);
        MenuOption time = new MenuOption("5","Edit Date or Time", 5);

        //Save LMBS options in Map
        LBMSoptions.put(books.getKeyWord(),books);
        LBMSoptions.put(visitors.getKeyWord(),visitors);
        LBMSoptions.put(transactions.getKeyWord(),transactions);
        LBMSoptions.put(stats.getKeyWord(),stats);
        LBMSoptions.put(time.getKeyWord(),time);
        LBMSoptions.put(exit.getKeyWord(),exit);


        /** Books **/
        TreeMap<String,MenuOption> BookOptions =  new TreeMap<>();
        MenuOption BookSearch = new MenuOption("info","Find a Book",new BackEndCommand("LibraryBookSearch"),2);
        MenuOption BookStoreSearch  = new MenuOption("search","Search for Books in the store",new BackEndCommand("BookStoreSearch"),1);
        MenuOption BookPurchase  = new MenuOption("buy","Purchase a book",new BackEndCommand("BookPurchase"),2);
        BookOptions.put(back.getKeyWord(),back);
        BookOptions.put(BookSearch.getKeyWord(),BookSearch);
        BookOptions.put(BookStoreSearch.getKeyWord(),BookStoreSearch);
        BookOptions.put(BookPurchase.getKeyWord(),BookPurchase);
        BookOptions.put(exit.getKeyWord(),exit);

        /** Stats **/
        TreeMap<String,MenuOption> StatsOptions =  new TreeMap<>();
        MenuOption LibraryStats = new MenuOption("report","Stats from the last n days",new BackEndCommand("LibraryStatsReport"),0);
        StatsOptions.put(back.getKeyWord(),back);
        StatsOptions.put(LibraryStats.getKeyWord(),LibraryStats);
        StatsOptions.put(exit.getKeyWord(),exit);

        /** Visitors **/

        TreeMap<String,MenuOption> VisitorOptions =  new TreeMap<>();
        MenuOption RegisterVisitor = new MenuOption("register","Register a New Visitor",new BackEndCommand("RegisterVisitor"),4);
        MenuOption BeginVisit = new MenuOption("arrive","Record a visit",new BackEndCommand("BeginVisit"),1);
        MenuOption EndVisit = new MenuOption("depart","Record a Departure",new BackEndCommand("EndVisit"),1);

        VisitorOptions.put(back.getKeyWord(),back);
        VisitorOptions.put(RegisterVisitor.getKeyWord(),RegisterVisitor);
        VisitorOptions.put(BeginVisit.getKeyWord(),BeginVisit);
        VisitorOptions.put(EndVisit.getKeyWord(),EndVisit);
        VisitorOptions.put(exit.getKeyWord(),exit);

        /** Transactions **/
        TreeMap<String,MenuOption> Transactions =  new TreeMap<>();
        MenuOption ReturnBook = new MenuOption("return","Return a book",new BackEndCommand("ReturnBook"),2);
        MenuOption BorrowBook = new MenuOption("borrow","Borrow a book",new BackEndCommand("BorrowBook"),2);
        MenuOption FindBorrowedBooks = new MenuOption("borrowed","List borrowed books",new BackEndCommand("FindBorrowedBooks"),1);
        MenuOption PayFine = new MenuOption("pay","Pay all or part of a fine",new BackEndCommand("PayFine"),2);

        Transactions.put(back.getKeyWord(),back);
        Transactions.put(ReturnBook.getKeyWord(),ReturnBook);
        Transactions.put(BorrowBook.getKeyWord(),BorrowBook);
        Transactions.put(FindBorrowedBooks.getKeyWord(),FindBorrowedBooks);
        Transactions.put(PayFine.getKeyWord(),PayFine);
        Transactions.put(exit.getKeyWord(),exit);

        /** Time options **/
        TreeMap<String,MenuOption> TimeOptions =  new TreeMap<>();
        MenuOption AdvanceTime = new MenuOption("advance","Advance time",new BackEndCommand("AdvanceTime"),1);
        MenuOption CurrentDateTime = new MenuOption("datetime","Get current Date and Time",new BackEndCommand("CurrentDateTime"),0);
        TimeOptions.put(back.getKeyWord(),back);
        TimeOptions.put(AdvanceTime.getKeyWord(),AdvanceTime);
        TimeOptions.put(CurrentDateTime.getKeyWord(),CurrentDateTime);
        TimeOptions.put(exit.getKeyWord(),exit);


        View LBMSView = new View(LBMSoptions,0,"LBMS main menu");
        View BooksView = new View(BookOptions,1,"Books Menu: ");
        View VisitsView = new View(VisitorOptions,2,"Visits Menu: ");
        View TransactionsView = new View(Transactions,3,"Transactions Menu: ");
        View StatsView = new View(StatsOptions,4,"Stats Menu: ");
        View TimeView = new View(TimeOptions,5,"Date and Time Menu: ");

        views = new HashMap<>();
        views.put(LBMSView.id,LBMSView);
        views.put(BooksView.id,BooksView);
        views.put(VisitsView.id,VisitsView);
        views.put(TransactionsView.id,TransactionsView);
        views.put(StatsView.id,StatsView);
        views.put(TimeView.id,TimeView);

    }

    public View(TreeMap<String,MenuOption> options, int id, String headerMessage){
        this.options = options;
        this.id = id;
        this.headerMessage = headerMessage;
    }




    /**
     * The client main loop. Receives a view to be displayed and starts an exchange
     * @param view
     */
    static public void setView(View view){
        //Find view in the hashMap of views, if found, print the UI and start the exchange wit
        view.printUI();
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

}
