package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Post;
import models.User;
import response.PostResponse;
import response.PostsResponse;
import response.UserResponse;
import response.UsersResponse;

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
    private static final String headerName = "content-type";
    private static final String headerValue = "application/json";

    static HttpRequest getPostRequest(String path, String json) {
        AqualityServices.getLogger().info("Build request: POST " + path);
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header(headerName, headerValue)
                .uri(URI.create(url + path))
                .build();
    }

    static HttpRequest getGetRequest(String path) {
        AqualityServices.getLogger().info("Build request: GET " + path);
        return HttpRequest.newBuilder()
                .GET()
                .header(headerName, headerValue)
                .uri(URI.create(url + path))
                .build();
    }

    static HttpResponse<String> getResponse(HttpRequest request) {
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
}
