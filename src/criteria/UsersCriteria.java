package criteria;

import user.User;

import java.util.List;

public abstract class UsersCriteria {
    private final List<List<String>> filters;
    private final List<User> users;

    public UsersCriteria(final String criteria, final List<List<String>> filters,
                         final List<User> users) {
        this.filters = filters;
        this.users = users;
    }

    /**
     * Gets list of users
     * @return users, list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Queries a list of users
     * @return list of queried users
     */
    public abstract List<User> getUsersByCriteria();
}
