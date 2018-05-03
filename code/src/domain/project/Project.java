package domain.project;

import domain.user.User;

import java.util.List;


public class Project {
    private String name;
    private List<User> managersList;
    private List<User> developersList;
    private List<User> testersList;

    public Project(String name, List<User> managersList, List<User> developersList, List<User> testersList) {
        this.name = name;
        this.managersList = managersList;
        this.developersList = developersList;
        this.testersList = testersList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getManagersList() {
        return managersList;
    }

    public void setManagersList(List<User> managersList) {
        this.managersList = managersList;
    }

    public List<User> getDevelopersList() {
        return developersList;
    }

    public void setDevelopersList(List<User> developersList) {
        this.developersList = developersList;
    }

    public List<User> getTestersList() {
        return testersList;
    }

    public void setTestersList(List<User> testersList) {
        this.testersList = testersList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", managersList=" + managersList +
                ", developersList=" + developersList +
                ", testersList=" + testersList +
                '}';
    }
}
