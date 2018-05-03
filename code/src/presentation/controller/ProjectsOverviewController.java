package presentation.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

public class ProjectsOverviewController {
    @FXML
    public MenuItem addProjectButton;

    @FXML
    public ListView<String> projectList;

    @FXML
    public Label userName;

    @FXML
    private void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList("Love", "me", "please", "baby");
        projectList.setItems(items);
        projectList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                System.out.println(projectList.getSelectionModel().getSelectedItem());
            }
        });
    }

    @FXML
    public void addProject(ActionEvent actionEvent) {

    }
}
