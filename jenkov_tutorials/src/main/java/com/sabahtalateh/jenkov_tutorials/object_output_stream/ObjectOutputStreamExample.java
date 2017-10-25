package com.sabahtalateh.jenkov_tutorials.object_output_stream;

import java.io.*;

/**
 * ObjectOutputStreamExample.
 */
public class ObjectOutputStreamExample implements Serializable {

    public int age;
    public String name;

    /**
     * @param age  age.
     * @param name name.
     */
    ObjectOutputStreamExample(int age, String name) {
        this.age = age;
        this.name = name;
    }

    /**
     * @param args args.
     * @throws IOException            exception.
     * @throws ClassNotFoundException exception.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("jenkov_tutorials/out/example_obj_out_stream.out"))) {
            oos.writeObject(new ObjectOutputStreamExample(18, "Ivan"));
            oos.writeObject(new ObjectOutputStreamExample(24, "Petr"));
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("jenkov_tutorials/out/example_obj_out_stream.out"))) {
            ObjectOutputStreamExample o1 = (ObjectOutputStreamExample) ois.readObject();
            System.out.println(o1.age);
            System.out.println(o1.name);

            ObjectOutputStreamExample o2 = (ObjectOutputStreamExample) ois.readObject();
            System.out.println(o2.age);
            System.out.println(o2.name);
        }
    }
}
