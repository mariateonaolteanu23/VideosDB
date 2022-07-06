package criteria;

import actor.Actor;
import common.Constants;
import common.Filters;

import java.util.Comparator;
import java.util.List;

public final class ByAwards extends ActorsCriteria {
    public ByAwards(final String criteria, final List<List<String>> filters,
                    final List<Actor> actors) {
        super(criteria, filters, actors);
    }

    /**
     * Filters actors by given genres
     * Sorts actors by number of awards in ascending order
     * @return a filtered and sorted list of actors
     */
    @Override
    public List<Actor> getActorsByCriteria() {
        //  Filter actors by genres
        List<Actor> filteredActors = Filters.getFilteredActors(getActors(),
                null, getFilters().get(Integer.parseInt(Constants.GENRE_FILTER)));

        //  Sort filtered actors by number of awards won
        filteredActors.sort(Comparator.comparing(Actor::getNumberOfWins));

        //  Get actors list
        return filteredActors;
    }

}
