package Library.LibraryState;

import FrontEnd.Response;

/**
 * Created by Brandon on 3/25/2017.
 */
public class ClosedState implements LibraryState {


    @Override
    public void toggle(LibraryContext wrapper){
        wrapper.setState(new OpenState());
    }
}
