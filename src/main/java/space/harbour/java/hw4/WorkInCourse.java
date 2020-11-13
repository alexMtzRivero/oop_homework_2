package space.harbour.java.hw4;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class WorkInCourse {

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

    public static void writeToFile(Object obj, String filename) throws IOException {
        FileOutputStream out = new FileOutputStream(filename);
        BufferedOutputStream but = new BufferedOutputStream(out);
        ObjectOutputStream dut = new ObjectOutputStream(but);
        dut.writeObject(obj);
        dut.flush();
    }

    public static void main(String[] args) {
        Object test = new Object();
        Integer integer = 5;
        String testString = "Test Test 123";


        try {
            byte[] serializedObj = serialize(integer);
            System.out.println(Arrays.toString(serialize(integer)));
            System.out.println(deserialize(serializedObj));

            writeToFile(testString, "./src/main/java/test_file.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}