package action;

import service.BlogServ;
import dao.User;

public class GetBlogAction extends BaseAction{
	BlogServ serv;
	@Override
	public String execute() throws Exception {
		if(req.getSession(false)!=null)
		{
			if(req.getSession(false).getAttribute("user") instanceof User)
			{
				
			}
		}
		return "nLoggedIn";
	}

}
