package action;

import java.util.ArrayList;

import service.VoterServ;
import dao.Candidate;
import dao.User;

public class WardUserProxyVoteAction extends BaseAction {
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
		{
			if(req.getSession(false).getAttribute("user") instanceof User)
			{
				User user = (User) req.getSession(false).getAttribute("user");
				String voterid;
				voterid = req.getParameter("voterid");
				if(serv.getVoterId(voterid))
				{
					String elecid;
					String candidate;
					
					elecid = req.getParameter("elecid");
					candidate = req.getParameter("vote");
					Candidate c = null;
					c = serv.getCandidateByUser(candidate);
					ArrayList<Candidate> list= serv.getCandidate(serv.getVoterid().getWardId(), elecid);
					if(list.contains(c))
					{
						if(serv.castVote(candidate, serv.getVoterid().getWardId(), serv.getVoterid().getVoterID(), user.getId(), elecid))
						{
							req.setAttribute("user", user);
							return "done";
						}else
						{
							req.setAttribute("error", "Cant vote, Already did or internal error.");
							return "error";
						}
					}else
					{
						req.setAttribute("error", "Cant vote, Already did or internal error.");
						return "error";
					}
				}else
				{
					req.setAttribute("error", "Cant vote, Already did or internal error.");
					return "error";
				}
			}else
			{
				req.setAttribute("error", "Cant vote, Already did or internal error.");
				return "error";
			}
		}
		return "nLoggedIn";
	}

}
