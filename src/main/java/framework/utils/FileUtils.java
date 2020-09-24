package framework.utils;

import java.io.File;

public class FileUtils {

    public static boolean hasFile(String path) {
        return new File(path).isFile();
    }
}
