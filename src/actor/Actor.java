package actor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Actor {
    /**
     * actor name
     */
    private String name;
    /**
     * description of the actor's career
     */
    private String careerDescription;
    /**
     * videos starring actor
     */
    private ArrayList<String> filmography;
    /**
     * awards won by the actor
     */
    private Map<ActorsAwards, Integer> awards;
    /**
     * actor's number of wins
     */
    private int numberOfWins;
    /**
     * actor's filmography rating
     */
    private Double filmographyRating;

    public Actor(final String name, final String careerDescription,
                          final ArrayList<String> filmography,
                          final Map<ActorsAwards, Integer> awards,
                        final Double filmographyRating) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.filmographyRating = filmographyRating;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public void setAwards(final Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }

    public Double getFilmographyRating() {
        return filmographyRating;
    }

    public void setFilmographyRating(final Double filmographyRating) {
        this.filmographyRating = filmographyRating;
    }

    public void setNumberOfWins(final int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    /**
     * Updates the number of wins
     */
    public void updateNumberOfWins() {
        int actorWins = 0;
        for (Map.Entry<ActorsAwards, Integer> award : awards.entrySet()) {
            actorWins += award.getValue();
        }

        setNumberOfWins(actorWins);
    }

    /**
     * Validates if actor passes the filters
     * @return true if actor meets the conditions, false otherwise
     */
    public boolean checkActorFilters(final List<String> words, final List<String> awards) {
        if (words != null) {
            List<String> description = Arrays.asList(getCareerDescription().
                    toLowerCase().split("\\W"));

            return description.containsAll(words);
        }

        if (awards != null) {
            List<ActorsAwards> awardsFilter = new ArrayList<>();

            for (String awardString : awards) {
                ActorsAwards award = ActorsAwards.valueOf(awardString);
                awardsFilter.add(award);
            }

            return getAwards().keySet().containsAll(awardsFilter);
        }

        return true;
    }
}
