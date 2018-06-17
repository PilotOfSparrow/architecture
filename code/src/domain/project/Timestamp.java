package domain.project;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;

public class Timestamp {
    @NotNull
    private final String time;

    @NotNull
    private final TaskStatus status;

    private int taskId;

    public Timestamp(@NotNull String time, @NotNull TaskStatus status) {
        this.time = time;
        this.status = status;
    }

    @NotNull
    public String getTime() {
        return this.time;
    }

    @NotNull
    public TaskStatus getStatus() {
        return this.status;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return "Status changed to " + status.toString() + "\n" + Instant.ofEpochSecond(Long.valueOf(time)).toString();
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
