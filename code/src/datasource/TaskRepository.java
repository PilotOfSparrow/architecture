package datasource;

import domain.project.Task;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaskRepository implements Repository<Task> {
    TaskMapper taskMapper = new TaskMapper();

    @Override
    public void add(Task item) {
        taskMapper.add(item);
    }

    @Override
    public void update(Task item) {
        taskMapper.update(item);
    }

    @Override
    public void remove(Task item) {
        taskMapper.remove(item);
    }

    @Override
    public Task get(int id) {
        return taskMapper.get(id);
    }

    @Override
    public List<Task> query() {
        return taskMapper.getAll();
    }

    @NotNull
    public List<Task> getProjectTasks(int projectId) {
        return taskMapper.getProjectTasks(projectId);
    }
}
