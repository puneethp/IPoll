package dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ElectionDAO extends BaseHibernate{
	public Election getElect(String elecid)
	{
		Election el = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Election.class);
			constraint.add(Restrictions.idEq(elecid));
			if(constraint.list().size()>0)
			{
				el = (Election) constraint.list().get(0);
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: ElectionDAO Method:getElect()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Election.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		
	
		return el;
	}
	public ArrayList<Election> getElections()
	{
		ArrayList<Election> el = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Election.class);
			el = new ArrayList<Election>();
			for(int i=0;i<constraint.list().size();i++)
			{
				el.add((Election) constraint.list().get(i));
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: ElectionDAO Method:getElect()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Election.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		if(el==null)
			return null;
		if(el.size()>0)
			return el;
		else
			return null;
	}
	public ArrayList<Election> getDoneElections()
	{
		ArrayList<Election> el = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Election.class);
			constraint.add(Restrictions.eq("validity", Election.DONE));
			el = new ArrayList<Election>();
			for(int i=0;i<constraint.list().size();i++)
			{
				el.add((Election) constraint.list().get(i));
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: ElectionDAO Method:getDoneElection()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Election.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		if(el==null)
			return null;
		if(el.size()>0)
			return el;
		else
			return null;
	}
	public ArrayList<Election> getElectionsResults()
	{
		ArrayList<Election> el = null;
		Session session=null;
		Criteria constraint=null,ctr2=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Vote.class);
			el = new ArrayList<Election>();
			for(int i=0;i<constraint.list().size();i++)
			{
				ctr2 = session.createCriteria(Election.class);
				ctr2.add(Restrictions.idEq(((Vote)constraint.list().get(i)).getElecid()));
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: ElectionDAO Method:getElect()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Election.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		if(el==null)
			return null;
		if(el.size()>0)
			return el;
		else
			return null;
	}
	
	public boolean writeElection(Timestamp start,Timestamp end,Locations[] locations,String title)
	{
		Election el;
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			el = new Election(start,end,1);
			el.setTitle(title);
			session.save(el);
			for (Locations location : locations) {
				location.setElecid(el.getElecid());
				session.save(location);
			}
			trans.commit();session.flush();
			return true;
		}catch(NullPointerException e)
		{
			e.printStackTrace();
			System.err.println("Error: creating ElectionID.");
			if(trans!=null)
				trans.rollback();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			System.err.println("Error: creating ElectionID.");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	
	public boolean updateElect(Election el)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.update(el);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:ElectionDAO method:updateElect ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public boolean persistElect(Election el)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(el);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:ElectionDAO method:persistElect ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}

	public ArrayList<Long> getLocationsByElecid(String elecid)
	{
		ArrayList<Long> list = null;
		Election e = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Election.class);
			constraint.add(Restrictions.idEq(elecid));
			constraint.add(Restrictions.le("start", new Timestamp(new Date().getTime())));
			constraint.add(Restrictions.ge("end", new Timestamp(new Date().getTime())));
			e = (Election)constraint.uniqueResult();
			if(e!=null)
			{
				list = new ArrayList<Long>();
				constraint=session.createCriteria(Locations.class);
				constraint.add(Restrictions.eq("elecid", elecid));
				Iterator<Locations> it = constraint.list().iterator();
				while(it.hasNext())
					list.add(it.next().getConstituency());
			}
			return list;
		}catch(HibernateException el)
		{
			System.out.println("Error:Hibernate Exception\nFileName: ElectionDAO Method:getElect()");
			el.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Election.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		
		return list;
	}
	
	public ArrayList<Locations> getLocationsElecid(String elecid)
	{
		ArrayList<Locations> list = null;
		Election e = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Election.class);
			constraint.add(Restrictions.idEq(elecid));
			constraint.add(Restrictions.le("start", new Timestamp(new Date().getTime())));
			constraint.add(Restrictions.ge("end", new Timestamp(new Date().getTime())));
			e = (Election)constraint.uniqueResult();
			if(e!=null)
			{
				list = new ArrayList<Locations>();
				constraint=session.createCriteria(Locations.class);
				constraint.add(Restrictions.eq("elecid", elecid));
				Iterator<Locations> it = constraint.list().iterator();
				while(it.hasNext())
					list.add(it.next());
			}
			return list;
		}catch(HibernateException el)
		{
			System.out.println("Error:Hibernate Exception\nFileName: ElectionDAO Method:getElect()");
			el.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Election.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		
		return list;
	}
	
}

