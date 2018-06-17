package domain.project;

import domain.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class Project {
    private int id;

    @NotNull
    private String name;

    @NotNull
    private User manager;

    @NotNull
    private List<User> developersList;

    @NotNull
    private List<User> testersList;

    public Project(
            @NotNull String name,
            @NotNull User manager,
            @NotNull List<User> developersList,
            @NotNull List<User> testersList) {
        this.name = name;
        this.manager = manager;
        this.developersList = developersList;
        this.testersList = testersList;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public User getManager() {
        return manager;
    }

    public void setManager(@NotNull User managersList) {
        this.manager = managersList;
    }

    @NotNull
    public List<User> getDevelopersList() {
        return developersList;
    }

    public void setDevelopersList(@NotNull List<User> developersList) {
        this.developersList = developersList;
    }

    @NotNull
    public List<User> getTestersList() {
        return testersList;
    }

    public void setTestersList(@NotNull List<User> testersList) {
        this.testersList = testersList;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
