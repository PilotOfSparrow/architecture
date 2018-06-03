package presentation.controller;

import datasource.ProjectRepository;
import domain.project.Project;
import domain.user.User;
import domain.user.UserUtility;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.CheckListView;

import java.util.ArrayList;
import java.util.List;

public class NewProjectCreationController {
    @FXML
    public TextField projectNameTextField;

    @FXML
    public Button projectCreateButton;

    @FXML
    public CheckListView<User> developersCheckListView;

    @FXML
    public CheckListView<User> testersCheckListView;

    @FXML
    private void initialize() {

    }

    @FXML
    private void createNewProject() {
        String projectName = projectNameTextField.getText();
        if (projectName == null || projectName.equals("")) {
            createWarning("No project name!", "Specify name for project");
            return;
        }

        List<User> developersList = developersCheckListView.getItems();
        if (developersList == null || developersList.isEmpty()) {
            createWarning("No developers specified!", "Specify at least one dev on project");
            return;
        }

        List<User> testersList = testersCheckListView.getItems();
        if (testersList == null || testersList.isEmpty()) {
            createWarning("No testers specified!", "Specify at least one tester on project");
            return;
        }

        List<User> managersList = new ArrayList<>(1);
        managersList.add(UserUtility.INSTANCE.getCurrentUser());

        Project project = new Project(projectName, managersList, developersList, testersList);

        final ProjectRepository projectRepository = new ProjectRepository();
        projectRepository.add(project);
    }

    private void createWarning(String title, String text) {
        ButtonType buttonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(buttonType);
        dialog.getDialogPane().lookupButton(buttonType).setDisable(false);
        dialog.setTitle(title);
        dialog.setContentText(text);
        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> dialog.close());
    }
}
