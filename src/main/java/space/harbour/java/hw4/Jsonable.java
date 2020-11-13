package space.harbour.java.hw4;

import javax.json.JsonObject;

public interface Jsonable<T> {
    public String toJsonString();

    public JsonObject toJsonObject();

    public T fromJson(String json);
}
