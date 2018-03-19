package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartFormController implements Initializable {

    @FXML
    private AnchorPane buttonPane;

    @FXML
    private JFXNodesList nodesList;

    @FXML
    private JFXButton startSearchBtn;

    @FXML
    private JFXButton nameSearchBtn;

    @FXML
    private JFXButton contentSearchBtn;

    @FXML
    private ImageView nextSearchIcon;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXButton createAccBtn;

    @FXML
    private JFXButton infoBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image imageNameBtn = new Image(getClass().getResourceAsStream("/icons/card_employee_badge_identification-512.png"),75,75,false,false);
        nameSearchBtn = new JFXButton("    Search By Name", new ImageView(imageNameBtn));
        nameSearchBtn.setButtonType(JFXButton.ButtonType.RAISED);
        nameSearchBtn.setFocusTraversable(false);
        nameSearchBtn.getStylesheets().add("css/search_btn.css");
        nameSearchBtn.getStyleClass().add("animated-option-button");

        Image imageContentBtn = new Image(getClass().getResourceAsStream("/icons/grey_new_seo-37-512.png"),75,75,false,false);
        contentSearchBtn = new JFXButton("  Search By Content", new ImageView(imageContentBtn));
        contentSearchBtn.setButtonType(JFXButton.ButtonType.RAISED);
        contentSearchBtn.setFocusTraversable(false);
        contentSearchBtn.getStylesheets().add("css/search_btn.css");
        contentSearchBtn.getStyleClass().add("animated-option-button");

        nodesList = new JFXNodesList();
        nodesList.addAnimatedNode(startSearchBtn);
        nodesList.addAnimatedNode(nameSearchBtn);
        nodesList.addAnimatedNode(contentSearchBtn);

        buttonPane.getChildren().add(nodesList);
        nodesList.setLayoutY(250);
        nodesList.setSpacing(5);

        nameSearchBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)-> {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/presentation/name_search_form.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Stage primaryStage = (Stage) nameSearchBtn.getScene().getWindow();
            primaryStage.setTitle("Search By Name");
            Image icon = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
            primaryStage.getIcons().add(icon);
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        });

        contentSearchBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)-> {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/presentation/content_search_form.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Stage primaryStage = (Stage) contentSearchBtn.getScene().getWindow();
            primaryStage.setTitle("Search By Content");
            Image icon = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
            primaryStage.getIcons().add(icon);
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        });

    }

    public void startSearchBtnAction(ActionEvent actionEvent) {
        RotateTransition rt = new RotateTransition(Duration.millis(250), nextSearchIcon);
        rt.setFromAngle(0);
        rt.setByAngle(90);
        rt.setCycleCount(2);
        rt.setAutoReverse(true);
        rt.play();
    }

    public void loginBtnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/presentation/login_form.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
        primaryStage.setTitle("Login");
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/img_311846.png"));
        primaryStage.getIcons().add(userIcon);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    public void createAccBtnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/presentation/add_user_form.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage = (Stage) createAccBtn.getScene().getWindow();
        primaryStage.setTitle("Create Account");
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/create-account-rooweb-clipart-380x380.png"));
        primaryStage.getIcons().add(userIcon);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void infoBtnAction(ActionEvent actionEvent) {
    }

}
