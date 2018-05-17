package business_logic;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import controllers.MethodLoader;
import file_access.FileCrawlerByName;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.util.ArrayList;

public class CrawlerConfig {

    private FileCrawlerByName fileCrawlerByName;
    private DataFlowManager dataFlowManager;
    private MethodLoader methodLoader;

    public CrawlerConfig(String searchPath, String searchTxt) {
        methodLoader = new MethodLoader();
        dataFlowManager = DataFlowManager.getInstance();
        fileCrawlerByName = new FileCrawlerByName(searchPath, dataFlowManager.getWorkspace(), searchTxt);
    }

    public void crawlByName(boolean saveChecker, JFXButton button, ObservableList<PDFFile> fileList, ArrayList<File> copyFileList, JFXTreeTableView<PDFFile> searchResultsTreeTableView, TreeItem<PDFFile> pdfFileTreeItem, String folderName) {
        if(saveChecker) {
            String tempPath = dataFlowManager.getWorkspace() + "/" + folderName + "/";
            fileCrawlerByName.setSavedDirectoryPath(tempPath);
            new File(tempPath).mkdir();
            fileCrawlerByName.crawlFiles(fileList, copyFileList);
            fileCrawlerByName.copyFiles(copyFileList);
        } else {
            fileCrawlerByName.crawlFiles(fileList, copyFileList);
        }
    }
}
