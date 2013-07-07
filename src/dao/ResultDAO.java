package dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

public class ResultDAO extends BaseHibernate {
	public Result getResult(String resultid)
	{
		Result result = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Result.class);
			constraint.add(Restrictions.idEq(resultid));
			if(constraint.list().size()>0)
			{
				result = (Result) constraint.list().get(0);
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: ResultDAO Method:getResult()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Result.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		
	
		return result;
	}
	
	public BigInteger getParticipantsForElectionPerLocation(String elecid,long constituency){
		BigInteger result = null;
		Session session;
		try{
			session = getSessionFactory().openSession();
			Query q = session.createSQLQuery("select count(*)as total from vote where elecid=:e and constituency=:c");
			q.setParameter("c", constituency);
			q.setParameter("e", elecid);
			List l =q.list();
			result = (BigInteger)l.get(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public BigInteger registeredVotersForElection(long constituency){
		BigInteger result = null;
		Session session;
		try{
			session = getSessionFactory().openSession();
			Query q = session.createSQLQuery("select count(*)as total from voterid where wardid=:c");
			q.setParameter("c", constituency);
			List l =q.list();
			result = (BigInteger)l.get(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<VoteRatio> getResultVoteRatioForElection(String elecid,long constituency){
		ArrayList<VoteRatio> result = new ArrayList<VoteRatio>();
		Session session;
		try{
			session = getSessionFactory().openSession();
			Query q = session.createSQLQuery("select count(*)as votes,vote.candidateid,result.elecid,result.resultid from vote,result where result.elecid = vote.elecid and result.constituency=:c and vote.elecid=:e group by vote.candidateid");
			q.setParameter("c", constituency);
			q.setParameter("e", elecid);
//			q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			q.setResultTransformer(Transformers.aliasToBean(VoteRatio.class));
			System.out.println("Query: "+q.getQueryString());
			result.addAll((List<VoteRatio>)q.list());
//			for(HashMap<String, Object> p:(List<HashMap<String,Object>>)q.list()){
//				System.out.println(p);
//				VoteRatio v = new VoteRatio();
//				v.setVotes((BigInteger)p.get("votes"));
//				v.setElecid((String)p.get("elecid"));
//				v.setCandidateid((String)p.get("candidateid"));
//				v.setResultid((String)p.get("resultid"));
//				result.add(v);
//			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Result> getResultForElection(String elecid)
	{
		ArrayList<Result> result = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Result.class);
			constraint.add(Restrictions.eq("elecid", elecid));
			result = new ArrayList<Result>();
			for(int i=0;i<constraint.list().size();i++)
			{
				result.add((Result) constraint.list().get(i));
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: ResultDAO Method:getResult()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Result.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		return result;
	}
	
	public boolean findResult(String elecid)
	{
		Session session = null;
		Transaction trans = null;
		Criteria ctr,ctr1;
		ArrayList<Long> locations;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			ElectionDAO edao = new ElectionDAO();
			edao.setSessionFactory(getSessionFactory());
			locations =edao.getLocationsByElecid(elecid);
			VoteDAO vdao = new VoteDAO();
			vdao.setSessionFactory(getSessionFactory());
			for(int i=0;i<locations.size();i++)
			{
				int max = 0;
				String candidatemax = null;
				ctr = session.createCriteria(Vote.class);
				ctr.add(Restrictions.eq("elecid", elecid));
				ctr.add(Restrictions.eq("constituency", locations.get(i)));
				ArrayList<Candidate> candidlist = vdao.getCandidate( locations.get(i));
				ArrayList<Integer> temp = new ArrayList<Integer>();
				for(int j=0;j<candidlist.size();j++)
				{
					ctr1  = session.createCriteria(Vote.class);
					ctr1.add(Restrictions.eq("elecid", elecid));
					ctr1.add(Restrictions.eq("constituency", locations.get(i)));
					ctr1.add(Restrictions.eq("candidateid", candidlist.get(j).getUser()));
					if(ctr1.list().size()>0)
					{
						if(ctr1.list().size()>max)
						{
							if(max!=0)
								temp.add(max);
							max = ctr1.list().size();
							candidatemax = candidlist.get(j).getUser();
						}else
						{
							temp.add(ctr1.list().size());
						}
					}
				}
				if(!temp.contains(max)&&candidatemax!=null)
				{
					Result result = new Result();
					result.setCandidate(candidatemax);
					result.setConstituency((locations.get(i)));
					result.setElecid(elecid);
					session.save(result);
				}else
				{
					Result result = new Result();	
					result.setConstituency((locations.get(i)));
					result.setElecid(elecid);
					session.save(result);
				}
			}
			Election el = edao.getElect(elecid);
			el.setValidity(Election.DONE);
			session.update(el);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			e.printStackTrace();
			if(trans!=null)
				trans.rollback();
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public ArrayList<Result> getResultsForElecid(String elecid)
	{
		ArrayList<Result> list = null;
		Session session = null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Result.class);
			constraint.add(Restrictions.eq("elecid",elecid));
			list = new ArrayList<Result>();
			for(int i=0;i<constraint.list().size();i++)
				list.add((Result)constraint.list().get(i));
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: ResultDAO Method:getResult()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Result.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		return list;
	}
}
