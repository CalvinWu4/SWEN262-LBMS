package FrontEnd.State;

import FrontEnd.Response;
import FrontEnd.State.OpenOrClosed;

/**
 * Created by Brandon on 3/25/2017.
 */
public class ClosedState implements OpenOrClosed {

    @Override
    public Response returnResponse() {
        return null;
    }
}
