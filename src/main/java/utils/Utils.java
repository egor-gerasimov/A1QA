package utils;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;

public class Utils {

    public static <T> T readObjectFromJSON(String json, Class<T> objectClass) {
        AqualityServices.getLogger().info("Read " + objectClass.getSimpleName() + " from JSON string");
        T object = null;
        try {
            object = new ObjectMapper().readValue(json, objectClass);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return object;
    }

    public static ObjectNode getObjectNode(String json) {
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(json, ObjectNode.class);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return node;
    }

    public static Image getImage(File file) {
        Image image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return image;
    }

    public static Image getImage(String url) {
        Image image = null;
        try {
            image = ImageIO.read(new URL(url));
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return image;
    }
}
