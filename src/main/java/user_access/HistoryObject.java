package user_access;

public class HistoryObject {
    private String username;
    private String keyword;
    private String search_type;
    private String time;

    public HistoryObject(String username, String keyword, String search_type, String time) {
        this.setUsername(username);
        this.setKeyword(keyword);
        this.setSearch_type(search_type);
        this.setTime(time);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
