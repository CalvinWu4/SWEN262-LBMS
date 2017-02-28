/**
 * Created by Brandon on 2/27/2017.
 */

import java.util.Scanner;

public class LBMSExchange extends Exchange {

    LBMSExchange() {
        Scanner userInput = new Scanner(System.in);
        sendRequest(userInput);
    }

    private void sendRequest(Scanner userInput) { //Retrieves user input and send it to parse().
        String userLine = "";
        while (!getIsComplete()) {
            System.out.print("~");
            userLine += userInput.next();
            parse(userLine);
        }
        interpret(getExchange());
    }

    @Override
    public void interpret(String request) {
        switch (request) {
            case "1":
                BookClient switchToBookClient = new BookClient();
                break;
            case "2":
                StatsClient switchToStatsClient = new StatsClient();
                break;
            case "3":
                VisitorClient switchToVisitorClient = new VisitorClient();
                break;
            case "4":
                DateClient switchToDateClient = new DateClient();
                break;
            case "5":
                System.exit(0);
            default:
                System.out.println("Invalid command.");
                LBMSClient freshLBMSClient = new LBMSClient();
                break;
        } //TODO: Interpret numerical command to switch between clients
    }
}
