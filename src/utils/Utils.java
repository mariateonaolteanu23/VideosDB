package utils;

import actor.Actor;
import actor.ActorsAwards;
import command.Command;
import command.Rating;
import command.View;
import common.Constants;

import criteria.ActorsCriteria;
import criteria.VideosCriteria;
import criteria.UsersCriteria;
import criteria.ByNumRatings;
import criteria.ByFilterDescription;
import criteria.ByAverage;
import criteria.ByAwards;
import criteria.ByFavorite;
import criteria.ByRatings;
import criteria.ByMostViewed;
import criteria.ByLongest;
import entertainment.Video;
import factory.CommandFactory;
import factory.QueryFactory;
import factory.RecommendationFactory;
import fileio.ActionInputData;

import query.MoviesQuery;
import query.ObjectQuery;
import query.ShowsQuery;
import query.UsersQuery;
import query.ActorsQuery;

import recommendation.Search;
import recommendation.Standard;
import recommendation.BestUnseen;
import recommendation.Recommendation;
import recommendation.Favorite;
import recommendation.Popular;
import entertainment.Genre;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import user.User;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * The class contains static methods that helps with parsing.
 *
 * We suggest you add your static methods here or in a similar class.
 */
public final class Utils {
    /**
     * for coding style
     */
    private Utils() {
    }

    /**
     * Transforms a string into an enum
     * @param genre of video
     * @return an Genre Enum
     */
    public static Genre stringToGenre(final String genre) {
        return switch (genre.toLowerCase()) {
            case "action" -> Genre.ACTION;
            case "adventure" -> Genre.ADVENTURE;
            case "drama" -> Genre.DRAMA;
            case "comedy" -> Genre.COMEDY;
            case "crime" -> Genre.CRIME;
            case "romance" -> Genre.ROMANCE;
            case "war" -> Genre.WAR;
            case "history" -> Genre.HISTORY;
            case "thriller" -> Genre.THRILLER;
            case "mystery" -> Genre.MYSTERY;
            case "family" -> Genre.FAMILY;
            case "horror" -> Genre.HORROR;
            case "fantasy" -> Genre.FANTASY;
            case "science fiction" -> Genre.SCIENCE_FICTION;
            case "action & adventure" -> Genre.ACTION_ADVENTURE;
            case "sci-fi & fantasy" -> Genre.SCI_FI_FANTASY;
            case "animation" -> Genre.ANIMATION;
            case "kids" -> Genre.KIDS;
            case "western" -> Genre.WESTERN;
            case "tv movie" -> Genre.TV_MOVIE;
            default -> null;
        };
    }

    /**
     * Transforms a string into an enum
     * @param award for actors
     * @return an ActorsAwards Enum
     */
    public static ActorsAwards stringToAwards(final String award) {
        return switch (award) {
            case "BEST_SCREENPLAY" -> ActorsAwards.BEST_SCREENPLAY;
            case "BEST_SUPPORTING_ACTOR" -> ActorsAwards.BEST_SUPPORTING_ACTOR;
            case "BEST_DIRECTOR" -> ActorsAwards.BEST_DIRECTOR;
            case "BEST_PERFORMANCE" -> ActorsAwards.BEST_PERFORMANCE;
            case "PEOPLE_CHOICE_AWARD" -> ActorsAwards.PEOPLE_CHOICE_AWARD;
            default -> null;
        };
    }

