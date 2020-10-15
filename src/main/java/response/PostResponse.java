package response;

import lombok.Getter;
import models.Post;
import utils.Utils;

import java.net.http.HttpResponse;

@Getter
public class PostResponse extends Response<Post> {
    public PostResponse(HttpResponse<String> response) {
        super(response);
    }

    @Override
    protected Post getInstance() {
        return Utils.readObjectFromJSON(getBody(), Post.class);
    }
}
