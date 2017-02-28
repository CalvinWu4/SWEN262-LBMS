/**
 * Created by Brandon on 2/27/2017.
 */

public class LBMSClient extends GenUserInterface {

    private LBMSClient(){}

    @Override
    public void printUI() {
        System.out.println(getBorder());
        System.out.println();
        System.out.println(getBorder());
    }

    public static void main(String[] args) {
        LBMSClient testPrint = new LBMSClient();
        testPrint.printUI();
        LBMSExchange newExchange = new LBMSExchange();
    }
}
