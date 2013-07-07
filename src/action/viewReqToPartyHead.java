package action;

import java.util.HashMap;

import service.RequestServ;
import dao.RequestForPartyHead;
import dao.Request;
import dao.User;


public class viewReqToPartyHead extends BaseAction{
	
	RequestServ serv;
	@Override
	public String execute() throws Exception {
		User user = null;
		if(req.getSession(false)!=null)
			if(( req.getSession(false).getAttribute("user")) != null)
			{
				if(req.getSession(false).getAttribute("user") instanceof User)
				{
					user = (User)req.getSession(false).getAttribute("user");
					req.setAttribute("user", user);
					if(user.getType().equalsIgnoreCase(User.CANDIDATEPARTY)||user.getType().equalsIgnoreCase(User.PARTYHEAD))
					{
						HashMap<Request,RequestForPartyHead> hm = serv.getRequestForPartyHead(user.getId());
						req.setAttribute("r4ph", hm);
						return "view";
					}
					req.setAttribute("error", "Dear "+user.getId()+",\n\tYou donot have the <b>permission</b>.\n\tPlease contact the admin for further details.");
					return "noAuthority";
				}
			}
		return "nLoggedIn";
	}
	public RequestServ getServ() {
		return serv;
	}
	public void setServ(RequestServ serv) {
		this.serv = serv;
	}

}