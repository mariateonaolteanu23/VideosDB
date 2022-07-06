package recommendation;

import common.Constants;
import common.Database;
import entertainment.Video;
import fileio.ActionInputData;
import user.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class BestUnseen extends Recommendation {

    public BestUnseen(final ActionInputData actionInputData) {
        super(actionInputData);
    }

    /**
     * Gets the best rated video unwatched by specified user
     * If user has watched all videos, recommendation can't be applied => error message
     * @return recommendation result, title of the best unseen video or error message
     */
    @Override
    public String executeAction() {
        //  Get user by username
        User user = getUser(getActionInputData().getUsername());

        //  Get all videos
        List<Video> videos = Database.getDatabase().getAllVideos();

        //  Get only rated videos and sort them by average rating
        List<Video> filteredVideos = videos.stream().filter(video -> video.getAverageRating() != 0).
                sorted(Comparator.comparing(Video::getAverageRating)).collect(Collectors.toList());

        //  Sort in descending order
        Collections.reverse(filteredVideos);

        for (Video video : filteredVideos) {
            //  Video hasn't been watched by user
            if (!isSeen(user, video.getTitle())) {
                //  Get best unseen recommendation
                return Constants.BEST_UNSEEN + Constants.REC_RESULT + video.getTitle();
            }
        }

        //  User has watched all the filteredVideos videos
        //  Get the first unseen video from the database
        for (Video video : videos) {
            //  Video hasn't been watched by user
            if (!isSeen(user, video.getTitle())) {
                //  Get recommendation
                return Constants.BEST_UNSEEN  + Constants.REC_RESULT + video.getTitle();
            }
        }

        //  No unwatched videos by user
        //  No recommendation possible
        return Constants.BEST_UNSEEN + Constants.NOT_APPLY;
    }
}
