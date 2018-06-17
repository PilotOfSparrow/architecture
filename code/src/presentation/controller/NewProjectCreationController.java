package presentation.controller;

import domain.model.ProjectModel;
import domain.model.UserModel;
import domain.user.Role;
import domain.user.User;
import domain.utils.ProjectUtil;
import domain.utils.UserUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckListView;
import org.jetbrains.annotations.NotNull;
import presentation.UiUtils;

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

    @NotNull ProjectModel projectModel = new ProjectModel();
    @NotNull UserModel userModel = new UserModel();

    @FXML
    private void initialize() {
        initLists();
        initButtons();
    }

    private void initButtons() {
        projectCreateButton.setOnMouseClicked(event -> {
            if (!createNewProject()) return;

            UiUtils.getInstance().open(
                    event,
                    "view/project_tasks.fxml",
                    ProjectUtil.INSTANCE.getCurrentProject().getName() + " tasks"
            );
        });
    }

    private void initLists() {
        List<User> developers = userModel.getAllWithRole(Role.DEVELOPER);
        List<User> testers = userModel.getAllWithRole(Role.TESTER);

        developersCheckListView.getItems().setAll(developers);
        testersCheckListView.getItems().setAll(testers);
    }

    private boolean createNewProject() {
        String projectName = projectNameTextField.getText();
        if (projectName == null || projectName.equals("")) {
            UiUtils.getInstance()
                    .showWarningDialog("No project name!", "Specify name for project");

            return false;
        }

        List<User> developersList = developersCheckListView.getCheckModel().getCheckedItems();
        if (developersList == null || developersList.isEmpty()) {
            UiUtils.getInstance()
                    .showWarningDialog("No developers specified!", "Specify at least one dev on project");

            return false;
        }

        List<User> testersList = testersCheckListView.getCheckModel().getCheckedItems();
        if (testersList == null || testersList.isEmpty()) {
            UiUtils.getInstance()
                    .showWarningDialog("No testers specified!", "Specify at least one tester on project");

            return false;
        }

        projectModel.addProject(projectName, UserUtils.INSTANCE.getCurrentUser(), developersList, testersList);

        return true;
    }


}
