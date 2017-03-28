
package FrontEnd.State;


import FrontEnd.Command.MenuOption;
import FrontEnd.Response;
import FrontEnd.View;
import Library.Visitors;

import java.util.Iterator;
import java.util.Map;

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
