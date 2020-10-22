package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

    private int postId;
    private String message;
    private String authorHref;
}
