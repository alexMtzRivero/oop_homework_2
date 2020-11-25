package space.harbour.java.hw10;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Assert;
import space.harbour.java.hw4.Movie;

public class MongoConectorTest extends TestCase {

    // it was executing the test in diferent orders each time IDK why so i put only 1 big test
    public void testAll() {

        MongoConector mc = new MongoConector();
        mc.conect();


        //    testAddMovie()

        Movie bladeRuner = new Movie();
        String jsonString = bladeRuner.readTextFromFile("BladeRunner.json");
        bladeRuner.fromJson(jsonString);

        Assert.assertNotNull(mc.addMovie(bladeRuner));

        //      testGetMoviesWithName()
        ArrayList<Movie> bladeRunerFromMongo = mc.getMoviesWithName("Blade Runner");
        Assert.assertTrue(1 <= bladeRunerFromMongo.size());

        for (Movie runerFromMongo : bladeRunerFromMongo) {
            System.out.println(runerFromMongo.toJsonString());
        }

        //      testDeleteMoviesWithName()
        Assert.assertTrue(mc.deleteMoviesWithName("Blade Runner"));


        //   testEmptyAfterDelete()
        bladeRunerFromMongo = mc.getMoviesWithName("Blade Runner");
        Assert.assertTrue(bladeRunerFromMongo.isEmpty());


        mc.disconect();
    }
}