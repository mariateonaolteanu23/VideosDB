package criteria;

import entertainment.Video;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public final class ByFavorite extends VideosCriteria {
    public ByFavorite(final String criteria, final List<List<String>> filters,
                      final List<Video> videos) {
        super(criteria, filters, videos);
    }

    /**
     * Sorts videos by number of appearances in users favorite lists (in ascending order)
     * Sorts only videos that were marked as favorite at least once
     * @return sorted list of videos
     */
    @Override
    public List<Video> getVideosByCriteria() {
        //  Gets videos sorted by the number of times each video was added to a favorite list
        //  Exclude videos with no favorite list appearance
        return getVideos().stream().
                filter(video -> video.getFavoriteChoice() != 0).
                sorted(Comparator.comparing(Video::getTitle)).
                sorted(Comparator.comparing(Video::getFavoriteChoice)).
                collect(Collectors.toList());
    }

}
