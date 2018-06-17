package presentation.controller;

import domain.model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;
import presentation.UiUtils;

public class LoginController {
    @FXML
    public TextField loginTextField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Button signInButton;

    @FXML
    public Button signUpButton;

    @NotNull
    private UserModel userModel = new UserModel();

    @FXML
    public void initialize() {
        initButtons();
    }

    private void initButtons() {
        assert signUpButton != null;
        signUpButton.setOnMouseClicked(event ->
                UiUtils.getInstance().open(event, "view/registration.fxml", "Registration")
        );

        assert signInButton != null;
        signInButton.setOnMouseClicked(event -> {
            assert loginTextField != null;
            assert passwordField != null;
            boolean isCredentialsCorrect = userModel.authenticate(loginTextField.getText(), passwordField.getText());
            if (isCredentialsCorrect) {
                UiUtils.getInstance().open(event, "view/all_projects.fxml", "All projects");
            } else {
                UiUtils.getInstance().showErrorDialog("Login Error", "No such user or incorrect password", null);
            }
        });
    }
}
