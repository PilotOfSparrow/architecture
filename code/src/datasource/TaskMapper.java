package datasource;

import domain.project.*;
import domain.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

class TaskMapper extends GenericMapper<Task> {
    GenericMapper<User> userMapper = new UserMapper();
    GenericMapper<Project> projectMapper = new ProjectMapper();
    TimestampMapper timestampMapper = new TimestampMapper();

    @Override
    public void add(Task task) {
        String sql = "INSERT " +
                "INTO Task(name, description, developer_id, tester_id, type, status, project_id) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setInt(3, task.getDeveloper().getId());
            preparedStatement.setInt(4, task.getTester().getId());
            preparedStatement.setString(5, task.getTaskType().name());
            preparedStatement.setString(6, task.getTaskStatus().name());
            preparedStatement.setInt(7, task.getProject().getId());

            preparedStatement.executeUpdate();

            if (preparedStatement.getGeneratedKeys().next()) {
                int taskId = preparedStatement.getGeneratedKeys().getInt(1);
                for (Timestamp timestamp : task.getTimestamps()) {
                    timestamp.setTaskId(taskId);
                    timestampMapper.add(timestamp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Task task) {
        String sql = "UPDATE Task SET name = ?, " +
                "description = ?, " +
                "developer_id = ?, " +
                "tester_id = ?, " +
                "type = ?, " +
                "status = ?, " +
                "project_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setInt(3, task.getDeveloper().getId());
            preparedStatement.setInt(4, task.getTester().getId());
            preparedStatement.setString(5, task.getTaskType().name());
            preparedStatement.setString(6, task.getTaskStatus().name());
            preparedStatement.setInt(7, task.getProject().getId());

            preparedStatement.executeUpdate();

            for (Timestamp timestamp : task.getTimestamps()) {
                timestamp.setTaskId(task.getId());
                timestampMapper.add(timestamp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Nullable
    public Task get(int id) {
        Task task = null;

        String sql = "SELECT id, name, description, developer_id, tester_id, type, status, project_id FROM Task WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            task = resultToTask(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Timestamp> timestamps = timestampMapper.getTaskTimestamps(id);
        task.setTimestamps(timestamps);

        return task;
    }

    public void remove(Task task) {
        String sql = "DELETE FROM Task WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, task.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getAll() {
        String sql = "SELECT id FROM Timestamp";
        List<Integer> taskIdList = new LinkedList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                taskIdList.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Task> tasks = new LinkedList<>();
        for (Integer id : taskIdList) {
            tasks.add(get(id));
        }

        return tasks;
    }

    @NotNull
    public List<Task> getProjectTasks(int projectId) {
        String sql = "SELECT id FROM Task WHERE project_id = ?";
        List<Integer> taskIdList = new LinkedList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, projectId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                taskIdList.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Task> tasks = new LinkedList<>();
        for (Integer id : taskIdList) {
            tasks.add(get(id));
        }

        return tasks;
    }

    private Task resultToTask(ResultSet resultSet) throws SQLException {
        int taskId = resultSet.getInt("id");
        String taskName = resultSet.getString("name");
        String taskDescription = resultSet.getString("description");
        User developer = userMapper.get(resultSet.getInt("developer_id"));
        User tester = userMapper.get(resultSet.getInt("tester_id"));
        TaskType taskType = TaskType.valueOf(resultSet.getString("type"));
        TaskStatus taskStatus = TaskStatus.valueOf(resultSet.getString("status"));
        Project project = projectMapper.get(resultSet.getInt("project_id"));

        Task task = new Task(taskName, taskDescription, developer, tester, taskType, taskStatus, project);
        task.setId(taskId);

        return task;
    }
}
