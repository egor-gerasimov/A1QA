package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Post;
import models.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

public class APIUtils {

    private static final String url = AqualityServices.get(ISettingsFile.class).getValue("/url").toString();
    private static final String postsPath = "/posts";
    private static final String usersPath = "/users";

    public static HttpResponse<String> getPosts() {
        return getResponse(getGetRequest(postsPath));
    }

    public static HttpResponse<String> getUsers() {
        return getResponse(getGetRequest(usersPath));
    }

    public static HttpResponse<String> getPost(long id) {
        return getResponse(getGetRequest(postsPath + "/" + id));
    }

    public static HttpResponse<String> getUser(long id) {
        return getResponse(getGetRequest(usersPath + "/" + id));
    }

    public static HttpResponse<String> put(Post post) {
        return getResponse(getPostRequest(postsPath, Utils.toJSONString(post)));
    }

    private static HttpRequest getPostRequest(String path, String json) {
        AqualityServices.getLogger().info("Build request: POST " + path);
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("content-type", "application/json")
                .uri(URI.create(url + path))
                .build();
    }

    private static HttpRequest getGetRequest(String path) {
        AqualityServices.getLogger().info("Build request: GET " + path);
        return HttpRequest.newBuilder()
                .GET()
                .header("content-type", "application/json")
                .uri(URI.create(url + path))
                .build();
    }

    private static HttpResponse<String> getResponse(HttpRequest request) {
        AqualityServices.getLogger().info("Send request: " + request.toString());
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return response;
    }

    public static <T> T readObject(HttpResponse<String> response, Class<T> objectClass) {
        AqualityServices.getLogger().info("Read " + objectClass.getSimpleName() + " from response: " + response.toString());
        return Utils.readObjectFromJSON(response.body(), objectClass);
    }

    public static List<Post> readPosts(HttpResponse<String> response) {
        AqualityServices.getLogger().info("Read posts from response: " + response.toString());
        List<Post> object = new ArrayList<>();
        try {
            object = new ObjectMapper().readValue(response.body(), new TypeReference<List<Post>>() {});
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return object;
    }

    public static List<User> readUsers(HttpResponse<String> response) {
        AqualityServices.getLogger().info("Read users from response: " + response.toString());
        List<User> object = new ArrayList<>();
        try {
            object = new ObjectMapper().readValue(response.body(), new TypeReference<List<User>>() {});
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return object;
    }
}
