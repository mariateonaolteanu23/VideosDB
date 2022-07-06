package factory;

import common.Constants;
import common.Helpers;
import criteria.VideosCriteria;
import entertainment.Video;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class VideosCriteriaFactory {
    private VideosCriteriaFactory() { }

    //  Gets class package and class name (e.g. criteria.ByLongest)
    private static String getVideosCriteria(final String criteria) {
        String parsedCriteria = Helpers.eraseUnderLine(criteria);
        return Constants.CRITERIA_PACKAGE
                + Constants.BY
                + parsedCriteria.substring(0, 1).toUpperCase()
                + parsedCriteria.substring(1);
    }

    /**
     * Calls a new VideosCriteria instance based on specified criteria
     * (ByMostViewed, ByFavorite, ByLongest, ByRatings)
     * @param criteria string specifies query's type of criteria
     * @param filters list of filters
     * @param videos the list of users to query
     * @return new instance of an VideosCriteria class
     */
    public static VideosCriteria createVideosCriteria(final String criteria,
                                                      final List<List<String>> filters,
                                                      final List<Video> videos) {
        try {
            //  Constructor's parameters types
            Class[] arguments = new Class[]{String.class, List.class, List.class};
            //  Use criteria String to determine class name
            //  Call constructor of the class with the given arguments
            //  Returns a new VideosCriteria object
            return (VideosCriteria)  Class.forName(getVideosCriteria(criteria)).
                    getDeclaredConstructor(arguments).newInstance(criteria, filters, videos);
            //  Catch exceptions
        } catch (ClassNotFoundException | InvocationTargetException
                | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
