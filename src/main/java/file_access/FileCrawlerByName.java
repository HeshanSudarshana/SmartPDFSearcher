package file_access;

import business_logic.PDFFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileCrawlerByName {

    private String selectedDirectoryPath;
    private String savedDirectoryPath;
    private String searchTxt;

    //take required parameters from the constructor
    public FileCrawlerByName(String selectedDirectoryPath, String savedDirectoryPath, String searchTxt) {
        this.selectedDirectoryPath = selectedDirectoryPath;
        this.savedDirectoryPath = savedDirectoryPath;
        this.setSearchTxt(searchTxt);
    }

    //take the selected directory and basic invocation of traversing
    public void crawlFiles(ObservableList<PDFFile> fileList, ArrayList<File> copyFileList) {
        File files[] = new File(selectedDirectoryPath).listFiles();
        traverseDirectory(files, fileList, copyFileList);
    }

    //recursive Depth First Search for name search
    public void traverseDirectory(File[] files, ObservableList<PDFFile> fileList, ArrayList<File> copyFileList) {
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    traverseDirectory(file.listFiles(), fileList, copyFileList);
                } else {
                    String[] type = file.getName().toString().split("\\.(?=[^\\.]+$)");
                    if (type.length > 1) {
                        if (type[1].equals("pdf")) {
                            if(type[0].toLowerCase().contains(getSearchTxt().toLowerCase())) {
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
    public void copyFiles(ArrayList<File> copyFileList) {
        for (File file : copyFileList) {
            try {
                FileUtils.copyFile(file, new File(savedDirectoryPath + "/" + file.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getSearchTxt() {
        return searchTxt;
    }

    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }

    public String getSavedDirectoryPath() {
        return savedDirectoryPath;
    }

    public void setSavedDirectoryPath(String savedDirectoryPath) {
        this.savedDirectoryPath = savedDirectoryPath;
    }
}
