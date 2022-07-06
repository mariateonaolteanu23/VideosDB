package query;

import common.Database;
import common.Filters;
import common.Helpers;
import criteria.VideosCriteria;
import entertainment.Video;
import factory.VideosCriteriaFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VideosQuery extends ObjectQuery {
    public VideosQuery(final String objectType, final String criteria,
                       final List<List<String>> filters) {
        super(objectType, criteria, filters);
    }

    /**
     * Executes query on videoss
     * @return string list, contains queried videos titles
     */
    @Override
    public List<String> executeQuery() {
        //  Get filtered videos
        List<Video> filteredVideos = Filters.getFilteredVideos(
                Database.getDatabase().getAllVideos(),
                getFilters().get(0),
                getFilters().get(1));

        //  Get filtered videos sorted in alphabetical order
        List<Video> sortedVideos = filteredVideos.stream().
                sorted(Comparator.comparing(Video::getTitle)).
                collect(Collectors.toList());

        //  Get specified video's criteria
        VideosCriteria videosCriteria = VideosCriteriaFactory.createVideosCriteria(
                getCriteria(), getFilters(), sortedVideos);
        assert videosCriteria != null;

        //  Get queried videos by specified criteria
        List<Video> queriedVideos = videosCriteria.getVideosByCriteria();

        //  Return titles of queried videos
        return Helpers.getOnlyTitles(queriedVideos);
    }
}
