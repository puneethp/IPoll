package action;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import dao.User;

public class WriteHtmlAction extends BaseAction {
	public static final String NEWS = "News";
	public static final String HOME = "Home";
	public static final String HELP = "Help";
	@Override
	public String execute() throws Exception {
		if(req.getSession(false)!=null)
			if(req.getSession(false).getAttribute("user") instanceof User)
			{
				User user = (User) req.getSession(false).getAttribute("user");
				if(user!=null)
				{
					req.setAttribute("user", user);
					String contents = req.getParameter("content_page");
					if(user.getType().equalsIgnoreCase(User.ADMIN))
					{
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
							if(contents!=null)
							{
								FileWriter fr = null;
								try
								{
									fr = new FileWriter(f);
									fr.write(contents);
								}catch(Exception e)
								{
									req.setAttribute("error", "Error reading the page.");
									return "error";
								}finally
								{
									if(fr!=null)
										fr.close();
								}
								return "done";
							}else
							{
								req.setAttribute("error", "Contents Empty.");
								return "error";
							}
						}else
						{
							req.setAttribute("error", "unkown page.");
							return "error";
						}
					}else
					{
						req.setAttribute("error", "You dont have permission to view this page.");
						return "error";
					}
				}
			}
		return "nLoggedIn";
	}
}
