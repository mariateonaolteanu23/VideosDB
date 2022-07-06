package criteria;

import entertainment.Video;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class ByRatings extends VideosCriteria {
    public ByRatings(final String criteria, final List<List<String>> filters,
                     final List<Video> videos) {
        super(criteria, filters, videos);
    }

    /**
     * Sorts videos by average rating in ascending order
     * Video has to be rated at least once
     * @return sorted list of videos
     */
    @Override
    public List<Video> getVideosByCriteria() {

        //  Gets sorted videos by rating
        //  Excludes videos with no rating
        return getVideos().stream().
                filter(video -> video.getAverageRating() != 0).
                sorted(Comparator.comparing(Video::getAverageRating)).
                collect(Collectors.toList());
    }

}
