/**
 * Created by Brandon on 2/27/2017.
 */

import java.util.Scanner;

public class LBMSExchange extends Exchange{

    public LBMSExchange(){
        Scanner userInput = new Scanner(System.in);
        sendRequest(userInput);
    }

    public void sendRequest(Scanner userInput){ //Retrieves user input and send it to parse().
        String userLine = "";
        while (!getIsComplete()){
            System.out.print("~");
            userLine += userInput.next();
            parse(userLine);
        }
        interpret(getExchange());
    }

    @Override
    public void interpret(String request){
        System.out.println(request);
    } //TODO: Interpret numerical command to switch between clients
}
