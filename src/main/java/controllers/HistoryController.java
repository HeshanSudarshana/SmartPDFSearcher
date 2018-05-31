package controllers;

import business_logic.DataFlowManager;
import business_logic.HistoryTableObject;
import business_logic.UserConfig;
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
import user_access.HistoryObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HistoryController implements Initializable{

    @FXML
    JFXTreeTableView<HistoryTableObject> historyTreeTableView;

    @FXML
    JFXButton backtoSearchBtn;

    @FXML
    JFXButton deleteBtn;

    @FXML
    JFXButton deleteAllBtn;

    @FXML
    JFXButton backBtn;

    private TreeItem<HistoryTableObject> historyTableObjectTreeItem;
    private UserConfig userConfig;
    private MethodLoader methodLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userConfig = new UserConfig();
        methodLoader = new MethodLoader();

        JFXTreeTableColumn<HistoryTableObject, String> keyword = new JFXTreeTableColumn<>("Searched Content");
        keyword.setPrefWidth(113);
        keyword.setCellValueFactory(param -> param.getValue().getValue().getKeyword());

        JFXTreeTableColumn <HistoryTableObject, String> search_type = new JFXTreeTableColumn<>("Search Type");
        search_type.setPrefWidth(113);
        search_type.setCellValueFactory(param -> param.getValue().getValue().getSearch_type());

        JFXTreeTableColumn <HistoryTableObject, String> time = new JFXTreeTableColumn<>("Time");
        time.setPrefWidth(113);
        time.setCellValueFactory(param -> param.getValue().getValue().getTime());

        JFXTreeTableColumn <HistoryTableObject, String> search_path = new JFXTreeTableColumn<>("Searched Path");
        search_path.setPrefWidth(113);
        search_path.setCellValueFactory(param -> param.getValue().getValue().getSearch_path());

        historyTreeTableView.getColumns().setAll(keyword, search_type, time, search_path);
        historyTreeTableView.setPlaceholder(new Label("No history to load!"));

        if(DataFlowManager.getInstance().getUsername()!=null) {

            refreshTableContent();
        }

    }

    public void backBtnAction(ActionEvent actionEvent) throws IOException {
        methodLoader.loadNextForm(backBtn);
    }

    public void backtoSearchBtnAction(ActionEvent actionEvent) throws IOException {
        TreeItem<HistoryTableObject> historyTableObjectTreeItem = historyTreeTableView.getSelectionModel().getSelectedItem();
        if(historyTableObjectTreeItem != null){
            HistoryTableObject selectedObject = historyTreeTableView.getSelectionModel().getSelectedItem().getValue();
            String username = DataFlowManager.getInstance().getUsername();
            if(selectedObject.getSearch_type().get().equals("Name")) {
                DataFlowManager.getInstance().setPreviousStage("name_search");
            } else {
                DataFlowManager.getInstance().setPreviousStage("content_search");
            }
            DataFlowManager.getInstance().setDataLoader(selectedObject.historyTableObjectToHistoryObject());
            methodLoader.loadNextForm(backBtn);
        } else {
            methodLoader.selectFileAlert();
        }
    }

    public void deleteBtnAction(ActionEvent actionEvent) {
        TreeItem<HistoryTableObject> historyTableObjectTreeItem = historyTreeTableView.getSelectionModel().getSelectedItem();
        if(historyTableObjectTreeItem != null){
            HistoryTableObject selectedObject = historyTreeTableView.getSelectionModel().getSelectedItem().getValue();
            String username = DataFlowManager.getInstance().getUsername();
            if (methodLoader.confirmationAlert()) {
                userConfig.deleteHistoryObject(username, selectedObject.getKeyword().get(), selectedObject.getSearch_type().get(), selectedObject.getTime().get(), selectedObject.getSearch_path().get());
                refreshTableContent();
            } else {
                //do nothing
            }
        } else {
            methodLoader.selectFileAlert();
        }
    }

    public void deleteAllBtnAction(ActionEvent actionEvent) {
        int numberOfItems = historyTreeTableView.getCurrentItemsCount();
        if(numberOfItems > 0) {
            String username = DataFlowManager.getInstance().getUsername();
            if(methodLoader.confirmationAlert()) {
                userConfig.deleteAllHistory(username);
                refreshTableContent();
            } else {
                //do nothing
            }
        } else {
            methodLoader.noItemAlert("No History", "There is no history to delete!");
        }
    }

    public void refreshTableContent() {
        ArrayList<HistoryObject> historyList = userConfig.getHistory(DataFlowManager.getInstance().getUsername());
        ObservableList<HistoryTableObject> historyTableObjectList = FXCollections.observableArrayList();
        for (int i=0; i<historyList.size(); i++) {
            historyTableObjectList.add(new HistoryTableObject(historyList.get(i)));
        }

        historyTableObjectTreeItem = new RecursiveTreeItem<>(historyTableObjectList, RecursiveTreeObject::getChildren);

        historyTreeTableView.setRoot(historyTableObjectTreeItem);
        historyTreeTableView.setShowRoot(false);
    }
}
