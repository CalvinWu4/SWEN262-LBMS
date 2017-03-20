package FrontEnd.Command;

import FrontEnd.Parameter;
import FrontEnd.Response;

import java.util.ArrayList;

/**
 * Created by Kevin
 *
 * This class is the representation of a FrontEnd.Command.BackEndCommand class in the FrontEnd.Command.BackEndCommand Design Pattern
 */
public class MenuOption {

    /** Message to display **/
    private String message;

    /** Size of Arguments to be provided**/
    private int min_args_size;

    /** KeyWord that will trigger the option as selected**/
    private String keyWord;

    /** The commands to execute when the option is selected **/
    private Command command;

    /** If this Option is just to travel to a different view, then we set a viewID **/
    private Integer futureViewId = null;

    /**
     * Constructor for an Option that connects a BackEndCommand in the backend to the an option in the front
     * end.
     * @param keyWord: The keyword that triggers the option
     * @param message: The Message that identifies the option to be displayed to the user
     * @param command: The command to be executed if the option is selected
     */
    public MenuOption(String keyWord, String message, BackEndCommand command, int min_args_size){
        this.keyWord = keyWord;
        this.message = message;
        this.command = command;
        this.min_args_size = min_args_size;
    }

    /**
     * Constructor for an Option that just displays a view. We only attach an ID because we have to
     * wait until the view is fully initialized and saved in the HashMap for us to look for it. Useful
     * for when we have command to takes us to a view that has not been saved on the Views HashMap yet.
     * @param keyWord: The keyword that triggers the option
     * @param message: The Message that identifies the option to be displayed to the user
     * @param futureViewId: The view to be loaded for the option selected
     */
    public MenuOption(String keyWord, String message, int futureViewId){
        this.keyWord = keyWord;
        this.message = message;
        this.futureViewId = futureViewId;
        this.min_args_size = 0;
    }

    /**
     * Execute the option's command
     */
    public Response execute() {
        //Check if this option has a view assigned, if yes then create a View command to setView to the view attached to
        // this option.
        this.setCommandIfView();
        //Response from the command received by the BackEnd class or just null if it just sets a view
        return this.command.execute();

    }

    /**
     * Set's up the command arguments only if the command is a BackEndCommand, Views command don't need arguments
     * @param args: Args to be set to the command.
     */
    public void setCommandArgs(ArrayList<Parameter> args){
        if(this.command instanceof BackEndCommand){
            this.command.setArgs(args);
        }

    }

    /**
     *  Check if this option has a view assigned, if yes then create a View command to setView to the view attached to
     *  this option.
     */
    public void setCommandIfView(){
        if(this.futureViewId != null && this.command == null){
            this.command = new ViewCommand(this.futureViewId);
        }
    }

    public Command getCommand(){
        return this.command;
    }

    public String getMessage(){
        return this.message;
    }

    public int getMinArgsSize() {
        return this.min_args_size;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
