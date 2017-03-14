import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Kevin
 *
 * This class is the representation of a Command class in the Command Design Pattern
 */
public class MenuOption {

    /** Message to display **/
    private String message;

    /** Client/View to go to if option is selected **/
    private Class<?> client;

    /** Size of the arguments accompanied **/
    private Object[] args;

    /** KeyWord that will trigger the option as selected**/
    private String keyWord;

    /** The commands to execute when the option is selected **/
    private Command[] commands;

    private int viewId;

    /**
     * Constructor for an Option that just displays a view. We only attach an ID because we have to
     * wait until the view is fully initialized and saved in the HashMap for us to look for it
     * @param keyWord: The keyword that triggers the option
     * @param message: The Message that identifies the option to be displayed to the user
     * @param viewID: The view to go to
     */
    public MenuOption(String keyWord, String message, int viewID){
        this.keyWord = keyWord;
        this.message = message;
        this.viewId = viewID;


    }

    /**
     * Constructor for a generic option with a list of commands
     * @param keyWord: The keyword that triggers the option
     * @param message: The Message that identifies the option to be displayed to the user
     * @param commands: The array of commands to be executed if the option is selected
     */
    public MenuOption(String keyWord, String message, Command[] commands){
        this.keyWord = keyWord;
        this.message = message;
        this.commands = commands;
    }

    /**
     * Execute all the commands.
     */
    public void execute() {
        //Check if the list of commands are initialized, if not then it is probably
        // because the view attached to it has not been fully initialized
        if(this.commands == null){
            //Find view in the hashMap of views

            View optionView = View.findView(this.viewId);
            this.commands = new Command[]{new Command(optionView,"setView",new Object[]{optionView})};
        }



        for (Command command : commands){
            command.execute();
        }


    }

    public String getMessage(){
        return this.message;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
