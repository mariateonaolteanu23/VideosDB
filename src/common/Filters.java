package common;

import actor.Actor;
import entertainment.Video;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Filters {

    private Filters() { }
    /** Filters a list of videos by appearance year and genres
     * @param years list of years
     * @param genres list of genres
     * @return list of filtered videos
     */
    public static List<Video> getFilteredVideos(final List<Video> videos, final List<String> years,
                                                final List<String> genres) {
        Predicate<Video> videoPredicate = video -> video.checkVideoFilters(years, genres);
        return videos.stream().filter(videoPredicate).collect(Collectors.toList());
    }

    /** Filters a list of actors by keywords (in career description) and awards
     * @param actors list of actors to be filtered
     * @param words list of keywords
     * @param awards list of awards
     * @return list of filtered actors
     */
    public static List<Actor> getFilteredActors(final List<Actor> actors, final List<String> words,
                                                final List<String> awards) {
        Predicate<Actor> actorPredicate = actor -> actor.checkActorFilters(words, awards);
        return actors.stream().filter(actorPredicate).collect(Collectors.toList());
    }
}
