package domain.user;

abstract class User {
    private String name;
    private String login;

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
