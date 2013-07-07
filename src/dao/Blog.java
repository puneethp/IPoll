package dao;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Blog {
	private String blog_id;
	private String title;
	private String post;
	private String user;
	private Timestamp date;
	private Long views;
	
	public ArrayList<Comments> comments;
	
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public Blog() {
		super();
		views = 0L;
	}
	public Blog(String title, String post, Timestamp date) {
		super();
		this.title = title;
		this.post = post;
		this.date = date;
		views = 0L;
	}
	public Blog(String title, String post, String user, Timestamp date) {
		super();
		this.title = title;
		this.post = post;
		this.user = user;
		this.date = date;
		views = 0L;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
