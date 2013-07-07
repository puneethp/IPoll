package action;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import service.UserServ;

public class CandidateReportAction extends HttpServlet{
	SessionFactory sessionFactory;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userid =req.getParameter("userid");
		UserServ serv = new UserServ();
		if(sessionFactory!=null){
//			System.out.println("Voterid: "+serv.getVoterId(voterid));
			if(serv.isUser(userid, sessionFactory)){
//				Session session = sessionFactory.openSession();
//				Transaction trans = session.beginTransaction();
				Map<String,Object> parameters = new HashMap<String,Object>();
//			    parameters.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, session);
//			    parameters.put("ReportTitle", "Address Report");
			    parameters.put("userid",userid);
//			    System.out.println("Voterid "+serv.getVoterIdObject());
			    String value = "attachment;filename=\"" + URLEncoder.encode(userid+".pdf", "UTF-8") +'"';
			    resp.setHeader("Content-Disposition", value);
			    resp.setHeader("Content-Transfer-Encoding", "binary");
			    resp.addHeader("Content-Type", "application/pdf");
			    File file = new File(req.getServletContext().getRealPath("/WEB-INF/jasper/Candidature.jasper"));
			    try {
			    	if(!file.exists()){
			    		file=new File(req.getServletContext().getRealPath("/WEB-INF/jasper/Candidature.jrxml"));
			    		String test = JasperCompileManager.compileReportToFile(file.getAbsolutePath());
			    		file=new File(test);
			    	}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			    
//			    System.out.println("Complete path = "+file.getAbsolutePath());
			    ServletOutputStream pw = resp.getOutputStream();
			    Connection connection = null;
			    try {
			    	connection = DriverManager.getConnection("jdbc:mysql://localhost/ipoll?"+"user=root&password=root");
					JasperPrint print=JasperFillManager.fillReport(file.getAbsolutePath(), parameters,connection);
					JasperExportManager.exportReportToPdfStream(print, pw);//(sourceFileName, destFileName)(print, pw);
					pw.close();
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(connection!=null)
						try {
							connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
				}
			    
//			    trans.rollback();
//			    session.close();
			}
			
		}
	}
	
	
	@Override
	public void init() throws ServletException {
		try{
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}catch(HibernateException h)
		{
			System.err.println("Error: at FileAction method: Init().");
			h.printStackTrace();
		}
	}
}