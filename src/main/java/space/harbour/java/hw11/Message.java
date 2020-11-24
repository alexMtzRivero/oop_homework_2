package space.harbour.java.hw11;

public class Message {
    String user;
    String text;
    String timestamp;

    @Override
    public String toString() {
        return user + ": " + text;
    }
}
