package action;

import service.WardIdServ;
import dao.User;

public class AddWardAction extends BaseAction {
	WardIdServ serv;
	
	public WardIdServ getServ() {
		return serv;
	}

	public void setServ(WardIdServ serv) {
		this.serv = serv;
	}

	@Override
	public String execute() throws Exception {
		if(req.getSession(false)!=null)
			if(req.getSession(false).getAttribute("user") instanceof User)
			{
				User user = (User) req.getSession(false).getAttribute("user");
				if(user.getType().compareToIgnoreCase(User.ADMIN)==0)
				{
					String city,state,wardno;
					city = req.getParameter("city");
					state = req.getParameter("state");
					wardno = req.getParameter("wardno");
					if(serv.writeWardId(state, city, wardno))
					{
						req.setAttribute("ward", serv.getWard());
						return "view";
					}else
					{
						req.setAttribute("error", "There was error adding it.");
						return "error";
					}
				}
			}
		return "nLoggedIn";
	}

}
