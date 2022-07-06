package common;

import actor.Actor;
import entertainment.Movie;
import entertainment.Show;
import entertainment.Video;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import user.User;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.stream.Collectors;

public final  class Helpers {
    private Helpers() { }

    /**
     * Loads the movies input list into database movies list
     * @param movieInputDataList input list of movies
     */
    public static void addMovies(final List<MovieInputData> movieInputDataList) {
        List<Movie> movies = new ArrayList<>();

        movieInputDataList.forEach(movieInputData ->
                movies.add(new Movie(movieInputData.getTitle(),
                        movieInputData.getYear(),
                        movieInputData.getCast(),
                        movieInputData.getGenres(),
                        (double) 0,
                        movieInputData.getDuration())
                ));

        Database.getDatabase().setMovies(movies);
    }

    /**
     * Loads the shows input list into database shows list
     * @param serialInputDataList input list of shows
     */
    public static void addShows(final List<SerialInputData> serialInputDataList) {
        List<Show> shows = new ArrayList<>();

        serialInputDataList.forEach(serialInputData ->
                shows.add(new Show(serialInputData.getTitle(),
                        serialInputData.getYear(),
                        serialInputData.getCast(),
                        serialInputData.getGenres(),
                        (double) 0,
                        serialInputData.getNumberSeason(),
                        serialInputData.getSeasons())
                ));

        Database.getDatabase().setShows(shows);
    }

    /**
     * Loads the users input list into database users list
     * @param userInputDataList input list of users
     */
    public static void addUsers(final List<UserInputData> userInputDataList) {
        List<User> users = new ArrayList<>();

        userInputDataList.forEach(userInputData ->
                users.add(new User(userInputData.getUsername(),
                        userInputData.getSubscriptionType(),
                        userInputData.getHistory(),
                        userInputData.getFavoriteMovies())));

        Database.getDatabase().setUsers(users);
    }

    /**
     * Loads the actors input list into database actors list
     * @param actorInputDataList input list of actors
     */
    public static void addActors(final List<ActorInputData> actorInputDataList) {
        List<Actor> actors = new ArrayList<>();

        actorInputDataList.forEach(actorInputData ->
                actors.add(new Actor(actorInputData.getName(),
                        actorInputData.getCareerDescription(),
                        actorInputData.getFilmography(),
                        actorInputData.getAwards(),
                        (double) 0)
                ));

        Database.getDatabase().setActors(actors);
    }

    /**
     * Gets titles list of given videos list
     * @param videos list of videos
     * @return list of videos titles
     */
    public static List<String> getOnlyTitles(final List<Video> videos) {
        List<String> videosToString = new ArrayList<>();
        for (Video video : videos) {
            videosToString.add(video.getTitle());
        }

        return  videosToString;
    }

    /**
     * Gets usernames list of given users list
     * @param users list of users
     * @return list of usernames
     */
    public static List<String> getOnlyUsernames(final List<User> users) {
        List<String> usersToString = new ArrayList<>();
        for (User user : users) {
            usersToString.add(user.getUsername());
        }

        return  usersToString;
    }

    /**
     * Gets names list of given actors list
     * @param actors list of actors
     * @return list of names
     */
    public static List<String> getOnlyNames(final List<Actor> actors) {
        List<String> actorsToString = new ArrayList<>();
        for (Actor actor : actors) {
            actorsToString.add(actor.getName());
        }

        return  actorsToString;
    }

    /**
     * Erases the "_" character in a string and concatenates the two words
     * Second word first letter is converted to uppercase
     * @param string given string
     * @return parsed string
     */
    public static String eraseUnderLine(final String string) {
        int index = string.indexOf("_");

        if (index != -1) {
            return string.substring(0, index)
                    + string.substring(index + 1, index + 2).toUpperCase()
                    + string.substring(index + 2);
        }
        return string;
    }

    /**
     * Updates the number of views for all the given videos
     * @param videos list of videos
     */
    public static void videoViewsHandler(final List<Video> videos) {
        for (Video video : videos) {
            for (User user : Database.getDatabase().getUsers()) {
                int viewCounter = user.getHistory().getOrDefault(video.getTitle(), 0);
                video.setNumberOfViews(video.getNumberOfViews() + viewCounter);
            }
        }
    }

    /**
     * Updates the number of times every given video was added to a favorite list
     * @param videos list of videos
     */
    public static void videoFavoriteHandler(final List<Video> videos) {
        for (Video video : videos) {
            for (User user : Database.getDatabase().getUsers()) {
                if (user.getFavoriteMovies().contains(video.getTitle())) {
                    video.setFavoriteChoice(video.getFavoriteChoice() + 1);
                }
            }
        }
    }

    /**
     * Updates the number of wins for every actor given
     * @param actors list of actors
     */
    public static void actorWinsHandler(final List<Actor> actors) {
        for (Actor actor : actors) {
            actor.updateNumberOfWins();
        }
    }

    /**
     * Gets average rating of a video by title
     * @param title title of the searched video
     * @return average rating of searched video
     */
    public static Double getVideoRating(final String title) {
        //  Get all videos
        List<Video> videos = Database.getDatabase().getAllVideos();
        for (Video video : videos) {
            //  Found video
            if (video.getTitle().equals(title)) {
                //  Get video average rating
                return video.getAverageRating();
            }
        }

        //  No video find with the given title
        return (double) 0;
    }

    /**
     * Updates the filmography rating of all the actors in the database
     */
    public static void videoFilmographyRatingHandler() {
        //  Get all actors
        List<Actor> actors = Database.getDatabase().getActors();

        for (Actor actor : actors) {
            double average = 0;
            int num = 0;

            //  For every video in actor's filmography
            for (String title : actor.getFilmography()) {
                //  Get the average rating of video
                Double videoRating = getVideoRating(title);

                //  Video has rating
                if (videoRating != 0) {
                    average += videoRating;
                    num++;
                }
            }

            //  There are rated videos in actor's filmography
            if (num != 0) {
                //  Set actor's filmography rating
                actor.setFilmographyRating(average / num);
            }
        }
    }

    /**
     * Checks if string equals to "PREMIUM"
     * @param subscription given string
     * @return true if subscription is "PREMIUM", false otherwise
     */
    public static boolean checkPremiumUser(final String subscription) {
        return subscription.equals(Constants.PREMIUM);
    }

    /**
     * Gets all genres present in database sorted by popularity in descending order
     * @return list of sorted genres
     */
    public static List<String> getPopularGenres() {
        //  Map genre name and the number of its occurrences
        Map<String, Integer> genres = new LinkedHashMap<>();

        //  Get all videos
        for (Video video : Database.getDatabase().getAllVideos()) {
            for (String genre : video.getGenres()) {
                //  Add genre to map or update genre occurrence
                int popularCounter = genres.getOrDefault(genre, 0);
                genres.put(genre, popularCounter + 1);
            }
        }

        //  Sort map and convert to sorted list
        List<String> sortedGenres = genres.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).
                collect(Collectors.toList());
        //  Sort in descending order
        Collections.reverse(sortedGenres);
        return sortedGenres;
    }

}
