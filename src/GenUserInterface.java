/**
 * Created by Brandon on 2/27/2017.
 */



public abstract class GenUserInterface {

    private String border; //Used as the top and bottom borders of the UI

    public GenUserInterface(){
        border = "=======================";
    }

    /*
    Gets the top/bottom border strings of the UI
     */
    public String getBorder(){
        return border;
    }

    public void printUI(){} //TODO: Prints the UI for each client class. Implemented by each client.
}
