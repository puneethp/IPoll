package action;

import java.util.ArrayList;

import service.ElectionServ;
import dao.Election;
import dao.Result;
import dao.User;
import dao.VoteRatio;

public class ViewElectionResultAction extends BaseAction {
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
				if(true)
				{
					String elecid = req.getParameter("elecid");
					if(elecid!=null&&!elecid.equals(""))
					{
						if(serv.isElection(elecid))
						{
							if(serv.getElection().getValidity()==Election.DONE)
							{
								req.setAttribute("user", user);
								ArrayList<Result> result = serv.getResultForElection(elecid);
								req.setAttribute("results",result);
								req.setAttribute("election", serv.getElection());
								return "view";
							}else
							{
								req.setAttribute("error", "Cant view result of Election in progress.");
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
				}
			}
		}
		req.setAttribute("error", "Login as administrator.");
		return "nLoggedIn";
	}

}
