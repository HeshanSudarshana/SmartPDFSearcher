package business_logic;

import com.jfoenix.controls.JFXTreeTableView;
import file_access.FileCrawlerByName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CrawlerConfigNameTest {

    @Test
    public void crawlByName() {
        ObservableList<PDFFile> postPDFFiles = FXCollections.observableArrayList();
        ArrayList<File> arrayList = new ArrayList<>();
        ObservableList<PDFFile> prePDFFiles = FXCollections.observableArrayList();
        prePDFFiles.add(new PDFFile(new File("/home/heshan/spsTest/licat/Applications of Linear Alge.pdf")));

        String path = "/home/heshan/spsTest/licat/";

        new FileCrawlerByName(path, path, "a").crawlFiles(postPDFFiles, arrayList);

        ArrayList<PDFFile> preArr = new ArrayList<>();
        for(PDFFile i: prePDFFiles){
            preArr.add(i);
        }

        ArrayList<PDFFile> postArr = new ArrayList<>();
        for(PDFFile i: prePDFFiles){
            postArr.add(i);
        }

        assertTrue(preArr.equals(postArr));
    }
}