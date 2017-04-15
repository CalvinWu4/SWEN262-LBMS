package FrontEnd.Command;

/**
 * Created by Calvin on 4/11/2017.
 */
public abstract class UndoableCommand {
    // Extends BackEndCommand
    public abstract void undo();
    public abstract void redo();
}
