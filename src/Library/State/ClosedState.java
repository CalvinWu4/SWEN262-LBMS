package Library.State;

import FrontEnd.Response;

/**
 * Created by Brandon on 3/25/2017.
 */
public class ClosedState implements OpenOrClosed {


    @Override
    public void toggle(OpenClosedContext wrapper){
        wrapper.setState(new OpenState());
    }

    @Override
    public Response returnResponse() {
        return null;
    }
}
