package domain.utils;

import domain.user.User;
import org.jetbrains.annotations.NotNull;

public enum UserUtils {
    INSTANCE;

    private User currentUser;

    @NotNull
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(@NotNull User currentUser) {
        this.currentUser = currentUser;
    }

}
