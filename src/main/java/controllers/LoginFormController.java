package controllers;


import business_logic.DataFlowManager;
import business_logic.UserConfig;
import business_logic.Validator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import user_access.User;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private JFXTextField usernameTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXButton loginBtn;

    private DataFlowManager dataFlowManager;
    private Validator validator;
    private boolean validated;
    private MethodLoader methodLoader;
    private UserConfig userConfig;
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataFlowManager = DataFlowManager.getInstance();
        validator = new Validator();
        methodLoader = new MethodLoader();
        userConfig = new UserConfig();
    }

    public void loginBtnAction(ActionEvent actionEvent) throws IOException {
        validated = validator.validateUserForm(usernameTxt, passwordTxt);
        if (validated) {
            user = userConfig.getUserDetails(usernameTxt.getText());
            if(dataFlowManager.getUsername() != null) {
                if (dataFlowManager.getUsername().equals(usernameTxt.getText())) {

                    methodLoader.alreadyLoggedinAlert(usernameTxt.getText());

                } else {

                    methodLoader.logoutAndLoginAlert(usernameTxt.getText(), user.getUserID(), user.getWorkspacePath(), "Do you want to logout from current session and login as " + usernameTxt.getText());

                }
            } else {
                dataFlowManager.login(usernameTxt.getText(), user.getUserID(), user.getWorkspacePath());
                System.out.println(user.getUserID());
                methodLoader.successfullyLoggedinAlert(usernameTxt.getText());
            }
            methodLoader.loadNextForm(cancelBtn);
        }
    }

    public void cancelBtnAction(ActionEvent actionEvent) throws IOException {
        methodLoader.loadNextForm(cancelBtn);
    }

}
