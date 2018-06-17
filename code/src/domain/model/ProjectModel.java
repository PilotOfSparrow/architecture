package domain.model;

import datasource.ProjectRepository;
import domain.project.Project;
import domain.user.User;
import domain.utils.ProjectUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProjectModel {
    @NotNull ProjectRepository projectRepository = new ProjectRepository();

    public void addProject(
            @NotNull String name,
            @NotNull User manager,
            @NotNull List<User> developersList,
            @NotNull List<User> testersList) {
        Project newProject = new Project(name, manager, developersList, testersList);

        projectRepository.add(newProject);

        setCurrentProject(newProject);
    }

    public List<Project> getAll() {
        return projectRepository.query();
    }

    public void setCurrentProject(@NotNull Project project) {
        ProjectUtil.INSTANCE.setCurrentProject(project);
    }

}
