package controllers;

import business_logic.CrawlerConfigName;
import business_logic.DataFlowManager;
import business_logic.PDFFile;
import business_logic.UserConfig;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import file_access.RecursiveWalk;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import user_access.FavouriteObject;
import user_access.HistoryObject;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ForkJoinPool;

public class ContentSearchFormController implements Initializable {

    @FXML
    private JFXHamburger sidebarHam;

    @FXML
    private VBox sidebarBox;

    @FXML
    private JFXDrawer sidebarDrawer;

    @FXML
    private AnchorPane searchPane;

    @FXML
    private JFXButton advanceBtn;

    @FXML
    private ImageView heartIcon;

    @FXML
    JFXNodesList nodesList;

    @FXML
    private JFXToggleButton saveDirBtn;

    @FXML
    private JFXToggleButton advanceSearchBtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton addFavBtn;

    @FXML
    private JFXTextField searchDirectoryTxt;

    @FXML
    private JFXTextField keywordTxt;

    @FXML
    private JFXButton browseBtn;

    @FXML
    private JFXButton searchBtn;

    @FXML
    private JFXTreeTableView<PDFFile> searchResultsTreeTableView;

    private HamburgerNextArrowBasicTransition transition;
    private RotateTransition rt;
    private MethodLoader methodLoader;
    private UserConfig userConfig;
    private ArrayList<File> fileList;
    private ObservableList<PDFFile> pdfFileObservableList;
    private TreeItem<PDFFile>  pdfFileTreeItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileList = new ArrayList<>();
        pdfFileObservableList = FXCollections.observableArrayList();
        methodLoader = new MethodLoader();
        userConfig = new UserConfig();
        DataFlowManager.getInstance().setPreviousStage("content_search");
        try {
            sidebarBox = FXMLLoader.load(getClass().getResource("/presentation/sidebarContent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sidebarDrawer.setSidePane(sidebarBox);
        sidebarDrawer.open();
        transition = new HamburgerNextArrowBasicTransition(sidebarHam);
        transition.setRate(-1);

        advanceSearchBtn = new JFXToggleButton();
        advanceSearchBtn.setText("Advanced Search");
        advanceSearchBtn.setFocusTraversable(false);
        advanceSearchBtn.getStylesheets().add("css/round_btn.css");
        advanceSearchBtn.getStyleClass().add("toggle-button");

        saveDirBtn = new JFXToggleButton();
        saveDirBtn.setText("Save to Directory");
        saveDirBtn.setFocusTraversable(false);
        saveDirBtn.getStylesheets().add("css/round_btn.css");
        saveDirBtn.getStyleClass().add("toggle-button");

        nodesList = new JFXNodesList();
        nodesList.addAnimatedNode(advanceBtn);
        nodesList.addAnimatedNode(saveDirBtn);
        nodesList.addAnimatedNode(advanceSearchBtn);

        searchPane.getChildren().add(nodesList);
        nodesList.setLayoutY(23);
        nodesList.setLayoutX(533);
        nodesList.setSpacing(5);

        methodLoader.loadSideBar(sidebarBox);

        if(DataFlowManager.getInstance().getDataLoader()!=null) {
            setValuesGUI(DataFlowManager.getInstance().getDataLoader());
            DataFlowManager.getInstance().setDataLoader(null);
        }

        JFXTreeTableColumn <PDFFile, String> name = new JFXTreeTableColumn<PDFFile, String>("Name");
        name.setPrefWidth(218);
        name.setCellValueFactory(param -> param.getValue().getValue().getFileName());

        JFXTreeTableColumn <PDFFile, String> path = new JFXTreeTableColumn<PDFFile, String>("Path");
        path.setPrefWidth(218);
        path.setCellValueFactory(param -> param.getValue().getValue().getFilePath());

        JFXTreeTableColumn <PDFFile, String> date = new JFXTreeTableColumn<PDFFile, String>("Date Modified");
        date.setPrefWidth(218);
        date.setCellValueFactory(param -> param.getValue().getValue().getDateModified());

        searchResultsTreeTableView.getColumns().setAll(name, path, date);
        searchResultsTreeTableView.setPlaceholder(new Label("No PDFs to show!"));

        searchResultsTreeTableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            methodLoader.realTimeFavLoader(newValue, heartIcon);
        });

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
        methodLoader.rotateTransition(rt, advanceBtn);
    }

