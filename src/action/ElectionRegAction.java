package action;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import dao.User;
import service.ElectionServ;

public class ElectionRegAction extends BaseAction {
	ElectionServ serv;

	public ElectionServ getServ() {
		return serv;
	}

	public void setServ(ElectionServ serv) {
		this.serv = serv;
	}

	@Override
	public String execute() throws Exception {
		if(req.getSession(false)!=null)
		{
			if(req.getSession(false).getAttribute("user") instanceof User)
			{
				User user = (User)req.getSession(false).getAttribute("user");
				if(user.getType().compareToIgnoreCase(User.ADMIN)==0)
				{
					if(req.getParameter("sday")!=null&&req.getParameter("smonth")!=null&&req.getParameter("syear")!=null&&req.getParameter("eday")!=null&&req.getParameter("emonth")!=null&&req.getParameter("eyear")!=null
						&&req.getParameter("locations")!=null)
					{
						String title = req.getParameter("title");
						try{
							int sday,smonth,syear;
							int eday,emonth,eyear;
							sday = Integer.parseInt(req.getParameter("sday"));
							smonth = Integer.parseInt(req.getParameter("smonth"));
							syear = Integer.parseInt(req.getParameter("syear"));
							eday = Integer.parseInt(req.getParameter("eday"));
							emonth = Integer.parseInt(req.getParameter("emonth"));
							eyear = Integer.parseInt(req.getParameter("eyear"));
							Calendar cal1 = Calendar.getInstance();
							Calendar cal = Calendar.getInstance();
							cal.set(Calendar.YEAR, syear);
							if (smonth == 12) {
								cal.set(Calendar.MONTH, Calendar.DECEMBER);
							} else if (smonth == 11) {
								cal.set(Calendar.MONTH, Calendar.NOVEMBER);
							} else if (smonth == 10) {
								cal.set(Calendar.MONTH, Calendar.OCTOBER);
							} else if (smonth == 9) {
								cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
							} else if (smonth == 8) {
								cal.set(Calendar.MONTH, Calendar.AUGUST);
							} else if (smonth == 7) {
								cal.set(Calendar.MONTH, Calendar.JULY);
							} else if (smonth == 6) {
								cal.set(Calendar.MONTH, Calendar.JUNE);
							} else if (smonth == 5) {
								cal.set(Calendar.MONTH, Calendar.MAY);
							} else if (smonth == 4) {
								cal.set(Calendar.MONTH, Calendar.APRIL);
							} else if (smonth == 3) {
								cal.set(Calendar.MONTH, Calendar.MARCH);
							} else if (smonth == 2) {
								cal.set(Calendar.MONTH, Calendar.FEBRUARY);
							} else if (smonth == 1) {
								cal.set(Calendar.MONTH, Calendar.JANUARY);
							}
							cal.set(Calendar.DATE, sday);
							cal.set(Calendar.HOUR, 0);
							cal.set(Calendar.MINUTE, 0);
							cal.set(Calendar.YEAR, syear);
							if (emonth == 12) {
								cal1.set(Calendar.MONTH, Calendar.DECEMBER);
							} else if (emonth == 11) {
								cal1.set(Calendar.MONTH, Calendar.NOVEMBER);
							} else if (emonth == 10) {
								cal1.set(Calendar.MONTH, Calendar.OCTOBER);
							} else if (emonth == 9) {
								cal1.set(Calendar.MONTH, Calendar.SEPTEMBER);
							} else if (emonth == 8) {
								cal1.set(Calendar.MONTH, Calendar.AUGUST);
							} else if (emonth == 7) {
								cal1.set(Calendar.MONTH, Calendar.JULY);
							} else if (emonth == 6) {
								cal1.set(Calendar.MONTH, Calendar.JUNE);
							} else if (emonth == 5) {
								cal1.set(Calendar.MONTH, Calendar.MAY);
							} else if (emonth == 4) {
								cal1.set(Calendar.MONTH, Calendar.APRIL);
							} else if (emonth == 3) {
								cal1.set(Calendar.MONTH, Calendar.MARCH);
							} else if (emonth == 2) {
								cal1.set(Calendar.MONTH, Calendar.FEBRUARY);
							} else if (emonth == 1) {
								cal1.set(Calendar.MONTH, Calendar.JANUARY);
							}
							cal1.set(Calendar.DATE, eday);
							cal1.set(Calendar.HOUR, 0);
							cal1.set(Calendar.MINUTE, 0);
							
							Timestamp start,end;
							start = new Timestamp(cal.getTimeInMillis());
							end = new Timestamp(cal1.getTimeInMillis());
							if(end.after(start)&&end.after(new Timestamp(new Date().getTime())))
							{
								String l = req.getParameter("locations");
								String locations[] = l.split(",");
								Long loc[];
								if(l.length()>0)
								{
									loc = new Long[locations.length];
									int i=0;
									for(String temp: locations)
									{
										loc[i++] = Long.parseLong(temp);
									}
									if(serv.writeElection(start, end, loc,title))
									{
										return "done";
									}else
									{
										req.setAttribute("error", "Can't create election, something went wrong.");
										return "error";
									}
								}else
								{
									req.setAttribute("error", "too few fields to continue.");
									return "error";	
								}
							}else
							{
								req.setAttribute("error", "Invalid dates.");
								return "error";	
							}
						}catch(NumberFormatException e)
						{
							req.setAttribute("error", "too few fields to continue.");
							return "error";	
						}
					}else
					{
						req.setAttribute("error", "too few fields to continue.");
						return "error";
					}
				}else
				{
					req.setAttribute("error", "you dont have permissions to view this page.");
					return "error";
				}
			}
		}
		return "nLoggedIn";
	}
	
}
