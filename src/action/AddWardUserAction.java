package action;

import service.UserServ;
import dao.User;

public class AddWardUserAction extends BaseAction {
	UserServ serv;
	
	public UserServ getServ() {
		return serv;
	}

	public void setServ(UserServ serv) {
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
					String username;
					String wardid;
					Long id = 0L;
					try
					{
						id = Long.parseLong(req.getParameter("wardid"));
					}catch(NumberFormatException e)
					{
						req.setAttribute("error", "You have to enter a number.");
						return "error";
					}
					username = req.getParameter("user");
					if(username.compareToIgnoreCase(user.getId())!=0)
					{
						 if(serv.writeWardUser(username, id))
						 {
							 req.setAttribute("user", user);
							 return "view";
						 }else
						 {
							 req.setAttribute("error", "WardUser exist or is already a warduser or candidare.");
								return "error";
						 }
					}else
					{
						req.setAttribute("error", "You donnot have the permissions to add yourself as wardUser");
						return "error";
					}
				}else
				{
					req.setAttribute("error", "You donnot have the permissions to add wardUser");
					return "error";
				}
			}
		return "nLoggedIn";
	}
	
}
