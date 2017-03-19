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
     */
    public BackEndCommand(String action) {
        try{
            this.action = BackEnd.class.getMethod(action,String.class);
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
            Object[] args = {this.args};
            return (Response) action.invoke(null,args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            if(BackEnd.isDebugMode()) {
                e.printStackTrace();
                return new Response("Debug error: 405 BackEnd Method not found");
            }
            return new Response("");


        }


    }



    /**
     * TODO: Method will undo the option's action
     * @return
     */
    public void unDo(){}


}
