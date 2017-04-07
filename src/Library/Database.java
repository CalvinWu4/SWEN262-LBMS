package Library;

import java.io.*;

/**
 * Created by Calvin on 4/7/2017.
 */
public class Database {


    public static Object read(File file) {
        try {
            if (!file.createNewFile()) {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Object object = ois.readObject();
                    ois.close();
                    fis.close();
                    return object;
                } catch (ClassNotFoundException c) {
                    c.printStackTrace();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public static void write(Object object, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
