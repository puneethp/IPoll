package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserServ;

import com.google.gson.Gson;

import dao.User;
import dao.UserDAO;

public class AjaxLogout extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {


		HttpSession session = req.getSession(false);

		if(session!=null){
			if(session.getAttribute("user")!=null)
				if(session.getAttribute("user")instanceof User){
					session.removeAttribute("user");
					session.invalidate();
				}
		}
	}
}
