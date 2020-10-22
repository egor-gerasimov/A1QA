package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.forms.Form;
import models.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WallForm extends Form {

    private static final String locator = "//div[@id='page_wall_posts']";
    private static final String postsLocator = locator + "//div[contains(@class, '_post post page_block')]";
    private static final String postLocatorFormat = postsLocator + "[%d]";

    public WallForm() {
        super(By.xpath(locator), "Post wall");
    }

    public List<PostForm> getPostForms() {
        List<PostForm> postForms = new ArrayList<>();
        List<WebElement> elements = AqualityServices.getBrowser().getDriver().findElements(By.xpath(postsLocator));
        for (int i = 0; i < elements.size(); i++) {
            postForms.add(new PostForm(String.format(postLocatorFormat, i + 1), "Post #" + (i + 1)));
        }
        return postForms;
    }

    public List<Post> getPosts() {
        return getPostForms().stream().map(PostForm::getPost).collect(Collectors.toList());
    }
}
