package action;

import java.io.File;
import java.sql.Blob;

import org.hibernate.Hibernate;
import org.springframework.util.FileCopyUtils;

import service.PartyServ;

import dao.User;

public class UpdatePartRegAction  extends BaseAction{
	PartyServ serv;
	private File userPhoto = null;
    private String userPhotoContentType = null;
    private String userPhotoFilename = null;
    
    /**
	 * @return the userPhoto
	 */
	public File getUserPhoto() {
		return userPhoto;
	}

	/**
	 * @param userPhoto the userPhoto to set
	 */
	public void setUserPhoto(File userPhoto) {
		this.userPhoto = userPhoto;
	}

	/**
	 * @return the userPhotoContentType
	 */
	public String getUserPhotoContentType() {
		return userPhotoContentType;
	}

	/**
	 * @param userPhotoContentType the userPhotoContentType to set
	 */
	public void setUserPhotoContentType(String userPhotoContentType) {
		this.userPhotoContentType = userPhotoContentType;
	}

	/**
	 * @return the userPhotoFilename
	 */
	public String getUserPhotoFileName() {
		return userPhotoFilename;
	}

	/**
	 * @param userPhotoFilename the userPhotoFilename to set
	 */
	public void setUserPhotoFileName(String userPhotoFilename) {
		this.userPhotoFilename = userPhotoFilename;
	}
    
	public PartyServ getServ() {
		return serv;
	}

	public void setServ(PartyServ serv) {
		this.serv = serv;
	}

	@Override
	public String execute() throws Exception {
		if(req.getSession(false)!=null)
		{
			if(req.getSession(false).getAttribute("user") instanceof User)
			{
				User user = (User)req.getSession(false).getAttribute("user");
				if(serv.getPartyByUser(user.getId()))
				{
					if(req.getParameter("partyname")!=null&&userPhotoFilename!=null)
					{
						String partyname = req.getParameter("partyname");
						if(serv.getParty(partyname))
						{
							String head = user.getId();
							byte[] symbol;
							symbol = (FileCopyUtils.copyToByteArray(userPhoto));
							String userImageContentType = userPhotoContentType;
							String userImageFileName = userPhotoFilename;
							if(serv.updatePhoto(partyname, head, symbol,userImageFileName,userImageContentType))
							{
								req.setAttribute("partycard", serv.getPartyObject());
								return "done";
							}else
							{
								
								req.setAttribute("error", "Internal error.");
								return "error";
							}
						}else
						{
							req.setAttribute("error", "party with the same name exists.");
							return "error";
						}
					}
				}
			}
		}
		return "nLoggedIn";
	}
	
}
