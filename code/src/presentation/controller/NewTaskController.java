package presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NewTaskController {
    @FXML
    public Label addAssigneeButton;

    @FXML
    public Button createTaskButton;

    @FXML
    public TextField taskNameTextField;

    @FXML
    public TextArea taskDescriptionTextArea;

    @FXML
    public ComboBox taskTypeComboBox;

    @FXML
    public TextField taskEstimateTextField;
}
