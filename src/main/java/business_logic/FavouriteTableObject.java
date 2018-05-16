package business_logic;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import user_access.FavouriteObject;

public class FavouriteTableObject extends RecursiveTreeObject<FavouriteTableObject> {

    private StringProperty username;
    private StringProperty path;

    public FavouriteTableObject(FavouriteObject favouriteObject) {

        this.setUsername(new SimpleStringProperty(favouriteObject.getUsername()));
        this.setPath(new SimpleStringProperty(favouriteObject.getPath()));

    }

    public FavouriteObject convertToFavouriteObject() {
        FavouriteObject favouriteObject = new FavouriteObject(this.getUsername().get(), this.getPath().get());
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
}
