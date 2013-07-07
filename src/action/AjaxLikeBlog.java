package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dao.BlogDAO;
import dao.LikesDAO;
import dao.User;

import service.BlogServ;

public class AjaxLikeBlog extends HttpServlet {

	SessionFactory factory=null;
	
	@Override
	public void init() throws ServletException {
		try
		{
		factory = new Configuration().configure().buildSessionFactory();
		}catch(HibernateException h)
		{
			h.printStackTrace();
			System.out.println("Servlet Hibernate config error.");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BlogServ serv = new BlogServ();
		PrintWriter pw = resp.getWriter();
		resp.setContentType("application/xml");
		if(factory!=null)
		{
			serv.setLdao(new LikesDAO());
			serv.setDao(new BlogDAO());
			serv.getLdao().setSessionFactory(factory);
			serv.getDao().setSessionFactory(factory);
			if(req.getSession(false)!=null)
			{
				if(req.getSession(false).getAttribute("user") instanceof User)
				{
					User user = (User)req.getSession(false).getAttribute("user");
					String blog_id = req.getParameter("blog_id");
					if(serv.isBlog(blog_id))
					{
						if(serv.likeBlog(blog_id, user.getId()))
						{
							String msg = Constants.addTag("Like","Query");
							msg += Constants.addTag("1", "value");
							msg = Constants.message(msg);
							msg = Constants.addTag(msg, "wrapper");
							pw.write(msg);
							return;
						}
					}
				}
			}
		}
		String msg = Constants.addTag("Like","Query");
		msg += Constants.addTag("0", "value");
		msg = Constants.message(msg);
		msg = Constants.addTag(msg, "wrapper");
		pw.write(msg);
		return;
	}
	
	
}
