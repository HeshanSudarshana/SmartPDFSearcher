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

    public HistoryTableObject(HistoryObject historyObject) {

        this.username = new SimpleStringProperty(historyObject.getUsername());
        this.keyword = new SimpleStringProperty(historyObject.getKeyword());
        this.search_type = new SimpleStringProperty(historyObject.getSearch_type());
        this.time = new SimpleStringProperty(historyObject.getTime());

    }
}
