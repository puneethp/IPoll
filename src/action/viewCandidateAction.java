package action;

import service.CandidateServ;
import dao.User;

public class viewCandidateAction extends BaseAction {
	
	CandidateServ serv;
	
	public CandidateServ getServ() {
		return serv;
	}

	public void setServ(CandidateServ serv) {
		this.serv = serv;
	}

	public String execute() throws Exception {
		String reqid;
		String candidateId;
		User user = null;
		if(req.getSession(false)!=null)
		{
			long id;
			reqid = req.getParameter("reqid");
			candidateId = req.getParameter("candidateid");
			
			if(candidateId!=null&&reqid!=null)
			{
				id = Long.parseLong(reqid);
				if(serv.isRequestForCandidateId(id))
				{
					user = (User)req.getSession(false).getAttribute("user");
					if(user!=null)
					{
						req.setAttribute("user", user);
						if(user.getType().compareToIgnoreCase(User.CANDIDATE)==0||user.getType().equalsIgnoreCase(User.CANDIDATEPARTY))
						{
							if(serv.getCandidateIdByUser(candidateId))
							{
								if(serv.getRc().getCandidateId().compareToIgnoreCase(candidateId)==0)
								{	
									req.setAttribute("candidatecard",serv.getCandidateIdObject());
									req.setAttribute("votercard",serv.getVoterIdByUserObject(serv.getCandidateIdObject().getUser()));
									
									return "view";
								}else
								{
									req.setAttribute("error", "Invalid request. Do you know what you are doing ?");
									return "error";
								}
							}else
							{
								req.setAttribute("error", "Invalid request. Do you know what you are doing ?");
								return "error";
							}
						}else
						{
							req.setAttribute("error", "You dont have permission to view this page.");
							return "error";
						}
					}else
					{
						return "nLoggedIn";
					}
				}else
				{
					req.setAttribute("error", "The Candidature is no longer under request.");
					return "error";
				}
			}
		}
		return "nLoggedIn";
	}

}
