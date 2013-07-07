package action;

import service.PartyServ;
import dao.User;

public class PartyReqRegAction extends BaseAction {

	PartyServ serv; 
	public PartyServ getServ() {
		return serv;
	}
	public void setServ(PartyServ serv) {
		this.serv = serv;
	}
	@Override
	public String execute() throws Exception {
		User user = null;
		if(req.getSession(false)!=null)
			if(req.getSession(false).getAttribute("user")!=null)
			{
				req.setAttribute("user", user = (User) req.getSession(false).getAttribute("user"));
				if(serv.getPartyByUser(user.getId())&&serv!=null)
				{
					req.setAttribute("partycard", serv.getPartyObject());
					return "exist";
				}
				return "loggedIn";
			}
		return "nLoggedIn";
	}
	
}
