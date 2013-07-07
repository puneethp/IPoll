package action;

import java.util.ArrayList;

import service.BlogServ;
import dao.Blog;
import dao.User;

public class CreateBlogAction extends BaseAction {
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
					String title = req.getParameter("title");
					String content = req.getParameter("content_blog");
					if(title!=null&&content!=null)
					{
						if(title.compareToIgnoreCase("")!=0&&content.compareToIgnoreCase("")!=0)
						{
							if(serv.writeBlog(title, user.getId(), content))
							{
								req.setAttribute("blog", serv.getBlogObject());
								return "view";
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
