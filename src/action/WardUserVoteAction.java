package action;

import java.util.ArrayList;

import service.VoterServ;
import dao.Candidate;
import dao.User;
import dao.VoterId;

public class WardUserVoteAction extends BaseAction {
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
						if(votercard.getValidity()==VoterId.ACCEPTED)
						{
							String elecid = req.getParameter("elecid");
							if(elecid!=null)
							{
								ArrayList<Candidate> retval = null;
								retval = serv.getCandidate(votercard.getWardId(), elecid);
								if(retval!=null)
								{
									req.setAttribute("elecid", elecid);
									req.setAttribute("candidates", retval);
									req.setAttribute("voterid", voterid);
									return "view";
								}else
								{
									req.setAttribute("error", "Something went wrong.");
									return "error";
								}
							}
						}else
						{
							req.setAttribute("error", "your voterid isnt accepted.");
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
