package FrontEnd;

import FrontEnd.Command.BackEndCommand;
import FrontEnd.Command.MenuOption;

import java.util.*;

/**
 * Created by Brandon and Kevin on 2/27/2017.
 */




public class View {

    /** The top and bottom borders of the UI **/
    static private String border = "==================================================";

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
        MenuOption back = new MenuOption("1","Go back",0);

        //Option to exit the program
        MenuOption exit = new MenuOption("quit","Exit program",new BackEndCommand("exit"),0,true);
        //Dummy option for the other clients
        MenuOption dummy = new MenuOption("2","Dummy Option",0);

        //LBMS view's options
        TreeMap<String,MenuOption> LBMSoptions =  new TreeMap<>();
        MenuOption books = new MenuOption("1","Books",1);
        MenuOption visitors = new MenuOption("2","Visits" ,2);
        MenuOption transactions = new MenuOption("3","Transactions" ,3);
        MenuOption stats = new MenuOption("4","Stats",4);
        MenuOption time = new MenuOption("5","Date and Time", 5);

        //Save LMBS options in Map
        LBMSoptions.put(books.getKeyWord(),books);
        LBMSoptions.put(visitors.getKeyWord(),visitors);
        LBMSoptions.put(transactions.getKeyWord(),transactions);
        LBMSoptions.put(stats.getKeyWord(),stats);
        LBMSoptions.put(time.getKeyWord(),time);
        LBMSoptions.put(exit.getKeyWord(),exit);


        /** Books **/
        TreeMap<String,MenuOption> BookOptions =  new TreeMap<>();
        MenuOption BookSearch = new MenuOption("info","Search for books in the bookstore - ex: \'info,\"title\",author(s),isbn,publisher,sortBy;\'",new BackEndCommand("LibraryBookSearch"),2,true);
        MenuOption BookStoreSearch  = new MenuOption("search","Search for books in the library - ex: \'info,\"title\",author(s),isbn,publisher,sortBy;\'",new BackEndCommand("BookStoreSearch"),1,true);
        MenuOption BookPurchase  = new MenuOption("buy","Purchase a book - ex: \'buy,quantity,author(s),isbn(isbns separated by commas);\'",new BackEndCommand("BookPurchase"),2,true);
        BookOptions.put(back.getKeyWord(),back);
        BookOptions.put(BookSearch.getKeyWord(),BookSearch);
        BookOptions.put(BookStoreSearch.getKeyWord(),BookStoreSearch);
        BookOptions.put(BookPurchase.getKeyWord(),BookPurchase);
        BookOptions.put(exit.getKeyWord(),exit);

        /** Stats **/
        TreeMap<String,MenuOption> StatsOptions =  new TreeMap<>();
        MenuOption LibraryStats = new MenuOption("report","Stats from the last n days",new BackEndCommand("LibraryStatsReport"),0,true);
        StatsOptions.put(back.getKeyWord(),back);
        StatsOptions.put(LibraryStats.getKeyWord(),LibraryStats);
        StatsOptions.put(exit.getKeyWord(),exit);

        /** Visitors **/

        TreeMap<String,MenuOption> VisitorOptions =  new TreeMap<>();
        MenuOption RegisterVisitor = new MenuOption("register","Register a new visitor",new BackEndCommand("RegisterVisitor"),4,true);
        MenuOption BeginVisit = new MenuOption("arrive","Record a visit",new BackEndCommand("BeginVisit"),1,false);
        MenuOption EndVisit = new MenuOption("depart","Record a departure",new BackEndCommand("EndVisit"),1,true);
        MenuOption User = new MenuOption("create","create user",new BackEndCommand("RegisterUser"),4,false);

        VisitorOptions.put(back.getKeyWord(),back);
        VisitorOptions.put(RegisterVisitor.getKeyWord(),RegisterVisitor);
        VisitorOptions.put(BeginVisit.getKeyWord(),BeginVisit);
        VisitorOptions.put(EndVisit.getKeyWord(),EndVisit);
        VisitorOptions.put(exit.getKeyWord(),exit);
        VisitorOptions.put(User.getKeyWord(),User);

        /** Transactions **/
        TreeMap<String,MenuOption> Transactions =  new TreeMap<>();
        MenuOption ReturnBook = new MenuOption("return","Return a book",new BackEndCommand("ReturnBook"),2,true);
        MenuOption BorrowBook = new MenuOption("borrow","Borrow a book",new BackEndCommand("BorrowBook"),2,false);
        MenuOption FindBorrowedBooks = new MenuOption("borrowed","List borrowed books",new BackEndCommand("FindBorrowedBooks"),1,true);
        MenuOption PayFine = new MenuOption("pay","Pay all or part of a fine",new BackEndCommand("PayFine"),2,true);

        Transactions.put(back.getKeyWord(),back);
        Transactions.put(ReturnBook.getKeyWord(),ReturnBook);
        Transactions.put(BorrowBook.getKeyWord(),BorrowBook);
        Transactions.put(FindBorrowedBooks.getKeyWord(),FindBorrowedBooks);
        Transactions.put(PayFine.getKeyWord(),PayFine);
        Transactions.put(exit.getKeyWord(),exit);

        /** Time options **/
        TreeMap<String,MenuOption> TimeOptions =  new TreeMap<>();
        MenuOption AdvanceTime = new MenuOption("advance","Advance time",new BackEndCommand("AdvanceTime"),1,true);
        MenuOption CurrentDateTime = new MenuOption("datetime","Get current date and time",new BackEndCommand("CurrentDateTime"),0,true);
        TimeOptions.put(back.getKeyWord(),back);
        TimeOptions.put(AdvanceTime.getKeyWord(),AdvanceTime);
        TimeOptions.put(CurrentDateTime.getKeyWord(),CurrentDateTime);
        TimeOptions.put(exit.getKeyWord(),exit);


        View LBMSView = new View(LBMSoptions,0,"LBMS Main Menu");
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

    public String printUI(){

        String text = new String();

        text += border+"\n";
        text += headerMessage+"\n";

//        System.out.println(border);
//        System.out.println(headerMessage);

        Collection c = options.keySet();

        for (MenuOption option : options.values()){
            //System.out.println(option.getKeyWord() + ") " + option.getMessage());
            text += option.getKeyWord() + ") " + option.getMessage()+"\n";
        }
        //System.out.println(border);
        text += border;

        return text;
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
