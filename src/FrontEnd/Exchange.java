package FrontEnd; /**
 * Created by Brandon and Kevin on 2/27/2017.
 */

import java.util.Scanner;
import java.util.TreeMap;

/** FrontEnd.Exchange class reads the user input, interprets the input and executes the selected action
 *  Uses state design pattern **/

public final class Exchange {


    /** The view where the exchange is taking place, used whenever the exchange ends up being invalid **/
    static private View preView;

    /** Is the exchange completed **/
    static private boolean isComplete;

    /** Is the exchange executed **/
    static private boolean isExecuted;

    /** The Partial input request **/
    static private String request = "";

    /** The available options for the exchange **/
    static private TreeMap<String,MenuOption> options;

    /**
     * The Std input exchange from the user, it will select from a list of options in a specific format
     * @param view: FrontEnd.View to show if the exchange ends up being invalid.
     */
    public Exchange(View view){
        preView = view;
        // Options to choose from, each will perform a series of Commands
        TreeMap<String,MenuOption> theOptions = view.getOptions();
        isComplete = false;
        isExecuted = false;
        options = theOptions;
        Scanner userInput = new Scanner(System.in);
        getRequest(userInput);
    }

    static boolean getIsComplete(){
        return isComplete;
    }

    static public boolean getIsExecuted(){
        return isExecuted;
    }

    /**
     * Retrieves user input and send it to parse().
     * @param userInput: std input received
     */
    public static void getRequest(Scanner userInput) {
        String userLine = "";
        while (!getIsComplete()) {
            System.out.print("~");
            userLine += userInput.nextLine();
            parse(userLine);
        }
        //Only interpret
        interpret(getExchange());
    }

    /**
     * Parses the user's input to decide whether the command was complete or not.
     * @param userInput: Input to be parsed
     */
    public static void parse(String userInput){
        Scanner StringParser = new Scanner(userInput);
        String completeUserLine = "";
        StringParser.useDelimiter("");

        while(StringParser.hasNext()){
            if(StringParser.hasNext(";")){
                StringParser.close();
                isComplete = true;
                request = completeUserLine;
                return;
            }
            else{
                completeUserLine += StringParser.next();
                request = completeUserLine;
            }
        }

        StringParser.close();

    }


    /**
     * Interprets the finished requrest
     * @param request
     */
    static public void interpret(String request){
        String[] query = request.split(",");
        String mainTrigger = query[0];
        if(options.get(mainTrigger) != null){
            options.get(mainTrigger).execute();
            isExecuted = true;
        }else{
            isExecuted = false;
            System.out.println("Invalid command");
            View.setView(preView);

        }

    }



    //Checks to see if the current command has been completed or not, then returns the command.
    static String getExchange(){
        return request;
    }

}
