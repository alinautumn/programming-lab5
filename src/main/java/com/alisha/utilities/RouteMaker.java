package com.alisha.utilities;

import com.alisha.data.Coordinates;
import com.alisha.data.LocationFrom;
import com.alisha.data.LocationTo;
import com.alisha.data.Route;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Asks and receives user input data to make a Route object
 */
public class RouteMaker {
    private static final String ERROR_MESSAGE = "Your enter was not correct type. Try again";
    private final OutputManager outputManager;
    private final Asker asker;
    private final CollectionManager collectionManager;

    public RouteMaker(UserInputManager userInputManager, OutputManager outputManager, CollectionManager collectionManager) {
        this.outputManager = outputManager;
        this.collectionManager = collectionManager;
        this.asker = new Asker(userInputManager, outputManager);
    }

    public Route makeRoute() {
        return askForRoute();
    }

    private Route askForRoute() {
        outputManager.println("Enter route data");
        String name = asker.ask(arg -> (arg).length() > 0, "Enter name (String)",
                ERROR_MESSAGE, "The string must not be empty", x -> x, false);
        long distance = asker.ask(arg -> true, "Enter distance (long)", ERROR_MESSAGE,
                ERROR_MESSAGE, Long::parseLong, false);
        Coordinates coordinates = askForCoordinates(); //not null
        LocationFrom from = askForLocationFrom(); //not null
        LocationTo to = askForLocationTo(); //not null
        return new Route(name, coordinates, from, to, distance, collectionManager);
    }

    private LocationTo askForLocationTo() {
        outputManager.println("Enter LocationTo data");
        String name = asker.ask(arg -> (arg).length() > 0, "Enter name (String) (can be null)",
                ERROR_MESSAGE, "The string must not be empty. Try again", x -> x, true);
        double x = asker.ask(arg -> true, "Enter x (double)", ERROR_MESSAGE,
                ERROR_MESSAGE, Double::parseDouble, false);
        Integer y = asker.ask(arg -> true, "Enter y (Double)", ERROR_MESSAGE,
                ERROR_MESSAGE, Integer::parseInt, false);

        return new LocationTo(x, y, name);
    }

    private Coordinates askForCoordinates() {
        outputManager.println("Enter coordinates data");
        Double x = asker.ask(arg -> true, "Enter x (Double)", ERROR_MESSAGE,
                ERROR_MESSAGE, Double::parseDouble,false);
        Float y = asker.ask(arg -> true, "Enter y (Float)", ERROR_MESSAGE,
                ERROR_MESSAGE, Float::parseFloat,false);
        return new Coordinates(x, y);
    }

    private LocationFrom askForLocationFrom() {
        outputManager.println("Enter locationFrom data");
        String name = asker.ask(arg -> (arg).length() > 0, "Enter name (String) (can be null)",
                ERROR_MESSAGE, "The string must not be empty. Try again", x -> x, true);
        Double x = asker.ask(arg -> true, "Enter x (Double)", ERROR_MESSAGE,
                ERROR_MESSAGE, Double::parseDouble, false);
        Double y = asker.ask(arg -> true, "Enter y (Double)", ERROR_MESSAGE,
                ERROR_MESSAGE, Double::parseDouble, false);

        return new LocationFrom(x, y, name);
    }

    public static class Asker {
        private final UserInputManager userInputManager;
        private final OutputManager outputManager;


        public Asker(UserInputManager userInputManager, OutputManager outputManager) {
            this.userInputManager = userInputManager;
            this.outputManager = outputManager;
        }

        public <T> T ask(Predicate<T> predicate,
                         String askMessage,
                         String errorMessage,
                         String wrongValueMessage,
                         Function<String, T> converter,
                         boolean nullable) {
            outputManager.println(askMessage);
            String input;
            T value;
            do {
                try {
                    input = userInputManager.nextLine();
                    if ("".equals(input) && nullable) {
                        return null;
                    }

                    value = converter.apply(input);

                } catch (IllegalArgumentException e) {
                    outputManager.println(errorMessage);
                    continue;
                }
                if (predicate.test(value)) {
                    return value;
                } else {
                    outputManager.println(wrongValueMessage);
                }
            } while (true);
        }
    }
}
