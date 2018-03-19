package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContentSearchFormController implements Initializable {

    @FXML
    private JFXHamburger sidebarHam;

    @FXML
    private AnchorPane sidebarPane;

    @FXML
    private JFXDrawer sidebarDrawer;

    @FXML
    private AnchorPane searchPane;

    @FXML
    private JFXButton advanceBtn;

    @FXML
    JFXNodesList nodesList;

    @FXML
    private JFXToggleButton saveDirBtn;

    @FXML
    private JFXToggleButton advanceSearchBtn;

    private HamburgerNextArrowBasicTransition transition;
    private RotateTransition rt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            sidebarPane = FXMLLoader.load(getClass().getResource("/presentation/sidebarContent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sidebarDrawer.setSidePane(sidebarPane);
        sidebarDrawer.open();
        transition = new HamburgerNextArrowBasicTransition(sidebarHam);
        transition.setRate(-1);

        saveDirBtn = new JFXToggleButton();
        saveDirBtn.setText("Save to Directory");
        saveDirBtn.setFocusTraversable(false);
        saveDirBtn.getStylesheets().add("css/round_btn.css");
        saveDirBtn.getStyleClass().add("toggle-button");

        advanceSearchBtn = new JFXToggleButton();
        advanceSearchBtn.setText("Save to Directory");
        advanceSearchBtn.setFocusTraversable(false);
        advanceSearchBtn.getStylesheets().add("css/round_btn.css");
        advanceSearchBtn.getStyleClass().add("toggle-button");

        nodesList = new JFXNodesList();
        nodesList.addAnimatedNode(advanceBtn);
        nodesList.addAnimatedNode(saveDirBtn);
        nodesList.addAnimatedNode(advanceSearchBtn);

        searchPane.getChildren().add(nodesList);
        nodesList.setLayoutY(23);
        nodesList.setLayoutX(533);
        nodesList.setSpacing(5);
        
    }

    public void sidebarHamClicked(MouseEvent mouseEvent) {

        transition.setRate(transition.getRate()*-1);
        transition.play();

        if (sidebarDrawer.isShown()) {
            sidebarDrawer.close();
        } else {
            sidebarDrawer.open();
        }

    }

    public void advanceButtonAction(ActionEvent actionEvent) {
        rt = new RotateTransition(Duration.millis(100), advanceBtn);
        rt.setByAngle(180);
        rt.setCycleCount(1);
        rt.setAutoReverse(false);
        rt.play();
    }
}
