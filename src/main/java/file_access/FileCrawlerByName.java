package file_access;

import business_logic.PDFFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileCrawlerByName {

    private ArrayList<File> copyFileList;
    private ObservableList<PDFFile> fileList;
    private String selectedDirectoryPath, savedDirectoryPath, searchTxt;

    //take required parameters from the constructor
    public FileCrawlerByName(String selectedDirectoryPath, String savedDirectoryPath, String searchTxt) {
        this.selectedDirectoryPath = selectedDirectoryPath;
        this.savedDirectoryPath = savedDirectoryPath;
        this.searchTxt = searchTxt;
        this.copyFileList = new ArrayList<File>();
        this.fileList = FXCollections.observableArrayList();
    }

    //take the selected directory and basic invocation of traversing
    public void crawlFiles() {
        File files[] = new File(selectedDirectoryPath).listFiles();
        traverseDirectory(files);
    }

    //recursive Depth First Search for name search
    public void traverseDirectory(File[] files) {
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    traverseDirectory(file.listFiles());
                } else {
                    String[] type = file.getName().toString().split("\\.(?=[^\\.]+$)");
                    if (type.length > 1) {
                        if (type[1].equals("pdf")) {
                            if(type[0].toLowerCase().contains(searchTxt.toLowerCase())) {
                                copyFileList.add(file);
                                fileList.add(new PDFFile(file));
                            }
                        }
                    }
                }
            }
        }
    }


    //copy a selected file to a given directory
    public void copyFiles() {
        for (File file : copyFileList) {
            try {
                FileUtils.copyFile(file, new File(savedDirectoryPath + "/" + file.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList<PDFFile> getFileList() {
        return fileList;
    }

    public ArrayList<File> getCopyFileList() {
        return copyFileList;
    }
}
