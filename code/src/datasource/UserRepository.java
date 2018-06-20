package datasource;

import domain.user.Role;
import domain.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UserRepository implements Repository<User> {
    @NotNull
    UserMapper userMapper = new UserMapper();

    @Override
    public void add(User item) {
        userMapper.add(item);
    }

    @Override
    public void update(User item) {
        userMapper.add(item);
    }

    @Override
    public void remove(User item) {

    }

    @Nullable
    @Override
    public User get(int id) {
        return userMapper.get(id);
    }

    @Nullable
    public User get(String login) {
        return userMapper.get(login);
    }

    @Nullable
    @Override
    public List<User> query() {
        return userMapper.getAll();
    }

    @Nullable
    public List<User> getAllWithRole(@NotNull Role role) {
        return userMapper.getAllWithRole(role);
    }
}
