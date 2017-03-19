/**
 * Created by kevin on 3/18/2017.
 */
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
            if(response!=null){
                System.out.println(response.getResponseMessage());
                if(!response.isEndResponse()) {
                    actual_view = response.getResponseView();
                }else{
                    System.exit(0);
                }
            }
        }
    }

    static public void main(String[] args){

        View.initMenuOptions();
        MainLoop();
    }
}
