package FrontEnd;

import java.util.Collection;

/**
 * Created by Kevin on 3/19/2017.
 *
 * This is a Parameter class, a list of parameters will be sent to the methods in the back end.
 */
public class Parameter<E> {

    protected E field;

    protected Boolean isList;


    public Parameter(E field){
        this.field = field;
        this.isList = (field instanceof Collection);
    }

    public E getParam(){
        return this.field;
    }



}
