package entertainment;

import java.util.ArrayList;
import java.util.List;

public final class Movie extends Video {
    /**
     * Duration in minutes of a movie
     */
    private final int duration;

    /**
     * Ratings list of a movie
     */
    private List<Double> ratings = new ArrayList<>();

    public Movie(final String title, final int year, final ArrayList<String> cast,
                 final ArrayList<String> genres, final Double averageRating, final int duration) {
        super(title, year, cast, genres, averageRating);
        this.duration = duration;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(final List<Double> ratings) {
        this.ratings = ratings;
    }

    /**
     * Gets duration of movie
     * @return duration of movie
     */
    @Override
    public int getDuration() {
        return duration;
    }


    /**
     * Adds rating to the ratings list of movie
     * @param grade given rating the movie
     * @param seasonNumber equals zero (movies don't have seasons)
     */
    @Override
    public void rateVideo(final Double grade, final int seasonNumber) {
        getRatings().add(grade);
    }

    /**
     * Updates the average rating of the movie
     */
    @Override
    public void updateRating() {
        if (ratings.size() != 0) {
            setAverageRating(ratings.stream().mapToDouble(rating -> rating).sum() / ratings.size());
        }
    }

    /**
     * Validates if movie passes the filters
     * @return true if movie meets the conditions, false otherwise
     */
    public boolean checkVideoFilters(final List<String> years, final List<String> genres) {
        return super.checkVideoFilters(years, genres);
    }
}
