import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MovieManagerTest {

    private MovieManager movieManager;

    @Before
    public void setUp() {
        movieManager = new MovieManager();
    }

    @Test
    public void testUserRegistration() {
        movieManager.addUser("a@gmail.com");
        Collection<User> users = movieManager.getUsers();
        assertEquals(1, users.size());
        assertEquals("a@gmail.com", users.iterator().next().getEmail());
    }

    @Test
    public void testSearchMoviesByTitle() {
        movieManager.addMovie(new Movie("The Lion King", Arrays.asList("Tim Robbins", "Morgan Freeman"), "Drama", LocalDate.of(1994, 9, 23), 25_000_000));
        List<Movie> searchResult = movieManager.searchMovies("Lion");
        assertEquals(1, searchResult.size());
        assertEquals("The Lion King", searchResult.getFirst().getTitle());
    }

    @Test
    public void testSearchMoviesByCast() {
        movieManager.addMovie(new Movie("The Godfather", Arrays.asList("Marlon Brando", "Al Pacino"), "Crime", LocalDate.of(1972, 3, 24), 6_000_000));
        List<Movie> searchResult = movieManager.searchMovies("Marlon Brando");
        assertEquals(1, searchResult.size());
        assertEquals("The Godfather", searchResult.getFirst().getTitle());
    }

    @Test
    public void testSearchMoviesByCategory() {
        movieManager.addMovie(new Movie("Inception", Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt"), "Sci-Fi", LocalDate.of(2010, 7, 16), 160_000_000));
        List<Movie> searchResult = movieManager.searchMovies("Sci-Fi");
        assertEquals(1, searchResult.size());
        assertEquals("Inception", searchResult.getFirst().getTitle());
    }

    @Test
    public void testSearchMoviesNoMatch() {
        movieManager.addMovie(new Movie("The Godfather", Arrays.asList("Marlon Brando", "Al Pacino"), "Crime", LocalDate.of(1972, 3, 24), 6_000_000));
        List<Movie> searchResult = movieManager.searchMovies("Action");
        assertEquals(0, searchResult.size());
    }

    @Test
    public void testGetMovieDetailsValid() {
        movieManager.addMovie(new Movie("The Godfather", Arrays.asList("Marlon Brando", "Al Pacino"), "Crime", LocalDate.of(1972, 3, 24), 6_000_000));
        Movie movie = movieManager.getMovieDetails("The Godfather");
        assertNotNull(movie);
        assertEquals("The Godfather", movie.getTitle());
    }

    @Test
    public void testGetMovieDetailsInvalid() {
        movieManager.addMovie(new Movie("The Godfather", Arrays.asList("Marlon Brando", "Al Pacino"), "Crime", LocalDate.of(1972, 3, 24), 6_000_000));
        Movie movie = movieManager.getMovieDetails("Inception");
        assertNull(movie);
    }

    @Test
    public void testAddMovieToFavorites() {
        User user = new User("a@gmail.com");
        Movie movie = new Movie("Inception", Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt"), "Sci-Fi", LocalDate.of(2010, 7, 16), 160_000_000);
        movieManager.addMovieToFavorites(user, movie);
        List<Movie> favorites = movieManager.getUserFavorites(user);
        assertEquals(1, favorites.size());
        assertEquals("Inception", favorites.getFirst().getTitle());
    }

    @Test
    public void testRemoveMovieFromFavorites() {
        User user = new User("a@gmail.com");
        Movie movie = new Movie("Inception", Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt"), "Sci-Fi", LocalDate.of(2010, 7, 16), 160_000_000);
        movieManager.addMovieToFavorites(user, movie);
        movieManager.removeMovieFromFavorites(user, movie);
        List<Movie> favorites = movieManager.getUserFavorites(user);
        assertEquals(0, favorites.size());
    }

    @Test
    public void testSearchUserFavorites() {
        User user = new User("a@gmail.com");
        Movie movie1 = new Movie("Inception", Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt"), "Sci-Fi", LocalDate.of(2010, 7, 16), 160_000_000);
        Movie movie2 = new Movie("The Lion King", Arrays.asList("Tim Robbins", "Morgan Freeman"), "Drama", LocalDate.of(1994, 9, 23), 25_000_000);
        movieManager.addMovieToFavorites(user, movie1);
        movieManager.addMovieToFavorites(user, movie2);
        List<Movie> searchResult = movieManager.searchUserFavorites(user, "Lion");
        assertEquals(1, searchResult.size());
        assertEquals("The Lion King", searchResult.getFirst().getTitle());
    }

}
