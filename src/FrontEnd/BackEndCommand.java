package FrontEnd;

import Library.BackEnd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Kevin Bastian
 */
public class BackEndCommand implements Command {
    /** The action to be executed by the receiver**/
    private Method action;

    /** The args for the method **/
    private ArrayList<Parameter> args;


    /**
     * Construct a command to be attached to an object by setting the method according to the type of args given
     * @param action: Action to be executed
     */
    public BackEndCommand(String action) {
        try{
            this.action = BackEnd.class.getMethod(action,ArrayList.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setArgs(ArrayList<Parameter> args){
        this.args = args;
    }

    /**
     * Execute the method upton the receiver and return the response generated form the BackEnd
     * @return Response generated, either an error or a success response.
     */
    @Override
    public Response execute(){

        try {
            Object[] args = {this.args};
            return (Response) action.invoke(null,args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            if(BackEnd.isDebugMode()) {
                e.printStackTrace();
                return new Response("Debug error: 405 Error in the Back End");
            }
            return new Response("");
        } catch (Exception e){
            return new Response("There was a problem in the query ");
        }


    }



    /**
     * TODO: Method will undo the option's action
     * @return
     */
    public void unDo(){}


}
