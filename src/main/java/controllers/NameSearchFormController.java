package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import business_logic.PDFFile;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private HamburgerNextArrowBasicTransition transition;
    private RotateTransition rt;
    private MethodLoader methodLoader;
    private int addFav;
    private TreeItem<PDFFile> pdfFileTreeItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        methodLoader = new MethodLoader();
        addFav = 1;
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
        addFav = methodLoader.heartAnimation(addFav, heartIcon);
    }

    public void searchBtnAction(ActionEvent actionEvent) {

    }

    public void browseBtnAction(ActionEvent actionEvent) {
        methodLoader.loadDirectorySelector(searchDirectoryTxt, "Select a Directory to Search");
    }
}
