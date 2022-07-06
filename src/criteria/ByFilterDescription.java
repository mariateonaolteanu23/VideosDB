package criteria;

import actor.Actor;
import common.Filters;

import java.util.List;

public final class ByFilterDescription extends ActorsCriteria {
    public ByFilterDescription(final String criteria, final List<List<String>> filters,
                               final List<Actor> actors) {
        super(criteria, filters, actors);
    }

    /**
     * Filters actors by keywords
     * Actor's career description has to contain all the keywords
     * @return filtered list of actors
     */
    @Override
    public List<Actor> getActorsByCriteria() {
        //  Get keywords
        List<String> wordsFilter = getFilters().get(2);

        //  Filer actors by keywords
        return Filters.getFilteredActors(getActors(), wordsFilter, null);
    }

}
