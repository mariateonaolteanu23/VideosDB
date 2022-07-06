package factory;

import common.Constants;
import common.Helpers;
import fileio.ActionInputData;
import recommendation.Recommendation;

import java.lang.reflect.InvocationTargetException;

public final class RecommendationFactory {
    //  Gets class package and class name (e.g. recommendation.Standard)
    private static String getRecommendation(final String actionType) {
        String type = Helpers.eraseUnderLine(actionType);
        return Constants.REC_PACKAGE
                + type.substring(0, 1).toUpperCase()
                + type.substring(1);
    }

    /**
     * Calls a new Recommendation instance based on specified action type
     * (e.g. BestUnseen, Standard, Search, Popular, Favorite)
     * @param actionInputData constructor parameter and info about new action
     * @return new instance of Recommendation class
     */
    public static Recommendation createAction(final ActionInputData actionInputData) {
        try {
            //  Constructor's parameter type
            Class[] arguments = new Class[]{ActionInputData.class};
            //  Use actionInputData.getType() String to determine class name
            //  Call constructor of the class with the given argument
            //  Returns a new Recommendation object
            return (Recommendation) Class.forName(getRecommendation(actionInputData.getType())).
                    getDeclaredConstructor(arguments).newInstance(actionInputData);
            //  Catch exceptions
        } catch (ClassNotFoundException | InvocationTargetException
                | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
