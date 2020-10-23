package models;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Post {

    private String id;
    private String message;
    private String authorHref;
    private Image photo;
}
