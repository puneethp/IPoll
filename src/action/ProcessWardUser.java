package action;

import dao.User;

public class ProcessWardUser extends BaseAction {
	public String execute() throws Exception {
		if(req.getSession(false)!=null)
			if(req.getSession(false).getAttribute("user")!=null)
			{
				if(((User)req.getSession(false).getAttribute("user")).getType().compareToIgnoreCase(User.WARDUSER)==0)
				{
					req.setAttribute("user", req.getSession(false).getAttribute("user"));
					return "loggedIn";
				}else{
					req.setAttribute("error", "you dont have permission to view the page.");
					return "error";
				}
					
			}
		return "nLoggedIn";
	}
}
