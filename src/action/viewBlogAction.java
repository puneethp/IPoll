package action;

import java.util.ArrayList;

import dao.Blog;
import dao.User;
import service.BlogServ;

public class viewBlogAction extends BaseAction {
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
					list = serv.getBlogs();
					if(list!=null)
					{
						req.setAttribute("blogs", list);
						req.setAttribute("user", user);
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
			}
		return "nLoggedIn";
	}

}
