/**
 * Created by Brandon on 2/28/2017.
 */
public class StatsClient extends GenUserInterface {
    StatsClient(){
        printUI();
        StatsExchange newExchange = new StatsExchange();
    }

    @Override
    public void printUI() {
        System.out.println(getBorder());
        System.out.println("System Statistics");
        System.out.println("1) Back");
        System.out.println("2) Dummy Option"); //TODO: List actual options in menu.
        System.out.println("3) Dummy Option"); //Do we want to use an array to keep track instead of hard coding?
        System.out.println("4) Dummy Option");
        System.out.println("5) Dummy Option");
        System.out.println(getBorder());
    }
}
