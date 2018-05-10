package business_logic;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import user_access.DBConnector;
import user_access.User;

public class Validator {

    private DBConnector dbConnector;
    private PasswordHasher passwordHasher;

    public Validator() {
        dbConnector = new DBConnector();
    }

    public void usernameValidator (JFXTextField textField, ImageView imageView) {

        textField.focusedProperty().addListener((arg0, oldValue, newValue)-> {
            if(!newValue) {
                if(!(textField.getText().matches("[a-zA-Z0-9]+")) || (textField.getText().length()>50) || (getDbConnector().getUsernameList().contains(textField.getText()))) {
                    imageView.setImage(new Image(getClass().getResourceAsStream("/icons/exclamation-mark.png"),30, 30, false, false));
                } else {
                    imageView.setImage(null);
                }
            }
        });

    }

    public void passwordValidator (JFXPasswordField textField, ImageView imageView) {

        textField.focusedProperty().addListener((arg0, oldValue, newValue)-> {
            if(!newValue) {
                if(!(textField.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"))) {
                    imageView.setImage(new Image(getClass().getResourceAsStream("/icons/exclamation-mark.png"),30, 30, false, false));
                } else {
                    imageView.setImage(null);
                }
            }
        });

    }

    public void confirmPasswordValidator (JFXPasswordField textField1, JFXPasswordField textField2, ImageView imageView) {

        textField1.focusedProperty().addListener((arg0, oldValue, newValue)-> {
            if(!newValue) {
                if(!textField1.getText().equals(textField2.getText())) {
                    imageView.setImage(new Image(getClass().getResourceAsStream("/icons/exclamation-mark.png"),30, 30, false, false));
                } else {
                    imageView.setImage(null);
                }
            }
        });

    }

    public void pathValidator (JFXTextField textField, ImageView imageView) {

        textField.focusedProperty().addListener((arg0, oldValue, newValue)-> {
            if(!newValue) {
                if(textField.getText() == null) {
                    imageView.setImage(new Image(getClass().getResourceAsStream("/icons/exclamation-mark.png"),30, 30, false, false));
                } else {
                    imageView.setImage(null);
                }
            }
        });

    }

    public boolean validateAddUserForm(ImageView usernameImg, ImageView passwordImg, ImageView confirmPasswordImg, JFXTextField path) {
        if(usernameImg.getImage()!=null){

            usernameAlert("Username should be unique, consist of only letters and numbers and should not be null.");
            return false;

        } else if (passwordImg.getImage() != null) {

            passwordAlert("Password should be at least 8 characters long with at least one uppercase letter, lowercase letter, symbol and a number included.");
            return false;

        } else if (confirmPasswordImg.getImage() != null) {

            confirmPasswordAlert("Carefully enter the confirmation password again.");
            return false;

        } else if(path.getText() == null || path.getText().equals("")) {

            pathNotValidAlert("Workspace Path should be defined.");
            return false;

        } else {
            return true;
        }
    }

    public boolean validateUserForm(JFXTextField username, JFXPasswordField password) {

        if(!username.getText().matches("[a-zA-Z0-9]+") || !dbConnector.getUsernameList().contains(username.getText())) {

            usernameAlert("Please check your username. (Hint - Username is case sensitive!)");
            return false;

        } else {
            User user = dbConnector.getUserDetails(username.getText());
            String hashedPwd = passwordHasher.generateHash(password.getText());
            if(password.getText().equals("") || password.getText().equals(null)) {
                passwordAlert("Please enter the password.");
                return false;
            } else if (!hashedPwd.equals(user.getPassword())) {
                passwordAlert("Wrong Password.");
                return false;
            }
            else {
                return true;
            }
        }
    }


    public DBConnector getDbConnector() {
        return dbConnector;
    }

    public void setDbConnector(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void usernameAlert(String errMsg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Username not valid!");
        alert.setContentText(errMsg);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    public void passwordAlert(String errMsg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Password not valid!");
        alert.setContentText(errMsg);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    public void confirmPasswordAlert(String errMsg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Password mismatch!");
        alert.setContentText(errMsg);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    public void pathNotValidAlert(String errMsg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Path not defined!");
        alert.setContentText(errMsg);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }
}
