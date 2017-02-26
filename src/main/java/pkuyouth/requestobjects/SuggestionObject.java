package pkuyouth.requestobjects;

/**
 * Created by WangJian on 2017/1/30.
 */
public class SuggestionObject {
    String user_id;
    String user_name;
    String suggestion;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
