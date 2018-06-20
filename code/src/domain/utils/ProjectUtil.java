package domain.utils;

import domain.project.Project;
import org.jetbrains.annotations.NotNull;

public enum ProjectUtil {
    INSTANCE;

    private Project currentProject;

    @NotNull
    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(@NotNull Project project) {
        this.currentProject = project;
    }
}
