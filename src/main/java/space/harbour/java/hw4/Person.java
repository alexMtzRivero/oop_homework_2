package space.harbour.java.hw4;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Person {
    int age;
    String name;
    float wheight;
    String nationality;
    Boolean sex;

    public Person(int age, String name, float wheight, String nationality, Boolean sex) {
        this.age = age;
        this.name = name;
        this.wheight = wheight;
        this.nationality = nationality;
        this.sex = sex;
    }

    public static void main(String[] args) throws IOException {
        Person me = new Person(22, "alejandro", 58.5f, "mexican", true);

        System.out.print("save in file: ");
        Scanner scanner = new Scanner(System.in);
        String inFileName = scanner.nextLine();

        // write obj in  file
        byte[] byteMe = serialize(me);

        writeToFile(byteMe, inFileName);

        //ask input aigain
        //read aigain from object
    }



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
    /*
    public static Object readFromFIle(String filename) throws IOException, ClassNotFoundException {
        FileInputStream out = new FileInputStream(filename);
        BufferedInputStream bOut = new BufferedInputStream(out);
        ObjectInputStream dOut = new ObjectInputStream(bOut);
        return dOut.readObject();
    }
     */

    public static void writeToFile(Object obj, String filename) throws IOException {
        FileOutputStream out = new FileOutputStream(filename);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        ObjectOutputStream dout = new ObjectOutputStream(bout);
        dout.writeObject(obj);
        dout.flush();
    }

}
