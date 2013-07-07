package action;

import java.util.ArrayList;

import service.ElectionServ;
import dao.Election;
import dao.User;

public class ViewResultElectionAction extends BaseAction {
	ElectionServ serv;

	public ElectionServ getServ() {
		return serv;
	}

	public void setServ(ElectionServ serv) {
		this.serv = serv;
	}
	public String execute() throws Exception {
		if(req.getSession(false)!=null)
		{
			if(req.getSession(false).getAttribute("user") instanceof User)
			{
				User user = (User)req.getSession(false).getAttribute("user");
				if(user.getType().compareToIgnoreCase(User.ADMIN)==0)
				{
					ArrayList<Election> list = serv.getAllElectionsWithResults();	
					if(list!=null)
					{
						req.setAttribute("electionlist", list);
						req.setAttribute("user", user);
						return "view";
					}else
					{
						req.setAttribute("error", "Something went wrong.");
						return "error";
					}
				}else
				{
					req.setAttribute("error", "you dont have permissions to view this page.");
					return "error";
				}
			}
		}
		req.setAttribute("error", "Login as administrator.");
		return "nLoggedIn";
	}
}
