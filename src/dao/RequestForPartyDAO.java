package dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class RequestForPartyDAO extends BaseHibernate {
	public boolean writeRequestForParty(RequestForParty req4party)
	{
		Session session=null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(req4party);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at ReqForPartyIdDAO function: createReqForParty()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	
	
	public RequestForParty getRequestForParty(Long reqid)
	{
		Session session = null;
		RequestForParty req4party = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(RequestForParty.class);
			ctr.add(Restrictions.idEq(reqid));
			if(ctr.list().size()>0)
				req4party = (RequestForParty) ctr.list().get(0); 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: ReqForPartyDAO method: getReqForParty()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		return req4party;
	}
	public HashMap<Request,RequestForParty> getRequestForPartyAdmin(String id)
	{
		Session session = null;
		RequestForVoterId req4voterid = null;
		Criteria ctr;
		HashMap<Request,RequestForParty> hm = new HashMap<Request,RequestForParty>();
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Request.class);
			ctr.add(Restrictions.eq("id",id));
			ctr.add(Restrictions.eq("reqtype",Request.PARTY));
			ctr.add(Restrictions.eq("reqstatus",Request.UNVERIFIED));
			if(ctr.list().size()>0)
			{
				Iterator<Request> it = ctr.list().iterator();
				
				while(it.hasNext())
				{
					Request r = it.next();
					Criteria ct = session.createCriteria(RequestForParty.class);
					ct.add(Restrictions.eq("reqid", r.getReqid()));
					hm.put(r,(RequestForParty)ct.uniqueResult());
				}
			} 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: ReqForVoterIdDAO method: getReqForVoterId()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return hm;
	} 
	
}
