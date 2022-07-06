package factory;

import common.Constants;
import query.ObjectQuery;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class ObjectQueryFactory {
    private ObjectQueryFactory() { }

    //  Gets class package and class name (e.g. query.UsersQuery)
    private static String getObjectQuery(final String object) {
        return Constants.QUERY_PACKAGE
                + object.substring(0, 1).toUpperCase()
                + object.substring(1)
                + Constants.QUERY.substring(0, 1).toUpperCase()
                + Constants.QUERY.substring(1);
    }

    /**
     * Calls a new ObjectQuery instance based on specified objectType
     * (e.g. ActorsQuery, MoviesQuery, VideosQuery, UsersQuery)
     * @param objectType strings specifies query's object type
     * @param criteria string specifies query's type of criteria
     * @param filters list of filters
     * @return new instance of an ObjectQuery
     */
    public static ObjectQuery createQuery(final String objectType, final String criteria,
                                          final List<List<String>> filters) {
        try {
            //  Constructor's parameters types
            Class[] arguments = new Class[]{String.class, String.class, List.class};
            //  Use objectType String to determine class name
            //  Call constructor of the class with the given arguments
            //  Returns a new ObjectQuery object
            return (ObjectQuery)  Class.forName(getObjectQuery(objectType)).
                    getDeclaredConstructor(arguments).newInstance(objectType, criteria, filters);
            //  Catch exceptions
        } catch (ClassNotFoundException | InvocationTargetException
                | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
