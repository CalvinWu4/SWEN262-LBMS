import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Kevin Bastian
 */
public class Command {
    /** The receiver object that will execute the method **/
    private Object receiver;

    /** The action to be executed by the receiver**/
    private Method action;

    /** The args for the method **/
    private Object[] args;


    /**
     * Construct a command by seting the method according to the type of args given
     * @param receiver: Receiver object
     * @param action: Action to be executed
     * @param args: Predefined args
     */
    public Command(Object receiver, String action, Object[] args) {
        this.receiver = receiver;
        this.args = args; //This is just a placeholder for the type of arguments, so that the object
                          // can find the correct method. The
        Class objectClass = receiver.getClass();
        //Get types of args
        Class[] argTypes = new Class[args.length];
        for (int i=0; i<args.length; i++) {
            System.out.println(args[i].getClass());
            argTypes[i] = args[i].getClass();
        }
        System.out.println(action);
        System.out.println(objectClass);
        try {
            this.action = objectClass.getMethod(action,View.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setArgs(Object[] args){
        this.args = args;
    }

    /**
     * Excute the method upton the receiver
     * @return
     */
    public Object execute(){
        try {
            return action.invoke(receiver,args);
        } catch (IllegalAccessException e)  {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;

    }



    /**
     * TODO: Method will undo the option's action
     * @return
     */
    public void unDo(){}


}
