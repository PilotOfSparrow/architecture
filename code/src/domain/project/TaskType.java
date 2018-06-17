package domain.project;

import org.jetbrains.annotations.NotNull;

public enum TaskType {
    NEW_FEATURE("New feature"),
    BUG("Bug");

    @NotNull
    private String label;


    TaskType(@NotNull String type_label) {
        this.label = type_label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
