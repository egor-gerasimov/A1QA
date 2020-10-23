import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import forms.NavigateBarForm;
import forms.PostForm;
import forms.WallForm;
import forms.WelcomePage;
import models.Post;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestData;
import utils.Utils;
import utils.VK;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class VkApiTest extends BaseTest {

    private final Browser browser = AqualityServices.getBrowser();
    private final String url = AqualityServices.get(ISettingsFile.class).getValue("/url").toString();

    @BeforeMethod
    public void goToUrl() {
        browser.goTo(url);
        browser.waitForPageToLoad();
    }

    @Test
    public void vkApiTest() {
        //2
        WelcomePage welcomePage = new WelcomePage();
        String email = TestData.getStringValue("email");
        String password = TestData.getStringValue("password");
        welcomePage.getLoginForm().login(email, password);
        //3
        NavigateBarForm navigateBarForm = new NavigateBarForm();
        navigateBarForm.goToProfile();
        browser.waitForPageToLoad();
        //4
        String message = RandomStringUtils.random(10, true, true);
        int postId = VK.wallPost(message);
        //5
        PostForm.waitForPost(message);
        WallForm wallForm = new WallForm();
        List<Post> posts = wallForm.getPosts();
        Post post = posts.stream().filter(o -> o.getMessage().equals(message)).findFirst().orElse(null);
        Assert.assertNotNull(post, "No such post with message: " + message);
        Assert.assertEquals(post.getAuthorHrefPath(), TestData.getStringValue("user.href"), "Wrong post author");
        //6
        File file = new File(TestData.getStringValue("photo.path"));
        String newMessage = RandomStringUtils.random(10, true, true);
        VK.wallEdit(postId, newMessage, file);
        //7
        Post editedPost = wallForm.getPostWithPhoto(post.getId());
        BufferedImage fileImage = Utils.getImage(file);
        BufferedImage photo = editedPost.getPhoto();
        Assert.assertEquals(editedPost.getMessage(), newMessage, "Wrong message");
        Assert.assertTrue(Utils.isSimilarImages(fileImage, photo), "Wrong photo");
        //8
        String comment = RandomStringUtils.random(10, true, true);
        VK.createReply(postId, comment);
        //9
        PostForm postForm = new PostForm(editedPost.getId());
        postForm.showReplies();
        List<Post> replies = postForm.getReplies();
        Post reply = replies.stream().filter(o -> o.getMessage().equals(comment)).findFirst().orElse(null);
        Assert.assertNotNull(reply, "No such post with message: " + comment);
        Assert.assertEquals(reply.getAuthorHrefPath(), TestData.getStringValue("user.href"), "Wrong post author");
        
    }
}
