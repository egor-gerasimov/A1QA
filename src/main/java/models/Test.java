package models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
public class Test {
    private String name;
    private Project project;
    private int minTime;
    private Timestamp startTime;
}
