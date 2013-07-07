package service;

import org.hibernate.SessionFactory;

import dao.WardID;
import dao.WardIdDAO;

public class WardIdServ {
	WardIdDAO dao;
	WardID ward;
	
	public WardIdDAO getDao() {
		return dao;
	}

	public void setDao(WardIdDAO dao) {
		this.dao = dao;
	}

	public WardID getWard() {
		return ward;
	}

	public void setWard(WardID ward) {
		this.ward = ward;
	}

	public boolean validity(String state,String city,String wardno,SessionFactory obj)
	{
		if(dao==null)
			dao=new WardIdDAO();
		if(dao.getSessionFactory()==null)
			dao.setSessionFactory(obj);
		if(dao.getWardId(state, city, wardno)!=null)
			return true;
		return false;
	}
	
	public boolean writeWardId(String state,String city,String wardno)
	{
		ward = new WardID(state, city, wardno);
		return dao.writeWardId(ward);
	}
	
	public boolean getWardFromId(Long wardid,SessionFactory obj)
	{
		if(dao==null)
			dao=new WardIdDAO();
		if(dao.getSessionFactory()==null)
			dao.setSessionFactory(obj);
		if(dao.getWardId(wardid)!=null)
			return true;
		return false;
	}
}
