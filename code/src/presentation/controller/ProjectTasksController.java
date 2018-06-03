package presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ProjectTasksController {
    @FXML
    public TextField taskSearchTextField;

    @FXML
    public CheckBox assignedToMeCheckBox;

    @FXML
    public CheckBox createdByMeCheckBox;

    @FXML
    public ListView allTasksListView;

    @FXML
    public ListView taskInfoListView;

    @FXML
    public Button newTaskButton;
}
