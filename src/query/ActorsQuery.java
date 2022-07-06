package query;

import actor.Actor;
import common.Database;
import common.Helpers;
import criteria.ActorsCriteria;
import factory.ActorsCriteriaFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class ActorsQuery extends ObjectQuery {

    public ActorsQuery(final String objectType, final String criteria,
                       final List<List<String>> filters) {
        super(objectType, criteria, filters);
    }

    /**
     * Executes query on actors
     * @return string list, contains queried actors names
     */
    @Override
    public List<String> executeQuery() {
        //  Sort actors in alphabetical order
        List<Actor> sortedActors = Database.getDatabase().getActors().stream().
                sorted(Comparator.comparing(Actor::getName)).collect(Collectors.toList());

        //  Get specified actor's criteria
        ActorsCriteria actorsCriteria = ActorsCriteriaFactory.
                createActorsCriteria(getCriteria(), getFilters(), sortedActors);
        assert actorsCriteria != null;

        //  Get queried actors by specified criteria
        List<Actor> queriedActors = actorsCriteria.getActorsByCriteria();

        //  Return only the names of the actors
        return Helpers.getOnlyNames(queriedActors);
    }

}
