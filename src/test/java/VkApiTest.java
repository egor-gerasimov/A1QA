import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import forms.NavigateBarForm;
import forms.PostForm;
import forms.WallForm;
import forms.WelcomePage;
import models.Post;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.ImageUtils;
import utils.SettingsData;
import utils.TestData;
import utils.VK;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class VkApiTest extends BaseTest {

    private final Browser browser = AqualityServices.getBrowser();
    private final String url = SettingsData.getStringValue("url");

    @BeforeMethod
    public void goToUrl() {
        browser.goTo(url);
        browser.waitForPageToLoad();
    }

    @Test
    public void vkApiTest() {
        AqualityServices.getLogger().info("Step 2");
        WelcomePage welcomePage = new WelcomePage();
        String email = TestData.getStringValue("email");
        String password = TestData.getStringValue("password");
        welcomePage.getLoginForm().login(email, password);

        AqualityServices.getLogger().info("Step 3");
        NavigateBarForm navigateBarForm = new NavigateBarForm();
        navigateBarForm.goToProfile();
        browser.waitForPageToLoad();

        AqualityServices.getLogger().info("Step 4");
        String message = RandomStringUtils.random(10, true, true);
        int postId = VK.wallPost(message);

        AqualityServices.getLogger().info("Step 5");
        PostForm.waitForPost(message);
        WallForm wallForm = new WallForm();
        List<Post> posts = wallForm.getPosts();
        Post post = posts.stream().filter(o -> o.getMessage().equals(message)).findFirst().orElse(null);
        Assert.assertNotNull(post, "No such post with message: " + message);
        Assert.assertEquals(post.getAuthorHrefPath(), TestData.getStringValue("user.href"), "Wrong post author");

        AqualityServices.getLogger().info("Step 6");
        File file = new File(TestData.getStringValue("photo.path"));
        String newMessage = RandomStringUtils.random(10, true, true);
        VK.wallEdit(postId, newMessage, file);

        AqualityServices.getLogger().info("Step 7");
        Post editedPost = wallForm.getPostWithPhoto(post.getId());
        BufferedImage fileImage = ImageUtils.getImage(file);
        BufferedImage photo = editedPost.getPhoto();
        Assert.assertEquals(editedPost.getMessage(), newMessage, "Wrong message");
        Assert.assertTrue(ImageUtils.isSimilarImages(fileImage, photo), "Wrong photo");

        AqualityServices.getLogger().info("Step 8");
        String comment = RandomStringUtils.random(10, true, true);
        VK.createReply(postId, comment);

        AqualityServices.getLogger().info("Step 9");
        PostForm postForm = new PostForm(editedPost.getId());
        postForm.showReplies();
        List<Post> replies = postForm.getReplies();
        Post reply = replies.stream().filter(o -> o.getMessage().equals(comment)).findFirst().orElse(null);
        Assert.assertNotNull(reply, "No such post with message: " + comment);
        Assert.assertEquals(reply.getAuthorHrefPath(), TestData.getStringValue("user.href"), "Wrong reply author");

        AqualityServices.getLogger().info("Step 10");
        postForm.clickLike();

        AqualityServices.getLogger().info("Step 11");
        boolean liked = VK.isLiked(postId);
        Assert.assertTrue(liked, "Post isn't liked");

        AqualityServices.getLogger().info("Step 12");
        VK.deletePost(postId);

        AqualityServices.getLogger().info("Step 13");
        PostForm.waitForDeletePost(editedPost.getId());
        Assert.assertFalse(wallForm.hasPost(editedPost.getId()), "Post didn't delete");
    }
}
