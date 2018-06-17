package domain.model;

import datasource.UserRepository;
import domain.user.Role;
import domain.user.User;
import domain.utils.UserUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UserModel {
    @NotNull
    private UserRepository userRepository = new UserRepository();

    public void addUser(
            @NotNull String login,
            @NotNull String name,
            @NotNull String surname,
            @NotNull String password,
            @NotNull Role role) {
        userRepository.add(new User(login, name, surname, password, role));
    }

    @Nullable
    public User getUser(int id) {
        return userRepository.get(id);
    }

    @Nullable
    public User getUser(@NotNull String login) {
        return userRepository.get(login);
    }

    @Nullable
    public List<User> getAllWithRole(@NotNull Role role) {
        return userRepository.getAllWithRole(role);
    }

    public boolean authenticate(@NotNull String login, @NotNull String password) {
        User user = getUser(login);

        boolean isAuthentic = user != null && password.equals(user.getPassword());

        if (isAuthentic) UserUtils.INSTANCE.setCurrentUser(user);

        return isAuthentic;
    }
}
