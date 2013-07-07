package action;

import dao.User;
import service.UserServ;


public class LogoutAction extends BaseAction {
	UserServ serv;
	
	public UserServ getServ() {
		return serv;
	}

	public void setServ(UserServ serv) {
		this.serv = serv;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		if(req.getSession(false)!=null)
			if(req.getSession(false).getAttribute("user")!=null)
			{
				User user = (User)req.getSession(false).getAttribute("user"); 
				serv.logOffUser(user);
				req.getSession(false).removeAttribute("user");
				req.getSession(false).invalidate();
				
				return "login";
			}
		return "login";
	}
	
}
