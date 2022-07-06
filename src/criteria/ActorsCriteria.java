package criteria;

import actor.Actor;

import java.util.List;

public abstract class ActorsCriteria {
    private final String criteria;
    private final List<List<String>> filters;
    private final List<Actor> actors;

    public ActorsCriteria(final String criteria, final List<List<String>> filters,
                          final List<Actor> actors) {
        this.criteria = criteria;
        this.filters = filters;
        this.actors = actors;
    }

    /**
     * Gets the specified filters
     * @return filters, list of given filters
     */
    public List<List<String>> getFilters() {
        return filters;
    }

    /**
     * Gets list of actors
     * @return actors, list of actors
     */
    public List<Actor> getActors() {
        return actors;
    }

    /**
     * Queries a list of actors
     * @return list of queried actors
     */
    public abstract List<Actor> getActorsByCriteria();
}
