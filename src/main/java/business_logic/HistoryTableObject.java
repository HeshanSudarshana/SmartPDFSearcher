package business_logic;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import user_access.HistoryObject;

public class HistoryTableObject extends RecursiveTreeObject<HistoryTableObject> {

    private StringProperty username;
    private StringProperty keyword;
    private StringProperty search_type;
    private StringProperty time;
    private StringProperty search_path;

    public HistoryTableObject(HistoryObject historyObject) {

        this.setUsername(new SimpleStringProperty(historyObject.getUsername()));
        this.setKeyword(new SimpleStringProperty(historyObject.getKeyword()));
        this.setSearch_type(new SimpleStringProperty(historyObject.getSearch_type()));
        this.setTime(new SimpleStringProperty(historyObject.getTime()));
        this.setSearch_path(new SimpleStringProperty(historyObject.getSearch_path()));
    }

    public HistoryObject historyTableObjectToHistoryObject() {
        HistoryObject historyObject = new HistoryObject(DataFlowManager.getInstance().getUsername(), getKeyword().get(), getSearch_type().get(), getTime().get(), getSearch_path().get());
        return historyObject;
    }


    public StringProperty getUsername() {
        return username;
    }

    public void setUsername(StringProperty username) {
        this.username = username;
    }

    public StringProperty getKeyword() {
        return keyword;
    }

    public void setKeyword(StringProperty keyword) {
        this.keyword = keyword;
    }

    public StringProperty getSearch_type() {
        return search_type;
    }

    public void setSearch_type(StringProperty search_type) {
        this.search_type = search_type;
    }

    public StringProperty getTime() {
        return time;
    }

    public void setTime(StringProperty time) {
        this.time = time;
    }

    public StringProperty getSearch_path() {
        return search_path;
    }

    public void setSearch_path(StringProperty search_path) {
        this.search_path = search_path;
    }
}
