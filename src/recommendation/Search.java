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
import java.util.Comparator;
import java.util.List;

public final class Search extends Recommendation {

    public Search(final ActionInputData actionInputData) {
        super(actionInputData);
    }

    /** Gets list of video titles filtered by genre
     * If user has watched all videos or user subscription is standard => error message
     * @return recommendation result, list of titles or error message
     */
    @Override
    public String executeAction() {
        //  Recommendation result
        List<String> recommendation = new ArrayList<>();

        //  Get user by username
        User user = getUser(getActionInputData().getUsername());

        //  Check if user subscription is standard
        if (!Helpers.checkPremiumUser(user.getSubscriptionType())) {
            //  Can't apply search recommendation for standard user
            return Constants.SEARCH + Constants.NOT_APPLY;
        }

        //  Filter videos by given genre
        List<Video> videos = Filters.getFilteredVideos(
                Database.getDatabase().getAllVideos(),
                new ArrayList<>(Collections.singleton(null)),
                new ArrayList<>(Collections.singleton(getActionInputData().getGenre())));

        //  Sort filtered videos in alphabetical order
        videos.sort(Comparator.comparing(Video::getTitle));

        // Sort filtered videos by average rating
        videos.sort(Comparator.comparing(Video::getAverageRating));


        for (Video video : videos) {
            //  Video unwatched by user
            if (!user.getHistory().containsKey(video.getTitle())) {
                //  Add to search recommendation list
                recommendation.add(video.getTitle());
            }
        }

        //  There are filtered videos unwatched by user
        //  Get search recommendation
        if (recommendation.size() != 0) {
            return Constants.SEARCH + Constants.REC_RESULT + recommendation;
        }

        //  No unwatched videos by user
        //  No recommendation possible
        return Constants.SEARCH + Constants.NOT_APPLY;
    }

}
