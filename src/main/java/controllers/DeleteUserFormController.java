package controllers;

import business_logic.UserConfig;
import business_logic.Validator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteUserFormController implements Initializable {

    @FXML
    private JFXTextField usernameTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton cancelBtn;

    private UserConfig userConfig;
    private Validator validator;
    private boolean validated;
    private MethodLoader methodLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userConfig = new UserConfig();
        validator = new Validator();
        methodLoader = new MethodLoader();
    }

    public void deleteBtnAction(ActionEvent actionEvent) throws IOException {
        validated = validator.validateDeleteUserForm(usernameTxt, passwordTxt);
        if (validated) {
            userConfig.deleteUser(usernameTxt.getText());
            methodLoader.startFormLoad((Stage) deleteBtn.getScene().getWindow());
        }
    }

    public void cancelBtnAction(ActionEvent actionEvent) throws IOException {
        methodLoader.startFormLoad((Stage) deleteBtn.getScene().getWindow());
    }

}
