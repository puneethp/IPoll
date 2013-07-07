package action;

import dao.User;
import dao.VoterIdDAO;
import service.CandidateServ;

public class CandidateRegReqAction extends BaseAction {

	/*public CandidateRegReqAction(){
	
	}*/
	private CandidateServ serv;
	
	
	
	public CandidateServ getServ() {
		return serv;
	}


	public void setServ(CandidateServ serv) {
		this.serv = serv;
	}


	@Override
	public String execute()throws Exception {
		User user;
		
		if(req.getSession(false)!=null)
		{
			if(req.getSession(false).getAttribute("user")!=null)
			{
				req.setAttribute("user", user= (User) req.getSession(false).getAttribute("user"));
				if(serv.getVoterIdByUser(user.getId())!= null)
				{
					if(serv.getCandidateIdByUser(user.getId())&&serv!=null )
					{
						req.setAttribute("candidatecard", serv.getCandidateIdObject());
						req.setAttribute("votercard",serv.getVid());
						return "exist";
					}
					else
					{
						req.setAttribute("user", user);
						return "register";
					}
				}
				else
				{
					req.setAttribute("error", "Register for Voter ID before resgistering for Candidature.");
					return "regVoterIdb4Candidate";
				}
			}
		}
		return "nLoggedIn";
	}
	
}
