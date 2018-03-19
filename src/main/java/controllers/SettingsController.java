package controllers;

import com.jfoenix.controls.JFXButton;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
