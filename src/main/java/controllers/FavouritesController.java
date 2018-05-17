package controllers;

import business_logic.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import user_access.FavouriteObject;
import user_access.HistoryObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavouritesController implements Initializable{

    @FXML
    JFXTreeTableView<FavouriteTableObject> favouritesTreeTableView;

    @FXML
    JFXButton openBtn;

    @FXML
    JFXButton deleteBtn;

    @FXML
    JFXButton deleteAllBtn;

    @FXML
    JFXButton backBtn;

    private TreeItem<FavouriteTableObject> favouriteTableObjectTreeItem;
    private UserConfig userConfig;
    private MethodLoader methodLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userConfig = new UserConfig();
        methodLoader = new MethodLoader();

        JFXTreeTableColumn<FavouriteTableObject, String>  fileName = new JFXTreeTableColumn<>("File Name");
        fileName.setPrefWidth(226);
        fileName.setCellValueFactory(param -> param.getValue().getValue().getFileName());

        JFXTreeTableColumn<FavouriteTableObject, String>  path = new JFXTreeTableColumn<>("Path");
        path.setPrefWidth(226);
        path.setCellValueFactory(param -> param.getValue().getValue().getPath());

        favouritesTreeTableView.getColumns().setAll(fileName, path);
        favouritesTreeTableView.setPlaceholder(new Label("No favourites to show!"));

        if(DataFlowManager.getInstance().getUsername()!=null) {

            refreshTableContent();
        }
    }

    public void openBtnAction(ActionEvent actionEvent) {
        TreeItem<FavouriteTableObject> selectedFile = favouritesTreeTableView.getSelectionModel().getSelectedItem();
        if(selectedFile != null){
            openPDFFile(selectedFile.getValue());
        } else {
            methodLoader.selectFileAlert();
        }
    }

    public void deleteBtnAction(ActionEvent actionEvent) {
        TreeItem<FavouriteTableObject> favouriteTableObjectTreeItem = favouritesTreeTableView.getSelectionModel().getSelectedItem();
        if(favouriteTableObjectTreeItem != null){
            FavouriteTableObject selectedObject = favouritesTreeTableView.getSelectionModel().getSelectedItem().getValue();
            String username = DataFlowManager.getInstance().getUsername();
            if (methodLoader.confirmationAlert()) {
                userConfig.deleteFavouriteObject(username, selectedObject.getPath().get());
                refreshTableContent();
            } else {
                //do nothing
            }
        } else {
            methodLoader.selectFileAlert();
        }
    }

    public void deleteAllBtnAction(ActionEvent actionEvent) {
        int numberOfItems = favouritesTreeTableView.getCurrentItemsCount();
        if(numberOfItems > 0) {
            String username = DataFlowManager.getInstance().getUsername();
            if(methodLoader.confirmationAlert()) {
                userConfig.deleteAllFavourites(username);
                refreshTableContent();
            } else {
                //do nothing
            }
        } else {
            methodLoader.noItemAlert("No Favourites", "No favourites to delete!");
        }
    }

    public void backBtnAction(ActionEvent actionEvent) throws IOException {
        methodLoader.loadNextForm(backBtn);
    }

    public void refreshTableContent() {
        ArrayList<FavouriteObject> favouritesList = userConfig.getFavourites(DataFlowManager.getInstance().getUsername());
        ObservableList<FavouriteTableObject> favouriteTableObjectsList = FXCollections.observableArrayList();
        for (int i=0; i<favouritesList.size(); i++) {
            favouriteTableObjectsList.add(new FavouriteTableObject(favouritesList.get(i)));
        }

        favouriteTableObjectTreeItem = new RecursiveTreeItem<>(favouriteTableObjectsList, RecursiveTreeObject::getChildren);

        favouritesTreeTableView.setRoot(favouriteTableObjectTreeItem);
        favouritesTreeTableView.setShowRoot(false);
    }

    public void openPDFFile(FavouriteTableObject favouriteTableObject) {
        File fileToBeOpened = new File(favouriteTableObject.getPath().get());
        System.out.println(String.valueOf(favouriteTableObject.getPath()));
        methodLoader.openPDFFile(fileToBeOpened);
    }
}
