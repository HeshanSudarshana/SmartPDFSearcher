package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class EditUserFormController {

    //Yet to be implemented!

    @FXML
    private JFXButton deleteAccountBtn;

    public void deleteAccountBtnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/presentation/delete_user_form.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage = (Stage) deleteAccountBtn.getScene().getWindow();
        primaryStage.setTitle("Create Account");
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/delete-user-512.png"));
        primaryStage.getIcons().add(userIcon);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
