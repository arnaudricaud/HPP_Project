package HPP_PROJECT;

public class Comment {
	

		
		private int ts; //is the comment's timestamp
		private int comment_id; // is the unique id of the comment
		private int user_id;// 	is the unique id of the user
		private String comment; // is a string containing the actual comment
		private String user; // 	is a string containing the actual user name
		private int comment_replied; // is the id of the comment being replied to (-1 if the tuple is a reply to a post)
		private int post_commented; // is the id of the post being commented (-1 if the tuple is a reply to a comment)


	public Comment(int time)
{
		ts = time;
	
 }
}

/**TODO Définir les methodes pour la classe comment**/
