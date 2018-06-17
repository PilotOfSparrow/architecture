package presentation.controller;

import domain.model.TaskModel;
import domain.project.Task;
import domain.project.TaskStatus;
import domain.utils.ProjectUtil;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;
import presentation.UiUtils;

import java.util.Arrays;
import java.util.Optional;

public class ProjectTasksController {
    @NotNull TaskModel taskModel = new TaskModel();

    @FXML
    public Button toAllProjectsButton;

    @FXML
    public ListView allTasksListView;

    @FXML
    public ListView taskInfoListView;

    @FXML
    public Button newTaskButton;

    @FXML
    public Button changeStatusButton;

    @FXML
    private void initialize() {
        initLists();
        initButtons();
    }

    private void initButtons() {
        toAllProjectsButton.setOnMouseClicked(event -> {
            UiUtils.getInstance().open(
                    event,
                    "view/all_projects.fxml",
                    "All projects"
            );
        });
        newTaskButton.setOnMouseClicked(event -> {
            UiUtils.getInstance().open(
                    event,
                    "view/new_task.fxml",
                    "New task"
            );
        });
        changeStatusButton.setOnMouseClicked(event -> {
            Task task = (Task) allTasksListView.getSelectionModel().getSelectedItem();
            if (task == null) return;

            TaskStatus[] statuses = Arrays.stream(TaskStatus.values())
                    .filter(taskStatus -> taskStatus != task.getTaskStatus())
                    .toArray(TaskStatus[]::new);

            ChoiceDialog<TaskStatus> choiceDialog = new ChoiceDialog<>(statuses[0], statuses);
            choiceDialog.setTitle("Select new status");
            choiceDialog.setHeaderText("Select new status");

            Optional<TaskStatus> result = choiceDialog.showAndWait();
            result.ifPresent(taskStatus -> taskModel.changeTaskStatus(task, taskStatus));
            taskInfoListView.getItems().setAll(task.toStringList());
        });
    }

    private void initLists() {
        updateAllTasksList();
        allTasksListView.setCellFactory(param -> {
            ListCell<Task> cell = new ListCell<>();
            final ContextMenu contextMenu = new ContextMenu();

            MenuItem deleteItem = new MenuItem();
            deleteItem.textProperty().setValue("Delete");
            deleteItem.setOnAction(event -> {
                Task task = cell.getItem();
                taskModel.removeTask(task);
                updateAllTasksList();
            });

            contextMenu.getItems().add(deleteItem);

            cell.textProperty().bind(Bindings.createObjectBinding(() -> {
                        if (cell.isEmpty()) {
                            return null;
                        } else {
                            if (cell.getItem() != null) {
                                return cell.getItem().toString();
                            }
                            return null;
                        }
                    }, cell.emptyProperty(), cell.itemProperty()
                    )
            );

            cell.emptyProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });

            return cell;
        });

        allTasksListView.setOnMouseClicked(event -> {
            Task task = (Task) allTasksListView.getSelectionModel().getSelectedItem();
            if (task == null) return;

            taskInfoListView.getItems().setAll(task.toStringList());
        });
    }

    private void updateAllTasksList() {
        allTasksListView.getItems().setAll(taskModel.getProjectTasks(ProjectUtil.INSTANCE.getCurrentProject().getId()));
    }
}
