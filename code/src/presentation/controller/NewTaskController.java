package presentation.controller;

import domain.model.TaskModel;
import domain.project.TaskStatus;
import domain.project.TaskType;
import domain.user.User;
import domain.utils.ProjectUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;
import presentation.UiUtils;

import java.util.Objects;

public class NewTaskController {
    @NotNull TaskModel taskModel = new TaskModel();

    @FXML
    public Button createTaskButton;

    @FXML
    public TextField taskNameTextField;

    @FXML
    public TextArea taskDescriptionTextArea;

    @FXML
    public ComboBox taskTypeComboBox;

    @FXML
    public ListView developerListView;

    @FXML
    public ListView testerListView;

    @FXML
    private void initialize() {
        initLists();
        initButtons();

        taskTypeComboBox.getItems().setAll(TaskType.values());
    }

    private void initButtons() {
        createTaskButton.setOnMouseClicked(event -> {
            if (!createNewTask()) return;

            UiUtils.getInstance().open(
                    event,
                    "view/project_tasks.fxml",
                    ProjectUtil.INSTANCE.getCurrentProject().getName() + " tasks"
            );
        });
    }

    private void initLists() {
        developerListView.getItems().setAll(ProjectUtil.INSTANCE.getCurrentProject().getDevelopersList());
        testerListView.getItems().setAll(ProjectUtil.INSTANCE.getCurrentProject().getTestersList());
    }

    private boolean createNewTask() {
        assert taskNameTextField != null;
        assert taskTypeComboBox != null;
        assert developerListView != null;
        assert testerListView != null;
        assert taskDescriptionTextArea != null;

        String title = taskNameTextField.getText();
        String description = taskDescriptionTextArea.getText();
        TaskType type = (TaskType) taskTypeComboBox.getValue();
        User developer = (User) developerListView.getSelectionModel().getSelectedItem();
        User tester = (User) testerListView.getSelectionModel().getSelectedItem();

        if (Objects.equals(title, "")) {
            UiUtils.getInstance()
                    .showWarningDialog("No name!", "Specify name for task");

            return false;
        }
        if (type == null) {
            UiUtils.getInstance()
                    .showWarningDialog("No type!", "Specify type of task");

            return false;
        }
        if (developer == null) {
            UiUtils.getInstance()
                    .showWarningDialog("No developer!", "Select developer");

            return false;
        }
        if (tester == null) {
            UiUtils.getInstance()
                    .showWarningDialog("No tester!", "Select tester");

            return false;
        }

        taskModel.addTask(
                title,
                description,
                developer,
                tester,
                type,
                TaskStatus.OPEN,
                ProjectUtil.INSTANCE.getCurrentProject()
        );

        return true;
    }
}
