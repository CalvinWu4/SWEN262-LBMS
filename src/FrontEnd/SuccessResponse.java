package FrontEnd;

/**
 * Created by Kevin on 3/17/2017.
 *
 */
public class SuccessResponse implements Response {

    private String successMessage;


    public SuccessResponse(String successMessage){
        this.successMessage = successMessage;
    }


    @Override
    public String getResponseMessage() {
        return this.successMessage;
    }

}
