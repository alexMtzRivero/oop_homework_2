package space.harbour.java.hw4;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ExampleValue implements Jsonable<ExampleValue> {
    public Integer ii = 10;
    private String ss = "ABC";
    protected float ff = .9f;
    private InsideClass hiddenClass = new InsideClass();

    class InsideClass implements Jsonable<InsideClass> {
        String ss = "XYZ";
        Integer ii = 1050;

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("ss", ss)
                    .add("ii", ii)
                    .build();
        }

        @Override
        public InsideClass fromJson(String json) {
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jobject = reader.readObject();
            this.ii = jobject.getInt("ii");
            this.ss = jobject.getString("ss");
            return this;
        }


        @Override
        public String toJsonString() {
            return toJsonObject().toString();
        }
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("ii", ii)
                .add("ss", ss)
                .add("ff", ff)
                .add("hiddenClass", hiddenClass.toJsonObject())
                .build();
    }



    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }


    public ExampleValue fromJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jobject = reader.readObject();
        this.ii = jobject.getInt("ii");
        this.ss = jobject.getString("ss");
        this.ff = (float) jobject.getJsonNumber("ff").doubleValue();

        this.hiddenClass = new InsideClass();
        this.hiddenClass.fromJson(jobject.getJsonObject("hiddenClass").toString());
        return this;
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

    public static void main(String... args) {
        ExampleValue value = new ExampleValue();
        System.out.println(value.toJsonString());

        String jsonString = value.readTextFromFile("exampleValue.json");
        System.out.println(jsonString);
        ExampleValue fromString = new ExampleValue();
        fromString.fromJson(jsonString);
        System.out.println(fromString.ss);
    }
}
