package utils;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Post;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Utils {

    public static boolean isSortedByID(List<Post> posts) {
        long[] sortedIDs = posts.stream().mapToLong(Post::getId).sorted().toArray();
        return IntStream.range(0, posts.size() - 1).noneMatch(i -> posts.get(i).getId() != sortedIDs[i]);
    }

    public static boolean isJson(String content) {
        AqualityServices.getLogger().info("Check if string is JSON");
        try {
            new ObjectMapper().readTree(content);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String toJSONString(Object object) {
        AqualityServices.getLogger().info("Write " + object.getClass().getSimpleName() + " to JSON string");
        String result = "{}";
        try {
            result = new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return result;
    }

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
}
