import java.util.Scanner;

/**
 * Created by Brandon on 2/28/2017.
 */
public class StatsExchange extends Exchange {
    StatsExchange(){
        Scanner userInput = new Scanner(System.in);
        sendRequest(userInput);
    }

    private void sendRequest(Scanner userInput){ //Retrieves user input and send it to parse().
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
        switch (request){
            case "1":
                LBMSClient switchToLBMSClient = new LBMSClient();
                break;
            case "2":
                System.out.println("Command not Implemented");
                break;
            case "3":
                System.out.println("Command not Implemented");
                break;
            case "4":
                System.out.println("Command not Implemented");
                break;
            case "5":
                System.out.println("Command not Implemented");
                break;
        }
    } //TODO: Interpret numerical command to switch between clients
}
