package action;

import java.util.ArrayList;

import service.VoterServ;
import dao.Candidate;
import dao.Election;
import dao.User;
import dao.VoterId;

public class WardUserVoteReqPage extends BaseAction {
VoterServ serv;
	
	public VoterServ getServ() {
		return serv;
	}

	public void setServ(VoterServ serv) {
		this.serv = serv;
	}
	@Override
	public String execute() throws Exception {
		if(req.getSession(false)!=null)
			if(req.getSession(false).getAttribute("user") instanceof User)
			{
				User user = null;
				user = (User) req.getSession(false).getAttribute("user");
				req.setAttribute("user", user);
				VoterId votercard = null;
				String voterid = req.getParameter("voterid");
				if(serv.getVoterId(voterid))
				{
					votercard = serv.getVoterid();
					if(votercard!=null)
					{
						ArrayList<Election> retval = serv.getElectionsInConstituency(votercard.getWardId());
						if(retval!=null)
						{
							req.setAttribute("voterid", voterid);
							req.setAttribute("electionlist", retval);
							return "view";
						}else
						{
							req.setAttribute("error", "Something went wrong.");
							return "error";
						}
					}else
					{
						req.setAttribute("error", "Register for a voterid before voting");
						return "voterId";
					}
				}else
				{
					req.setAttribute("error", "Register for a voterid before voting");
					return "voterId";
				}
				
			}
		return "nLoggedIn";
	}

}
