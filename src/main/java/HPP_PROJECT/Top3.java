package HPP_PROJECT;


public class Top3 {
	private Post post1;
	private Post post2;
	private Post post3;
	private String str_tk;
	
	public Post getPost1() {
		return post1;
	}
	public void setPost1(Post post1) {
		this.post1 = post1;
	}
	public Post getPost2() {
		return post2;
	}
	public void setPost2(Post post2) {
		this.post2 = post2;
	}
	public Post getPost3() {
		return post3;
	}
	public void setPost3(Post post3) {
		this.post3 = post3;
	}
	public String getStr_tk() {
		return str_tk;
	}
	public void setStr_tk(String str_tk) {
		this.str_tk = str_tk;
	}
	public Top3(Post post1, Post post2, Post post3, String str_tk) {
		super();
		this.post1 = post1;
		this.post2 = post2;
		this.post3 = post3;
		this.str_tk = str_tk;
	}


}