    /**
     * Transforms an array of JSON's into an array of strings
     * @param array of JSONs
     * @return a list of strings
     */
    public static ArrayList<String> convertJSONArray(final JSONArray array) {
        if (array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * Transforms an array of JSON's into a map
     * @param jsonActors array of JSONs
     * @return a map with ActorsAwardsa as key and Integer as value
     */
    public static Map<ActorsAwards, Integer> convertAwards(final JSONArray jsonActors) {
        Map<ActorsAwards, Integer> awards = new LinkedHashMap<>();

        for (Object iterator : jsonActors) {
            awards.put(stringToAwards((String) ((JSONObject) iterator).get(Constants.AWARD_TYPE)),
                    Integer.parseInt(((JSONObject) iterator).get(Constants.NUMBER_OF_AWARDS)
                            .toString()));
        }

        return awards;
    }

    /**
     * Transforms an array of JSON's into a map
     * @param movies array of JSONs
     * @return a map with String as key and Integer as value
     */
    public static Map<String, Integer> watchedMovie(final JSONArray movies) {
        Map<String, Integer> mapVideos = new LinkedHashMap<>();

        if (movies != null) {
            for (Object movie : movies) {
                mapVideos.put((String) ((JSONObject) movie).get(Constants.NAME),
                        Integer.parseInt(((JSONObject) movie).get(Constants.NUMBER_VIEWS)
                                .toString()));
            }
        } else {
            System.out.println("NU ESTE VIZIONAT NICIUN FILM");
        }

        return mapVideos;
    }

/**
 * NOTE: ADAPTATION FOR DESIGNATED CHECKER TOOL
 * The following methods are never used in the actual implementation!
 * Vmchecker doesn't load classes the same way as the local machine
 * More details in README file
 */

    /**
     * Instantiates all factories
     */
    public static void loadActions() {
        QueryFactory queryFactory = new QueryFactory();
        CommandFactory commandFactory =  new CommandFactory();
        RecommendationFactory recommendationFactory = new RecommendationFactory();
    }

    /**
     * Instantiates all Recommendation subclasses
     * @param actionInputData constructor parameter
     */
    public static void loadRecommendations(final ActionInputData actionInputData) {
        Recommendation bestUnseen = new BestUnseen(actionInputData);
        Recommendation favorite = new Favorite(actionInputData);
        Recommendation popular = new Popular(actionInputData);
        Recommendation search = new Search(actionInputData);
        Recommendation standard = new Standard(actionInputData);
    }

    /**
     * Instantiates all Command subclasses
     * @param actionInputData info about specified action
     */
    public static void loadCommands(final ActionInputData actionInputData) {
        Command favorite = new command.Favorite(actionInputData);
        Command rating = new Rating(actionInputData);
        Command view = new View(actionInputData);
    }

    /**
     * Instantiates all ObjectQuery subclasses
     * @param actionInputData info about specified action
     */
    public static void loadQueries(final ActionInputData actionInputData) {
        ObjectQuery showsQuery = new ShowsQuery(actionInputData.getObjectType(),
                actionInputData.getCriteria(),
                actionInputData.getFilters());
        ObjectQuery moviesQuery = new MoviesQuery(actionInputData.getObjectType(),
                actionInputData.getCriteria(),
                actionInputData.getFilters());
        ObjectQuery usersQuery = new UsersQuery(actionInputData.getObjectType(),
                actionInputData.getCriteria(),
                actionInputData.getFilters());
        ObjectQuery actorsQuery = new ActorsQuery(actionInputData.getObjectType(),
                actionInputData.getCriteria(),
                actionInputData.getFilters());
    }

    /**
     * Instantiates all VideosCriteria subclasses
     * @param criteria specified query's criteria
     * @param filters list of filters
     * @param videos list of videos
     */
    public static void loadVideosCriteria(final String criteria,
                                          final List<List<String>> filters,
                                          final List<Video> videos) {
        VideosCriteria byLongest = new ByLongest(criteria, filters, videos);
        VideosCriteria byMostViewed = new ByMostViewed(criteria, filters, videos);
        VideosCriteria byRatings = new ByRatings(criteria, filters, videos);
        VideosCriteria byFavorite = new ByFavorite(criteria, filters, videos);
    }

    /**
     * Instantiates all ActorsCriteria subclasses
     * @param criteria specified query's criteria
     * @param filters list of filters
     * @param actors list of actors
     */
    public static void loadActorsCriteria(final String criteria,
                                          final List<List<String>> filters,
                                          final List<Actor> actors) {
        ActorsCriteria byAwards = new ByAwards(criteria, filters, actors);
        ActorsCriteria byAverage = new ByAverage(criteria, filters, actors);
        ActorsCriteria byFilterDescription = new ByFilterDescription(criteria,
                filters, actors);
    }

    /**
     * Instantiates all UsersCriteria subclasses
     * @param criteria specified query's criteria
     * @param filters list of filters
     * @param users list of users
     */
    public static void loadUsersCriteria(final String criteria,
                                         final List<List<String>> filters,
                                         final List<User> users) {
        UsersCriteria byNumRatings = new ByNumRatings(criteria, filters, users);
    }
}
