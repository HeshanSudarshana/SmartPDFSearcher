package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class AddUserFormController {

    @FXML
    private JFXTextField usernameTxt;

    @FXML
    private JFXTextField passwordTxt;

    @FXML
    private JFXTextField confirmPasswordTxt;

    @FXML
    private JFXTextField workspaceTxt;

    @FXML
    private JFXButton signupBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private ImageView workspaceErr;

    @FXML
    private ImageView passwordErr;

    @FXML
    private ImageView confirmPasswordErr;

    public void signupBtnAction(ActionEvent actionEvent) {
    }


    public void cancelBtn(ActionEvent actionEvent) {
    }
}
