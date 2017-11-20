package com.sabahtalateh.jenkov_tutorials.reflection.class_loading;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * MyClassLoader.
 */
public class MyClassLoader extends ClassLoader {
    /**
     * @param parent parent loader.
     */
    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * @param name class name.
     * @return class.
     * @throws ClassNotFoundException exception.
     */
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        if (!"com.sabahtalateh.jenkov_tutorials.reflection.class_loading.MyObject".equals(name)
                || !"com.sabahtalateh.jenkov_tutorials.reflection.class_loading.MyObject2".equals(name)) {
            return super.loadClass(name);
        }

        String nameToPath = name.replace('.', '/');
        Path classPath = Paths.get("", "jenkov_tutorials/target/classes", nameToPath + ".class");

        try {
            URL url = new URL("file:" + classPath.toString());
            InputStream input = url.openConnection().getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while (data != -1) {
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return defineClass(name, classData, 0, classData.length);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
