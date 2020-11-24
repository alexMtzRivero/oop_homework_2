package space.harbour.java.hw11;


import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
    private final Set<ChatHandler> chats = ConcurrentHashMap.newKeySet();

    public ChatServer(int port) {
        try (ServerSocket s = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                ChatHandler chat = new ChatHandler(this, s.accept());
                chats.add(chat);
                chat.start();
            }
        } catch (IOException e) {
            System.out.println("Server failed on port " + port);
        }
    }

    public synchronized void broadcast(String message) {
        System.out.println("New message -> " + message);
        for (ChatHandler chat : chats) {
            chat.sendMessage(message);
        }
    }

    public synchronized void broadcastAndSave(String user, String text) {
        Message message = new Message();
        message.user = user;
        message.text = text;
        message.timestamp =  new Timestamp(System.currentTimeMillis()).toString();
        MongoConector.addMessage(message);
        System.out.println("saving New message -> " + text);
        for (ChatHandler chat : chats) {
            chat.sendMessage(text);
        }
    }


    public void chatDisconnected(ChatHandler chat) {
        chats.remove(chat);
        broadcast("Chat member " + chat.name + " left");
    }

    public static void main(String[] args) {
        new ChatServer(8008);
    }

    public void sendAllTo(String name) {
        System.out.println("sending all to -> " + name + chats.size());
        for (ChatHandler chat : chats) {
            System.out.println(chat.name);
            if (chat.name.equals(name)) {
                String all = MongoConector.getallFormated();
                System.out.println(all);
                chat.sendMessage(all);
            }
        }
    }
}