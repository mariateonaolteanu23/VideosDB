package factory;

import common.Constants;
import common.Helpers;
import criteria.UsersCriteria;
import user.User;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class UsersCriteriaFactory {
    private UsersCriteriaFactory() { }

    //  Gets class package and class name (e.g. criteria.ByNumRatings)
    private static String getUsersCriteria(final String criteria) {
        String parsedCriteria = Helpers.eraseUnderLine(criteria);
        return Constants.CRITERIA_PACKAGE
                + Constants.BY
                + parsedCriteria.substring(0, 1).toUpperCase()
                + parsedCriteria.substring(1);
    }

    /**
     * Calls a nea UsersCriteria instance based on specified criteria
     * (e.g. ByNumRatings)
     * @param criteria string specifies query's type of criteria
     * @param filters list of filters
     * @param users the list of users to query
     * @return new instance of an UsersCriteria class
     */
    public static UsersCriteria createUsersCriteria(final String criteria,
                                                    final List<List<String>> filters,
                                                    final List<User> users) {
        try {
            //  Constructor's parameters types
            Class[] arguments = new Class[]{String.class, List.class, List.class};
            //  Use criteria String to determine class name
            //  Call constructor of the class with the given arguments
            //  Returns a new UsersCriteria object
            return (UsersCriteria)  Class.forName(getUsersCriteria(criteria)).
                    getDeclaredConstructor(arguments).newInstance(criteria, filters, users);
            //  Catch exceptions
        } catch (ClassNotFoundException | InvocationTargetException
                | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
