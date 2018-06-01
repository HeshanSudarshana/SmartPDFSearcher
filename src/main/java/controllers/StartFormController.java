package controllers;

import business_logic.DataFlowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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

    @FXML
    private Label hiLabel;

    private MethodLoader methodLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        methodLoader = new MethodLoader();

        DataFlowManager.getInstance().setPreviousStage("start_page");

        Image imageNameBtn = new Image(getClass().getResourceAsStream("/icons/icons8-name-tag-filled-50.png"),50,50,false,false);
        nameSearchBtn = new JFXButton("   Search By Name", new ImageView(imageNameBtn));
        nameSearchBtn.setButtonType(JFXButton.ButtonType.RAISED);
        nameSearchBtn.setFocusTraversable(false);
        nameSearchBtn.getStylesheets().add("css/search_btn.css");
        nameSearchBtn.getStyleClass().add("animated-option-button");

        Image imageContentBtn = new Image(getClass().getResourceAsStream("/icons/icons8-content-50.png"),50,50,false,false);
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

        nameSearchBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)-> methodLoader.nameSearchFormLoad(nameSearchBtn));

        contentSearchBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)-> methodLoader.contentSearchFormLoad(contentSearchBtn));

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

        methodLoader.loginFormLoad(loginBtn);

    }

    public void createAccBtnAction(ActionEvent actionEvent) {

        methodLoader.createAccountFormLoad(createAccBtn);

    }

    public void infoBtnAction(ActionEvent actionEvent) {

        methodLoader.loadInfoPage(infoBtn);

    }

}
