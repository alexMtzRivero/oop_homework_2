package space.harbour.java.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;


public class Movie implements Jsonable<Movie> {
    public static void main(String... args) {

        Movie bladeRuner = new Movie();

        String jsonString = bladeRuner.readTextFromFile("BladeRunner.json");
        System.out.println(jsonString);

        bladeRuner.fromJson(jsonString);
        System.out.println(bladeRuner.toJsonString());
    }

    public String readTextFromFile(String path) {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            StringBuilder data = new StringBuilder();
            while (myReader.hasNextLine()) {
                data.append(" ").append(myReader.nextLine());
            }
            myReader.close();
            return data.toString();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public static class BasePerson implements Jsonable<BasePerson> {
        public String name;

        public BasePerson(String name) {
            this.name = name;
        }

        @Override
        public String toJsonString() {
            return toJsonObject().toString();
        }

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("Name", name)
                    .build();
        }

        @Override
        public BasePerson fromJson(String json) {
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jsonObject = reader.readObject();
            this.name = jsonObject.getString("Name");

            return this;
        }
    }

    class Writer extends BasePerson {
        String type;

        public Writer(String name) {
            super(name);
        }

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("Name", name)
                    .add("Type", type)
                    .build();
        }

        @Override
        public Writer fromJson(String json) {
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jsObject = reader.readObject();
            this.name = jsObject.getString("Name");
            this.type = jsObject.getString("Type");
            return this;
        }
    }

    public static class Actor extends BasePerson {
        public String as;

        public Actor(String name) {
            super(name);
        }

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("Name", name)
                    .add("As", as)
                    .build();
        }

        @Override
        public Actor fromJson(String json) {
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jsObject = reader.readObject();
            this.name = jsObject.getString("Name");
            this.as = jsObject.getString("As");

            return this;
        }
    }

    class Rating implements Jsonable<Rating> {
        String source;
        String value;
        int votes;

        @Override
        public String toJsonString() {
            return toJsonObject().toString();
        }

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("Source", source)
                    .add("Value", value)
                    .add("Votes", votes)
                    .build();
        }

        @Override
        public Rating fromJson(String json) {
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jsObject = reader.readObject();
            this.source = jsObject.getString("Source");
            this.value = jsObject.getString("Value");
            this.votes = jsObject.getInt("Votes", 0);

            return this;
        }
    }

    public String title;
    public Integer year;
    public String released;
    public Integer runtime;
    public String awards;
    public String poster;
    public String plot;

    public BasePerson director;
    public ArrayList<String> genres;
    public ArrayList<String> languages;
    public ArrayList<String> countries;

    public ArrayList<Writer> writers;
    public ArrayList<Actor> actors;
    public ArrayList<Rating> ratings;

    public Movie() {
        this.genres = new ArrayList<String>();
        this.languages = new ArrayList<String>();
        this.countries = new ArrayList<String>();
        this.writers = new ArrayList<Writer>();
        this.actors = new ArrayList<Actor>();
        this.ratings = new ArrayList<Rating>();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    @Override
    public JsonObject toJsonObject() {

        JsonArrayBuilder generesBuilder = Json.createArrayBuilder();
        for (String genere : genres) {
            generesBuilder.add(genere);
        }

        JsonArrayBuilder languagesBuilder = Json.createArrayBuilder();
        for (String language : languages) {
            languagesBuilder.add(language);
        }

        JsonArrayBuilder countriesBuilder = Json.createArrayBuilder();
        for (String countrie : countries) {
            countriesBuilder.add(countrie);
        }

        JsonArrayBuilder ritersBuilder = Json.createArrayBuilder();
        for (Writer rite : writers) {
            ritersBuilder.add(rite.toJsonObject());
        }
        JsonArray writerArr = ritersBuilder.build();

        JsonArrayBuilder ctorsBuilder = Json.createArrayBuilder();
        for (Actor cto : actors) {
            ctorsBuilder.add(cto.toJsonObject());
        }
        JsonArray actorArr = ctorsBuilder.build();

        JsonArrayBuilder atingsBuilder = Json.createArrayBuilder();
        for (Rating atin : ratings) {
            atingsBuilder.add(atin.toJsonObject());
        }
        JsonArray ratingArr = atingsBuilder.build();

        JsonArray generesArr = generesBuilder.build();
        JsonArray languagesArr = languagesBuilder.build();
        JsonArray countriesArr = countriesBuilder.build();


        return Json.createObjectBuilder()
                .add("Title", title)
                .add("Year", year)
                .add("Released", released)
                .add("Runtime", runtime)
                .add("Awards", awards)
                .add("Poster", poster)
                .add("Plot", plot)

                .add("Director", director.toJsonObject())
                .add("Genres", generesArr)
                .add("Languages", languagesArr)
                .add("Countries", countriesArr)

                .add("Writers", writerArr)
                .add("Actors", actorArr)
                .add("Ratings", ratingArr)
                .build();
    }

    @Override
    public Movie fromJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jobject = reader.readObject();

        this.title = jobject.getString("Title");
        this.year = jobject.getInt("Year");
        this.released = jobject.getString("Released");
        this.runtime = jobject.getInt("Runtime");
        this.awards = jobject.getString("Awards");
        this.poster = jobject.getString("Poster");
        this.plot = jobject.getString("Plot");

        this.director  = new BasePerson("").fromJson(jobject.getJsonObject("Director").toString());

        JsonArray tempGeneres = jobject.getJsonArray("Genres");
        this.genres = new ArrayList<String>();
        for (JsonValue value : tempGeneres) {
            this.genres.add(value.toString());
        }

        JsonArray tempLanguages = jobject.getJsonArray("Languages");
        this.languages = new ArrayList<String>();
        for (JsonValue value : tempLanguages) {
            this.languages.add(value.toString());
        }

        JsonArray tempCountries = jobject.getJsonArray("Countries");
        this.countries = new ArrayList<String>();
        for (JsonValue value : tempCountries) {
            this.countries.add(value.toString());
        }

        JsonArray warray = jobject.getJsonArray("Writers");
        this.writers = new ArrayList<Writer>();
        for (JsonValue value : warray) {
            this.writers.add(new Writer("").fromJson(value.asJsonObject().toString()));
        }
        JsonArray aarray = jobject.getJsonArray("Actors");
        this.actors = new ArrayList<Actor>();
        for (JsonValue value : aarray) {
            this.actors.add(new Actor("").fromJson(value.asJsonObject().toString()));
        }
        JsonArray rarray = jobject.getJsonArray("Ratings");
        this.ratings = new ArrayList<Rating>();
        for (JsonValue value : rarray) {
            this.ratings.add(new Rating().fromJson(value.asJsonObject().toString()));
        }

        return this;
    }
}
