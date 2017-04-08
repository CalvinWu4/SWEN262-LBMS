package FrontEnd;

/**
 * Created by Brandon and Kevin on 2/27/2017.
 */

import FrontEnd.Command.BackEndCommand;
import FrontEnd.Command.MenuOption;
import FrontEnd.Command.ViewCommand;
import Library.State.OpenState;
import Library.BackEnd;
import Library.Time;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/** FrontEnd.Exchange class reads the user input, interprets the input and executes the selected action
 *  Uses state design pattern **/

public final class Exchange {

    /** Is the exchange completed **/
    static private boolean isComplete;

    /** The Partial input request **/
    static private String request = "";

    /** The available options for the exchange **/
    static private TreeMap<String,MenuOption> options;

    /** The view to load after the response is received **/
    static private View viewAfterResponse;


    /**
     * The Std input exchange from the user, it will select from a list of options in a specific format
     * @param ViewForOptions: View to obtain the options from
     */

    static public Response setExchangeView(View ViewForOptions){
        viewAfterResponse = ViewForOptions;
        // Options to choose from, each will perform a series of Commands
        TreeMap<String,MenuOption> theOptions = ViewForOptions.getOptions();
        isComplete = false;
        options = theOptions;
        Scanner userInput = new Scanner(System.in);
        return getRequest(userInput);
    }

    static boolean getIsComplete(){
        return isComplete;
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
     * Upon execution set the response view to be the actual view.
     * @param query: The finished query typed by the user.
     */
    static public Response interpret(String query) {
        String[] args = query.split(",");
        String mainTrigger = args[0];
        // Check that the query has at least a comma to show that iu
        MenuOption chosenOption = options.get(mainTrigger);
        if (chosenOption != null) {
            //Check that the option is available after the library has closed
            if (chosenOption.isAvailableAfterClosed() || Time.getTimeContext().getState() instanceof OpenState) {
                if (args.length >= chosenOption.getMinArgsSize() + 1) {
                    //Set the arguments for the commands
                    if (chosenOption.getCommand() instanceof ViewCommand) {
                        //Empty arrayList if the command is to just change views
                        chosenOption.setCommandArgs(new ArrayList<>());
                    } else {
                        chosenOption.setCommandArgs(getParamsFromRequest(query));
                    }

                    //Execute command
                    Response response = chosenOption.execute();

                    //Assign the same view that this exchange is taking place on if the command doesn't have one already
                    if (chosenOption.getCommand() instanceof BackEndCommand) {
                        if (response == null) {
                            if (BackEnd.isDebugMode()) {
                                return new Response("Backend method not implemented or something went wrong").setResponseView(viewAfterResponse);
                            } else {
                                return new Response("Something went wrong").setResponseView(viewAfterResponse);
                            }
                        } else {
                            if (response.getResponseView() == null) {
                                response.setResponseView(viewAfterResponse);
                            }
                        }
                    }
                    return response;

                } else {
                    int numMissing = chosenOption.getMinArgsSize() + 1 - args.length;
                    return new Response("Incorrect number of arguments. Missing: " + numMissing + " arguments").setResponseView(viewAfterResponse);
                }

            }else{
                return new Response("Command not available during closed library").setResponseView(viewAfterResponse);
            }

        } else {
            return new Response("Invalid command").setResponseView(viewAfterResponse);

        }

    }
    //Checks to see if the current command has been completed or not, then returns the command.
    static String getExchange(){
        return request;
    }


    static ArrayList<Parameter> getParamsFromRequest(String query){
        String[] args = query.split(",");
        ArrayList<Parameter> parameters = new ArrayList<>();
        if(args.length <= 1){
            //return an arrayList with just the query as the only parameter
            parameters.add(new Parameter<>(query));
            return parameters;
        }else{
            Boolean braketChecker = false;
            ArrayList<String> multiparam = new ArrayList<>();
            String subparam = "";
            for(int i=1; i<args.length;i++){
                if (args[i].contains("{")) {
                    braketChecker = true;
                } else if (args[i].contains("}")) {
                    braketChecker = false;
                    multiparam.add(args[i]);
                    parameters.add(new Parameter<>(multiparam));
                    continue;
                }
                if (braketChecker){
                    multiparam.add(args[i]);
                    if(args[i].contains("}")){
                        parameters.add(new Parameter<>(multiparam));
                    }
                }else {
                    parameters.add(new Parameter<>(args[i]));
                }

            }
        }
        return parameters;
    }
}
