package controllers;

import business_logic.DataFlowManager;
import business_logic.PDFFile;
import business_logic.UserConfig;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import user_access.DBConnector;
import user_access.FavouriteObject;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Optional;

public class MethodLoader {

    private DataFlowManager dataFlowManager = DataFlowManager.getInstance();
    private UserConfig userConfig = new UserConfig();
    private DBConnector dbConnector = new DBConnector();

    public void createAccountFormLoad(JFXButton btn) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/presentation/add_user_form.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage = (Stage) btn.getScene().getWindow();
        primaryStage.setTitle("Create Account");
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
        primaryStage.getIcons().add(userIcon);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void loginFormLoad(JFXButton btn) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/presentation/login_form.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage = (Stage) btn.getScene().getWindow();
        primaryStage.setTitle("Login");
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
        primaryStage.getIcons().add(userIcon);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    public void nameSearchFormLoad(JFXButton btn) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/presentation/name_search_form.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage = (Stage) btn.getScene().getWindow();
        primaryStage.setTitle("Search By Name");
        Image icon = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public void contentSearchFormLoad(JFXButton btn) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/presentation/content_search_form.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage = (Stage) btn.getScene().getWindow();
        primaryStage.setTitle("Search By Content");
        Image icon = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public void accountSettingsFormLoad(JFXButton btn) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/presentation/edit_user_form.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage = (Stage) btn.getScene().getWindow();
        primaryStage.setTitle("Create Account");
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
        primaryStage.getIcons().add(userIcon);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void deleteAccountFormLoad (JFXButton btn) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/presentation/delete_user_form.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage = (Stage) btn.getScene().getWindow();
        primaryStage.setTitle("Login");
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
        primaryStage.getIcons().add(userIcon);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    public void startFormLoad (Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/presentation/start_form.fxml"));
        Image icon = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Smart PDF Searcher");
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public void historyLoad (Node btn) {

        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(getClass().getResource("/presentation/history.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage1 = (Stage) btn.getScene().getWindow();
        primaryStage1.setTitle("History");
        Image userIcon1 = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
        primaryStage1.getIcons().add(userIcon1);
        primaryStage1.resizableProperty().setValue(Boolean.FALSE);
        primaryStage1.setScene(new Scene(root1, 600, 465));
        primaryStage1.show();

    }

    public void favouritesLoad (JFXButton btn) {

        Parent root2 = null;
        try {
            root2 = FXMLLoader.load(getClass().getResource("/presentation/favourites.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage2 = (Stage) btn.getScene().getWindow();
        primaryStage2.setTitle("Favourites");
        Image userIcon2 = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
        primaryStage2.getIcons().add(userIcon2);
        primaryStage2.resizableProperty().setValue(Boolean.FALSE);
        primaryStage2.setScene(new Scene(root2, 600, 465));
        primaryStage2.show();

    }

    public void settingsLoad(JFXButton btn) {
        Parent root3 = null;
        try {
            root3 = FXMLLoader.load(getClass().getResource("/presentation/settings.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage3 = (Stage) btn.getScene().getWindow();
        primaryStage3.setTitle("Settings");
        Image userIcon3 = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
        primaryStage3.getIcons().add(userIcon3);
        primaryStage3.setScene(new Scene(root3, 500, 100));
        primaryStage3.show();
    }

    public void loadSideBar(VBox sidebarBox) {
        for (Node node: sidebarBox.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    switch (node.getAccessibleText()) {
                        case "History":
                            historyLoad(node);
                            break;
                        case "Favourites":
                            favouritesLoad((JFXButton) node);
                            break;
                        case "Settings":
                            settingsLoad((JFXButton) node);
                            break;
                        case "Login":
                            loginFormLoad((JFXButton) node);
                            break;
                        case "Logout":
                            //logout code
                            break;
                    }
                });
            }
        }
    }

//    public void heartAnimation( ImageView heartIcon, String username, String path) {
//        if(heartIcon.getImage().equals(getClass().getResourceAsStream("/icons/001-like.png"))) {
//            heartIcon.setImage(new Image(getClass().getResourceAsStream("/icons/002-like-1.png")));
//            userConfig.addFavouriteObject(username, path);
//        } else if(heartIcon.getImage().equals(getClass().getResourceAsStream("/icons/002-like-1.png"))){
//            heartIcon.setImage(new Image(getClass().getResourceAsStream("/icons/001-like.png")));
//            userConfig.deleteFavouriteObject(username, path);
//        } else {
//            System.out.println("There's something wrong with the heart!");
//            System.out.println(heartIcon.getImage());
//            System.out.println(getClass().getResourceAsStream("/icons/001-like.png"));
//        }
//    }

    public void heartAnimation(ImageView heartIcon, String username, String path, JFXTreeTableView<PDFFile> treeTableView) {
        if(!isInTheDB(treeTableView)) {
            heartIcon.setImage(new Image(getClass().getResourceAsStream("/icons/002-like-1.png")));
            userConfig.addFavouriteObject(username, path);
        } else {
            heartIcon.setImage(new Image(getClass().getResourceAsStream("/icons/001-like.png")));
            userConfig.deleteFavouriteObject(username, path);
        }
    }

    public boolean isInTheDB(JFXTreeTableView<PDFFile> treeTableView) {
        ArrayList<FavouriteObject> arrayList = dbConnector.getFavourites(DataFlowManager.getInstance().getUsername());
        ArrayList<String> pathList = new ArrayList<>();
        for (FavouriteObject i: arrayList) {
            pathList.add(i.getPath());
        }
        if(pathList.contains(treeTableView.getSelectionModel().getSelectedItem().getValue().getFilePath().get())) {
            return true;
        } else {
            for (FavouriteObject i: arrayList) {
                System.out.println(i.getPath());
            }
            System.out.println("other: " + treeTableView.getSelectionModel().getSelectedItem().getValue().PDFFiletoFavouriteObject(DataFlowManager.getInstance().getUsername()).getPath() );
            return false;
        }
    }

    public void logoutAndLoginAlert(String username, int userID, String workspace, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Alert");
        alert.setGraphic(new ImageView(getClass().getResource("/icons/ic_power_settings_new_black_24dp_2x.png").toString()));
        alert.setHeaderText(dataFlowManager.getUsername()+" Logout?");
        alert.setContentText(contentText);
        DialogPane dialogPane1 = alert.getDialogPane();
        dialogPane1.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane1.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane1.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane1.getStyleClass().add("myDialog");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dataFlowManager.logout();
            dataFlowManager.login(username, userID, workspace);
            successfullyLoggedinAlert(username);
        } else {
            //do nothing
        }
    }

    public void alreadyLoggedinAlert(String username) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert!");
        alert.setHeaderText("Already Logged in!");
        alert.setContentText("You are currently logged in as " + username);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    public void successfullyLoggedinAlert(String username) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful!");
        alert.setHeaderText("Successfully Logged in as "+ username);
        alert.setContentText("");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    public void rotateTransition(RotateTransition rt, JFXButton advanceBtn) {
        rt = new RotateTransition(Duration.millis(100), advanceBtn);
        rt.setByAngle(180);
        rt.setCycleCount(1);
        rt.setAutoReverse(false);
        rt.play();
    }

    public void loadDirectorySelector(JFXTextField textField, String headerTxt) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(headerTxt);
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory!=null) {
            textField.setText(selectedDirectory.getAbsolutePath());
            textField.setAlignment(Pos.CENTER_LEFT);
        } else {
            textField.setText("");
            textField.setAlignment(Pos.CENTER);
        }
    }

    public void copiedSuccessfully() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful!");
        alert.setHeaderText("Successfully copied the files!");
        alert.setContentText("");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    public void loginAlert(JFXButton button) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login Alert");
        alert.setGraphic(new ImageView(getClass().getResource("/icons/ic_power_settings_new_black_24dp_2x.png").toString()));
        alert.setHeaderText(dataFlowManager.getUsername()+" Login?");
        alert.setContentText("You need to be logged in to access that Feature! Do you want to Log in now?");
        DialogPane dialogPane1 = alert.getDialogPane();
        dialogPane1.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane1.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane1.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane1.getStyleClass().add("myDialog");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            loginFormLoad(button);
        } else {
            //do nothing
        }
    }

    public String folderNameAlert(String givenTxt) {

        TextInputDialog dialog = new TextInputDialog(givenTxt);
        dialog.setTitle("Folder Name");
        dialog.setHeaderText("Name of the folder?");
        dialog.setContentText("Please do not enter <>?[]/\\|: when entering the name. Else the search result will be the name of the folder!");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        // getting user input
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            if (!result.get().matches("^([a-zA-Z0-9][^*/><?\\|:]*).+$")) {
                return givenTxt;
            }
            return result.get();
        } else {
            return givenTxt;
        }
    }

    public void selectFileAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("File Not Selected!");
        alert.setHeaderText("Please select a file from the list!");
        alert.setContentText("");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    public void fileDoesnotExistAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("The File does not exist!");
        alert.setContentText("");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    public void desktopEnvNotSupportedAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Desktop environment is not supported!");
        alert.setContentText("");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    public void loadNextForm (JFXButton button) throws IOException {
        String previous = DataFlowManager.getInstance().getPreviousStage();
        if(previous.equals("start_page")) {
            startFormLoad((Stage) button.getScene().getWindow());
        } else if (previous.equals("name_search")) {
            nameSearchFormLoad(button);
        } else if (previous.equals("content_search")) {
            contentSearchFormLoad(button);
        }
    }
}
