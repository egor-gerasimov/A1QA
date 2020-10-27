package utils;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class Utils {

    public static ObjectNode getObjectNode(String json) {
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(json, ObjectNode.class);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return node;
    }

}
