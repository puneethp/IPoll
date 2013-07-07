package action;

import dao.User;
import service.CandidateServ;

public class verifyCandidatureStatus extends BaseAction{
	
		CandidateServ serv;
		
		

		@Override
		public String execute() throws Exception {
			User user=null;
			String candidateid;
			String reqid;
			if(req.getSession(false)!=null)
			{
				if(req.getSession(false).getAttribute("user") instanceof User)
				{
					user = (User) req.getSession(false).getAttribute("user");
					req.setAttribute("user",user);
					if(user.getType().compareToIgnoreCase(User.WARDUSER)==0)
					{
						long id;
						if(serv!=null)
						{
							if(req.getParameter("reqid")!=null&&req.getParameter("candidateid")!=null&&req.getParameter("status")!=null)
							{
								reqid = req.getParameter("reqid");
								candidateid = req.getParameter("candidateid");
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
								if(serv.getVoterIdByUser(candidateid)!=null)
								{
									if(serv.isRequestForCandidateIdWard(id))
									{
										if(serv.getRcw().getCandidateId().compareToIgnoreCase(candidateid)==0)
										{
											String status = req.getParameter("status");
											if(status.compareToIgnoreCase("accept")==0)
											{
												if(serv.acceptCandidatureWard(candidateid, id, user.getId()))
												{
													req.setAttribute("candidatecard",serv.getCandidateIdObject());
													req.setAttribute("votercard",serv.getVoterIdByUserObject(serv.getCandidateIdObject().getUser()));
													return "view";
												}else
												{
													req.setAttribute("error","Something went terribly wrong.");
													return "error";
												}
											}else if(status.compareToIgnoreCase("reject")==0)
											{
												if(serv.rejectCandidatureWard(candidateid, id, user.getId()))
												{
													req.setAttribute("candidateCard",serv.getCandidateIdObject());
													req.setAttribute("votercard",serv.getVoterIdByUserObject(serv.getCandidateIdObject().getUser()));
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



		public CandidateServ getServ() {
			return serv;
		}



		public void setServ(CandidateServ serv) {
			this.serv = serv;
		}

	}


