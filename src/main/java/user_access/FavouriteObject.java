package user_access;

public class FavouriteObject {
    private String username;
    private String path;

    public FavouriteObject(String username, String path) {
        this.setUsername(username);
        this.setPath(path);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
