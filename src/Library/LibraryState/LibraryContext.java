package Library.LibraryState;

/**
 * Created by Brandon on 3/25/2017.
 */

public class LibraryContext {
    private LibraryState state;

    public LibraryContext(){
        this.state = new OpenState();
    }

    public void setState(LibraryState state){ this.state = state; }

    public void toggleOpenClosed(){
        state.toggle(this);
    }
    public LibraryState getState(){
        return state;
    }
}
