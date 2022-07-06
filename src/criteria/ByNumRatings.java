package criteria;

import user.User;

import java.util.List;
import java.util.stream.Collectors;

public final class ByNumRatings extends UsersCriteria {
    public ByNumRatings(final String criteria, final List<List<String>> filters,
                        final List<User> users) {
        super(criteria, filters, users);
    }

    /**
     * Sorts users by number of ratings/reviews given in ascending order
     * User has to have at least one rated video
     * @return sorted list of users
     */
    @Override
    public List<User> getUsersByCriteria() {

        //  Gets sorted users by rating activity
        //  Excludes inactive users
        return  getUsers().stream().filter(user -> user.getRatedVideos().size() != 0).
                sorted(((u1, u2) -> {
                    Integer s1 = u1.getRatedVideos().size();
                    Integer s2 = u2.getRatedVideos().size();
                    return s1.compareTo(s2);
                }
                )).collect(Collectors.toList());
    }

}
