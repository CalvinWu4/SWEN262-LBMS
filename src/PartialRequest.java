/**
 * Created by Brandon on 2/27/2017.
 */
public class PartialRequest{

    private String request;

    PartialRequest(){
        request = "";
    }

    public void setExchange(String newInput, String partialInput){
        request = partialInput + newInput;
    }

    public void setExchange(String newInput){ request = newInput; }

    public String getExchange(){
        return request;
    }
}
