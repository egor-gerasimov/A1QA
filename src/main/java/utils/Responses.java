package utils;

import models.Post;
import response.PostResponse;
import response.PostsResponse;
import response.UserResponse;
import response.UsersResponse;

import static utils.APIUtils.*;

public class Responses {

    private static final String postsPath = "/posts";
    private static final String usersPath = "/users";

    public static PostsResponse getPosts() {
        return new PostsResponse(getResponse(getGetRequest(postsPath)));
    }

    public static UsersResponse getUsers() {
        return new UsersResponse(getResponse(getGetRequest(usersPath)));
    }

    public static PostResponse getPost(long id) {
        return new PostResponse(getResponse(getGetRequest(postsPath + "/" + id)));
    }

    public static UserResponse getUser(long id) {
        return new UserResponse(getResponse(getGetRequest(usersPath + "/" + id)));
    }

    public static PostResponse put(Post post) {
        return new PostResponse(getResponse(getPostRequest(postsPath, Utils.toJSONString(post))));
    }

}
