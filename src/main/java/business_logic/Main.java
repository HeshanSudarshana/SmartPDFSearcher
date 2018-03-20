package business_logic;

import controllers.MethodLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private MethodLoader methodLoader;

    @Override
    public void start(Stage primaryStage) throws Exception{

        methodLoader = new MethodLoader();
        methodLoader.startFormLoad(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
