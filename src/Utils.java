import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Utils {
    private static Utils instance;

    private Utils () {

    }

    public static Utils getInstance() {
        if(instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    private static void createEmptyFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println(fileName + " created successfully!");
                System.out.println("Path: " + file.getAbsolutePath());
            } else {
                System.out.println(fileName + " already exists.");
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

//    private static void writeObject(Object data, File file) {
//
//        try (OutputStream oos = new OutputStream(new FileOutputStream(file.getName()))) {
//            oos.w(object);
//            System.out.println("Object written to file: " + fileName);
//        } catch (IOException e) {
//            System.err.println("Error writing object to file: " + e.getMessage());
//        }
//    }


}
