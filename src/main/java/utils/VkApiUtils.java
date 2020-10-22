package utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class VkApiUtils {

    private static final String contentTypeHeaderName = "content-type";
    private static final String contentTypeParamsHeaderValue = "application/x-www-form-urlencoded";
    private static final String urlMethod = "https://api.vk.com/method/";

    private static final Map<String, String> commonParams = new HashMap<>() {
        {
            put("v", "5.52");
            put("access_token", TestData.getStringValue("token"));
        }
    };

    protected static Map<String, String> getCommonParams() {
        return new HashMap<>(commonParams);
    }

    static HttpRequest getPostRequest(String method, Map<String, String> params) {
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(StringUtils.getParametersString(params)))
                .uri(URI.create(urlMethod + method))
                .header(contentTypeHeaderName, contentTypeParamsHeaderValue)
                .build();
    }

    public static String getPhotoWallUploadUrl() {
        Map<String, String> params = getCommonParams();
        params.put("user_id", TestData.getStringValue("user.id"));
        HttpResponse<String> response = APIUtils.getResponse(getPostRequest("photos.getWallUploadServer", params));
        return Utils.getObjectNode(response.body()).get("response").get("upload_url").asText();
    }

    public static void sendPhoto(int postId) {
        File file = new File("src/test/resources/image.jpg");
        HttpPost post = new HttpPost(getPhotoWallUploadUrl());
        HttpEntity entity = MultipartEntityBuilder.create()
                .addBinaryBody("photo", file).build();
        post.setEntity(entity);
        CloseableHttpClient client = HttpClients.createDefault();
        String server = "";
        String photo = "";
        String hash = "";
        try {
            String body = EntityUtils.toString(client.execute(post).getEntity());
            server = Utils.getObjectNode(body).get("server").asText();
            photo = Utils.getObjectNode(body).get("photo").asText();
            hash = Utils.getObjectNode(body).get("hash").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> params = getCommonParams();
        params.put("user_id", TestData.getStringValue("user.id"));
        params.put("photo", photo);
        params.put("server", server);
        params.put("hash", hash);
        HttpResponse<String> response = APIUtils.getResponse(getPostRequest("photos.saveWallPhoto", params));
        String photoId = Utils.getObjectNode(response.body()).get("response").get(0).get("id").asText();
        String ownerId = Utils.getObjectNode(response.body()).get("response").get(0).get("owner_id").asText();
        params = getCommonParams();
        params.put("post_id", String.valueOf(postId));
        params.put("message", "new message");
        params.put("attachments", "photo" + ownerId + "_" + photoId);
        response = APIUtils.getResponse(getPostRequest("wall.edit", params));
        response.body();
    }

}
