package query;

import common.Database;
import entertainment.Show;

import java.util.ArrayList;
import java.util.List;

public final class ShowsQuery extends VideosQuery {
    public ShowsQuery(final String objectType, final String criteria,
                      final List<List<String>> filters) {
        super(objectType, criteria, filters);
    }

    /**
     * Executes query on shows
     * @return string list, contains queried shows titles
     */
    @Override
    public List<String> executeQuery() {
        //  Get all shows titles
        List<String> shows = new ArrayList<>();
        for (Show show: Database.getDatabase().getShows()) {
            shows.add(show.getTitle());
        }

        //  Get queried videos by specified criteria
        List<String> queriedVideos = super.executeQuery();

        //  Get queried shows
        //  Find common elements between queried videos and shows in database
        List<String> queriedShow = new ArrayList<>(queriedVideos);
        queriedShow.retainAll(shows);

        //  Return titles of queried shows
        return queriedShow;
    }

}
