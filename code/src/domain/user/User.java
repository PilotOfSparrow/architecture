package domain.user;

public abstract class User {
    private String name;
    private String surname;
    private String login;

    User(String name, String surname, String login) {
        this.name  = name;
        this.surname = surname;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
