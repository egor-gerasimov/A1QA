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
import utils.VK;
import utils.VkApiUtils;

import java.net.URI;
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
        WelcomePage welcomePage = new WelcomePage();
        String email = TestData.getStringValue("email");
        String password = TestData.getStringValue("password");
        welcomePage.getLoginForm().login(email, password);
        NavigateBarForm navigateBarForm = new NavigateBarForm();
        navigateBarForm.goToProfile();
        browser.waitForPageToLoad();
        String message = RandomStringUtils.random(10, true, true);
        int postId = VK.wallPost(message);
        PostForm.waitForPost(message);
        WallForm wallForm = new WallForm();
        List<Post> posts = wallForm.getPosts();
        models.Post post = posts.stream().filter(o -> o.getMessage().equals(message)).findFirst().orElse(null);
        Assert.assertNotNull(post, "No such post with message: " + message);
        String href = URI.create(post.getAuthorHref()).getPath();
        Assert.assertEquals(href, TestData.getStringValue("user.href"), "Wrong post author");
        VkApiUtils.sendPhoto(postId);
    }
}
