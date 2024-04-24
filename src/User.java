import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private String email;
    private List<Movie> favorites;

    public User(String email) {
        this.email = email;
        this.favorites = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public List<Movie> getFavorites() {
        return favorites;
    }

    public void addFavorite(Movie movie) {
        favorites.add(movie);
    }

    public void removeFavorite(Movie movie) {
        favorites.remove(movie);
    }
}

class Movie {
    private String title;
    private List<String> cast;
    private String category;
    private LocalDate releaseDate;
    private double budget;

    public Movie(String title, List<String> cast, String category, LocalDate releaseDate, double budget) {
        this.title = title;
        this.cast = cast;
        this.category = category;
        this.releaseDate = releaseDate;
        this.budget = budget;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCast() {
        return cast;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public double getBudget() {
        return budget;
    }
}

