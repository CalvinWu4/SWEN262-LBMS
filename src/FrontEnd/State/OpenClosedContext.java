package FrontEnd.State;

/**
 * Created by Brandon on 3/25/2017.
 */

public class OpenClosedContext {
    private OpenOrClosed state;

    public OpenClosedContext(){
        this.state = null;
    }

    public void setState(OpenOrClosed state){ this.state = state; }

    public OpenOrClosed getState(){
        return state;
    }
}
