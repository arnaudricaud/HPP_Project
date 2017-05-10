package HPP_PROJECT;

import org.joda.time.DateTime;

public class Comment {


    private DateTime ts; //is the comment's timestamp
    private int comment_id; // is the unique id of the comment
    private int user_id;// 	is the unique id of the user
    private String user; // 	is a string containing the actual user name
    private int comment_replied; // is the id of the comment being replied to (-1 if the tuple is a reply to a post)
    private int post_commented; // is the id of the post being commented (-1 if the tuple is a reply to a comment)
    private int score;
    private int age;

    public DateTime getTs() {
        return ts;
    }

    public void setTs(DateTime ts) {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (age != other.age)
			return false;
		if (comment_id != other.comment_id)
			return false;
		if (comment_replied != other.comment_replied)
			return false;
		if (post_commented != other.post_commented)
			return false;
		if (score != other.score)
			return false;
		if (ts == null) {
			if (other.ts != null)
				return false;
		} else if (!ts.equals(other.ts))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	public Comment(DateTime time, int comment_id, int user_id, String user, int comment_replied, int post_commented) {
        ts = time;
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.comment_replied = comment_replied;
        this.post_commented = post_commented;
        this.user = user;
        this.score = 10;
        this.age = 0;
    }


}

/**
 * TODO Définir les methodes pour la classe comment
 * TODO Penser à utiliser JodaTime pour la méthode de lecture de la date
 **/
