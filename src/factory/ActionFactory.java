package factory;

import action.Action;
import common.Constants;
import fileio.ActionInputData;

import java.lang.reflect.InvocationTargetException;

public final class ActionFactory {

    private ActionFactory() { }

    //  Gets class package and class name (e.g. factory.CommandFactory)
    private static String getActionType(final String actionType) {
        return Constants.FACTORY_PACKAGE
                + actionType.substring(0, 1).toUpperCase()
                + actionType.substring(1)
                + Constants.FACTORY;
    }

    /**
     * Creates and invokes "createAction" method on an action factory class
     * (e.g. CommandFactory, QueryFactory, ...)
     * Method reflects the specified public method of the Factory classes
     * @param actionInputData parameter in target method, info about new action
     * @return method returns an instance of a new Action class
     * */
    public static Action createAction(final ActionInputData actionInputData) {
        try {
            //  Declare the formal parameters the method takes (parameter type)
            Class[] arguments = new Class[]{ActionInputData.class};
            //  Get a method instance by passing method name and parameter type
            //  Invoke method on class name determined by actionType String
            //  Invoke static method by passing actionInputData parameter
            return (Action) Class.forName(getActionType(actionInputData.getActionType())).
                    getMethod("createAction", arguments).invoke(null, actionInputData);

            //  Catch exceptions
        } catch (ClassNotFoundException | InvocationTargetException
                | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
