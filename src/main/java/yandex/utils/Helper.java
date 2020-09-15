package yandex.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Helper {

    private static final String CATEGORIES_FILE_PATH = PropertyManager.getProperty("resources.path") + "categories.csv";

    public static String toMultiOS(String path) {
        return path.replace("/", File.separator);
    }

    public static void writeCategoriesToFile(List<String> categories) {
        try (PrintWriter writer = new PrintWriter(new File(CATEGORIES_FILE_PATH))) {
            StringBuilder sb = new StringBuilder();
            for (String category : categories) {
                sb.append(category).append('\n');
            }
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean categoriesFileIsExists() {
        File file = new File(CATEGORIES_FILE_PATH);
        return file.exists() && !file.isDirectory();
    }
}
