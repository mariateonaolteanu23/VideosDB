package command;

import common.Constants;
import entertainment.Video;
import fileio.ActionInputData;
import user.User;

public final class View extends Command {

    public View(final ActionInputData actionInputData) {
        super(actionInputData);
    }

    @Override
    public String executeAction() {
        //  Get video title being watched
        String title = getActionInputData().getTitle();

        //  Get user by username
        User user = getUser(getActionInputData().getUsername());

        //  Update the number of views
        //  For user
        int views = user.getHistory().getOrDefault(title, 0);
        user.getHistory().put(title, views + 1);

        //  For video
        Video watchedVideo = getVideo(title);
        watchedVideo.setNumberOfViews(watchedVideo.getNumberOfViews() + 1);

        //  Video was viewed successfully
        return Constants.SUCCESS + title + Constants.VIEWED + (views + 1);
    }
}
