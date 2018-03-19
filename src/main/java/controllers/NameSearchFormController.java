package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NameSearchFormController implements Initializable {

    @FXML
    private JFXHamburger sidebarHam;

    @FXML
    private VBox sidebarBox;

    @FXML
    private JFXButton historyBtn;

    @FXML
    private JFXButton favouritesBtn;

    @FXML
    private JFXButton settingsBtn;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private JFXDrawer sidebarDrawer;

    @FXML
    private AnchorPane searchPane;

    @FXML
    private JFXButton advanceBtn;

    @FXML JFXNodesList nodesList;

    @FXML
    private JFXToggleButton saveDirBtn;

    @FXML
    private ImageView heartIcon;

    @FXML
    private JFXButton backBtn;

    private HamburgerNextArrowBasicTransition transition;
    private RotateTransition rt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            sidebarBox = FXMLLoader.load(getClass().getResource("/presentation/sidebarContent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sidebarDrawer.setSidePane(sidebarBox);
        sidebarDrawer.open();
        transition = new HamburgerNextArrowBasicTransition(sidebarHam);
        transition.setRate(-1);

        saveDirBtn = new JFXToggleButton();
        saveDirBtn.setText("Save to Directory");
        saveDirBtn.setFocusTraversable(false);
        saveDirBtn.getStylesheets().add("css/round_btn.css");
        saveDirBtn.getStyleClass().add("toggle-button");

        nodesList = new JFXNodesList();
        nodesList.addAnimatedNode(advanceBtn);
        nodesList.addAnimatedNode(saveDirBtn);

        searchPane.getChildren().add(nodesList);
        nodesList.setLayoutY(23);
        nodesList.setLayoutX(533);
        nodesList.setSpacing(5);

        for (Node node: sidebarBox.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    switch (node.getAccessibleText()) {
                        case "History":
                            Parent root1 = null;
                            try {
                                root1 = FXMLLoader.load(getClass().getResource("/presentation/history.fxml"));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            Stage primaryStage1 = (Stage) node.getScene().getWindow();
                            primaryStage1.setTitle("Create Account");
                            Image userIcon1 = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
                            primaryStage1.getIcons().add(userIcon1);
                            primaryStage1.resizableProperty().setValue(Boolean.FALSE);
                            primaryStage1.setScene(new Scene(root1, 600, 465));
                            primaryStage1.show();
                            break;
                        case "Favourites":
                            Parent root2 = null;
                            try {
                                root2 = FXMLLoader.load(getClass().getResource("/presentation/favourites.fxml"));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            Stage primaryStage2 = (Stage) node.getScene().getWindow();
                            primaryStage2.setTitle("Create Account");
                            Image userIcon2 = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
                            primaryStage2.getIcons().add(userIcon2);
                            primaryStage2.resizableProperty().setValue(Boolean.FALSE);
                            primaryStage2.setScene(new Scene(root2, 600, 465));
                            primaryStage2.show();
                            break;
                        case "Settings":
                            Parent root3 = null;
                            try {
                                root3 = FXMLLoader.load(getClass().getResource("/presentation/settings.fxml"));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            Stage primaryStage3 = new Stage();
                            primaryStage3.initStyle(StageStyle.UNDECORATED);
                            primaryStage3.initStyle(StageStyle.TRANSPARENT);
                            primaryStage3.initModality(Modality.APPLICATION_MODAL);
//                            Stage primaryStage3 = (Stage) node.getScene().getWindow();
//                            primaryStage3.setTitle("Create Account");
                            Image userIcon3 = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
                            primaryStage3.getIcons().add(userIcon3);
//                            primaryStage3.resizableProperty().setValue(Boolean.FALSE);
                            primaryStage3.setScene(new Scene(root3, 500, 100));
//                            primaryStage3.show();
                            primaryStage3.showAndWait();
                            break;
                        case "Login":
                            Parent root4 = null;
                            try {
                                root4 = FXMLLoader.load(getClass().getResource("/presentation/login_form.fxml"));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            Stage primaryStage4 = (Stage) node.getScene().getWindow();
                            primaryStage4.setTitle("Create Account");
                            Image userIcon4 = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
                            primaryStage4.getIcons().add(userIcon4);
                            primaryStage4.resizableProperty().setValue(Boolean.FALSE);
                            primaryStage4.setScene(new Scene(root4, 500, 300));
                            primaryStage4.show();
                            break;
                        case "Logout":
                            //logout code
                            break;
                    }
                });
            }
        }

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

    public void backBtnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/presentation/start_form.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        primaryStage.setTitle("Create Account");
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/open-book-icon-32.png"));
        primaryStage.getIcons().add(userIcon);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
