package action;

import java.io.File;
import java.io.FileReader;

import dao.User;

public class processPage extends BaseAction {
	public static final String NEWS = "News";
	public static final String HOME = "Home";
	public static final String HELP = "Help";
	@Override
	public String execute() throws Exception {


		String page = req.getParameter("page");
		if(page!=null)
		{
			File f;
			if(page.equalsIgnoreCase(HOME))
			{
				f = new File(context.getRealPath("/WEB-INF/jsp/home.html"));
			}else if(page.equalsIgnoreCase(NEWS))
			{
				f = new File(context.getRealPath("/WEB-INF/jsp/news.html"));
			}else if(page.equalsIgnoreCase(HELP))
			{
				f = new File(context.getRealPath("/WEB-INF/jsp/help.html"));
			}else
			{
				req.setAttribute("error", "unkown page.");
				return "error";
			}
			req.setAttribute("page", page);
			String contents = "";
			FileReader fr = null;
			try
			{
				fr = new FileReader(f);
				char []buffer = new char[4096];
				while(fr.read(buffer)!=-1)
				{
					contents = contents.concat(new String(buffer));
				}
			}catch(Exception e)
			{
				req.setAttribute("error", "Error reading the page.");
				return "error";
			}finally
			{
				if(fr!=null)
					fr.close();
			}
			contents = contents.trim();
			req.setAttribute("contents", contents);
			if(req.getSession(false)!=null)
				if(req.getSession(false).getAttribute("user") instanceof User)
				{
					User user = (User) req.getSession(false).getAttribute("user");
					if(user!=null)
					{								
						req.setAttribute("user", user);
						return "loggedIn";
					}
				}
		}
		return "nLoggedIn";
	}
}
