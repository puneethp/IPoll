package action;

import java.util.ArrayList;

import service.BlogServ;

import dao.Blog;
import dao.User;

public class CommentAction extends BaseAction {
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
				ArrayList<Blog> list;
				if(serv!=null)
				{
					String blog_id = req.getParameter("blog_id");
					String comment = req.getParameter("comment");
					if(blog_id!=null&&comment!=null)
					{
						if(blog_id.compareToIgnoreCase("")!=0&&comment.compareToIgnoreCase("")!=0)
						{
							if(serv.isBlog(blog_id))
							{
								if(serv.commentBlog(blog_id, comment, user.getId()))
								{
									
									req.setAttribute("blog", serv.getBlogObject());
									req.setAttribute("user", user);
									req.setAttribute("likes", serv.getLikesForBlog(blog_id));
									req.setAttribute("comments", serv.getCommentsForBlog(blog_id));
									if(serv.getBlogObject().getUser().compareToIgnoreCase(user.getId())!=0)
										req.setAttribute("userLikes",new Long(serv.getLikesByUserBlog(user.getId(), blog_id)?1:0));
									else
										req.setAttribute("userLikes",new Long(1));
									return "view";
								}else
								{
									req.setAttribute("user", user);
									req.setAttribute("error", "something went horribly wrong.");
									return "error";
								}
							}else
							{
								req.setAttribute("user", user);
								req.setAttribute("error", "something went horribly wrong.");
								return "error";
							}
						}else
						{
							req.setAttribute("user", user);
							req.setAttribute("error", "something went horribly wrong.");
							return "error";
						}
					}else
					{
						req.setAttribute("user", user);
						req.setAttribute("error", "something went horribly wrong.");
						return "error";
					}
					
				}else
				{
					req.setAttribute("user", user);
					req.setAttribute("error", "something went horribly wrong.");
					return "error";
				}
			}
		return "nLoggedIn";
	}

}
