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

    public static void wallEdit(int postId, String message, File image) {
        String photoName = saveWallPhoto(image);
        Map<String, String> params = getCommonParams();
        params.put("post_id", String.valueOf(postId));
        params.put("message", message);
        params.put("attachments", photoName);
        execute(VkMethods.WALL_EDIT, params);
    }

    public static void createReply(int postId, String message) {
        Map<String, String> params = getCommonParams();
        params.put("post_id", String.valueOf(postId));
        params.put("message", message);
        execute(VkMethods.WALL_CREATE_COMMENT, params);
    }

    public static boolean isLiked(int postId) {
        Map<String, String> params = getCommonParams();
        params.put("type", "post");
        params.put("item_id", String.valueOf(postId));
        String responseBody = execute(VkMethods.LIKES_IS_LIKED, params);
        return Utils.getObjectNode(responseBody).get("response").get("liked").asInt() == 1;
    }

    public static void deletePost(int postId) {
        Map<String, String> params = getCommonParams();
        params.put("post_id", String.valueOf(postId));
        execute(VkMethods.WALL_DELETE, params);
    }
}
