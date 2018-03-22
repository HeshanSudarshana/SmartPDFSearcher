package business_logic;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import user_access.DBConnector;

public class Validator {

    private DBConnector dbConnector;

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

    public boolean validateForm(ImageView usernameImg, ImageView passwordImg, ImageView confirmPasswordImg, JFXTextField path) {
        if(usernameImg.getImage()!=null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Username not valid!");
            alert.setContentText("username should consist of only letters and numbers and should not be null.");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setMinHeight(Region.USE_PREF_SIZE);
            dialogPane.setMinWidth(Region.USE_PREF_SIZE);
            dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
            return false;

        } else if (passwordImg.getImage() != null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Password not valid!");
            alert.setContentText("password should be at least 8 characters long with at least one uppercase letter, lowercase letter, symbol and a number included. user already ");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setMinHeight(Region.USE_PREF_SIZE);
            dialogPane.setMinWidth(Region.USE_PREF_SIZE);
            dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
            return false;

        } else if (confirmPasswordImg.getImage() != null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Password mismatch!");
            alert.setContentText("Carefully enter the confirmation password again.");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setMinHeight(Region.USE_PREF_SIZE);
            dialogPane.setMinWidth(Region.USE_PREF_SIZE);
            dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
            return false;

        } else if(path.getText() == null || path.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Path not defined!");
            alert.setContentText("Workspace Path should be defined.");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setMinHeight(Region.USE_PREF_SIZE);
            dialogPane.setMinWidth(Region.USE_PREF_SIZE);
            dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
            return false;

        } else {
            return true;
        }
    }


    public DBConnector getDbConnector() {
        return dbConnector;
    }

    public void setDbConnector(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }
}
