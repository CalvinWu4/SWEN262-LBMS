package FrontEnd;

/**
 * Created by Kevin on 3/18/2017.
 *
 */
public class ViewCommand implements Command {

    private int viewToGo;

    public ViewCommand(int ViewId){
        this.viewToGo = ViewId;
    }

    /**
     * Execute the command by setting up the view
     * @return A Response depending if the view was able to be loaded or not.
     */
    @Override
    public Response execute() {
        View nextView = View.findView(this.viewToGo);
        if (nextView != null) {
            return new Response().setResponseView(nextView);
        } else {
            return new Response("404 view not found");
        }
    }

    /**
     * There are not arguments for a command that just sets the view, since this command already has the info of the
     * view to change to.
     */
    @Override
    public void setArgs(String dummy){}
}
