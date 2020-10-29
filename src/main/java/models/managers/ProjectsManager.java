package models.managers;

import models.Project;

import java.util.HashSet;
import java.util.Set;

public class ProjectsManager {

    private final Set<Project> projects = new HashSet<>();

    public Project getProject(String name) {
        Project project;
        if (projects.stream().noneMatch(o -> o.getName().equals(name))) {
            project = new Project(name);
            projects.add(project);
        }
        else {
            project = projects.stream().filter(o -> o.getName().equals(name)).findAny().orElse(null);
        }
        return project;
    }
}
