package pkuyouth.responsevos;

/**
 * Created by WangJian on 2017/1/30.
 */
public class Comment {
    String user_id;
    String user_name;
    String user_img_url;
    String comment;

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

    public String getUser_img_url() {
        return user_img_url;
    }

    public void setUser_img_url(String user_img_url) {
        this.user_img_url = user_img_url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
