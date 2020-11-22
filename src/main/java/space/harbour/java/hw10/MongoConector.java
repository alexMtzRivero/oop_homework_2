package space.harbour.java.hw10;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;
import space.harbour.java.hw4.Movie;


public class MongoConector {

    MongoClient mongoClient;


    public Boolean addMovie(Movie movie) {
        Document doc = Document.parse(movie.toJsonString());

        MongoCollection<Document> moviesColl = getColection("movies");

        moviesColl.insertOne(doc);

        return true;
    }

    public MongoCollection<Document> getColection(String colName) {
        MongoDatabase database = mongoClient.getDatabase("MoviesTest");
        return database.getCollection(colName);
    }

    public ArrayList<Movie> getMoviesWithName(String name) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        MongoCollection<Document> moviesColl = getColection("movies");
        for (Document doc : moviesColl.find(Filters.eq("Title", name))) {
            Movie movie = new Movie();
            movie.fromJson(doc.toJson());
            movies.add(movie);
        }
        return movies;
    }

    public boolean deleteMoviesWithName(String name) {
        MongoCollection<Document> moviesColl = getColection("movies");
        moviesColl.deleteMany(Filters.eq("Title", name));
        return true;
    }

    public void conect() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://root:thisisarealyhardpassword@cluster0-shard-00-00.wnwbw.mongodb.net:27"
                        + "017,cluster0-shard-00-01.wnw"
                        + "bw.mongodb.net:27017,cluster0-shard-00-02.wnwbw.mongo"
                        + "db.net:27017/QuizProject?ssl=true&rep"
                        + "licaSet=atlas-ogak8t-shard-0&authSource=admin&w=majority");
        mongoClient = new MongoClient(uri);
    }

    public  void disconect() {
        mongoClient.close();
    }

}
