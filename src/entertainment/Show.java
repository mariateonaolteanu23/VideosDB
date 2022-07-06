package entertainment;

import java.util.ArrayList;
import java.util.List;

public final class Show extends Video {
    /**
     * Number of seasons
     */
    private final int numberOfSeasons;

    /**
     * Season list
     */
    private final ArrayList<Season> seasons;


    public Show(final String title, final int year, final ArrayList<String> cast,
                final ArrayList<String> genres, final Double averageRating,
                final int numberOfSeasons, final ArrayList<Season> seasons) {

        super(title, year, cast, genres, averageRating);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    public int getNumberSeason() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * Adds rating to the ratings list of a season
     * @param grade given rating for specified season
     * @param seasonNumber the number of the season rated
     */
    public void rateVideo(final Double grade, final int seasonNumber) {
        Season season = getSeasons().get(seasonNumber - 1);
        season.getRatings().add(grade);
    }

    /**
     * Updates the average rating of the video
     */
    public void updateRating() {
        double average = 0;

        //  Get the rating per season
        for (Season season : seasons) {
            //  Season is at least rated once
            if (season.getRatings().size() != 0) {
                //  Get average rating per season
                average += season.getRatings().stream().
                        mapToDouble(rating -> rating).sum() / season.getRatings().size();
            }
        }

        //  No season of the show was rated before
        //  Show doesn't have average rating
        if (average == 0) {
            return;
        }

        //  Set nea average rating for show
        super.setAverageRating(average / numberOfSeasons);
    }

    /**
     * Validates if show passes the filters
     * @return true if show meets the conditions, false otherwise
     */
    @Override
    public boolean checkVideoFilters(final List<String> years, final List<String> genres) {
        return super.checkVideoFilters(years, genres);
    }

    /**
     * Gets duration of show
     * @return duration of show
     */
    @Override
    public int getDuration() {
        int duration = 0;
        //  Get duration of every season
        for (Season season : seasons) {
            //  sum
            duration += season.getDuration();
        }

        //  return show's duration
        return duration;
    }
}
