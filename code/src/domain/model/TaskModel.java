package domain.model;

import datasource.TaskRepository;
import datasource.TimestampRepository;
import domain.project.*;
import domain.user.User;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.List;

public class TaskModel {
    @NotNull TaskRepository taskRepository = new TaskRepository();
    @NotNull TimestampRepository timestampRepository = new TimestampRepository();

    public void addTask(@NotNull String name,
                        @NotNull String description,
                        @NotNull User developer,
                        @NotNull User tester,
                        @NotNull TaskType taskType,
                        @NotNull TaskStatus taskStatus,
                        @NotNull Project project) {
        Task task = new Task(name, description, developer, tester, taskType, taskStatus, project);

        markTime(task, task.getTaskStatus());

        taskRepository.add(task);
    }

    public void removeTask(@NotNull Task task) {
        taskRepository.remove(task);
    }

    public void changeTaskStatus(@NotNull Task task, @NotNull TaskStatus status) {
        task.setTaskStatus(status);

        markTime(task, status);

        taskRepository.update(task);
    }

    @NotNull
    public List<Task> getProjectTasks(int projectId) {
        return taskRepository.getProjectTasks(projectId);
    }

    private void markTime(@NotNull Task task, @NotNull TaskStatus status) {
        String time = String.valueOf(Instant.now().getEpochSecond());
        Timestamp timestamp = new Timestamp(time, status);
        task.addTimestamp(timestamp);
    }
}
