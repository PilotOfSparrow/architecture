package presentation.controller;

import domain.model.UserModel;
import domain.user.Role;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;
import presentation.UiUtils;

import java.util.Objects;

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

    @NotNull
    UserModel userModel = new UserModel();

    @FXML
    public void initialize() {
        assert roleChoiceBox != null;

        roleChoiceBox.getItems().setAll(Role.values());

        initButtons();
    }

    private void initButtons() {
        assert signUpButton != null;

        signUpButton.setOnMouseClicked(event -> {
            assert loginTextField != null;
            assert nameTextField != null;
            assert surnameTextField != null;
            assert roleChoiceBox != null;
            assert passwordField != null;
            assert repeatPasswordField != null;

            String login = loginTextField.getText();
            String name = nameTextField.getText();
            String surname = surnameTextField.getText();
            Role role = (Role) roleChoiceBox.getValue();
            String password = passwordField.getText();
            String repeatedPassword = repeatPasswordField.getText();

            if (Objects.equals(login, "")) {
                showEmptyFieldError("Login");
                return;
            }
            if (Objects.equals(name, "")) {
                showEmptyFieldError("Name");
                return;
            }
            if (Objects.equals(surname, "")) {
                showEmptyFieldError("Surname");
                return;
            }
            if (Objects.equals(role, null)) {
                showEmptyFieldError("Role");
                return;
            }
            if (Objects.equals(password, "")) {
                showEmptyFieldError("Password");
                return;
            }
            if (Objects.equals(repeatedPassword, "")) {
                showEmptyFieldError("Repeat password");
                return;
            }
            if (!Objects.equals(password, repeatedPassword)) {
                UiUtils.getInstance().showErrorDialog(
                        "Passwords not match",
                        "Enter matching passwords",
                        null);
                return;
            }

            userModel.addUser(login, name, surname, password, role);

            UiUtils.getInstance().open(event, "view/all_projects.fxml", "All projects");
        });
    }

    private void showEmptyFieldError(@NotNull String fieldName) {
        UiUtils.getInstance().showErrorDialog(fieldName, "Empty " + fieldName + " field!", null);
    }
}
