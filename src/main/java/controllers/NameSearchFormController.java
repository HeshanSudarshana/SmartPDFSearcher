package controllers;

import business_logic.CrawlerConfig;
import business_logic.DataFlowManager;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import business_logic.PDFFile;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import user_access.DBConnector;
import user_access.FavouriteObject;

import javax.xml.crypto.Data;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.ResourceBundle;
import java.util.Set;

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

    @FXML
    private JFXButton searchBtn;

    @FXML
    private JFXButton browseBtn;

    @FXML
    private JFXTextField searchDirectoryTxt;

    @FXML
    private JFXTextField searchTxt;

    @FXML
    private JFXTreeTableView<PDFFile> searchResultsTreeTableView;

    @FXML
    private JFXButton fileOpenBtn;

    @FXML
    private JFXButton addFavBtn;

    private HamburgerNextArrowBasicTransition transition;
    private RotateTransition rt;
    private MethodLoader methodLoader;
    private TreeItem<PDFFile> pdfFileTreeItem;
    private CrawlerConfig crawlerConfig;
    private ObservableList<PDFFile> pdfFileObservableList;
    private ArrayList<File> copyFileList;
    private DBConnector dbConnector;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnector = new DBConnector();
        methodLoader = new MethodLoader();
        pdfFileObservableList = FXCollections.observableArrayList();
        copyFileList = new ArrayList<>();
        DataFlowManager.getInstance().setPreviousStage("name_search");
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

        methodLoader.loadSideBar(sidebarBox);

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

            TreeItem<PDFFile> selectedItem = (TreeItem<PDFFile>) newValue;
            System.out.println("Selected Text : " + selectedItem.getValue().getFileName().get());
            if(DataFlowManager.getInstance().getUsername()!=null){
                ArrayList<FavouriteObject> arrayList = dbConnector.getFavourites(DataFlowManager.getInstance().getUsername());
                ArrayList<String> pathList = new ArrayList<>();
                for(FavouriteObject i: arrayList) {
                    pathList.add(i.getPath());
                }
                if(pathList.contains(selectedItem.getValue().getFilePath().get())) {
                    heartIcon.setImage(new Image(getClass().getResourceAsStream("/icons/002-like-1.png")));
                } else {
                    heartIcon.setImage(new Image(getClass().getResourceAsStream("/icons/001-like.png")));
                }

            } else {
                heartIcon.setImage(new Image(getClass().getResourceAsStream("/icons/001-like.png")));
            }
        });

//        Set<Thread> threadSet = Thread.getAllStackTraces().keySet(); Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]); for (Thread t : threadArray) { if (t.isAlive() && !t.isDaemon()) { System.out.println(t); } }
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
            methodLoader.heartAnimation(heartIcon, DataFlowManager.getInstance().getUsername(), searchResultsTreeTableView.getSelectionModel().getSelectedItem().getValue().getFilePath().get(), searchResultsTreeTableView);
        } else {
            methodLoader.loginAlert(addFavBtn);
        }

    }

    public void searchBtnAction(ActionEvent actionEvent) {

        if(DataFlowManager.getInstance().getUsername()!=null) {
            dbConnector.addToHistory(DataFlowManager.getInstance().getUsername(), searchTxt.getText(), "Name", searchDirectoryTxt.getText());
        }

        try {
            Runnable task = () -> {

                crawlerConfig = new CrawlerConfig(searchDirectoryTxt.getText(), searchTxt.getText());
                pdfFileObservableList.clear();
                copyFileList.clear();
                pdfFileTreeItem = new RecursiveTreeItem<>(pdfFileObservableList, RecursiveTreeObject::getChildren);

                Thread thread = Thread.currentThread();

                crawlerConfig.crawlByName(saveDirBtn.isSelected(),searchBtn,pdfFileObservableList,copyFileList,searchResultsTreeTableView,pdfFileTreeItem);

                Platform.runLater(() -> {
                    searchResultsTreeTableView.setRoot(pdfFileTreeItem);
                    searchResultsTreeTableView.setShowRoot(false);
                });
            };

            new Thread(task).start();

//            Set<Thread> threadSet = Thread.getAllStackTraces().keySet(); Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]); for (Thread t : threadArray) { if (t.isAlive() && !t.isDaemon()) { System.out.println(t); } }

        } catch (ConcurrentModificationException error) {
            //
        }


    }

    public void browseBtnAction(ActionEvent actionEvent) {
        methodLoader.loadDirectorySelector(searchDirectoryTxt, "Select a Directory to Search");
    }

    public void fileOpenBtnAction(ActionEvent actionEvent) {
        TreeItem<PDFFile> selectedFile = searchResultsTreeTableView.getSelectionModel().getSelectedItem();
        if(selectedFile != null){
            openPDFFile(selectedFile.getValue());
        } else {
            methodLoader.selectFileAlert();
        }
    }

    public void openPDFFile(PDFFile pdfFile) {
        File fileToBeOpened = new File(pdfFile.getFilePath().get());
        System.out.println(String.valueOf(pdfFile.getFilePath()));
        if(fileToBeOpened.exists()) {
            if(Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(fileToBeOpened);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                methodLoader.desktopEnvNotSupportedAlert();
            }
        } else {
            methodLoader.fileDoesnotExistAlert();
        }
    }


}
