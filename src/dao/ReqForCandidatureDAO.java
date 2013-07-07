package dao;

import java.util.HashMap;
import java.util.Iterator;

import dao.ReqForCandidature;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ReqForCandidatureDAO extends BaseHibernate {
	
	public ReqForCandidatureDAO(){
		
	}
	public Boolean writeReqForCandidature(ReqForCandidatureWard reqForCandidature)
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
			System.err.println("Error: at ReqForCandidatureDAO function: writeReqForCandidature()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	
	public ReqForCandidature getRequestForCandidateId(Long id)
	{
		Session session = null;
		ReqForCandidature req4candidateid = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(ReqForCandidature.class);
			ctr.add(Restrictions.idEq(id));
			if(ctr.list().size()>0)
				req4candidateid = (ReqForCandidature) ctr.list().get(0); 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: ReqForCandidatureDAO method: getRequestForCandidateId()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		return req4candidateid;
	} 
	
	
	
	public HashMap<Request,ReqForCandidature> getRequestForCandidature(String id)
	{
		Session session = null;
		ReqForCandidature req4candidature = null;
		Criteria ctr;
		HashMap<Request,ReqForCandidature> hm = new HashMap<Request,ReqForCandidature>();
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
					Criteria ct = session.createCriteria(ReqForCandidature.class);
					ct.add(Restrictions.idEq(r.getReqid()));
					req4candidature = (ReqForCandidature) ct.uniqueResult();
					hm.put(r,req4candidature);
				}
			} 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: ReqForCandidatureDAO method: getRequestForCandidature()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return hm;
	} 
	
}
