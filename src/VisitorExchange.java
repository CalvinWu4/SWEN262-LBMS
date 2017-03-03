import java.util.Scanner;

/**
 * Created by Brandon on 2/28/2017.
 */
public class VisitorExchange extends Exchange{
    VisitorExchange(){
        Scanner userInput = new Scanner(System.in);
        sendRequest(userInput);
    }

    private void sendRequest(Scanner userInput){ //Retrieves user input and send it to parse().
        String userLine = "";
        while (!getIsComplete()){
            System.out.print("~");
            userLine += userInput.nextLine();
            parse(userLine);
        }
        interpret(getExchange());
    }

    @Override
    public void interpret(String request){
        VisitorClient freshVisitorClient;
        switch (request){
            case "1":
                LBMSClient switchToLBMSClient = new LBMSClient();
                break;
            case "2":
                System.out.println("Command not Implemented");
                freshVisitorClient = new VisitorClient();
                break;
            case "3":
                System.out.println("Command not Implemented");
                freshVisitorClient = new VisitorClient();
                break;
            case "4":
                System.out.println("Command not Implemented");
                freshVisitorClient = new VisitorClient();
                break;
            case "5":
                System.out.println("Command not Implemented");
                freshVisitorClient = new VisitorClient();
                break;
            default:
                System.out.println("Invalid Command.");
                freshVisitorClient = new VisitorClient();
                break;
        }
    } //TODO: Interpret numerical command to switch between clients
}
