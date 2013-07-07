package dao;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class PartyDAO extends BaseHibernate{
	
	
	public Party getPartyByUser(String head)
	{
		Party par = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Party.class);
			constraint.add(Restrictions.eq("head",head));
			if(constraint.list().size()>0)
			{
				par = (Party) constraint.list().get(0);
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: PartyDAO Method:getParty()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Party.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		
	
		return par;
	}
	
	public Party getParty(String partyname)
	{
		Party par = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Party.class);
			constraint.add(Restrictions.idEq(partyname));
			if(constraint.list().size()>0)
			{
				par = (Party) constraint.list().get(0);
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: PartyDAO Method:getParty()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Party.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		
	
		return par;
	}
	public boolean updateParty(Party par)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.update(par);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:PartyDAO method:updateParty ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public boolean updatePartyPhoto(String partyname,FileContent content,FileInfo file)
	{
		Session session = null;
		Transaction trans = null;
		Party party;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(file);
			content.setFileId(file.getFileId());
			session.save(content);
			party = getParty(partyname);
			party.setSymbol(file.getFileId());
			party.setValidity(Party.NOTVALIDATED);
			session.update(party);
			Request req = new Request(Request.PARTY,"Party registration.",Request.UNVERIFIED,new Timestamp(new Date().getTime()),null,User.ADMIN);
			session.save(req);
			RequestForParty rp= new RequestForParty();
			rp.setHead(party.getHead());
			rp.setReqid(req.getReqid());
			session.save(rp);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at VoterIdDAO function: approveVoterId()");
			e.printStackTrace();
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			System.err.println("Error: at VoterIdDAO function: approveVoterId()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	
	public boolean writeParty(Party party,FileContent content,FileInfo file)
	{
		Session session = null;
		Transaction trans = null;
		UserDAO udao = new UserDAO();
		User uobject;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(file);
			content.setFileId(file.getFileId());
			content.setBlob(Hibernate.createBlob(content.temp));
			session.save(content);
			party.setSymbol(file.getFileId());
			session.save(party);
			udao.setSessionFactory(getSessionFactory());
			uobject = udao.getUser(party.getHead());
			if( uobject.getType().compareToIgnoreCase(User.CANDIDATE)==0){
				uobject.setType(User.CANDIDATEPARTY);
			}
			else
				uobject.setType(User.PARTYHEAD);
			session.update(uobject);
			Request req = new Request(Request.PARTY,"Party registration.",Request.UNVERIFIED,new Timestamp(new Date().getTime()),null,User.USERADMIN);
			session.save(req);
			RequestForParty rp= new RequestForParty();
			rp.setHead(party.getHead());
			rp.setReqid(req.getReqid());
			session.save(rp);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at VoterIdDAO function: approveVoterId()");
			e.printStackTrace();
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			System.err.println("Error: at VoterIdDAO function: approveVoterId()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	
	public boolean persistParty(Party par)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(par);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:PartyDAO method:persistparty ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public boolean acceptParty(Long reqid,String head)
	{
		Session session=null;
		Transaction trans = null;
		Request req = null;
		Party party;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			ctr = session.createCriteria(Request.class);
			ctr.add(Restrictions.idEq(reqid));
			if(ctr.list().size()>0)
				req = (Request) ctr.list().get(0);
			if(req==null)
				throw new Exception("Cannot find the desired request");
			if(req.getReqstatus().compareToIgnoreCase(Request.UNVERIFIED)!=0)
				throw new Exception("The desired request is served.");
			req.setReqstatus(Request.ACCEPTED);
			req.setReqServ(new Timestamp(new Date().getTime()));
			session.update(req);
			party = getPartyByUser(head);
			if(party==null)
				throw new Exception("The requested voterid doesnt exist.");
			party.setValidity(Party.ACCEPTED);
			session.update(party);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at VoterIdDAO function: approveVoterId()");
			e.printStackTrace();
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			System.err.println("Error: at VoterIdDAO function: approveVoterId()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	
	public boolean rejectParty(Long reqid,String head)
	{
		Session session=null;
		Transaction trans = null;
		Request req = null;
		Party party;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			ctr = session.createCriteria(Request.class);
			ctr.add(Restrictions.idEq(reqid));
			if(ctr.list().size()>0)
				req = (Request) ctr.list().get(0);
			if(req==null)
				throw new Exception("Cannot find the desired request");
			if(req.getReqstatus().compareToIgnoreCase(Request.UNVERIFIED)!=0)
				throw new Exception("The desired request is served.");
			req.setReqstatus(Request.REJECTED);
			req.setReqServ(new Timestamp(new Date().getTime()));
			session.update(req);
			party = getPartyByUser(head);
			if(party==null)
				throw new Exception("The requested voterid doesnt exist.");
			party.setValidity(Party.REJECTED);
			session.update(party);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at VoterIdDAO function: approveVoterId()");
			e.printStackTrace();
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			System.err.println("Error: at VoterIdDAO function: approveVoterId()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
}


