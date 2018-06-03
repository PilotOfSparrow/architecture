package presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {
    @FXML
    public TextField loginTextField;

    @FXML
    public TextField nameTextField;

    @FXML
    public TextField surnameTextField;

    @FXML
    public ChoiceBox roleChoiceBox;

    @FXML
    public PasswordField passwordField;

    @FXML
    public PasswordField repeatPasswordField;

    @FXML
    public Button signUpButton;
}
