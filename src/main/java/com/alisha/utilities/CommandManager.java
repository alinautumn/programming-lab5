package com.alisha.utilities;

import com.alisha.commands.*;

import java.util.HashSet;

/**
 * Class for storing commands objects
 */
public class CommandManager {
    private final HashSet<Command> commands = new HashSet<>();

    public CommandManager(FileManager fileManager, UserInputManager userInputManager,
                          CollectionManager collectionManager, OutputManager outputManager,
                          HistoryManager historyManager) {
        commands.add(new HelpCommand());
        commands.add(new AddCommand(collectionManager, userInputManager, outputManager));
        commands.add(new SaveCommand(fileManager, collectionManager));
        commands.add(new ShowCommand(collectionManager));
        commands.add(new UpdateCommand(collectionManager, userInputManager, outputManager));
        commands.add(new RemoveByIdCommand(collectionManager));
        commands.add(new RemoveGreaterCommand(collectionManager, userInputManager, outputManager));
        commands.add(new ClearCommand(collectionManager));
        commands.add(new ExecuteScriptCommand(userInputManager));
        commands.add(new AddIfMinCommand(collectionManager, userInputManager, outputManager));
        commands.add(new AddIfMaxCommand(collectionManager, userInputManager, outputManager));
        commands.add(new RemoveAnyByDistanceCommand(collectionManager));
        commands.add(new PrintAscendingCommand(collectionManager));
        commands.add(new InfoCommand(collectionManager));
        commands.add(new ExitCommand());
        commands.add(new HistoryCommand(historyManager));
    }

    public HashSet<Command> getCommands() {
        return commands;
    }
}
