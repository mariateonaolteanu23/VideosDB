package recommendation;

import common.Constants;
import common.Database;
import common.Filters;
import common.Helpers;
import entertainment.Video;
import fileio.ActionInputData;
import user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class Popular extends Recommendation {
    public Popular(final ActionInputData actionInputData) {
        super(actionInputData);
    }

    /**
     * Gets the video with the most popular genre unwatched by PREMIUM user
     * If user watched all videos with the most popular genre, pick the next most popular
     * genre and so on
     * If user has watched all videos or user subscription is standard => error message
     * @return recommendation result, video title or error message
     */
    @Override
    public String executeAction() {
        //  Get user by username
        User user = getUser(getActionInputData().getUsername());

        //  Check if user subscription is standard
        if (!Helpers.checkPremiumUser(user.getSubscriptionType())) {
            //  Can't apply popular recommendation for standard user
            return Constants.POPULAR + Constants.NOT_APPLY;
        }

        //  Get list of genres sorted by popularity in descending order
        List<String> popularGenres = Helpers.getPopularGenres();

        //  Iterate through sorted list genres
        for (String genre : popularGenres) {
            //  Get all the videos with a certain genre
            List<Video> filteredVideos = Filters.getFilteredVideos(
                    Database.getDatabase().getAllVideos(),
                    new ArrayList<>(Collections.singleton(null)),
                    new ArrayList<>(Collections.singleton(genre))
            );

            for (Video video : filteredVideos) {
                //  Video is unwatched by user
                if (!user.getHistory().containsKey(video.getTitle())) {
                    //  Get popular recommendation
                    return Constants.POPULAR + Constants.REC_RESULT + video.getTitle();
                }
            }
        }

        //  There are no unwatched videos by user
        //  No recommendation possible
        return Constants.POPULAR + Constants.NOT_APPLY;
    }

}
