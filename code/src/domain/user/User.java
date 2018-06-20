package domain.user;

import org.jetbrains.annotations.NotNull;

public class User {
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private Role role;

    public User(
            @NotNull String login,
            @NotNull String name,
            @NotNull String surname,
            @NotNull String password,
            @NotNull Role role) {
        this.name  = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    public void setSurname(@NotNull String surname) {
        this.surname = surname;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getSurname() {
        return surname;
    }

    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    @NotNull
    public Role getRole() {
        return role;
    }

    public void setRole(@NotNull Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
