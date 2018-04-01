package business_logic;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import user_access.FavouriteObject;

public class FavouriteTableObject extends RecursiveTreeObject<FavouriteTableObject> {

    private StringProperty username;
    private StringProperty path;

    public FavouriteTableObject(FavouriteObject favouriteObject) {

        this.username = new SimpleStringProperty(favouriteObject.getUsername());
        this.path = new SimpleStringProperty(favouriteObject.getPath());

    }
}
