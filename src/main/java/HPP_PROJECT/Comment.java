package HPP_PROJECT;

public class Comment {


    private int ts; //is the comment's timestamp
    private int comment_id; // is the unique id of the comment
    private int user_id;// 	is the unique id of the user
    private String comment; // is a string containing the actual comment
    private String user; // 	is a string containing the actual user name
    private int comment_replied; // is the id of the comment being replied to (-1 if the tuple is a reply to a post)
    private int post_commented; // is the id of the post being commented (-1 if the tuple is a reply to a comment)


    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getComment_replied() {
        return comment_replied;
    }

    public void setComment_replied(int comment_replied) {
        this.comment_replied = comment_replied;
    }

    public int getPost_commented() {
        return post_commented;
    }

    public void setPost_commented(int post_commented) {
        this.post_commented = post_commented;
    }

    public Comment(int time) {
        ts = time;
    }


}

/**
 * TODO Définir les methodes pour la classe comment
 **/
