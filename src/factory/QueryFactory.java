package factory;

import common.Constants;
import fileio.ActionInputData;
import query.Query;

import java.lang.reflect.InvocationTargetException;

public final class QueryFactory {

    //  Gets class package and class name (e.g. query.Query)
    private static String getQueryType(final String actionType) {
        return Constants.QUERY_PACKAGE
                + actionType.substring(0, 1).toUpperCase()
                + actionType.substring(1);
    }

    /**
     * Calls a new Query instance based on specified action type
     * @param actionInputData constructor parameter and info about new action
     * @return new instance of Query class
     */
    public static Query createAction(final ActionInputData actionInputData) {
        try {
            //  Constructor's parameter type
            Class[] arguments = new Class[]{ActionInputData.class};
            //  Use actionInputData.getType() String to determine class name
            //  Call constructor of the class with the given argument
            //  Returns a new Query object
            return (Query) Class.forName(getQueryType(actionInputData.getActionType())).
                    getDeclaredConstructor(arguments).newInstance(actionInputData);
            //  Catch exceptions
        } catch (ClassNotFoundException | InvocationTargetException
                | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
