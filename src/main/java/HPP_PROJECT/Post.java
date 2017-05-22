package HPP_PROJECT;

import org.joda.time.DateTime;

public class Post {

    private DateTime ts;
    private String str_ts;
    private long post_id;
    private long user_id;
    private String user;
    private int scorePost;
    private int scoreTotal;
    private int age;
    private int nbCommentateur; 


    public String getStr_ts() {
		return str_ts;
	}

	public void setStr_ts(String str_ts) {
		this.str_ts = str_ts;
	}

	public int getNbCommentateur() {
		return nbCommentateur;
	}

	public void setNbCommentateur(int nbCommentateur) {
		this.nbCommentateur = nbCommentateur;
	}

	public int getScorePost() {
        return scorePost;
    }

    public void setScorePost(int score) {
        this.scorePost = score;
    }

    public DateTime getTs() {
        return ts;
    }

    public void setTs(DateTime ts) {
        this.ts = ts;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
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
		Post other = (Post) obj;
		if (age != other.age)
			return false;
		if (post_id != other.post_id)
			return false;
		if (scorePost != other.scorePost)
			return false;
		if (scoreTotal != other.scoreTotal)
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
	

	
	public Post(DateTime time, long l, long m, String user) {
        ts = time;
        this.post_id = l;
        this.user_id = m;
        this.user = user;
        this.scorePost = 10;
        this.scoreTotal = 10;
        this.age = 0;

    }

	public Post(Post post) {
	    DateTime ts = post.getTs();
	    str_ts = post.getStr_ts();
	    post_id = post.getPost_id();
	    user_id = post.getUser_id();
	    user = post.getUser();
	    scorePost = post.getScorePost();
	    scoreTotal = post.getScoreTotal();
	    age = post.getAge();
	    nbCommentateur = post.getNbCommentateur(); 
	}

    /**
     * TODO Définir les methodes pour la classe post
     **/


}