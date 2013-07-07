package action;

import java.util.HashMap;

import service.PartyServ;
import service.RequestServ;

import dao.Request;
import dao.RequestForParty;
import dao.RequestForVoterId;
import dao.User;

public class RequestForPartyAdmin extends BaseAction {
	RequestServ serv;
	
	public RequestServ getServ() {
		return serv;
	}

	public void setServ(RequestServ serv) {
		this.serv = serv;
	}

	public String execute() throws Exception {
		User user = null;
		if(req.getSession(false)!=null)
			if(( req.getSession(false).getAttribute("user")) != null)
			{
				if(req.getSession(false).getAttribute("user") instanceof User)
				{
					user = (User)req.getSession(false).getAttribute("user");
					req.setAttribute("user", user);
					if(user.getType().equalsIgnoreCase(User.ADMIN))
					{
						HashMap<Request,RequestForParty> hm = serv.getRequestForParty();
						req.setAttribute("reqforparty", hm);
						return "view";
					}
					req.setAttribute("error", "Dear "+user.getId()+",\n\tYou donot have the <b>permission</b>.\n\tPlease contact the admin for further details.");
					return "noAuthority";
				}
			}
		return "nLoggedIn";
	}
	
}
