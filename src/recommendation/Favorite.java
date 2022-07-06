package recommendation;

import common.Constants;
import common.Database;
import common.Helpers;
import entertainment.Video;
import fileio.ActionInputData;
import user.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public final class Favorite extends Recommendation {
    public Favorite(final ActionInputData actionInputData) {
        super(actionInputData);
    }

    /**
     * Gets the video with the most appearances in all favorite lists unwatched by PREMIUM user
     * If user has watched all videos or user subscription is standard => error message
     * @return recommendation result, video title or error message
     */
    public String executeAction() {
        //  Get user by username
        User user = getUser(getActionInputData().getUsername());

        //  Check if user subscription is standard
        if (!Helpers.checkPremiumUser(user.getSubscriptionType())) {
            //  Can't apply favorite recommendation for standard user
            return getClass().getSimpleName() + Constants.NOT_APPLY;
        }

        //  Get all videos in database
        List<Video> videos = Database.getDatabase().getAllVideos();

        //  Reverse list to maintain the database order the second sorting criteria
        Collections.reverse(videos);

        //  Sort videos by number of favorite picks
        //  Exclude videos with no favorite list appearances
        List<Video> favoriteVideos = videos.stream().
                filter(video -> video.getFavoriteChoice() != 0).
                sorted(Comparator.comparing(Video::getFavoriteChoice)).
                collect(Collectors.toList());
        //  Sort in descending order
        Collections.reverse(favoriteVideos);

        for (Video favVideo : favoriteVideos) {
            //  Video is unwatched by user
            if (!user.getHistory().containsKey(favVideo.getTitle())) {
                //  Get favorite recommendation
                return getClass().getSimpleName() + Constants.REC_RESULT + favVideo.getTitle();
            }
        }

        //  No unwatched videos by user
        //  No recommendation possible
        return getClass().getSimpleName() + Constants.NOT_APPLY;
    }
}
