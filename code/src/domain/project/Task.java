package domain.project;

import domain.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Task {
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private User developer;

    @NotNull
    private User tester;

    @NotNull
    private TaskType taskType;

    @NotNull
    private TaskStatus taskStatus;

    @NotNull
    private Project project;

    @NotNull
    private List<Timestamp> timestamps = new LinkedList<>();

    public Task(
            @NotNull String name,
            @NotNull String description,
            @NotNull User developer,
            @NotNull User tester,
            @NotNull TaskType taskType,
            @NotNull TaskStatus taskStatus,
            @NotNull Project project) {
        this.name = name;
        this.description = description;
        this.developer = developer;
        this.tester = tester;
        this.taskType = taskType;
        this.taskStatus = taskStatus;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    @NotNull
    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(@NotNull User developer) {
        this.developer = developer;
    }

    @NotNull
    public User getTester() {
        return tester;
    }

    public void setTester(@NotNull User tester) {
        this.tester = tester;
    }

    @NotNull
    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(@NotNull TaskType taskType) {
        this.taskType = taskType;
    }

    @NotNull
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(@NotNull TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @NotNull
    public List<Timestamp> getTimestamps() {
        return timestamps;
    }

    public void addTimestamp(@NotNull Timestamp timestamp) {
        timestamps.add(timestamp);
    }

    public void setTimestamps(@NotNull List<Timestamp> timestamps) {
        this.timestamps = timestamps;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<String> toStringList() {
        List<String> taskFields = new LinkedList<>();
        taskFields.add("Name:\n" + name);
        taskFields.add("Description:\n" + description);
        taskFields.add("Developer:\n" + developer.getName());
        taskFields.add("Tester:\n" + tester.getName());
        taskFields.add("Type:\n" + taskType);
        taskFields.add("Status:\n" + taskStatus);
        taskFields.add("Timestamps:\n" +
                timestamps.stream()
                        .sorted(Comparator.comparing(Timestamp::getTime))
                        .map(Object::toString)
                        .collect(Collectors.joining("\n")));

        return taskFields;
    }

    @Override
    public String toString() {
        return name;
    }
}
