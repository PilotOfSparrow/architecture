package presentation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class UiUtils {
    private static UiUtils ourInstance = new UiUtils();

    public static UiUtils getInstance() {
        return ourInstance;
    }

    private UiUtils() {}

    public void open(@NotNull InputEvent event, @NotNull String filePath, @NotNull String title) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(filePath)));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();

            ((Node)event.getSource()).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showErrorDialog(@NotNull String title, @NotNull String header, @Nullable String content) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle(title);
        error.setHeaderText(header);
        if (content != null) error.setContentText(content);

        error.showAndWait();
    }

    public void showWarningDialog(String title, String text) {
        ButtonType buttonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(buttonType);
        dialog.getDialogPane().lookupButton(buttonType).setDisable(false);
        dialog.setTitle(title);
        dialog.setContentText(text);
        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> dialog.close());
    }
}
