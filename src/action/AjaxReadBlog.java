package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;

import service.BlogServ;

import dao.Blog;
import dao.BlogDAO;
import dao.CommentsDAO;
import dao.User;

public class AjaxReadBlog extends HttpServlet {
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


		HttpSession session = req.getSession(false);

		if(session!=null){
			if(session.getAttribute("user")!=null)
				if(session.getAttribute("user")instanceof User){
					User currentUser = (User)session.getAttribute("user");
					String blogid = req.getParameter("blogid");
					if(blogid!=null){
						BlogServ bserv = new BlogServ();
						CommentsDAO cdao = new CommentsDAO();
						cdao.setSessionFactory(factory);
						BlogDAO bdao = new BlogDAO();
						bdao.setSessionFactory(factory);
						bserv.setCdao(cdao);
						bserv.setDao(bdao);
						if(bserv.isBlog(blogid)){
							Blog b = bserv.getBlogObject();
							b.comments = bserv.getCommentsForBlog(blogid);
							Gson gson = new Gson();
							String json = gson.toJson(b);
							resp.getWriter().write(json);
							b.setViews(b.getViews()+1);
							b.comments = null;
							bserv.updateBlog(b);
							
						}
					}
				}
		}
	}
}