    public void backBtnAction(ActionEvent actionEvent) throws IOException {
        methodLoader.startFormLoad((Stage) backBtn.getScene().getWindow());
    }

    public void addFavBtnAction(ActionEvent actionEvent) {
        if(DataFlowManager.getInstance().getUsername()!=null) {
            methodLoader.heartAnimation(heartIcon, DataFlowManager.getInstance().getUsername(), searchResultsTreeTableView.getSelectionModel().getSelectedItem().getValue().getFileName().get(), searchResultsTreeTableView.getSelectionModel().getSelectedItem().getValue().getFilePath().get(), searchResultsTreeTableView);
        } else {
            methodLoader.loginAlert(addFavBtn);
        }
    }

    public void setValuesGUI(HistoryObject historyObject) {
        searchDirectoryTxt.setText(historyObject.getSearch_path());
        keywordTxt.setText(historyObject.getKeyword());
    }

    public void filOpenBtnAction(ActionEvent actionEvent) {
        TreeItem<PDFFile> selectedFile = searchResultsTreeTableView.getSelectionModel().getSelectedItem();
        if(selectedFile != null){
            openPDFFile(selectedFile.getValue());
        } else {
            methodLoader.selectFileAlert();
        }
    }

    public void browseBtnAction(ActionEvent actionEvent) {
        methodLoader.loadDirectorySelector(searchDirectoryTxt, "Select a Directory to Search");
    }

    public void searchBtnAction(ActionEvent actionEvent) {
        if (searchDirectoryTxt.getText()==null || searchDirectoryTxt.getText().equals("")) {
            methodLoader.selectDirectoryToSearchAlert();
        } else {
            if(DataFlowManager.getInstance().getUsername()!=null) {
                userConfig.addHistoryObject(DataFlowManager.getInstance().getUsername(), keywordTxt.getText(), "Name", searchDirectoryTxt.getText());

                if (saveDirBtn.isSelected()) {
                    String folderName = methodLoader.folderNameAlert(keywordTxt.getText().toLowerCase());
                    searchTask(searchDirectoryTxt.getText());
                    //copy a selected file to a given directory
                    for (File file : fileList) {
                        try {
                            FileUtils.copyFile(file, new File(DataFlowManager.getInstance().getWorkspace() + "/" + folderName + "/" + file.getName()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    methodLoader.copiedSuccessfully();
                } else {
                    searchTask(searchDirectoryTxt.getText());
                }

            } else {
                if(saveDirBtn.isSelected()) {
                    methodLoader.loginAlert(searchBtn);
                } else {
                    searchTask(searchDirectoryTxt.getText());
                }
            }
        }
    }

    public void openPDFFile(PDFFile pdfFile) {
        File fileToBeOpened = new File(pdfFile.getFilePath().get());
        System.out.println(String.valueOf(pdfFile.getFilePath()));
        methodLoader.openPDFFile(fileToBeOpened);
    }

    public void searchTask(String search_path) {

        Runnable task = () -> {

            pdfFileObservableList.clear();

            pdfFileTreeItem = new RecursiveTreeItem<>(pdfFileObservableList, RecursiveTreeObject::getChildren);

            RecursiveWalk recursiveWalk =  new RecursiveWalk(Paths.get(search_path), fileList, keywordTxt.getText(), advanceSearchBtn.isSelected(), pdfFileObservableList);
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            forkJoinPool.invoke(recursiveWalk);

            Platform.runLater(() -> {
                searchResultsTreeTableView.setRoot(pdfFileTreeItem);
                searchResultsTreeTableView.setShowRoot(false);
            });
        };

        new Thread(task).start();
    }
}
