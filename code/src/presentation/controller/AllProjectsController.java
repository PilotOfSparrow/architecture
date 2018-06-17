package presentation.controller;

import domain.model.ProjectModel;
import domain.project.Project;
import domain.user.Role;
import domain.utils.UserUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.jetbrains.annotations.NotNull;
import presentation.UiUtils;

public class AllProjectsController {
    @NotNull ProjectModel projectModel = new ProjectModel();

    @FXML
    public ListView projectsListView;

    @FXML
    public Button newProjectButton; // only for managers

    @FXML
    public void initialize() {
        initButtons();
        initList();
    }

    private void initButtons() {
        assert newProjectButton != null;

        if (UserUtils.INSTANCE.getCurrentUser().getRole() != Role.MANAGER) {
            newProjectButton.setDisable(true);
            return;
        }
        newProjectButton.setOnMouseClicked(event ->
            UiUtils.getInstance().open(event, "view/new_project_creation.fxml", "Create new project")
        );
    }

    private void initList() {
        projectsListView.getItems().setAll(projectModel.getAll());
        projectsListView.setOnMouseClicked(event -> {
            Project project = (Project) projectsListView.getSelectionModel().getSelectedItem();
            if (project == null) return;

            projectModel.setCurrentProject(project);

            UiUtils.getInstance().open(event, "view/project_tasks.fxml", project.getName() + " tasks");
        });
    }
}
