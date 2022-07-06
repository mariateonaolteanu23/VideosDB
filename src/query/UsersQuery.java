package query;

import common.Database;
import common.Helpers;
import criteria.UsersCriteria;
import factory.UsersCriteriaFactory;
import user.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class UsersQuery extends ObjectQuery {
    public UsersQuery(final String objectType, final String criteria,
                      final List<List<String>> filters) {
        super(objectType, criteria, filters);
    }

    /**
     * Executes query on users
     * @return string list, contains queried users usernames
     */
    @Override
    public List<String> executeQuery() {
        //  Sort users in alphabetical order
        List<User> sortedUsers = Database.getDatabase().getUsers().stream().
                sorted(Comparator.comparing(User::getUsername)).collect(Collectors.toList());

        //  Get specified user's criteria
        UsersCriteria usersCriteria = UsersCriteriaFactory.createUsersCriteria(
                getCriteria(), getFilters(), sortedUsers);
        assert usersCriteria != null;

        //  Get queried users
        List<User> queriedUsers = usersCriteria.getUsersByCriteria();

        //  Return only the usernames of the users
        return Helpers.getOnlyUsernames(queriedUsers);
    }
}
