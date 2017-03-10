/**
 * Created by Brandon on 2/28/2017.
 */
public class BookClient extends GenUserInterface {
    BookClient(){
        printUI();
        BookExchange newExchange = new BookExchange();
    }

    @Override
    public void printUI() {
        System.out.println(getBorder());
        System.out.println("Books");
        System.out.println("1) Back");
        System.out.println("2) Book Search"); //TODO: List actual options in menu.
        System.out.println("3) View Available Books"); //Do we want to use an array to keep track instead of hard coding?
        System.out.println("4) View all Books");
        System.out.println("5) Dummy Option");
        System.out.println(getBorder());
    }

}
