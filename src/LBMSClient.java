/**
 * Created by Brandon on 2/27/2017.
 */

public class LBMSClient extends GenUserInterface {

    LBMSClient(){
        printUI();
        LBMSExchange newExchange = new LBMSExchange();
    }

    @Override
    public void printUI() {
        System.out.println(getBorder());
        System.out.println("Welcome to the LBMS");
        System.out.println("1) Books");
        System.out.println("2) Stats"); //TODO: Replace dummy options with actual options in menu.
        System.out.println("3) Dummy Option"); //Do we want to use an array to keep track instead of hard coding?
        System.out.println("4) Dummy Option");
        System.out.println("5) Quit");
        System.out.println(getBorder());
    }

    public static void main(String[] args) {
        LBMSClient testPrint = new LBMSClient();

    }
}
