
package dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class VoteDAO extends BaseHibernate{
	public Vote getVote(String voteid)
	{
		Vote v = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Vote.class);
			constraint.add(Restrictions.idEq(voteid));
			if(constraint.list().size()>0)
			{
				v = (Vote) constraint.list().get(0);
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: VoteDAO Method:getVote()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Vote.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		
	
		return v;
	}
	public boolean updateVote(Vote v)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.update(v);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:VoteDAO method:updateVote ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public boolean persistVote(Vote v)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(v);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:VoteDAO method:persistVote ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public Vote castVoteByUser(String user)
	{
		Session session = null;
		Vote v = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Vote.class);
			ctr.add(Restrictions.eq("user",user));
			if(ctr.list().size()>0)
					v = (Vote) ctr.list().get(0); 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: VoteDAO method: castVoteByUser()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return v;
	}
	
	public Vote castVoteByCandidate(String candidateid)
	{
		Session session = null;
		Vote v = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Vote.class);
			ctr.add(Restrictions.eq("candidateid",candidateid));
			if(ctr.list().size()>0)
					v = (Vote) ctr.list().get(0); 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: VoteDAO method: castVoteByCandidate()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return v;
	}
	
	public ArrayList<Vote> getVoteByElecid(String elecid,String constituency)
	{
		Session session = null;
		ArrayList<Vote> Votelist = new ArrayList<Vote>();
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Vote.class);
			ctr.add(Restrictions.eq("elecid",elecid));
			ctr.add(Restrictions.eq("constituency",constituency));
			for(int i=0;ctr.list().size()>0&&i<ctr.list().size();i++)
					Votelist.add((Vote)ctr.list().get(i)); 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: VoteDAO method: getVoteByElecid()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return Votelist;
	}
	
	public ArrayList<Candidate> getCandidate(long constituency)
	{
		Session session = null;
		ArrayList<Candidate> list = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Candidate.class);
			ctr.add(Restrictions.eq("constituency",constituency));
			ctr.add(Restrictions.eq("validity",Candidate.ACCEPTED));
			list = new ArrayList<Candidate>();
			for(int i=0;ctr.list().size()>0&&i<ctr.list().size();i++)
					list.add((Candidate)ctr.list().get(i)); 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: VoteDAO method: getVoteByElecid()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public boolean getVoteByUserForElecId(String voterid,String elecid)
	{
		Session session = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Vote.class);
			ctr.add(Restrictions.eq("voterid", voterid));
			ctr.add(Restrictions.eq("elecid", elecid));
			if(ctr.list().size()>0)
				return true;
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: VoteDAO Method:getVote()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Vote.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		return false;
	}
}
