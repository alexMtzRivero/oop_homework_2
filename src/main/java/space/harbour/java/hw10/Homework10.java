package space.harbour.java.hw10;

import java.util.ArrayList;
import space.harbour.java.hw4.Movie;

public class Homework10 {
    public static void main(String[] args) {


        MongoConector mc = new MongoConector();

        mc.conect();

        mc.deleteMoviesWithName("Blade Runner");

        ArrayList<Movie> bladeRunerFromMongo = mc.getMoviesWithName("Blade Runner");
        System.out.println("primer mongo desde bladeruner: ");
        System.out.println(bladeRunerFromMongo);



        Movie bladeRuner = new Movie();
        String jsonString = bladeRuner.readTextFromFile("BladeRunner.json");
        bladeRuner.fromJson(jsonString);


        mc.addMovie(bladeRuner);

        bladeRunerFromMongo = mc.getMoviesWithName("Blade Runner");
        System.out.println("segundo mongo desde bladeruner: ");
        for (Movie runerFromMongo : bladeRunerFromMongo) {
            System.out.println(runerFromMongo.toJsonString());
        }

        mc.disconect();
    }
}
