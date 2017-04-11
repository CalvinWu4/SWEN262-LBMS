
package Library.LibraryState;


import FrontEnd.Response;

/**
 * Created by Brandon on 3/25/2017.
 */
public class OpenState implements LibraryState {


    @Override
    public void toggle(LibraryContext wrapper){
        wrapper.setState(new ClosedState());
    }
    @Override
    public Response returnResponse() {
        return null;
    }
}
