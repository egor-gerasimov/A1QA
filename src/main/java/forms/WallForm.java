package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import models.Post;
import org.openqa.selenium.By;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WallForm extends Form {

    private static final String locator = "//div[@id='page_wall_posts']";
    private static final String postsLocator = "//div[contains(@class, '_post post page_block')]";
    private static final String postLocatorFormat = postsLocator + "[%d]";
    private static final String postByIdLocatorFormat = "//div[@id='%s']";

    public WallForm() {
        super(By.xpath(locator), "Post wall");
    }

    public List<PostForm> getPostForms() {
        List<PostForm> postForms = new ArrayList<>();
        List<IElement> elements = getFormLabel().findChildElements(By.xpath(postsLocator), ElementType.LABEL);
        for (int i = 1; i <= elements.size(); i++) {
            postForms.add(new PostForm(By.xpath(String.format(postLocatorFormat, i)), "Post #" + i));
        }
        return postForms;
    }

    public List<Post> getPosts() {
        return getPostForms().stream().map(PostForm::getPost).collect(Collectors.toList());
    }

    public PostForm getPostForm(String postId) {
        String locator = String.format(postByIdLocatorFormat, postId);
        return new PostForm(By.xpath(locator), "Post with id " + postId);
    }

    public Post getPostWithPhoto(String postId) {
        PostForm postForm = getPostForm(postId);
        Image image = postForm.getImage();
        Post post = postForm.getPost();
        post.setPhoto(image);
        return post;
    }
}
