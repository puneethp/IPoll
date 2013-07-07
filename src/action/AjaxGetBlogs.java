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

import service.BlogServ;

import com.google.gson.Gson;

import dao.BlogDAO;
import dao.BlogList;
import dao.User;

public class AjaxGetBlogs extends HttpServlet {
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
					BlogServ bserv = new BlogServ();
					BlogDAO bdao = new BlogDAO();
					bdao.setSessionFactory(factory);
					bserv.setDao(bdao);


					BlogList reply = new BlogList();
//					System.out.println("Param: "+req.getParameter("mine"));
					if(req.getParameter("mine")==null){
						reply.setData(bserv.getBlogs());
					}
					else{
						reply.setData(bserv.getMyBlogs(currentUser.getId()));
					}
//					System.out.println("Blogs:"+reply.getData());

					Gson gson = new Gson();
					String json= gson.toJson(reply);
					resp.setContentType("text/json");
					resp.getWriter().write(json);
				}
		}
	}
}
