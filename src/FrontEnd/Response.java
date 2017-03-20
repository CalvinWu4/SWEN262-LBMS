package FrontEnd;

import FrontEnd.Command.View;

/**
 * Created by kevin on 3/17/2017.
 */
public class Response {


    private String message;

    private View responseView;

    /** Whether the Response is a response to end the program **/
    private Boolean endResponse = false;


    /**
     * Construct a response with a message to output
     * @param message The message to be outputted
     */
    public Response(String message){
        this.message = message;
    }

    /**
     * Construct a response with a message to output and a viewId to set a view to
     * @param message The message to be outputted
     */
    public Response(String message, int viewId){
        this.message = message;
        this.responseView = View.findView(viewId);
    }

    /**
     * Construct a response with no message
     */
    public Response(){
        this.message = "";
    }


    public String getResponseMessage() {
        return this.message;
    }

    public Response setResponseView(View view){
        this.responseView = view;
        return this;
    }

    public View getResponseView(){
        return this.responseView;
    }

    /**
     * Toggles the current type of response
     */
    public Boolean isEndResponse(){
        return this.endResponse;
    }

    public Response toggleEndResponse(){
        this.endResponse = !endResponse;
        return this;
    }

}
