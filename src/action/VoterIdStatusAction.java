package action;

import dao.User;
import service.VoterIdServ;

public class VoterIdStatusAction extends BaseAction {

	VoterIdServ serv;
	
	public VoterIdServ getServ() {
		return serv;
	}

	public void setServ(VoterIdServ serv) {
		this.serv = serv;
	}

	@Override
	public String execute() throws Exception {
		User user=null;
		String voterid;
		String reqid;
		if(req.getSession(false)!=null)
		{
			if(req.getSession(false).getAttribute("user").getClass()==User.class)
			{
				user = (User) req.getSession(false).getAttribute("user");
				req.setAttribute("user",user);
				if(user.getType().compareToIgnoreCase(User.WARDUSER)==0)
				{
					long id;
					if(serv!=null)
					{
						if(req.getParameter("reqid")!=null&&req.getParameter("voterid")!=null&&req.getParameter("status")!=null)
						{
							reqid = req.getParameter("reqid");
							voterid = req.getParameter("voterid");
							id = 0;
							try{
							id = Long.parseLong(reqid);
							}catch(Exception e)
							{
								id = -1;
							}
							if(id<0)
							{
								req.setAttribute("error","Something went terribly wrong.");
								return "error";
							}
							if(serv.getVoterId(voterid))
							{
								if(serv.isRequestForVoterId(id))
								{
									if(serv.getReq4voterid().getVoterid().compareToIgnoreCase(voterid)==0)
									{
										String status = req.getParameter("status");
										if(status.compareToIgnoreCase("accept")==0)
										{
											if(serv.acceptVoterId(voterid, id, user.getId()))
											{
												req.setAttribute("votercard",serv.getVoterIdObject());
												return "view";
											}else
											{
												req.setAttribute("error","Something went terribly wrong.");
												return "error";
											}
										}else if(status.compareToIgnoreCase("reject")==0)
										{
											if(serv.rejectVoterId(voterid, id, user.getId()))
											{
												req.setAttribute("votercard",serv.getVoterIdObject());
												return "view";
											}else
											{
												req.setAttribute("error","Something went terribly wrong.");
												return "error";
											}
										}else
										{
											req.setAttribute("error","Do not tweak.");
											return "error";
										}
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
								req.setAttribute("error","Please Donnot tweak.");
								return "error";
							}
						}
					}else
					{
						req.setAttribute("error","Something went terribly wrong.");
						return "error";
					}
				}
			}
		}
		return "nLoggedIn";
	}

}
