package FrontEnd.Command;

import FrontEnd.Response;

/**
 * Created by Calvin on 4/11/2017.
 * Common interface for commands that can execute an undo or redo operations
 *
 */
public interface UndoableCommand extends Command {

    Response undo();
    Response redo();
}
