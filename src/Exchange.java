/**
 * Created by Brandon and Kevin on 2/27/2017.
 */

import java.util.Scanner;

public abstract class Exchange {

    private boolean isComplete;
    private boolean isExecuted;

    private String request = "";

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

    public void interpret(String request){};
    //TODO: Implemented by the several exchange subclasses. Checks to see if command is valid and how to execute it.


    //Checks to see if the current command has been completed or not, then returns the command.
    String getExchange(){
        return request;
    }

}
