/**
 * Created by Brandon on 2/28/2017.
 */
public class VisitorClient extends GenUserInterface {

    VisitorClient(){
        printUI();
        VisitorExchange newExchange = new VisitorExchange();
    }

    @Override
    public void printUI() {
        System.out.println(getBorder());
        System.out.println("Visitors");
        System.out.println("1) Back");
        System.out.println("2) Register a New Visitor");
        System.out.println("3) Record a Visit");
        System.out.println("4) Record a Departure");
        System.out.println("5) Dummy Option");
        System.out.println(getBorder());
    }

}
