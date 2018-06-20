package datasource;

import domain.project.Timestamp;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TimestampRepository implements Repository<Timestamp> {
    @NotNull TimestampMapper timestampMapper = new TimestampMapper();

    @Override
    public void add(Timestamp item) {
        timestampMapper.add(item);
    }

    @Override
    public void update(Timestamp item) {
        timestampMapper.add(item);
    }

    @Override
    public void remove(Timestamp item) {

    }

    @Override
    public Timestamp get(int id) {
        return timestampMapper.get(id);
    }

    @Override
    public List<Timestamp> query() {
        return timestampMapper.getAll();
    }

    public List<Timestamp> getTaskTimestamps(int taskId) {
        return timestampMapper.getTaskTimestamps(taskId);
    }
}
