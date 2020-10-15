package response;

import models.Post;
import utils.Utils;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class PostsResponse extends Response<List<Post>> {

    public PostsResponse(HttpResponse<String> response) {
        super(response);
    }

    @Override
    protected List<Post> getInstance() {
        return Arrays.asList(Utils.readObjectFromJSON(getBody(), Post[].class));
    }

}
