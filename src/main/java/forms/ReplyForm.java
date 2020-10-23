package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import models.Post;
import org.openqa.selenium.By;

public class ReplyForm extends Form {
    private static final String messageLoc = "//div[contains(@class, 'wall_reply_text')]";
    private static final String userLoc = "//*[@class='author']";
//    private static final String messageTextLocFormat = messageLoc + "//*[text()='%s']";
    private static final String idAttribute = "id";
    private static final String postByIdLocFormat = "//div[@id='%s']";
    private final ILabel lblMessage = getFormLabel().findChildElement(By.xpath(messageLoc), "Message", ElementType.LABEL);
    private final ILabel lblAuthor = getFormLabel().findChildElement(By.xpath(userLoc), "User", ElementType.LABEL);

    public ReplyForm(By locator, String name) {
        super(locator, name);
    }

    public ReplyForm(String postId) {
        super(By.xpath(String.format(postByIdLocFormat, postId)), "Post with id " + postId);
    }

    public String getMessage() {
        return lblMessage.getText();
    }

    public Post getReply() {
        Post reply = new Post();
        reply.setMessage(getMessage());
        reply.setAuthorHref(lblAuthor.getAttribute("href"));
        reply.setId(getFormLabel().getAttribute(idAttribute));
        return reply;
    }
} 