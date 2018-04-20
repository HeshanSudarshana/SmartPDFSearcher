package business_logic;

import user_access.DBConnector;
import user_access.FavouriteObject;
import user_access.HistoryObject;
import user_access.User;

import java.util.ArrayList;

public class UserConfig {

    private DBConnector dbConnector;

    public UserConfig() {
        dbConnector = new DBConnector();
    }

    public void addUser(String name, String password, String workspace) {
        User user = new User(name, password, workspace);
        dbConnector.addUser(user);
    }

    public User getUserDetails(String username) {
        User user = dbConnector.getUserDetails(username);
        return user;
    }

    public void deleteUser (String username) {
        dbConnector.deleteUser(username);
    }

    public void addHistoryObject(String username, String keyword, String search_type, String search_path) {
        dbConnector.addToHistory(username, keyword, search_type, search_path);
    }

    public ArrayList<HistoryObject> getHistory(int userID) {
        ArrayList<HistoryObject> list = dbConnector.getHistory(userID);
        return list;
    }

    public void deleteHistoryObject(String username, String keyword, String search_type, String time, String search_path) {
        dbConnector.deleteHistoryObject(username, keyword, search_type, time, search_path);
    }

    public void deleteAllHistory(String username) {
        dbConnector.deleteAllHistory(username);
    }

    public ArrayList<FavouriteObject> getFavourites(int userID) {
        ArrayList<FavouriteObject> list = dbConnector.getFavourites(userID);
        return list;
    }

    public void deleteFavouriteObject(String username, String path) {
        dbConnector.deleteFavoriteObject(username, path);
    }

    public void deleteAllFavourites(String username) {
        dbConnector.deleteAllFavourites(username);
    }

    public void addFavouriteObject(String username, String path) {
        dbConnector.addToFavourites(username, path);
    }

}
