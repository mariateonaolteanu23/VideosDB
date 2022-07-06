package query;

import common.Database;
import entertainment.Movie;

import java.util.ArrayList;
import java.util.List;

public final class MoviesQuery extends VideosQuery {
    public MoviesQuery(final String objectType, final String criteria,
                       final List<List<String>> filters) {
        super(objectType, criteria, filters);
    }

    /**
     * Executes query on movies
     * @return string list, contains queried movies titles
     */
    @Override
    public List<String> executeQuery() {
        //  Get all movies titles
        List<String> movies = new ArrayList<>();
        for (Movie movie: Database.getDatabase().getMovies()) {
            movies.add(movie.getTitle());
        }
        //  Get queried videos by specified criteria
        List<String> queriedVideos = super.executeQuery();

        //  Get queried movies
        //  Find common elements between queried videos and movies in database
        List<String> queriedMovies = new ArrayList<>(queriedVideos);
        queriedMovies.retainAll(movies);

        //  Return titles of queried movies
        return queriedMovies;
    }

}
