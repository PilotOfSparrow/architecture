package datasource.mapper;

import domain.project.TaskStatus;
import domain.project.Timestamp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TimestampMapper extends GenericMapper<Timestamp> {

    @Override
    public void add(Timestamp timestamp) {
        String sql = "INSERT OR IGNORE INTO Timestamp(time, status, task_id) VALUES(?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, timestamp.getTime());
            preparedStatement.setString(2, timestamp.getStatus().name());
            preparedStatement.setInt(3, timestamp.getTaskId());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Timestamp get(int id) {
        String sql = "SELECT time, status, task_id FROM Timestamp WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return new Timestamp(
                    resultSet.getString("time"),
                    TaskStatus.valueOf(resultSet.getString("status"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Timestamp> getAll() {
        String sql = "SELECT id FROM Timestamp";
        List<Integer> timestampIdList = new LinkedList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                timestampIdList.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Timestamp> timestamps = new LinkedList<>();
        for (Integer id : timestampIdList) {
            timestamps.add(get(id));
        }

        return null;
    }

    public List<Timestamp> getTaskTimestamps(int taskId) {
        String sql = "SELECT id FROM Timestamp WHERE task_id = ?";
        List<Integer> timestampIdList = new LinkedList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, taskId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                timestampIdList.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Timestamp> timestamps = new LinkedList<>();
        for (Integer id : timestampIdList) {
            timestamps.add(get(id));
        }

        return timestamps;
    }
}
