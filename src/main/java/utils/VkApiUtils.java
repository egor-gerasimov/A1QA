package utils;

import aquality.selenium.browser.AqualityServices;
import constants.VkMethods;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

public class VkApiUtils {

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

    private static String execute(HttpPost httpPost) {
        String body = "";
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            body = EntityUtils.toString(client.execute(httpPost).getEntity());
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return body;
    }

    protected static String execute(String method, Map<String, String> params) {
        String parametersString = StringUtils.getParametersString(params);
        HttpEntity entity = EntityBuilder.create()
                .setContentType(ContentType.APPLICATION_FORM_URLENCODED)
                .setText(parametersString)
                .build();
        HttpPost httpPost = new HttpPost(urlMethod + method);
        httpPost.setEntity(entity);
        return execute(httpPost);
    }

    private static String getPhotoWallUploadUrl() {
        Map<String, String> params = getCommonParams();
        params.put("user_id", TestData.getStringValue("user.id"));
        String responseBody = execute(VkMethods.PHOTOS_GET_WALL_UPLOAD_SERVER, params);
        return Utils.getObjectNode(responseBody).get("response").get("upload_url").asText();
    }

    protected static String getWallPhotoName(File file1) {
        File file = new File("src/test/resources/image.jpg");
        HttpEntity entity = MultipartEntityBuilder.create().addBinaryBody("photo", file).build();
        HttpPost post = new HttpPost(getPhotoWallUploadUrl());
        post.setEntity(entity);
        String responseBody = execute(post);
        String server = Utils.getObjectNode(responseBody).get("server").asText();
        String photo = Utils.getObjectNode(responseBody).get("photo").asText();
        String hash = Utils.getObjectNode(responseBody).get("hash").asText();
        Map<String, String> params = getCommonParams();
        params.put("user_id", TestData.getStringValue("user.id"));
        params.put("photo", photo);
        params.put("server", server);
        params.put("hash", hash);
        responseBody = execute(VkMethods.PHOTOS_SAVE_WALL_PHOTO, params);
        String photoId = Utils.getObjectNode(responseBody).get("response").get(0).get("id").asText();
        String ownerId = Utils.getObjectNode(responseBody).get("response").get(0).get("owner_id").asText();
        return "photo" + photoId + "_" + ownerId;
    }

}
