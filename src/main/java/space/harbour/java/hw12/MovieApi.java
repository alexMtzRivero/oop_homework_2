package space.harbour.java.hw12;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFileLocation;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.jetty.http.HttpStatus;
import space.harbour.java.hw10.MongoConector;
import space.harbour.java.hw4.Movie;
import spark.ModelAndView;
import spark.Request;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;


public class MovieApi {

    public static void main(String[] args) {
        Spark.exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
        });
        staticFileLocation("public");
        System.out.println(" server running in -> http://localhost:4567");
        //crud
        //create
        post("/movies", (request, response) -> {
            Movie movie = new Movie().fromJson(request.body());
            MongoConector.addMovie(movie);
            return MongoConector.addMovie(movie);
        });


        //read
        get("/movies/:name", (request, response) -> {
            String name = request.params(":name");
            ArrayList<Movie> movies = MongoConector.getMoviesWithName(name);

            if (!movies.isEmpty() && movies.get(0) != null) {
                if (clientAcceptsJson(request)) {
                    response.type("application/json");
                    return movies.get(0).toJsonString();
                }

                Map<String, Object> movieMap = new HashMap<>();
                movieMap.put("movie", movies.get(0));
                return render(movieMap, "MOVIE.ftl");
            }
            response.status(HttpStatus.NOT_FOUND_404);
            return "Movie not found";
        });

        //readAll
        get("/movies", (request, response) -> {
                    Gson gson = new Gson();
                    ArrayList<Movie> movies = MongoConector.getAllMovies();
                    if (clientAcceptsJson(request)) {
                        response.type("application/json");
                        return gson.toJson(movies);
                    }
            Map<String, Object> moviemap = new HashMap<>();
            Map<Integer, Object> movieMapMap = new HashMap<>();
            for (int i = 0; i < movies.size(); i++) {
                System.out.println(movies.get(i).title);
                movieMapMap.put(i, movies.get(i));
            }
            moviemap.put("movies", movieMapMap);
            return render(moviemap, "movies.ftl");
                }
        );
        //update
        put("/movies/:name", (request, response) -> {
            String name = request.params(":name");
                    return MongoConector.updateMovie(name, request.body()).toJson();
                }
        );
        //delete
        delete("/movies/:name", (request, response) -> {
                    String name = request.params(":name");
                    MongoConector.deleteMoviesWithName(name);
                    return "deleted successfully";
                }
        );

    }

    public static String render(Map values, String template) {
        return new FreeMarkerEngine().render(new ModelAndView(values, template));
    }

    public static boolean clientAcceptsHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }

    public static boolean clientAcceptsJson(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("application/json");
    }
}
