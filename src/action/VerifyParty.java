package action;

import dao.Party;
import dao.Request;
import dao.User;
import service.PartyServ;

public class VerifyParty extends BaseAction {
	PartyServ serv;

	public PartyServ getServ() {
		return serv;
	}

	public void setServ(PartyServ serv) {
		this.serv = serv;
	}

	@Override
	public String execute() throws Exception {
		String reqid;
		String head;
		if(req.getSession(false)!=null)
		{
			User user = null;
			if(req.getSession(false).getAttribute("user") instanceof User)
			{
				user = (User)req.getSession(false).getAttribute("user");
				req.setAttribute("user", user);
				long id;
				reqid = req.getParameter("reqid");
				head = req.getParameter("head");
				if(reqid!=null&&head!=null)
				{
					id = Long.parseLong(reqid);
					if(serv.isRequestForParty(id))
					{
						String status  = req.getParameter("status");
						if(status.compareToIgnoreCase("accept")==0)
						{
							if(serv.acceptParty(head,id))
							{
								req.setAttribute("partycard", serv.getPartyObject());
								return "view";
							}else
							{
								req.setAttribute("error", "something went wrong.");
								return "error";
							}
						}else
						{
							if(serv.rejectParty(head,id))
							{
								return "view";
							}else
							{
								req.setAttribute("error", "something went wrong.");
								return "error";
							}
						}
					}else
					{
						req.setAttribute("error", "please donot try stuff.");
						return "error";
					}
				}else
				{
					req.setAttribute("error", "please donot try stuff.");
					return "error";
				}
			}
		}
		return "nLoggedIn";
	}
}
