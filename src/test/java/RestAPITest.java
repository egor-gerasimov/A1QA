import models.Post;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.APIUtils;
import utils.TestData;
import utils.Utils;

import java.net.http.HttpResponse;
import java.util.List;

public class RestAPITest extends BaseTest {

    @Test
    public void getAPI() {
        //step1
        HttpResponse<String> response = APIUtils.getPosts();
        Assert.assertEquals(response.statusCode(), 200, "Wrong status code");
        Assert.assertTrue(Utils.isJson(response.body()), "Response isn't JSON");
        List<Post> posts = APIUtils.readPosts(response);
        Assert.assertTrue(Utils.isSortedByID(posts), "Not sorted list");
        //step2
        long postId = TestData.getLongValue("post.id.step.2");
        long postUserId = TestData.getLongValue("post.userid.step.2");
        response = APIUtils.getPost(postId);
        Assert.assertEquals(response.statusCode(), 200, "Wrong status code");
        Post post = APIUtils.readObject(response, Post.class);
        Assert.assertEquals(post.getUserId(), postUserId, "Wrong user id");
        Assert.assertEquals(post.getId(), postId, "Wrong id");
        Assert.assertFalse(post.getTitle().isEmpty(), "Title is empty");
        Assert.assertFalse(post.getBody().isEmpty(), "Body is empty");
        //step3
        postId =  TestData.getLongValue("post.id.step.3");
        response = APIUtils.getPost(postId);
        Assert.assertEquals(response.statusCode(), 404, "Wrong status code");
        Assert.assertEquals(response.body(), "{}", "Wrong response");
        //step4
        postUserId = TestData.getLongValue("post.userid.step.4");
        String randomTitle = RandomStringUtils.random(10, true, true);
        String randomBody = RandomStringUtils.random(10, true, true);
        Post newPost = new Post();
        newPost.setUserId(postUserId);
        newPost.setTitle(randomTitle);
        newPost.setBody(randomBody);
        response = APIUtils.put(newPost);
        Assert.assertEquals(response.statusCode(), 201, "Wrong status code");
        post = APIUtils.readObject(response, Post.class);
        Assert.assertEquals(newPost, post, "Wrong new post");
        Assert.assertNotEquals(post.getId(), 0, "Not valid post id");
        //step5
        response = APIUtils.getUsers();
        Assert.assertEquals(response.statusCode(), 200, "Wrong status code");
        Assert.assertTrue(Utils.isJson(response.body()), "Response isn't JSON");
        List<User> users = APIUtils.readUsers(response);
        User testUser = TestData.getUserStep5();
        User user = users.stream().filter((o) -> o.getId() == testUser.getId()).findAny().orElseThrow();
        Assert.assertEquals(user, testUser, "Wrong data user " + user.getId());
        //step6
        response = APIUtils.getUser(testUser.getId());
        User newUser = APIUtils.readObject(response, User.class);
        Assert.assertEquals(newUser, user, "Wrong data user " + user.getId());
    }
}
