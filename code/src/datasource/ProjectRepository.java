package datasource;

import domain.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProjectRepository implements Repository<Project> {
    @NotNull ProjectMapper projectMapper = new ProjectMapper();

    @Override
    public void add(Project item) {
        projectMapper.add(item);
    }

    @Override
    public void update(Project item) {
        projectMapper.add(item);
    }

    @Override
    public void remove(Project item) {

    }

    @Override
    public Project get(int id) {
        return projectMapper.get(id);
    }

    @Override
    public List<Project> query() {
        return projectMapper.getAll();
    }
}
