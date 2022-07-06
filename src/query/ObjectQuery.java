package query;

import java.util.List;

public abstract class ObjectQuery {
    private final String objectType;
    private final String criteria;
    private final List<List<String>> filters;

    public ObjectQuery(final String objectType, final String criteria,
                       final List<List<String>> filters) {
        this.objectType = objectType;
        this.criteria = criteria;
        this.filters = filters;
    }

    /**
     * Gets specified query type of object
     * @return given query object type
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * Gets specified query criteria
     * @return given query criteria
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * Gets the specified filters
     * @return filters, list of given filters
     */
    public List<List<String>> getFilters() {
        return filters;
    }

    /**
     * Queries a list of object type
     * @return queried names/titles of object type lists
     */
    public abstract List<String> executeQuery();
}
