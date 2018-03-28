package controllers;

import business_logic.DataFlowManager;
import business_logic.PasswordHasher;
import business_logic.UserConfig;
import business_logic.Validator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import user_access.User;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddUserFormController implements Initializable{

    @FXML
    private JFXTextField usernameTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private JFXPasswordField confirmPasswordTxt;

    @FXML
    private JFXTextField workspaceTxt;

    @FXML
    private JFXButton signupBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXButton browseBtn;

    @FXML
    private ImageView usernameErr;

    @FXML
    private ImageView workspaceErr;

    @FXML
    private ImageView passwordErr;

    @FXML
    private ImageView confirmPasswordErr;

    private Validator validator;
    private boolean validated;
    private MethodLoader methodLoader;
    private UserConfig userConfig;
    private DataFlowManager dataFlowManager;
    private PasswordHasher passwordHasher;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        methodLoader = new MethodLoader();
        userConfig = new UserConfig();
        validator = new Validator();
        passwordHasher = new PasswordHasher();
        dataFlowManager = DataFlowManager.getInstance();

        validator.usernameValidator(usernameTxt, usernameErr);
        validator.passwordValidator(passwordTxt, passwordErr);
        validator.confirmPasswordValidator(confirmPasswordTxt, passwordTxt, confirmPasswordErr);
        validator.pathValidator(workspaceTxt, workspaceErr);

    }

    public void signupBtnAction(ActionEvent actionEvent) throws IOException {

        validated = validator.validateAddUserForm(usernameErr, passwordErr, confirmPasswordErr, workspaceTxt);
        if (validated) {

            userConfig.addUser(usernameTxt.getText(), passwordHasher.generateHash(passwordTxt.getText()), workspaceTxt.getText());

            User user = userConfig.getUserDetails(usernameTxt.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Added Successfully!");
            alert.setHeaderText("User Successfully Registered!");
            alert.setContentText("User - "+ usernameTxt.getText() +" added to the System.");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setMinHeight(Region.USE_PREF_SIZE);
            dialogPane.setMinWidth(Region.USE_PREF_SIZE);
            dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();

            if (dataFlowManager.getUsername() != null) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("Logout Alert");
                alert1.setHeaderText(dataFlowManager.getUsername()+" Logout?");
                alert1.setContentText("Do you want to logout from current session and login as " + usernameTxt.getText());
                DialogPane dialogPane1 = alert1.getDialogPane();
                dialogPane1.setMinHeight(Region.USE_PREF_SIZE);
                dialogPane1.setMinWidth(Region.USE_PREF_SIZE);
                dialogPane1.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
                dialogPane1.getStyleClass().add("myDialog");
                Optional<ButtonType> result = alert1.showAndWait();
                if (result.get() == ButtonType.OK){
                    dataFlowManager.logout();
                    dataFlowManager.login(usernameTxt.getText(), user.getUserID(), workspaceTxt.getText());
                } else {
                    //do nothing
                }
            } else {
                dataFlowManager.login(usernameTxt.getText(), user.getUserID(), workspaceTxt.getText());
            }

            methodLoader.startFormLoad((Stage) signupBtn.getScene().getWindow());
        }
    }


    public void cancelBtn(ActionEvent actionEvent) throws IOException {

        methodLoader.startFormLoad((Stage) signupBtn.getScene().getWindow());

    }

    public void browseBtnAction(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a Directory to Search");
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory!=null) {
            workspaceTxt.setText(selectedDirectory.getAbsolutePath());
            workspaceTxt.setAlignment(Pos.CENTER_LEFT);
        } else {
            workspaceTxt.setText("");
            workspaceTxt.setAlignment(Pos.CENTER);
        }
    }
}
