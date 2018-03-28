package controllers;


import business_logic.DataFlowManager;
import business_logic.Validator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private JFXTextField usernameTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    private DataFlowManager dataFlowManager;
    private Validator validator;
    private boolean validated;
    private MethodLoader methodLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataFlowManager = DataFlowManager.getInstance();
        validator = new Validator();
        methodLoader = new MethodLoader();
    }

    public void loginBtnAction(ActionEvent actionEvent) {
        validated = validator.validateUserForm(usernameTxt, passwordTxt);
        if (validated) {
            if (dataFlowManager.getUsername() != null) {
                //logout alert should be done
            }
        }
    }

    public void cancelBtnAction(ActionEvent actionEvent) {

    }

}
