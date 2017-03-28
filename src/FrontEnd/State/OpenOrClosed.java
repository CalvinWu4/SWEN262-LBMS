package FrontEnd.State;

import FrontEnd.Response;

/**
 * Created by Brandon on 3/25/2017.
 */
public interface OpenOrClosed {

    public void toggle(OpenClosedContext wrapper);
    public Response returnResponse();
}
