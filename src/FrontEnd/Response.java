package FrontEnd;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Collection;

/**
 * Created by kevin on 3/17/2017.
 */
public class Response {


    private String message;

    private View responseView;

    /** Whether the Response is a response to end the program **/
    private Boolean endResponse = false;


    public Response(String message){
        this.message = message;
    }
    public Response(){
        this.message = "Empty response";
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
