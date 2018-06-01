package controllers;

import business_logic.DataFlowManager;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import javax.xml.crypto.Data;
import java.io.IOException;
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
        DataFlowManager.getInstance().setPreviousStage("settings");

    }

    public void addUserBtnAction(ActionEvent actionEvent) {

        methodLoader.createAccountFormLoad(addUserBtn);

    }

    public void userSettingsBtnAction(ActionEvent actionEvent) {

        if(DataFlowManager.getInstance().getUsername() != null) {
            methodLoader.accountSettingsFormLoad(userSettingsBtn);
        } else {
            methodLoader.loginAlert(userSettingsBtn);
        }

    }

    public void deleteUserBtnAction(ActionEvent actionEvent) {

        methodLoader.deleteAccountFormLoad(deleteUserBtn);

    }

    public void infoBtnAction(ActionEvent actionEvent) {

        methodLoader.loadInfoPage(infoBtn);

    }

    public void backBtnAction(ActionEvent actionEvent) throws IOException {

        methodLoader.loadNextForm(backBtn);

    }
}
