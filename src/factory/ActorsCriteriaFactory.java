package factory;

import actor.Actor;
import common.Constants;
import common.Helpers;
import criteria.ActorsCriteria;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class ActorsCriteriaFactory {

    private ActorsCriteriaFactory() { }

    //  Gets class package and class name (e.g. criteria.ByAverage)
    private static String getActorsCriteria(final String criteria) {
        String parsedCriteria = Helpers.eraseUnderLine(criteria);
        return Constants.CRITERIA_PACKAGE
                + Constants.BY
                + parsedCriteria.substring(0, 1).toUpperCase()
                + parsedCriteria.substring(1);
    }

    /**
     * Calls a new ActorsCriteria instance based on specified criteria
     * (e.g. ByAverage, ByAwards, ByFilterDescription)
     * @param criteria string specifies query's type of criteria
     * @param filters list of filters
     * @param actors the list of actors to query
     * @return new instance of an ActorsCriteria class
     */
    public static ActorsCriteria createActorsCriteria(final String criteria,
                                                      final List<List<String>> filters,
                                                      final List<Actor> actors) {
        try {
            //  Constructor's parameters types
            Class[] arguments = new Class[]{String.class, List.class, List.class};
            //  Use criteria String to determine class name
            //  Call constructor of the class with the given arguments
            //  Returns a new ActorsCriteria object
            return (ActorsCriteria)  Class.forName(getActorsCriteria(criteria)).
                    getDeclaredConstructor(arguments).newInstance(criteria, filters, actors);
            //  Catch exceptions
        } catch (ClassNotFoundException | InvocationTargetException
                | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
