
package FrontEnd.State;


import FrontEnd.Response;
import Library.Users.Visitors;

/**
 * Created by Brandon on 3/25/2017.
 */
public class OpenState implements OpenOrClosed{


    @Override
    public void toggle(OpenClosedContext wrapper){
        Visitors.exitActiveVisitors();
        wrapper.setState(new ClosedState());
    }
    @Override
    public Response returnResponse() {
        return null;
    }
}
