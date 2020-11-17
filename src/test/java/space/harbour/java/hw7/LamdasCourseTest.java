package space.harbour.java.hw7;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Assert;
import space.harbour.java.hw4.Movie;

public class LamdasCourseTest extends TestCase {

    public void testSortByYear() {
        int len = 5;
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            movies.add(new Movie());
        }
        movies.get(0).year = 0;
        movies.get(1).year = 14;
        movies.get(2).year = 430;
        movies.get(3).year = 50;
        movies.get(4).year = -4;

        movies = LamdasCourse.sortByYear(movies);


        for (int i = 1; i < len; i++) {
            Assert.assertTrue(movies.get(i - 1).year < movies.get(i).year);
        }
    }

    public void testSortByAwards() {
        int len = 5;
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            movies.add(new Movie());
        }

        movies.get(0).awards = "a lot of awards OMG";
        movies.get(1).awards = "none";
        movies.get(2).awards = "some of them";
        movies.get(3).awards = "2 golens globes but it soesnt deserve its";
        movies.get(4).awards = "best movie";

        movies = LamdasCourse.sortByAwards(movies);

        for (int i = 1; i < len; i++) {
            Assert.assertTrue(movies.get(i - 1).awards.length() < movies.get(i).awards.length());
        }
    }

    public void testSortByRuntime() {
        int len = 5;
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            movies.add(new Movie());
        }

        movies.get(0).runtime = 200;
        movies.get(1).runtime = 140;
        movies.get(2).runtime = 300;
        movies.get(3).runtime = 290;
        movies.get(4).runtime = 109;

        movies = LamdasCourse.sortByRuntime(movies);

        for (int i = 1; i < len; i++) {
            Assert.assertTrue(movies.get(i - 1).runtime < movies.get(i).runtime);
        }
    }

    public void testFilterByDirectorFirstLetter() {
        int len = 5;
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            movies.add(new Movie());
        }
        movies.get(0).director = new Movie.BasePerson("Alex");
        movies.get(1).director = new Movie.BasePerson("Ahmed");
        movies.get(2).director = new Movie.BasePerson("Guillermo");
        movies.get(3).director = new Movie.BasePerson("Tarantino");
        movies.get(4).director = new Movie.BasePerson("Tim Burton");
        movies = LamdasCourse.filterByDirectorFirstLetter(movies, 'A');

        for (Movie movie : movies) {
            Assert.assertEquals('A', movie.director.name.charAt(0));
        }
    }

    public void testFilterByActor() {
        int len = 5;
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            movies.add(new Movie());
        }
        movies.get(0).actors = new ArrayList<>();
        movies.get(0).actors.add(new Movie.Actor("Leonardo"));
        movies.get(1).actors = new ArrayList<>();
        movies.get(1).actors.add(new Movie.Actor("Robin"));
        movies.get(2).actors = new ArrayList<>();
        movies.get(2).actors.add(new Movie.Actor("Jenifer"));
        movies.get(3).actors = new ArrayList<>();
        movies.get(3).actors.add(new Movie.Actor("Jim"));
        movies.get(4).actors = new ArrayList<>();
        movies.get(4).actors.add(new Movie.Actor("Arnold"));

        movies = LamdasCourse.filterByActor(movies, "Arnold");

        for (Movie movie : movies) {
            Assert.assertEquals("Arnold", movie.actors.get(0).name);
        }
    }

    public void testFilterByGenre() {
        int len = 5;
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            movies.add(new Movie());
        }


        movies.get(0).genres = new ArrayList<>();
        movies.get(0).genres.add("horror");
        movies.get(1).genres = new ArrayList<>();
        movies.get(1).genres.add("comedy");
        movies.get(2).genres = new ArrayList<>();
        movies.get(2).genres.add("romance");
        movies.get(3).genres = new ArrayList<>();
        movies.get(3).genres.add("noire");
        movies.get(4).genres = new ArrayList<>();
        movies.get(4).genres.add("action");

        movies = LamdasCourse.filterByGenre(movies, "action");

        for (Movie movie : movies) {
            Assert.assertTrue(movie.genres.contains("action"));
        }
    }
}