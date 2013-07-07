package dao;

public class Likes {
	private String likes_id;
	private String blog_id;
	private long likes;
	private String user_name;
	public String getLikes_id() {
		return likes_id;
	}
	public void setLikes_id(String likes_id) {
		this.likes_id = likes_id;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Likes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
