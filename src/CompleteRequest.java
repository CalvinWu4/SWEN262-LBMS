/**
 * Created by Brandon on 2/27/2017.
 */
public class CompleteRequest{

    private String request;

    CompleteRequest(){
        request = "";
    }

    public void setExchange(String newInput, String partialInput){
        request = partialInput + newInput;
    }

    public String getExchange(){
        return request;
    }
}
