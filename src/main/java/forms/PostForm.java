package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import models.Post;
import org.openqa.selenium.By;
import utils.Utils;

import java.awt.*;
import java.util.concurrent.TimeoutException;

public class PostForm extends Form {

    private static final String messageLoc = "//div[contains(@class, 'wall_post_cont')]";
    private static final String userLoc = "//*[@class='author']";
    private static final String messageTextLocFormat = messageLoc + "//*[text()='%s']";
    private static final String idAttribute = "id";
    private static final String photoLoc = "//a[contains(@class, 'page_post_thumb_wrap']";
    private final ILabel lblMessage = getFormLabel().findChildElement(By.xpath(messageLoc), "Message", ElementType.LABEL);
    private final ILabel lblAuthor = getFormLabel().findChildElement(By.xpath(userLoc), "User", ElementType.LABEL);
    private final IButton btnPhoto = getFormLabel().findChildElement(By.xpath(photoLoc), "Photo", ElementType.BUTTON);

    public PostForm(By locator, String name) {
        super(locator, name);
    }

    public static void waitForPost(String message) {
        try {
            AqualityServices.getConditionalWait().waitForTrue(() -> !AqualityServices.getBrowser().getDriver()
                    .findElementsByXPath(String.format(messageTextLocFormat, message)).isEmpty());
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
        post.setId(getFormLabel().getAttribute(idAttribute));
        return post;
    }

    public Image getImage() {
        btnPhoto.click();
        String photoURL = new PhotoForm().getPhotoURL();
        return Utils.getImage(photoURL);
    }
}
