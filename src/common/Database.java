package common;

import actor.Actor;
import entertainment.Movie;
import entertainment.Show;
import entertainment.Video;
import user.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Database {
    private static  Database database = null;
    private List<Movie> movies;
    private List<Show> shows;
    private List<User> users;
    private List<Actor> actors;

    private Database() { }

    /** Creates database instance
     * @return database
     */
    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public void setMovies(final List<Movie> movies) {
        this.movies = movies;
    }

    public void setShows(final List<Show> shows) {
        this.shows = shows;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

    public void setActors(final List<Actor> actors) {
        this.actors = actors;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Show> getShows() {
        return shows;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Actor> getActors() {
        return actors;
    }

    /** Merges the movies and shows lists
     * @return list of all the videos in database
     */
    public List<Video> getAllVideos() {
        return Stream.of(getMovies(), getShows()).flatMap(Collection::stream).
                collect(Collectors.toList());
    }

}
