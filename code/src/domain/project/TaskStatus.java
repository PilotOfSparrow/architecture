package domain.project;

import org.jetbrains.annotations.NotNull;

public enum TaskStatus {
    OPEN("Open"),
    IN_PROGRESS("In progress"),
    ON_CHECK("On check"),
    CHECKED("Checked"),
    CLOSED("Closed");

    @NotNull
    private String label;

    TaskStatus(@NotNull String typeLabel) {
        this.label = typeLabel;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
