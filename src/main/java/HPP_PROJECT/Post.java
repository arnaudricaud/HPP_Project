package HPP_PROJECT;

public class Post {

    private int ts;
    private int post_id;
    private int user_id;
    private String user;

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public Post(int time) {
        ts = time;
    }
    
    /**
     * TODO Définir les methodes pour la classe post
     **/


}