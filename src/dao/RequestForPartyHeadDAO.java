package dao;

import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class RequestForPartyHeadDAO extends BaseHibernate {
	public RequestForPartyHeadDAO(){
		
	}
	public boolean writeReqForPartyHead(RequestForPartyHead req4phead)
	{
		Session session=null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(req4phead);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at RequestForPartyHeadDAO function: writeReqForPartyHead()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	
	public HashMap<Request,RequestForPartyHead> getRequestForPartyHead(String id)
	{
		Session session = null;
		Criteria ctr;
		HashMap<Request,RequestForPartyHead> hm = new HashMap<Request,RequestForPartyHead>();
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Request.class);
			ctr.add(Restrictions.eq("id",id));
			ctr.add(Restrictions.eq("reqtype",Request.CANDIDATUREPARTY));
			ctr.add(Restrictions.eq("reqstatus", Request.UNVERIFIED ));
			if(ctr.list().size()>0)
			{
				Iterator<Request> it = ctr.list().iterator();
				
				while(it.hasNext())
				{
					Request r = it.next();
					Criteria ct = session.createCriteria(RequestForPartyHead.class);
					ct.add(Restrictions.eq("reqid", r.getReqid()));
					hm.put(r,(RequestForPartyHead)ct.uniqueResult());
				}
			} 
		}catch(HibernateException e)
		{
			e.printStackTrace();
			System.err.println("Error: file: RequestForPartyHeadDAO method: getRequestForPartyHead()");
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		
		return hm;
	} 
	public RequestForPartyHead getRequestForPartyHead(Long id)
	{
		Session session = null;
		RequestForPartyHead req4phead = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(RequestForPartyHead.class);
			ctr.add(Restrictions.idEq(id));
			if(ctr.list().size()>0)
				req4phead = (RequestForPartyHead) ctr.list().get(0); 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: RequestForPartyHeadDAO method: getRequestForPartyHead()");
			e.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return req4phead;
	} 
}
