package HPP_PROJECT;

import org.joda.time.DateTime;

public class Post {

    private DateTime ts;
    private int post_id;
    private int user_id;
    private String user;

    private int score;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public DateTime getTs() {
        return ts;
    }

    public void setTs(DateTime ts) {
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


    public Post(DateTime time, int post_id, int user_id, String user, int score) {
        ts = time;
        this.post_id = post_id;
        this.user_id = user_id;
        this.user = user;
        this.score = 10;

    }

    /**
     * TODO Définir les methodes pour la classe post
     **/


}