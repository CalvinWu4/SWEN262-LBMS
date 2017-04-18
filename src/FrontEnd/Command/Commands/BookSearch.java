package FrontEnd.Command.Commands;

import FrontEnd.Command.Command;
import FrontEnd.Parameter;
import FrontEnd.Response;
import Library.Database.ProxyBooks;

import java.util.ArrayList;

/**
 * Created by Kevin Bastian on 4/17/2017.
 *
 */
public class BookSearch implements Command{

    private ArrayList<Parameter> args;

    public BookSearch(ArrayList<Parameter> args) {
        this.args = args;
    }

    @Override
    public Response execute() {
        String[] bookSearchParams = getBookSearchParams(this.args);
        return new Response(new ProxyBooks().search(bookSearchParams[0],bookSearchParams[1],bookSearchParams[2],bookSearchParams[3],bookSearchParams[4],"library",true));
    }

    @Override
    public void setArgs(ArrayList<Parameter> args) {
        this.args = args;
    }

    /**
     * Obtains the parsed array of parameters out of the given.
     * @param params Raw parameters, might not contain some, might have "*" for others
     * @return The parsed array of parameters
     */
    static public String[] getBookSearchParams(ArrayList<Parameter> params){
        // A list of strings with the parameters without including the authors
        String[] bookSearchParams = new String[5];
        String authors = "";//Initial authors string, it will stay like that if there are no authors

        for(int i = 0; i<bookSearchParams.length; i++){
            if(i < params.size()) {
                Parameter param = params.get(i);
                //If the parameter is an author then add it to authors array
                if (param.getParam() instanceof ArrayList){
                    for (String author : (ArrayList<String>) param.getParam()) {
                        authors = authors.concat("," + author);
                    }
                } else {
                    bookSearchParams[i] = (String) param.getParam();
                }
            }else{
                bookSearchParams[i] = "*";
            }
        }
        //Remove the first comma
        String finished_authors;
        if(!authors.equals("")){
            finished_authors = authors.substring(1);
        }else{
            finished_authors = authors;
        }
        if(bookSearchParams[1] == null || !bookSearchParams[1].equals("*")){
            bookSearchParams[1] = finished_authors;
        }
        return bookSearchParams;
    }
}
