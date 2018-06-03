package presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

public class AllProjectsController {
    @FXML
    public CheckBox showOnlyMyProjectsCheckBox;

    @FXML
    public ListView projectsListView;

    @FXML
    public Button newProjectButton; // only for managers
}
