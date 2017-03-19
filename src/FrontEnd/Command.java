package FrontEnd;

import java.util.ArrayList;

/**
 * Created by Kevin on 3/18/2017.
 *  The command interface for BackEndCommands and Change of View Commands.
 */
public interface Command {

    Response execute();

    void setArgs(ArrayList<Parameter> args);
}
