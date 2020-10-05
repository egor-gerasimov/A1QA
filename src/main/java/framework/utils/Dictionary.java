package framework.utils;

import java.util.HashMap;

public class Dictionary {

    private static HashMap<String, String> ru = new HashMap<String, String>() {
        {
            put("Action", "Экшен");
            put("Indie", "Инди");
        }
    };

    private static HashMap<String, String> en = new HashMap<String, String>() {
        {
            put("Action", "Action");
            put("Indie", "Indie");
        }
    };

    private static HashMap<String, String> getMap() {
        switch (PropertyManager.getProperty("lang")) {
            case "ru":
                return ru;
            case "en":
                return en;
            default:
                throw new LanguageException("Wrong language in properties");
        }
    }

    public static String getWord(String key) {
        return getMap().get(key);
    }
}
