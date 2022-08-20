package com.alisha.commands;

import com.alisha.utilities.CollectionManager;

public class RemoveByIdCommand extends Command {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResult execute(String arg) {
        int intArg;
        try {
            intArg = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            return new CommandResult(false, "Your argument was incorrect. The command was not executed");
        }


        if (collectionManager.removeById(intArg)) {
            return new CommandResult(false, "The element was deleted successfully");
        } else {
            return new CommandResult(false, "Could not find written id. The command was not executed");
        }
    }

}
