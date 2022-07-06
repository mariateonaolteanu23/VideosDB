package recommendation;

import common.Constants;
import common.Database;
import entertainment.Video;
import fileio.ActionInputData;
import user.User;

public final class Standard extends Recommendation {
    public Standard(final ActionInputData actionInputData) {
        super(actionInputData);
    }

    /**
     * Gets the first unwatched video by user from the database
     * If user has watched all videos => error message
     * @return recommendation result, video title or error message
     */
    public String executeAction() {
        //  Get user by username
        User user = getUser(getActionInputData().getUsername());

        //  Get first unwatched video from database
        for (Video video : Database.getDatabase().getAllVideos()) {
            //  Video hasn't been watched by user
            if (!isSeen(user, video.getTitle())) {
                return Constants.STANDARD + Constants.REC_RESULT + video.getTitle();
            }
        }

        //  No unwatched videos by user
        //  No recommendation possible
        return Constants.STANDARD + Constants.NOT_APPLY;
    }
}
