package business_logic;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import user_access.FavouriteObject;

public class FavouriteTableObject extends RecursiveTreeObject<FavouriteTableObject> {

    private StringProperty username;
    private StringProperty path;
    private StringProperty fileName;

    public FavouriteTableObject(FavouriteObject favouriteObject) {

        this.setUsername(new SimpleStringProperty(favouriteObject.getUsername()));
        this.setFileName(new SimpleStringProperty(favouriteObject.getFileName()));
        this.setPath(new SimpleStringProperty(favouriteObject.getPath()));

    }

    public FavouriteObject convertToFavouriteObject() {
        FavouriteObject favouriteObject = new FavouriteObject(this.getUsername().get(), this.getFileName().get(), this.getPath().get());
        return favouriteObject;
    }


    public StringProperty getUsername() {
        return username;
    }

    public void setUsername(StringProperty username) {
        this.username = username;
    }

    public StringProperty getPath() {
        return path;
    }

    public void setPath(StringProperty path) {
        this.path = path;
    }

    public StringProperty getFileName() {
        return fileName;
    }

    public StringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(StringProperty fileName) {
        this.fileName = fileName;
    }
}
