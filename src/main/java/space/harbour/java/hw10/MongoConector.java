package space.harbour.java.hw10;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;
import space.harbour.java.hw4.Movie;


public class MongoConector {

    public static MongoClient mongoClient;


    public static String addMovie(Movie movie) {
        Document doc = Document.parse(movie.toJsonString());

        MongoCollection<Document> moviesColl = getColection("movies");
        moviesColl.insertOne(doc);

        return doc.toJson();
    }

    public static MongoCollection<Document> getColection(String colName) {
        if (mongoClient == null) {
            conect();
        }
        MongoDatabase database = mongoClient.getDatabase("MoviesTest");
        return database.getCollection(colName);
    }

    public static Document updateMovie(String name, Movie movie) {
        Gson gson = new Gson();
        Document doc = Document.parse(gson.toJson(movie));
        System.out.println(name);
        return getColection("movies").findOneAndUpdate(Filters.eq("Title", name), doc);
    }

    public static Document updateMovie(String name, String movie) {
        Document doc = Document.parse(movie);
        return getColection("movies").findOneAndReplace(Filters.eq("Title", name), doc);
    }

    public static ArrayList<Movie> getMoviesWithName(String name) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        MongoCollection<Document> moviesColl = getColection("movies");
        for (Document doc : moviesColl.find(Filters.eq("Title", name))) {
            Movie movie = new Movie();
            movie.fromJson(doc.toJson());
            movies.add(movie);
        }
        return movies;
    }

    public static ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        MongoCollection<Document> moviesColl = getColection("movies");
        for (Document doc : moviesColl.find()) {
            Movie movie = new Movie();
            movie.fromJson(doc.toJson());
            movies.add(movie);
        }
        return movies;
    }

    public static boolean deleteMoviesWithName(String name) {
        MongoCollection<Document> moviesColl = getColection("movies");
        moviesColl.deleteMany(Filters.eq("Title", name));
        return true;
    }

    public static void conect() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://root:thisisarealyhardpassword@cluster0-shard-00-00.wnwbw.mongodb.net:27"
                        + "017,cluster0-shard-00-01.wnw"
                        + "bw.mongodb.net:27017,cluster0-shard-00-02.wnwbw.mongo"
                        + "db.net:27017/QuizProject?ssl=true&rep"
                        + "licaSet=atlas-ogak8t-shard-0&authSource=admin&w=majority");
        mongoClient = new MongoClient(uri);
    }

    public static void disconect() {
        mongoClient.close();
    }

}
