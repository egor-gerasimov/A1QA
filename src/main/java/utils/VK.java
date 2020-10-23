package utils;

import constants.VkMethods;

import java.io.File;
import java.util.Map;

import static utils.VkApiUtils.*;

public class VK {

    public static int wallPost(String message) {
        Map<String, String> params = getCommonParams();
        params.put("message", message);
        String body = execute(VkMethods.WALL_POST, params);
        return Utils.getObjectNode(body).get("response").get("post_id").asInt();
    }

    public static int wallEdit(int postId, String message, File image) {
        String photoName = getWallPhotoName(image);
        Map<String, String> params = getCommonParams();
        params.put("post_id", String.valueOf(postId));
        params.put("message", message);
        params.put("attachments", photoName);
        String responseBody = execute(VkMethods.WALL_EDIT, params);
        return Utils.getObjectNode(responseBody).get("response").asInt();
    }
}
