package domain.model;

import datasource.TimestampRepository;
import domain.project.TaskStatus;
import domain.project.Timestamp;
import org.jetbrains.annotations.NotNull;

public class TimestampModel {
    TimestampRepository timestampRepository = new TimestampRepository();

    public void addTimestamp(@NotNull String time, @NotNull TaskStatus status, int taskId) {
        timestampRepository.add(new Timestamp(time, status));
    }

}
