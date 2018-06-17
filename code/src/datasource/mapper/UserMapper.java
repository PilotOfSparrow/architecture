package datasource.mapper;

import domain.user.Role;
import domain.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserMapper extends GenericMapper<User> {
    @Nullable
    public User get(int id) {
        String sql = "SELECT id, login, name, surname, password, role FROM User WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultToUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Nullable
    public User get(String login) {
        String sql = "SELECT id, login, name, surname, password, role FROM User WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultToUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @NotNull
    public List<User> getAll() {
        String sql = "SELECT login, name, surname, password, role FROM User";
        List<User> list = new LinkedList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(resultToUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @NotNull
    public List<User> getAllWithRole(@NotNull Role role) {
        String sql = "SELECT id FROM User WHERE role = ?";
        List<Integer> ids = new LinkedList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, role.name());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<User> users = new LinkedList<>();
        for (Integer id : ids) {
            User user = get(id);
            if (user != null) users.add(user);
        }

        return users;
    }

    public void add(User user) {
        String sql = "INSERT INTO User(login, name, surname, password, role) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole().name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User resultToUser(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("id");
        String userLogin = resultSet.getString("login");
        String userName = resultSet.getString("name");
        String userSurname = resultSet.getString("surname");
        String userPassword = resultSet.getString("password");
        Role userRole = Role.valueOf(resultSet.getString("role"));

        User user = new User(userName, userSurname, userLogin, userPassword, userRole);
        user.setId(userId);

        return user;
    }
}
