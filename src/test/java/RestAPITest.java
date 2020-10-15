import models.Post;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.PostResponse;
import response.PostsResponse;
import response.UserResponse;
import response.UsersResponse;
import utils.*;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class RestAPITest extends BaseTest {

    @Test
    public void getAPI() {
        //step1
        PostsResponse postsResponse = Responses.getPosts();
        Assert.assertEquals(postsResponse.getStatusCode(), HttpStatus.OK, "Wrong status code");
        Assert.assertTrue(postsResponse.isJSON(), "Response isn't JSON");
        List<Post> posts = postsResponse.getObject();
        Assert.assertTrue(Utils.isSortedByID(posts), "Not sorted list");
        //step2
        long postId = TestData.getLongValue("post.id.step.2");
        long postUserId = TestData.getLongValue("post.userid.step.2");
        PostResponse postResponse = Responses.getPost(postId);
        Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.OK, "Wrong status code");
        Post post = postResponse.getObject();
        Assert.assertEquals(post.getUserId(), postUserId, "Wrong user id");
        Assert.assertEquals(post.getId(), postId, "Wrong id");
        Assert.assertFalse(post.getTitle().isEmpty(), "Title is empty");
        Assert.assertFalse(post.getBody().isEmpty(), "Body is empty");
        //step3
        postId =  TestData.getLongValue("post.id.step.3");
        postResponse = Responses.getPost(postId);
        Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.NOT_FOUND, "Wrong status code");
        Assert.assertEquals(postResponse.getBody(), "{}", "Wrong response");
        //step4
        postUserId = TestData.getLongValue("post.userid.step.4");
        String randomTitle = RandomStringUtils.random(10, true, true);
        String randomBody = RandomStringUtils.random(10, true, true);
        Post newPost = new Post();
        newPost.setUserId(postUserId);
        newPost.setTitle(randomTitle);
        newPost.setBody(randomBody);
        postResponse = Responses.put(newPost);
        Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.CREATED, "Wrong status code");
        post = postResponse.getObject();
        Assert.assertEquals(newPost, post, "Wrong new post");
        Assert.assertNotEquals(post.getId(), 0, "Not valid post id");
        //step5
        UsersResponse usersResponse = Responses.getUsers();
        Assert.assertEquals(usersResponse.getStatusCode(), HttpStatus.OK, "Wrong status code");
        Assert.assertTrue(usersResponse.isJSON(), "Response isn't JSON");
        List<User> users = usersResponse.getObject();
        User testUser = TestData.getUserStep5();
        User user = users.stream().filter((o) -> o.getId() == testUser.getId()).findAny().orElseThrow();
        Assert.assertEquals(user, testUser, "Wrong data user " + user.getId());
        //step6
        UserResponse userResponse = Responses.getUser(testUser.getId());
        User newUser = userResponse.getObject();
        Assert.assertEquals(newUser, user, "Wrong data user " + user.getId());
    }
}
