package factory;

import command.Command;
import common.Constants;
import fileio.ActionInputData;

import java.lang.reflect.InvocationTargetException;

public final class CommandFactory {
    //  Gets class package and class name (e.g. command.View)
    private static String getCommandType(final String actionType) {
        return Constants.COMMAND_PACKAGE
                + actionType.substring(0, 1).toUpperCase()
                + actionType.substring(1);
    }

    /**
     * Calls a new Command instance based on specified action type
     * (e.g. Rating, View, Favorite)
     * @param actionInputData constructor parameter and info about new action
     * @return new instance of Command class
     */
    public static Command createAction(final ActionInputData actionInputData) {
        try {
            //  Constructor's parameter type
            Class[] arguments = new Class[]{ActionInputData.class};
            //  Use actionInputData.getType() String to determine class name
            //  Call constructor of the class with the given argument
            //  Returns a new Command object
            return (Command) Class.forName(getCommandType(actionInputData.getType())).
                    getDeclaredConstructor(arguments).newInstance(actionInputData);
            //  Catch exceptions
        } catch (ClassNotFoundException | InvocationTargetException
                | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
