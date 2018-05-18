package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoPageController implements Initializable{

    private MethodLoader methodLoader;

    @FXML
    private JFXButton backBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        methodLoader = new MethodLoader();
    }

    public void backBtnAction(ActionEvent actionEvent) throws IOException {
        methodLoader.loadNextForm(backBtn);
    }

}
