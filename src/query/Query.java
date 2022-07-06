package query;

import action.Action;
import common.Constants;
import factory.ObjectQueryFactory;
import fileio.ActionInputData;

import java.util.Collections;
import java.util.List;

public final class Query extends Action {

    public Query(final ActionInputData actionInputData) {
        super(actionInputData);
    }

    /**
     * Executes query specified by actionInputData
     * @return query result
     */
    public String executeAction() {
        //  Get specified query's object type
        ObjectQuery objectQuery = ObjectQueryFactory.createQuery(
                getActionInputData().getObjectType(),
                getActionInputData().getCriteria(),
                getActionInputData().getFilters());

        assert objectQuery != null;
        //  Execute query on given object type
        List<String> queryResult = objectQuery.executeQuery();

        //  Sort in descending order
        if (getActionInputData().getSortType().equals(Constants.DESC_ORDER)) {
            Collections.reverse(queryResult);
        }

        //  Get number of queried objects
        int number = getActionInputData().getNumber();
        //  Get all queried objects
        if (number > queryResult.size()) {
            number = queryResult.size();
        }

        //  Get query result
        return Constants.QUERY_RESULT + queryResult.subList(0, number);
    }
}
