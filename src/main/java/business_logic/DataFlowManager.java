package business_logic;

public class DataFlowManager {

    public static String username;
    public static int userID;
    public static String workspace;


    public void login(String name, int id, String path) {
        username = name;
        userID = id;
        workspace = path;
    }

    public void logout() {
        username = null;
        userID = Integer.parseInt(null);
        workspace = null;
    }

}
