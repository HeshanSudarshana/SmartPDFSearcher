package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private HBox settingsBox;

    @FXML
    private JFXButton addUserBtn;

    @FXML
    private JFXButton userSettingsBtn;

    @FXML
    private JFXButton deleteUserBtn;

    @FXML
    private JFXButton infoBtn;

    @FXML
    private JFXButton backBtn;

    private MethodLoader methodLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        methodLoader = new MethodLoader();

    }

    public void addUserBtnAction(ActionEvent actionEvent) {

        methodLoader.createAccountFormLoad(addUserBtn);

    }

    public void userSettingsBtnAction(ActionEvent actionEvent) {

        methodLoader.accountSettingsFormLoad(userSettingsBtn);

    }

    public void deleteUserBtnAction(ActionEvent actionEvent) {

        methodLoader.deleteAccountFormLoad(deleteUserBtn);

    }

    public void infoBtnAction(ActionEvent actionEvent) {

        //documentation

    }

    public void backBtnAction(ActionEvent actionEvent) {

        methodLoader.nameSearchFormLoad(backBtn);

    }
}
