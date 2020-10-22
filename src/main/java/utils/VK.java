package utils;

import java.io.File;
import java.net.http.HttpResponse;
import java.util.Map;

import static utils.VkApiUtils.getCommonParams;
import static utils.VkApiUtils.getPostRequest;

public class VK {

    private static final String methodWallPost = "wall.post";
    private static final String messageParam = "message";
    private static final String postIdParam = "post_id";

    public static int wallPost(String message) {
        Map<String, String> params = getCommonParams();
        params.put(messageParam, message);
        HttpResponse<String> response = APIUtils.getResponse(getPostRequest(methodWallPost, params));
        return Utils.getObjectNode(response.body()).get("response").get("post_id").asInt();
    }

    public static void editPost(int postId, String message, File image) {
        Map<String, String> params = getCommonParams();
        params.put(messageParam, message);
        params.put(postIdParam, String.valueOf(postId));
    }
}
