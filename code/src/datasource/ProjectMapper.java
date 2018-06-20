package datasource;

import domain.project.Project;
import domain.user.User;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

class ProjectMapper extends GenericMapper<Project> {
    @NotNull
    private GenericMapper<User> userMapper = new UserMapper();

    @Override
    public void add(Project project) {
        String projectSql = "INSERT INTO Project(name, manager_id) VALUES(?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(projectSql)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getManager().getId());

            preparedStatement.executeUpdate();

            if (preparedStatement.getGeneratedKeys().next()) {
                project.setId(preparedStatement.getGeneratedKeys().getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String developersSql = "INSERT INTO Developers_on_Project(developer_id, project_id) VALUES(?, ?)";
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(developersSql, Statement.RETURN_GENERATED_KEYS)) {
            for (User developer : project.getDevelopersList()) {
                preparedStatement.setInt(1, developer.getId());
                preparedStatement.setInt(2, project.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String testersSql = "INSERT INTO Testers_on_Project(tester_id, project_id) VALUES(?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(testersSql)) {
            for (User tester : project.getTestersList()) {
                preparedStatement.setInt(1, tester.getId());
                preparedStatement.setInt(2, project.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project get(int id) {
        String projectSql = "SELECT name, manager_id FROM Project WHERE id = ?";
        String projectName = "";
        Integer projectManagerId = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(projectSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            projectName = resultSet.getString("name");
            projectManagerId = resultSet.getInt("manager_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String developersSql = "SELECT developer_id FROM Developers_on_Project WHERE project_id = ?";
        List<Integer> developersIdList = new LinkedList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(developersSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                developersIdList.add(resultSet.getInt("developer_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String testersSql = "SELECT tester_id FROM Testers_on_Project WHERE project_id = ?";
        List<Integer> testersIdList = new LinkedList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(testersSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                testersIdList.add(resultSet.getInt("tester_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User manager = userMapper.get(projectManagerId);
        List<User> developers = new LinkedList<>();
        List<User> testers = new LinkedList<>();

        for (Integer developerId : developersIdList) {
            developers.add(userMapper.get(developerId));
        }

        for (Integer testerId : testersIdList) {
            testers.add(userMapper.get(testerId));
        }

        Project project = new Project(projectName, manager, developers, testers);
        project.setId(id);

        return project;
    }

    @Override
    public List<Project> getAll() {
        String sql = "SELECT id FROM Project";
        List<Integer> projectsIdList = new LinkedList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                projectsIdList.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Project> projects = new LinkedList<>();
        for (Integer id : projectsIdList) {
            projects.add(get(id));
        }

        return projects;
    }
}
