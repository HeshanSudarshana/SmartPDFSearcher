package business_logic;

import javafx.beans.property.StringProperty;
import user_access.DBConnector;
import user_access.FavouriteObject;
import user_access.HistoryObject;
import user_access.User;

import java.awt.*;
import java.io.IOException;
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

    public void editUser(int userID, String username, String password, String workspace) {
        dbConnector.updateUser(userID, username, password, workspace);
    }

    public void deleteUser (String username) {
        dbConnector.deleteUser(username);
    }

    public void addHistoryObject(String username, String keyword, String search_type, String search_path) {
        dbConnector.addToHistory(username, keyword, search_type, search_path);
    }

    public ArrayList<HistoryObject> getHistory(String username) {
        ArrayList<HistoryObject> list = dbConnector.getHistory(username);
        return list;
    }

    public void deleteHistoryObject(String username, String keyword, String search_type, String time, String search_path) {
        dbConnector.deleteHistoryObject(username, keyword, search_type, time, search_path);
    }

    public void deleteAllHistory(String username) {
        dbConnector.deleteAllHistory(username);
    }

    public ArrayList<FavouriteObject> getFavourites(String username) {
        ArrayList<FavouriteObject> list = dbConnector.getFavourites(username);
        return list;
    }

    public void deleteFavouriteObject(String username, String path) {
        dbConnector.deleteFavoriteObject(username, path);
    }

    public void addFavouriteObject(String username, String filename, String path) {
        dbConnector.addToFavourites(username, filename, path);
    }

    public void deleteAllFavourites(String username) {
        dbConnector.deleteAllFavourites(username);
    }

}
