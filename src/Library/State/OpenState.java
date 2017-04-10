
package Library.State;


import FrontEnd.Response;

/**
 * Created by Brandon on 3/25/2017.
 */
public class OpenState implements OpenOrClosed{


    @Override
    public void toggle(OpenClosedContext wrapper){
        wrapper.setState(new ClosedState());
    }
    @Override
    public Response returnResponse() {
        return null;
    }
}
