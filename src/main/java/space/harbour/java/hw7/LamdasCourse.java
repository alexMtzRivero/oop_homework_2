package space.harbour.java.hw7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import space.harbour.java.hw4.Movie;

public class LamdasCourse {


    public static void main(String[] args) {

    }

    public static ArrayList<Movie> sortByYear(ArrayList<Movie> movies) {
        Comparator<Movie> byYear = Comparator.comparingInt(m -> m.year);
        movies.sort(byYear);
        movies.forEach(movie -> System.out.println(movie.year));
        return  movies;
    }

    public static ArrayList<Movie> sortByAwards(ArrayList<Movie> movies) {
        Comparator<Movie> byAwardLen = Comparator.comparingInt(m -> m.awards.length());
        movies.sort(byAwardLen);
        movies.forEach(movie -> System.out.println(movie.awards));
        return movies;
    }

    public static ArrayList<Movie> sortByRuntime(ArrayList<Movie> movies) {
        Comparator<Movie> byRuntime = Comparator.comparingInt(m -> m.runtime);
        movies.sort(byRuntime);
        movies.forEach(movie -> System.out.println(movie.runtime));
        return movies;
    }

    public static ArrayList<Movie> filterByDirectorFirstLetter(ArrayList<Movie> movies,
                                                               Character c) {

        return (ArrayList<Movie>) movies.stream()
                .filter(movie -> movie.director.name.charAt(0) == c).collect(Collectors.toList());

    }

    public static ArrayList<Movie> filterByActor(ArrayList<Movie> movies, String actorName) {
        return (ArrayList<Movie>) movies.stream()
                .filter(movie -> movie.actors.get(0).name.equals(actorName))
                .collect(Collectors.toList());
    }

    public static ArrayList<Movie> filterByGenre(ArrayList<Movie> movies, String genre) {
        return (ArrayList<Movie>) movies.stream()
                .filter(movie -> movie.genres.contains(genre)).collect(Collectors.toList());
    }
}
