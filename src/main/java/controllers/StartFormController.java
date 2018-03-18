package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameSearchBtn = new JFXButton("Search By Name");
        nameSearchBtn.setButtonType(JFXButton.ButtonType.RAISED);
        nameSearchBtn.setFocusTraversable(false);
        nameSearchBtn.getStylesheets().add("css/search_btn.css");
        nameSearchBtn.getStyleClass().add("animated-option-button");

        contentSearchBtn = new JFXButton("Search By Content");
        contentSearchBtn.setButtonType(JFXButton.ButtonType.RAISED);
        contentSearchBtn.setFocusTraversable(false);
        contentSearchBtn.getStylesheets().add("css/search_btn.css");
        contentSearchBtn.getStyleClass().add("animated-option-button");

        nodesList = new JFXNodesList();
        nodesList.addAnimatedNode(startSearchBtn);
        nodesList.addAnimatedNode(nameSearchBtn);
        nodesList.addAnimatedNode(contentSearchBtn);

        buttonPane.getChildren().add(nodesList);
        nodesList.setLayoutY(300);
        nodesList.setSpacing(5);

    }

    public void startSearchBtnAction(ActionEvent actionEvent) {
        RotateTransition rt = new RotateTransition(Duration.millis(500), nextSearchIcon);
        rt.setFromAngle(0);
        rt.setByAngle(90);
        rt.setCycleCount(2);
        rt.setAutoReverse(true);
        rt.play();
    }
}
