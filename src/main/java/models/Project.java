package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project {
    private String name;
    private int testCount;

    public Project(String name) {
        this.name = name;
    }
}
