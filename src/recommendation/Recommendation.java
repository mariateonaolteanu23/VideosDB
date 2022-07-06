package recommendation;

import action.Action;
import fileio.ActionInputData;

public abstract class Recommendation extends Action {
    public Recommendation(final ActionInputData actionInputData) {
        super(actionInputData);
    }

    /**
     * Gets user recommendation specified by actionInputData
     * @return recommendation result
     */
    public abstract String executeAction();

}
