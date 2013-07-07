package action;

import java.util.ArrayList;

import service.BlogServ;
import dao.Blog;
import dao.Comments;
import dao.User;

public class ViewABlogAction extends BaseAction {
	BlogServ serv;
	
	public BlogServ getServ() {
		return serv;
	}

	public void setServ(BlogServ serv) {
		this.serv = serv;
	}

	@Override
	public String execute() throws Exception {
		if(req.getSession(false)!=null)
			if(req.getSession(false).getAttribute("user") instanceof User)
			{
				User user = (User)req.getSession(false).getAttribute("user");
				if(serv!=null)
				{
					String blog_id = req.getParameter("blog_id");
					if(blog_id!=null)
					{
						if(serv.isBlog(blog_id))
						{
							ArrayList<Comments> comments = serv.getCommentsForBlog(blog_id);
							serv.getBlogObject().setViews(serv.getBlogObject().getViews()+1);
							serv.updateBlog(serv.getBlogObject());
							req.setAttribute("user", user);
							req.setAttribute("blog", serv.getBlogObject());
							req.setAttribute("comments", comments);
							if(serv.getBlogObject().getUser().compareToIgnoreCase(user.getId())!=0)
								req.setAttribute("userLikes",new Long(serv.getLikesByUserBlog(user.getId(), blog_id)?1:0));
							else
								req.setAttribute("userLikes",new Long(1));
							req.setAttribute("likes", serv.getLikesForBlog(blog_id));
							return "view";
						}else
						{
							req.setAttribute("error", "This is not a blog, do you know what you are doing.");
							return "error";
						}
					}else
					{
						req.setAttribute("error", "something went horribly wrong.");
						return "error";
					}
				}else
				{
					req.setAttribute("error", "something went horribly wrong.");
					return "error";
				}
			}
		return "nLoggedIn";
	}
}
