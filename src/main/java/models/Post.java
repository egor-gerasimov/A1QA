package models;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.net.URI;

@Getter
@Setter
public class Post {

    private String id;
    private String message;
    private String authorHref;
    private BufferedImage photo;

    public String getAuthorHrefPath() {
        return URI.create(getAuthorHref()).getPath();
    }
}
