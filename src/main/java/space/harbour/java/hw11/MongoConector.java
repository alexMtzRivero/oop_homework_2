package space.harbour.java.hw11;

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
    static  final String COLLECTION = "messages";

    public  static Boolean addMessage(Message message) {
        Gson gson = new Gson();
        Document doc = Document.parse(gson.toJson(message));

        MongoCollection<Document> messagesColl = getColection(COLLECTION);
        messagesColl.insertOne(doc);

        return true;
    }

    public static MongoCollection<Document> getColection(String colName) {
        if (mongoClient == null) {
            conect();
        }
        MongoDatabase database = mongoClient.getDatabase("HW11");
        return database.getCollection(colName);
    }

    public static ArrayList<Message> getall() {
        ArrayList<Message> messages = new ArrayList<Message>();
        MongoCollection<Document> messColl = getColection(COLLECTION);
        for (Document doc : messColl.find()) {
            Gson gson = new Gson();
            Message message =  gson.fromJson(doc.toJson(), Message.class);
            messages.add(message);
        }
        return messages;
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

    public  static void disconect() {
        mongoClient.close();
    }

    public static String getallFormated() {
        ArrayList<Message> messages = getall();
        StringBuilder result = new StringBuilder("Hel"
                + "lo here are the previous messages from mongo:\n");
        for (Message message : messages) {
            result.append(message.toString()).append("\n");
        }
        return  result.toString();
    }
}
