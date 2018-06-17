package domain.user;

import org.jetbrains.annotations.NotNull;

public enum Role {
    DEVELOPER("Developer"),
    MANAGER("Manager"),
    TESTER("Tester");

    @NotNull
    private String label;

    Role(@NotNull String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
