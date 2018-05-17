package controllers;

import business_logic.DataFlowManager;
import business_logic.PasswordHasher;
import business_logic.UserConfig;
import business_logic.Validator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.xml.internal.ws.api.FeatureConstructor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import user_access.User;

import javax.xml.crypto.Data;
import javax.xml.transform.sax.SAXTransformerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditUserFormController implements Initializable {

    @FXML
    private JFXPasswordField passwordText;

    @FXML
    private JFXPasswordField confirmPasswordTxt;

    @FXML
    private JFXTextField usernameTxt;

    @FXML
    private JFXButton browseBtn;

    @FXML
    private JFXButton saveChangesBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXTextField workspaceTxt;

    private UserConfig userConfig;
    private MethodLoader methodLoader;
    private Validator validator;
    private PasswordHasher passwordHasher;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        methodLoader = new MethodLoader();
        userConfig = new UserConfig();
        validator = new Validator();
        passwordHasher = new PasswordHasher();

        User user = userConfig.getUserDetails(DataFlowManager.getInstance().getUsername());

        usernameTxt.setText(user.getUsername());
        workspaceTxt.setText(user.getWorkspacePath());

    }

    public void saveChangesBtnAction(ActionEvent actionEvent) throws IOException {
        if(!usernameTxt.getText().matches("[a-zA-Z0-9]+") && !usernameTxt.getText().equals(DataFlowManager.getInstance().getUsername())) {
            validator.usernameAlert("Username should be unique, consist of only letters and numbers and should not be null.");
        } else if(!passwordText.getText().equals("") && !passwordText.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            validator.passwordAlert("Password should be at least 8 characters long with at least one uppercase letter, lowercase letter, symbol and a number included.");
        } else if(!passwordText.getText().equals(confirmPasswordTxt.getText())) {
            validator.confirmPasswordAlert("Carefully enter the confirmation password again.");
        } else if(workspaceTxt.getText().equals(null) || workspaceTxt.getText().equals("")) {
            validator.pathNotValidAlert("Workspace Path should be defined.");
        } else {
            String username;
            String password;
            if(usernameTxt.getText().equals("") || usernameTxt.getText().equals(null)) {
                username = DataFlowManager.getInstance().getUsername();
            } else {
                username = usernameTxt.getText();
            }
            if(passwordText.getText().equals("") || passwordText.getText().equals(null)) {
                password = userConfig.getUserDetails(DataFlowManager.getInstance().getUsername()).getPassword();
            } else {
                password = passwordHasher.generateHash(passwordText.getText());
            }
            userConfig.editUser(DataFlowManager.getInstance().getUserID(), username, password, workspaceTxt.getText());
            methodLoader.userUpdatedSuccessfully();
            methodLoader.loadNextForm(saveChangesBtn);
        }
    }

    public void cancelBtnAction(ActionEvent actionEvent) throws IOException {
        methodLoader.loadNextForm(saveChangesBtn);
    }

    public void browseBtnAction(ActionEvent actionEvent) {
        methodLoader.loadDirectorySelector(workspaceTxt, "Select Workspace Directory");
    }

}
