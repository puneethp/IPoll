package dao;

import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ReqForCandidatureWardDAO extends BaseHibernate {

	public ReqForCandidatureWardDAO(){
		
	}
	public boolean writeReqForCandidatureWard(ReqForCandidatureWard reqForCandidature)
	{
		Session session=null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(reqForCandidature);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at ReqForCandidatureWardDAO function: writeReqForCandidatureWard()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}

	public HashMap<Request,ReqForCandidatureWard> getRequestForCandidatureWardUser(String id)
	{
		Session session = null;
		ReqForCandidature req4candidature = null;
		Criteria ctr;
		HashMap<Request,ReqForCandidatureWard> hm = new HashMap<Request,ReqForCandidatureWard>();
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Request.class);
			ctr.add(Restrictions.eq("id",id));
			ctr.add(Restrictions.eq("reqtype",Request.CANDIDATURE));
			ctr.add(Restrictions.eq("reqstatus",Request.UNVERIFIED));
			if(ctr.list().size()>0)
			{
				Iterator<Request> it = ctr.list().iterator();
				
				while(it.hasNext())
				{
					Request r = it.next();
					Criteria ct = session.createCriteria(ReqForCandidatureWard.class);
					ct.add(Restrictions.eq("reqId", r.getReqid()));
					hm.put(r,(ReqForCandidatureWard)ct.uniqueResult());
				}
			} 
		}catch(HibernateException e)
		{
			e.printStackTrace();
			System.err.println("Error: file: ReqForCandidatureWardDAO method: getRequestForCandidatureWardUser()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return hm;
	} 
	public ReqForCandidatureWard getRequestForCandidateIdWard(Long id)
	{
		Session session = null;
		ReqForCandidatureWard req4candidateid = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(ReqForCandidatureWard.class);
			ctr.add(Restrictions.idEq(id));
			if(ctr.list().size()>0)
				req4candidateid = (ReqForCandidatureWard) ctr.list().get(0); 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: ReqForCandidatureDAO method: getRequestForCandidateIdWard()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		return req4candidateid;
	} 
}
