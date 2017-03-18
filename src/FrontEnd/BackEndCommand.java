package FrontEnd;

import Library.BackEnd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Kevin Bastian
 */
public class BackEndCommand implements Command {
    /** The action to be executed by the receiver**/
    private Method action;

    /** The args for the method **/
    private String args;


    /**
     * Construct a command to be attached to an object by setting the method according to the type of args given
     * @param action: Action to be executed
     * @param args: Predefined args
     */
    public BackEndCommand(String action, String args) {
        this.args = args;
        try{
            this.action = BackEnd.class.getMethod(action);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setArgs(String args){
        this.args = args;
    }

    /**
     * Execute the method upton the receiver and return the response generated form the BackEnd
     * @return Response generated, either an error or a success response.
     */
    @Override
    public Response execute(){
        try {
            return (Response) action.invoke(args);
        } catch (IllegalAccessException | InvocationTargetException e)  {
            e.printStackTrace();
        }
        return new ErrorResponse("Debug error: 405 BackEnd Method not found");
    }



    /**
     * TODO: Method will undo the option's action
     * @return
     */
    public void unDo(){}


}
