package business_logic;

import controllers.MethodLoader;
import user_access.HistoryObject;

public class DataFlowManager {

    private static DataFlowManager dataFlowManager;
    private String username;
    private int userID;
    private String workspace;
    private String previousStage;
    private HistoryObject dataLoader;

    private DataFlowManager() { }

    public static DataFlowManager getInstance() {
        if(dataFlowManager == null) {
            dataFlowManager = new DataFlowManager();
        }
        return dataFlowManager;
    }

    public void login(String name, int id, String path) {
        setUsername(name);
        setUserID(id);
        setWorkspace(path);
    }

    public void logout() {
        setUsername(null);
        setUserID(0);
        setWorkspace(null);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public String getPreviousStage() {
        return previousStage;
    }

    public void setPreviousStage(String previousStage) {
        this.previousStage = previousStage;
    }

    public HistoryObject getDataLoader() {
        return dataLoader;
    }

    public void setDataLoader(HistoryObject dataLoader) {
        this.dataLoader = dataLoader;
    }
}
