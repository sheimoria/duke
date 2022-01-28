package duke;
import duke.command.*;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ByeCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            String[] commandArr = fullCommand.split(" ");
            if (commandArr.length > 1) {
                String type = commandArr[0];
                String description = commandArr[1];
                if (type.equals("find")) {
                    return new FindCommand(description);
                } else if (type.equals("todo")) {
                    return new ToDoCommand(description);
                } else if (type.equals("deadline")) {
                    String by = fullCommand.split(" /by ")[1];
                    return new DeadlineCommand(description, by);
                } else if (type.equals("event")) {
                    String at = fullCommand.split(" /at ")[1];
                    return new EventCommand(description, at);
                } else {
                    int index = Integer.parseInt(description) - 1;
                    if (type.equals("mark")) {
                        return new MarkCommand(index);
                    } else if (type.equals("unmark")) {
                        return new UnmarkCommand(index);
                    } else if (type.equals("delete")) {
                        return new DeleteCommand(index);
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
