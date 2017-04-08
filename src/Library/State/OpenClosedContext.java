package Library.State;

/**
 * Created by Brandon on 3/25/2017.
 */

public class OpenClosedContext {
    private OpenOrClosed state;

    public OpenClosedContext(){
        this.state = new OpenState();
    }

    public void setState(OpenOrClosed state){ this.state = state; }

    public void toggleOpenClosed(){
        state.toggle(this);
    }
    public OpenOrClosed getState(){
        return state;
    }
}
