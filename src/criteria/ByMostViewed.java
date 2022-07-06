package criteria;

import entertainment.Video;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class ByMostViewed extends VideosCriteria {
    public ByMostViewed(final String criteria, final List<List<String>> filters,
                        final List<Video> videos) {
        super(criteria, filters, videos);
    }

    /**
     * Sorts videos by number of views in ascending order
     * Video has to have at least one view
     * @return sorted list of videos
     */
    @Override
    public List<Video> getVideosByCriteria() {

        //  Gets sorted videos by number of views
        //  Excludes unwatched videos
        return getVideos().stream().filter(video -> video.getNumberOfViews() != 0).
                sorted(Comparator.comparing(Video::getTitle)).
                sorted(Comparator.comparing(Video::getNumberOfViews)).
                collect(Collectors.toList());
    }

}
