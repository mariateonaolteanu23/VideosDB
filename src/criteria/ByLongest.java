package criteria;

import entertainment.Video;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class ByLongest extends VideosCriteria {
    public ByLongest(final String criteria, final List<List<String>> filters,
                     final List<Video> videos) {
        super(criteria, filters, videos);
    }

    /**
     * Sorts videos by duration in ascending order
     * @return sorted list of videos
     */
    @Override
    public List<Video> getVideosByCriteria() {

        //  Gets videos sorted by duration
        return getVideos().stream().
                sorted(Comparator.comparing(Video::getDuration)).
                collect(Collectors.toList());
    }

}
