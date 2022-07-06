package criteria;

import entertainment.Video;

import java.util.List;

public abstract class VideosCriteria {
    private final List<List<String>> filters;
    private final List<Video> videos;

    public VideosCriteria(final String criteria, final List<List<String>> filters,
                          final List<Video> videos) {
        this.filters = filters;
        this.videos = videos;
    }

    /**
     * Gets the specified filters
     * @return filters, list of given filters
     */
    public List<List<String>> getFilters() {
        return filters;
    }

    /**
     * Gets list of videos
     * @return videos, list of videos
     */
    public List<Video> getVideos() {
        return videos;
    }

    /**
     * Queries a list of videos
     * @return list of queried videos
     */
    public abstract List<Video> getVideosByCriteria();
}
