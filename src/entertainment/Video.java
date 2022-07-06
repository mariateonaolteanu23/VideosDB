package entertainment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Video {
    /**
     * Video's title
     */
    private final String title;
    /**
     * The year the video was released
     */
    private final int year;
    /**
     * Video casting
     */
    private final ArrayList<String> cast;
    /**
     * Video genres
     */
    private final ArrayList<String> genres;
    /**
     * Video average rating
     */
    private Double averageRating;

    /**
     * Video number of views
     */
    private int numberOfViews;

    /**
     * Number of times video was added to a favorite list
     */
    private int favoriteChoice;

    public Video(final String title, final int year,
                     final ArrayList<String> cast, final ArrayList<String> genres,
                 final Double averageRating) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.averageRating = averageRating;
    }

    public final String getTitle() {
        return title;
    }

    public final int getYear() {
        return year;
    }

    public final ArrayList<String> getCast() {
        return cast;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }

    public final Double getAverageRating() {
        return averageRating;
    }

    public final void setAverageRating(final Double averageRating) {
        this.averageRating = averageRating;
    }

    public final int getNumberOfViews() {
        return numberOfViews;
    }

    public final int getFavoriteChoice() {
        return favoriteChoice;
    }

    public final void setNumberOfViews(final int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public final void setFavoriteChoice(final int favoriteChoice) {
        this.favoriteChoice = favoriteChoice;
    }

    /**
     * Adds rating to the ratings list of a video or video's season(if it has one)
     * @param grade given rating for the video
     * @param seasonNumber the number of the season rated if it has one
     */
    public void rateVideo(final Double grade, final int seasonNumber) { }

    /**
     * Updates the average rating of a video
     */
    public void updateRating() { }

    /**
     * Validates if video passes the filters
     * @return true if video meets the conditions, false otherwise
     */
    public boolean checkVideoFilters(final List<String> years, final List<String> genres) {

        if (years.get(0) == null && genres.get(0) == null) {
            return true;
        }

        if (genres.get(0) == null) {
            return years.contains(String.valueOf(this.year));
        }

        if (years.get(0) == null) {
            return !Collections.disjoint(this.genres, genres);
        }

        return years.contains(String.valueOf(this.year))
                && !Collections.disjoint(this.genres, genres);
    }

    /**
     * Gets duration of video
     * @return duration of video
     */
    public int getDuration() {
        return 0;
    }
}
