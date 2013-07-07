package action;

import java.io.File;
import java.sql.Blob;
import java.sql.Timestamp;

import org.hibernate.Hibernate;
import org.springframework.util.FileCopyUtils;

import service.CandidateServ;
import dao.User;

public class UpdateCandidatureAction extends BaseAction {
	
	private File userPhoto = null;
    private String userPhotoContentType = null;
    private String userPhotoFilename = null;
    private CandidateServ serv;
    
	public File getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(File userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getUserPhotoContentType() {
		return userPhotoContentType;
	}

	public void setUserPhotoContentType(String userPhotoContentType) {
		this.userPhotoContentType = userPhotoContentType;
	}

	public String getUserPhotoFileName() {
		return userPhotoFilename;
	}

	public void setUserPhotoFileName(String userPhotoFilename) {
		this.userPhotoFilename = userPhotoFilename;
	}

	public CandidateServ getServ() {
		return serv;
	}

	public void setServ(CandidateServ serv) {
		this.serv = serv;
	}

	@Override
	public String execute() throws Exception {
		String candidate1;
		String candidate2;
		String candidate3;
		String candidate4;
		String candidate5;
		String candidate6;
		String candidate7;
		String candidate8;
		String candidate9;
		String candidate10;
		String voterId;
		String id;
		Long constituency;
		Timestamp dateOfEffect;
		String representingParty;
		String symbol;
		
		
		if(req.getSession(false)!=null)
		{
			if(req.getSession(false).getAttribute("user")!=null)
			{
				if(req.getSession(false).getAttribute("user").getClass()==User.class)
				{
					User user;
					user= (User)req.getSession(false).getAttribute("user");
					if(serv.getCandidateIdByUser(user.getId()))
					{
						if(req.getParameter("constituency")!=null)
						{
							if(userPhoto!=null&&userPhotoContentType!=null&&userPhotoFilename!=null)
							{
								id=user.getId();
								voterId=serv.getVoterIdByUser(user.getId());
								if(serv.getVoterId(voterId)!=null)
								{
									candidate1=req.getParameter("candidate1");
									candidate2=req.getParameter("candidate2");
									candidate3=req.getParameter("candidate3");
									candidate4=req.getParameter("candidate4");
									candidate5=req.getParameter("candidate5");
									candidate6=req.getParameter("candidate6");
									candidate7=req.getParameter("candidate7");
									candidate8=req.getParameter("candidate8");
									candidate9=req.getParameter("candidate9");
									candidate10=req.getParameter("candidate10");
									representingParty = req.getParameter("representingParty");
									constituency=Long.parseLong(req.getParameter("constituency"));
								
									byte[] userImage;
									userImage = (FileCopyUtils.copyToByteArray(userPhoto));
									String userImageContentType = userPhotoContentType;
									String userImageFileName = userPhotoFilename;
									
									if(serv.updateCandidature(user.getId(),candidate1, candidate2,candidate3, candidate4,
										candidate5, candidate6, candidate7, candidate8, candidate9, candidate10,
										constituency, userImageContentType,userImageFileName, userImage,representingParty ))
									{
										return "done";
									}
									else
									{
										req.setAttribute("error", "Registration for Candidature failed");
										return "error";
									}
								}
								else
								{
									req.setAttribute("error", "VoterID does not exists.");
									return "error";
								}
							}
							else
							{
								id=user.getId();
								voterId=serv.getVoterIdByUser(user.getId());
								if(serv.getVoterId(voterId)!=null){
									representingParty = req.getParameter("representingParty");
									constituency=Long.parseLong(req.getParameter("constituency"));
									if(serv.updateCandidature(user.getId(),null, null,null, null,
											null, null, null, null, null, null,
											constituency, null,null, null,representingParty ))
										{
											return "done";
										}
										else
										{
											req.setAttribute("error", "Registration for Candidature failed");
											return "error";
										}
								}else{
									
								}
							}
						}
						else
						{
							req.setAttribute("error", "Fields not specified.");
							return "error";
						}	
					}
				}else{
					req.setAttribute("error", "Please register as candidate to update!");
					return "error";
				}
			}
			return "nLoggedIn";
		}
		return "nLoggedIn";
	}

}
