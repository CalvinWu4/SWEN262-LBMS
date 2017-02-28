/**
 * Created by Brandon on 2/27/2017.
 */

import java.util.Scanner;

public abstract class Exchange {

    private boolean isComplete;
    private boolean isExecuted;
    private CompleteRequest completeRequest = new CompleteRequest();
    private PartialRequest partialRequest = new PartialRequest();

    Exchange(){
        isComplete = false;
        isExecuted = false;
    }

    boolean getIsComplete(){
        return isComplete;
    }

    public boolean getIsExecuted(){
        return isExecuted;
    }

    /*
    Parses the user's input to decide whether the command was complete or not.
     */
    void parse(String userInput){
        Scanner StringParser = new Scanner(userInput);
        String completeUserLine = "";
        StringParser.useDelimiter("");

        while(StringParser.hasNext()){
            if(StringParser.hasNext(";")){
                StringParser.close();
                isComplete = true;
                completeRequest.setExchange(completeUserLine, partialRequest.getExchange());
                return;
            }
            else{
                completeUserLine += StringParser.next();
            }
        }

        completeUserLine = "";
        StringParser.close();

        if(partialRequest.getExchange() != null) {
            partialRequest.setExchange(completeUserLine, partialRequest.getExchange());
        }
        else{
            partialRequest.setExchange(completeUserLine);
        }
    }

    public void interpret(String request){};
    //TODO: Implemented by the several exchange subclasses. Checks to see if command is valid and how to execute it.

    //Checks to see if the current command has been completed or not, then returns the command.
    String getExchange(){
        if(completeRequest.getExchange().isEmpty()){
            return partialRequest.getExchange();
        }
        else{
            return completeRequest.getExchange();
        }
    };

}
