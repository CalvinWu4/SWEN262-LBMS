package FrontEnd;

/**
 * Created by Kevin on 3/17/2017.
 *
 */
public class ErrorResponse implements Response {

    private String errorMessage;

    public ErrorResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String getResponseMessage() {
        return this.errorMessage;
    }

}
