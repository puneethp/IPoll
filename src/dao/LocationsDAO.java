package dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class LocationsDAO extends BaseHibernate{
	
	public Locations getLocation(long constituency)
	{
		Locations loc = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Locations.class);
			constraint.add(Restrictions.idEq(constituency));
			if(constraint.list().size()>0)
			{
				loc = (Locations) constraint.list().get(0);
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: LocationsDAO Method:getLocations()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Locations.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		
	
		return loc;
	}
	public boolean updateLocation(Locations loc)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.update(loc);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:LocationsDAO method:updateLocation");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public boolean persistLocation(Locations loc)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(loc);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:LocationsDAO method:persistLocation ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	public ArrayList<Candidate> getNumOfCandidateConstituency(Long constituency,String elecid)
	{
		Session session = null;
		ArrayList<String> numcon = new ArrayList<String>();
		ArrayList<Candidate> numcan=new ArrayList<Candidate>();
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Locations.class);
			ctr.add(Restrictions.eq("elecid",elecid));
			ctr.add(Restrictions.eq("constituency",constituency));
			if(ctr.list().size()>0)
			{
				ctr = session.createCriteria(Candidate.class);
				ctr.add(Restrictions.eq("constituency",constituency));
				ctr.add(Restrictions.eq("validity",Candidate.ACCEPTED));
				for(int i=0;ctr.list().size()>0&&i<ctr.list().size();i++)
					numcan.add((Candidate)ctr.list().get(i)); 
			}
			
		}catch(HibernateException e)
		{
			System.err.println("Error: file: LocationsDAO method: getNumOfCOnsti()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return numcan;
	}
	
	public ArrayList<Long> getConstiWhereElec(Long elecid)
	{
		Session session = null;
		ArrayList<Long> numcon = new ArrayList<Long>();
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Locations.class);
			ctr.add(Restrictions.eq("elecid",elecid));
			for(int i=0;ctr.list().size()>0&&i<ctr.list().size();i++)
			{
				numcon.add(((Locations)ctr.list().get(i)).getConstituency()); 
			}
			
		}catch(HibernateException e)
		{
			System.err.println("Error: file: LocationsDAO method: getNumOfCOnsti()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return numcon;
	}
	

	public ArrayList<Election> getElectionByConstituency(Long constituency)
	{
		Session session = null;
		
		ArrayList<Election> eleclist = new ArrayList<Election>();
		Criteria ctr,tempctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Locations.class);
			ctr.add(Restrictions.eq("constituency",constituency));
			for(int i=0;ctr.list().size()>0&&i<ctr.list().size();i++)
			{
				tempctr = session.createCriteria(Election.class);
				tempctr.add(Restrictions.eq("validity", Election.NOTDONE));
				tempctr.add(Restrictions.idEq(((Locations)ctr.list().get(i)).getElecid()));
				tempctr.add(Restrictions.le("start", new Timestamp(new Date().getTime())));
				tempctr.add(Restrictions.ge("end", new Timestamp(new Date().getTime())));
				if(tempctr.list().size()>0)
					eleclist.add((Election)tempctr.list().get(0));
			}
			
		}catch(HibernateException e)
		{
			System.err.println("Error: file: LocationsDAO method: getNumOfCOnsti()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return eleclist;
	}

}
