package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import models.Post;
import org.openqa.selenium.By;
import utils.Utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class PostForm extends Form {

    private static final String messageLoc = "//div[contains(@class, 'wall_post_text')]";
    private static final String userLoc = "//*[@class='author']";
    private static final String messageTextLocFormat = messageLoc + "//*[text()='%s']";
    private static final String idAttribute = "id";
    private static final String photoLoc = "//a[contains(@class, 'page_post_thumb_wrap')]";
    private static final String showRepliesLoc = "//a[contains(@class, 'replies_next  replies_next_main')]";
    private static final String repliesLoc = "//div[contains(@class, 'replies_list _replies_list')]";
    private static final String repliesLocFormat = repliesLoc + "[%d]";

    protected final ILabel lblMessage = getFormLabel().findChildElement(By.xpath(messageLoc), "Message", ElementType.LABEL);
    protected final ILabel lblAuthor = getFormLabel().findChildElement(By.xpath(userLoc), "User", ElementType.LABEL);
    private final ILink lnkPhoto = getFormLabel().findChildElement(By.xpath(photoLoc), "Photo", ElementType.LINK);
    private final IButton btnShowReplies = getFormLabel().findChildElement(By.xpath(showRepliesLoc), "Show comments", ElementType.BUTTON);
    private final IButton btnLike = getFormLabel().findChildElement(By.className("like_button_icon"), "Like", ElementType.BUTTON);

    public PostForm(String postId) {
        super(By.id(postId), "Post with id " + postId);
    }

    public static void waitForPost(String message) {
        try {
            AqualityServices.getConditionalWait().waitForTrue(() -> !AqualityServices.getBrowser().getDriver()
                    .findElementsByXPath(String.format(messageTextLocFormat, message)).isEmpty());
        } catch (TimeoutException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static void waitForDeletePost(String id) {
        try {
            AqualityServices.getConditionalWait().waitForTrue(() -> AqualityServices.getBrowser().getDriver()
                    .findElementsById(id).isEmpty());
        } catch (TimeoutException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public Post getPost() {
        Post post = new Post();
        post.setMessage(lblMessage.getText());
        post.setAuthorHref(lblAuthor.getAttribute("href"));
        post.setId(getFormLabel().getAttribute(idAttribute));
        return post;
    }

    public BufferedImage getImage() {
        BufferedImage image = null;
        if (lnkPhoto.state().isExist()) {
            lnkPhoto.click();
            PhotoForm photoForm = new PhotoForm();
            String photoURL = photoForm.getPhotoURL();
            photoForm.close();
            image = Utils.getImage(photoURL);
        }
        return image;
    }

    public void showReplies() {
        if (btnShowReplies.state().isExist()) {
            btnShowReplies.click();
        }
    }

    public List<ReplyForm> getReplyForms() {
        List<ReplyForm> postForms = new ArrayList<>();
        List<IElement> elements = getFormLabel().findChildElements(By.xpath(repliesLoc), ElementType.LABEL);
        for (int i = 1; i <= elements.size(); i++) {
            postForms.add(new ReplyForm(By.xpath(String.format(repliesLocFormat, i)), "Post #" + i));
        }
        return postForms;
    }

    public List<Post> getReplies() {
        return getReplyForms().stream().map(ReplyForm::getReply).collect(Collectors.toList());
    }

    public void clickLike() {
        btnLike.click();
    }
}
