package user_access;

public class User {

    private int userID;
    private String username;
    private String password;
    private String workspacePath;

    public User(String username, String password, String workspacePath) {

        this.setUsername(username);
        this.setPassword(password);
        this.setWorkspacePath(workspacePath);

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorkspacePath() {
        return workspacePath;
    }

    public void setWorkspacePath(String workspacePath) {
        this.workspacePath = workspacePath;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
