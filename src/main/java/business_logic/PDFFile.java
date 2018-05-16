package business_logic;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import user_access.FavouriteObject;

import java.io.File;
import java.text.SimpleDateFormat;

public class PDFFile extends RecursiveTreeObject<PDFFile> {

    private StringProperty fileName;
    private StringProperty filePath;
    private StringProperty dateModified;

    //get a file an save its required attributes to a simpler class
    public PDFFile(File file) {
        this.setFileName(new SimpleStringProperty(file.getName()));
        this.setFilePath(new SimpleStringProperty(file.getAbsolutePath()));
        long tempDate = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        this.setDateModified(new SimpleStringProperty(sdf.format(tempDate)));
    }


    public StringProperty getFileName() {
        return fileName;
    }

    public void setFileName(StringProperty fileName) {
        this.fileName = fileName;
    }

    public StringProperty getFilePath() {
        return filePath;
    }

    public void setFilePath(StringProperty filePath) {
        this.filePath = filePath;
    }

    public StringProperty getDateModified() {
        return dateModified;
    }

    public void setDateModified(StringProperty dateModified) {
        this.dateModified = dateModified;
    }

    public FavouriteObject PDFFiletoFavouriteObject(String username) {
        FavouriteObject favouriteObject = new FavouriteObject(username, this.getFilePath().get());
        return favouriteObject;
    }
}
