import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MovieManager {
    private List<Movie> movies;
    private List<User> users;

    public MovieManager() {
        this.movies = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addUser(String email) {
        users.add(new User(email));
    }

    public List<Movie> searchMovies(String query) {
        return movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        movie.getCast().stream().anyMatch(cast -> cast.toLowerCase().contains(query.toLowerCase())) ||
                        movie.getCategory().toLowerCase().contains(query.toLowerCase()))
                .sorted((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle()))
                .collect(Collectors.toList());
    }

    public Movie getMovieDetails(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public void addMovieToFavorites(User user, Movie movie) {
        user.addFavorite(movie);
    }

    public void removeMovieFromFavorites(User user, Movie movie) {
        user.removeFavorite(movie);
    }

    public List<Movie> getUserFavorites(User user) {
        return user.getFavorites();
    }

    public List<Movie> searchUserFavorites(User user, String query) {
        return user.getFavorites().stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }


    public Collection<User> getUsers() {
        return users;
    }
}
