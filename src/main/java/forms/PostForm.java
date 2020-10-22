package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import models.Post;
import org.openqa.selenium.By;

import java.util.concurrent.TimeoutException;

public class PostForm extends Form {

    private static final String messageLocator = "//div[contains(@class, 'wall_post_cont')]";
    private static final String userLocator = "//*[@class='author']";
    private static final String messageTextLocatorFormat = messageLocator + "//*[text()='%s']";
    private final ILabel lblMessage;
    private final ILabel lblAuthor;

    public PostForm(String locator, String name) {
        super(By.xpath(locator), name);
        lblMessage = getElementFactory().getLabel(By.xpath(locator + messageLocator), "Message");
        lblAuthor = getElementFactory().getLabel(By.xpath(locator + userLocator), "User");
    }

    public static void waitForPost(String message) {
        try {
            AqualityServices.getConditionalWait().waitForTrue(() -> !AqualityServices.getBrowser().getDriver()
                    .findElementsByXPath(String.format(messageTextLocatorFormat, message)).isEmpty());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return lblMessage.getText();
    }

    public Post getPost() {
        Post post = new Post();
        post.setMessage(getMessage());
        post.setAuthorHref(lblAuthor.getAttribute("href"));
        return post;
    }
}
