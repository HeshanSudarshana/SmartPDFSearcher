package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.animation.RotateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class MethodLoader {

    public void createAccountFormLoad(JFXButton btn) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/presentation/add_user_form.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage primaryStage = (Stage) btn.getScene().getWindow();
        primaryStage.setTitle("Create Account");
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/account-plus.png"));
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
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/account.png"));
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
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/account-settings-variant.png"));
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
        Image userIcon = new Image(getClass().getResourceAsStream("/icons/account-remove.png"));
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
        Image userIcon1 = new Image(getClass().getResourceAsStream("/icons/ic_history_black_24dp_2x.png"));
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
        Image userIcon2 = new Image(getClass().getResourceAsStream("/icons/ic_favorite_black_24dp_2x.png"));
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

    public int heartAnimation(int addFav, ImageView heartIcon) {
        if(addFav==1) {
            heartIcon.setImage(new Image(getClass().getResourceAsStream("/icons/001-like.png")));
            addFav *= -1;
        } else {
            heartIcon.setImage(new Image(getClass().getResourceAsStream("/icons/002-like-1.png")));
            addFav *= -1;
        }
        return addFav;
    }
}
