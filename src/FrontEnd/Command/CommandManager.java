package FrontEnd.Command;

import Library.BackEnd;

import java.util.Stack;

/**
 * Created by Calvin on 4/11/2017.
 */
public class CommandManager {
    private Stack<BackEndCommand> undoStack = new Stack<>();
    private Stack<BackEndCommand> redoStack = new Stack<>();

    public void executeCmd(BackEndCommand cmd) {
//        redoStack = new Stack<>(); // clear the redo stack
        cmd.execute();
//        if(cmd instanceof UndoableCommand) {
//            undoStack.push(cmd);
//            redoStack.push(cmd);
//        }
    }

    public void undo(){
        if(undoStack.isEmpty()) {
            return;
        }
//        UndoableCommand cmd = (UndoableCommand)undoStack.pop();
//        cmd.undo();

//        undoStack.peek().undo();          // undo most recently executed command
//        redoStack.push(undoStack.peek()); // add undone command to undo stack
//        undoStack.pop();                  // remove top entry from undo stack
    }

    public void redo(){
        if(redoStack.isEmpty()) {
            return;
        }
//        UndoableCommand cmd = (UndoableCommand)redoStack.pop();
//        cmd.redo();

//        redoStack.peek().redo();          // redo most recently executed command
//        undoStack.push(redoStack.peek()); // add undone command to redo stack
//        redoStack.pop();                  // remove top entry from redo stack
//            cmd.execute();

    }
}
