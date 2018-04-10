package domain.user;

abstract class User {
    private String name;
    private String login;

    User(String name, String login) {
        this.name  = name;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
