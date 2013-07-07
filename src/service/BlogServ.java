package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import dao.Blog;
import dao.BlogDAO;
import dao.Comments;
import dao.CommentsDAO;
import dao.Likes;
import dao.LikesDAO;

public class BlogServ {
	BlogDAO dao;
	LikesDAO ldao;
	CommentsDAO cdao; 
	Blog blog;
	Likes like;
	Comments comments;
	
	
	public BlogDAO getDao() {
		return dao;
	}

	public void setDao(BlogDAO dao) {
		this.dao = dao;
	}

	public LikesDAO getLdao() {
		return ldao;
	}

	public void setLdao(LikesDAO ldao) {
		this.ldao = ldao;
	}

	public CommentsDAO getCdao() {
		return cdao;
	}

	public void setCdao(CommentsDAO cdao) {
		this.cdao = cdao;
	}

	public boolean writeBlog(String title,String user,String content)
	{
		blog = new Blog(title,content,user,new Timestamp(new Date().getTime()));
		if(dao.persistBlog(blog))
			return true;
		return false;	
	}
	
	public ArrayList<Blog> getBlogs()
	{
		return dao.getBlogs();
	}
	
	public ArrayList<Blog> getMyBlogs(String id)
	{
		return dao.getUserBlogs(id);
	}
	
	public boolean commentBlog(String blog_id,String content,String user)
	{
		comments = new Comments(content, user, new Timestamp(new Date().getTime()));
		comments.setBlog_id(blog_id);
		return cdao.persistComments(comments);
	}
	
	public boolean likeBlog(String blog_id,String user)
	{

		like = new Likes();
		like.setBlog_id(blog_id);
		like.setLikes(1);
		like.setUser_name(user);
		return ldao.persistLikes(like);
	}
	
	public boolean isBlog(String blog_id)
	{
		blog = dao.getBlog(blog_id);
		if(blog!=null)
			return true;
		return false;
	}
	
	public boolean isComments(String commentid)
	{
		comments = cdao.getComments(commentid);
		if(comments!=null)
			return true;
		return false;
	}
	
	public boolean isLike(String like_id)
	{
		if(ldao.getLikes(like_id)!=null)
			return true;
		return false;
	}
	
	public Blog getBlogObject()
	{
		return blog;
	}
	
	public Comments getCommentsObject()
	{
		return comments;
	}
	public ArrayList<Comments> getCommentsForBlog(String blog_id)
	{
		return cdao.getCommentsForBlog(blog_id);
	}
	
	public Long getLikesForBlog(String blog_id)
	{
		return ldao.getLikesForBlog(blog_id);
	}
	
	public boolean updateBlog(Blog blog)
	{
		return dao.updateBlog(blog);
	}
	
	public boolean getLikesByUserBlog(String user,String blog_id)
	{
		return ldao.getLikesUserBlog(blog_id, user);
	}
}
