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

import service.UserServ;

import com.google.gson.Gson;

import dao.User;
import dao.UserDAO;

public class AjaxLogin extends HttpServlet {
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
					User user = (User) session.getAttribute("user");
					Gson gson = new Gson();
					String json = gson.toJson(user);
					resp.getWriter().write(json);
				}
		}else{
			if(req.getParameter("id") !=null && req.getParameter("password")!=null){
				UserServ serv;
				serv = new UserServ();
				UserDAO udao = new UserDAO();
				udao.setSessionFactory(factory);
				serv.setDao(udao);
				if(serv.isValidUser(req.getParameter("id"), req.getParameter("password"))){
					User user = serv.getUser();
					Gson gson = new Gson();
					String json = gson.toJson(user);
					resp.getWriter().write(json);
					session = req.getSession();
					session.setAttribute("user", user);
				}
			}
		}
	}
}