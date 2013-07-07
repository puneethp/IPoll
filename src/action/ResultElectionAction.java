package action;

import service.ElectionServ;
import dao.User;

public class ResultElectionAction extends BaseAction {
	ElectionServ serv;

	public ElectionServ getServ() {
		return serv;
	}

	public void setServ(ElectionServ serv) {
		this.serv = serv;
	}
	@Override
	public String execute() throws Exception {
		if(req.getSession(false)!=null)
		{
			if(req.getSession(false).getAttribute("user") instanceof User)
			{
				User user = (User)req.getSession(false).getAttribute("user");
				if(user.getType().compareToIgnoreCase(User.ADMIN)==0)
				{
					String elecid = req.getParameter("elecid");
					if(elecid!=null&&!elecid.equals(""))
					{
						if(serv.isElection(elecid))
						{
							
							if(serv.getResults(elecid))
							{
								req.setAttribute("user", user);
								return "done";
							}else
							{
								req.setAttribute("error", "Something went wrong.");
								return "error";
							}
						}else
						{
							req.setAttribute("error", "Something went wrong.");
							return "error";
						}
					}else
					{
						req.setAttribute("error", "too few parameters.");
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
