package FrontEnd; /**
 * Created by Brandon and Kevin on 2/27/2017.
 */

import java.util.Scanner;
import java.util.TreeMap;

/** FrontEnd.Exchange class reads the user input, interprets the input and executes the selected action
 *  Uses state design pattern **/

public final class Exchange {


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
     * @param ViewForOptions: View to obtain the options from
     */

    static public Response setExchangeView(View ViewForOptions){
        // Options to choose from, each will perform a series of Commands
        TreeMap<String,MenuOption> theOptions = ViewForOptions.getOptions();
        isComplete = false;
        isExecuted = false;
        options = theOptions;
        Scanner userInput = new Scanner(System.in);
        return getRequest(userInput);
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
    static public Response getRequest(Scanner userInput) {
        String userLine = "";
        while (!getIsComplete()) {
            System.out.print("~");
            userLine += userInput.nextLine();
            parse(userLine);
        }
        // Interpret the userInput
        return interpret(getExchange());
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
     * Interprets the finished request query from the user.
     * If the query has a proper format then it executes the selected option's command.
     * @param query: The finished query typed by the user.
     */
    static public Response interpret(String query){
        String[] args = query.split(",");
        String mainTrigger = args[0];
        // Check that the query has at least a comma to show that iu
        MenuOption chosenOption =  options.get(mainTrigger);
        if(chosenOption != null){
            isExecuted = true;
            //TODO: Check that the syntax is correct
            chosenOption.setCommandArgs(query);
            return options.get(mainTrigger).execute();
        }else{
            isExecuted = false;
            return new ErrorResponse("Invalid command");

        }


    }



    //Checks to see if the current command has been completed or not, then returns the command.
    static String getExchange(){
        return request;
    }

}
