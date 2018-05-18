package business_logic;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import controllers.MethodLoader;
import file_access.FileCrawlerByContent;
import file_access.FileCrawlerByName;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CrawlerConfigName {

    private FileCrawlerByName fileCrawlerByName;
    private FileCrawlerByContent fileCrawlerByContent;
    private DataFlowManager dataFlowManager;
    private MethodLoader methodLoader;

    public CrawlerConfigName(String searchPath, String searchTxt) {
        methodLoader = new MethodLoader();
        dataFlowManager = DataFlowManager.getInstance();
        if (DataFlowManager.getInstance().getPreviousStage().equals("name_search")) {
            fileCrawlerByName = new FileCrawlerByName(searchPath, dataFlowManager.getWorkspace(), searchTxt);
        }
    }

    public void crawlByName(boolean saveChecker, ObservableList<PDFFile> fileList, ArrayList<File> copyFileList, JFXTreeTableView<PDFFile> searchResultsTreeTableView, TreeItem<PDFFile> pdfFileTreeItem, String folderName) {
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
