package criteria;

import actor.Actor;
import common.Helpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class ByAverage extends ActorsCriteria {

    public ByAverage(final String criteria, final List<List<String>> filters,
                     final List<Actor> actors) {
        super(criteria, filters, actors);
    }

    /**
     * Sorts a list of actors by average filmography rating in ascending order
     * @return a sorted list of actors
     */
    @Override
    public List<Actor> getActorsByCriteria() {
        //  Update average filmography rating for every actor
        Helpers.videoFilmographyRatingHandler();

        //  Sort actors by average filmography rating
        //  If actors have the same rating => sort alphabetically
        return getActors().stream().
                filter(actor -> actor.getFilmographyRating() != 0).
                sorted(Comparator.comparing(Actor::getName)).
                sorted(Comparator.comparing(Actor::getFilmographyRating)).
                collect(Collectors.toList());
    }

}
