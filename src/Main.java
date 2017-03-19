/**
 * Created by kevin on 3/18/2017.
 */
import FrontEnd.BackEndCommand;
import FrontEnd.Exchange;
import FrontEnd.Response;
import FrontEnd.View;

public class Main {


    static public void MainLoop(){
        //Initial view is the main menu
        View actual_view = View.findView(0);
        // Print the first Main menu view, and then print the response received by the exchange and change the view to
        // the one attached to the response.
        while (true){
            actual_view.printUI();
            Response response = Exchange.setExchangeView(actual_view);
            // If there is a problem with the response just loop again
            if(response!=null){
                System.out.println(response.getResponseMessage());
                //Check that the response is not an end response
                if(!response.isEndResponse()){
                    // Only if there is a view attached to the response we assign a new view.
                    if(response.getResponseView() != null ){
                        actual_view = response.getResponseView();
                    }
                }else{
                    System.exit(0);
                }
            }
        }
    }

    static public void main(String[] args){
        //BackEndCommand.setDebugMode();
        View.initMenuOptions();
        MainLoop();
    }
}
