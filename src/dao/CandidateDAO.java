package dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.Date;

public class CandidateDAO extends BaseHibernate{
	
	public Boolean updateCandidate(Candidate cand)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.update(cand);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:CandidateDAO method:updateCandidate ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public Boolean persistcandidate(Candidate cand)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(cand);
			trans.commit();session.flush();
			session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:CandidateDAO method:persistCandidate ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	public Candidate getCandidateIdByUser(String id){
	
		Session session = null;
		Candidate candidateCard = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Candidate.class);
			ctr.add(Restrictions.idEq(id));
			if(ctr.list().size()>0)
					candidateCard = (Candidate) ctr.list().get(0); 
		}catch(HibernateException e)
		{
			e.printStackTrace();
			System.err.println("Error: file: CnadidateDAO method: getCandidateByUserId()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return candidateCard;
	}
	public Boolean writeCandidatureIndependent( ReqForCandidature reqCandidate1,
			ReqForCandidature reqCandidate2, ReqForCandidature reqCandidate3, ReqForCandidature reqCandidate4,
			ReqForCandidature reqCandidate5, ReqForCandidature reqCandidate6, ReqForCandidature reqCandidate7,
			ReqForCandidature reqCandidate8, ReqForCandidature reqCandidate9, ReqForCandidature reqCandidate10,
			Request reqToWard, FileInfo info, FileContent content, Candidate candidate,
			WardID wardId, String user, ReqForCandidatureWard reqForCandidatureWard){
		
		VoterIdDAO vdao= new VoterIdDAO();
		Session session=null;
		Transaction trans=null;
		UserDAO udao= new UserDAO();
		User uobject= null;
		try{
			
			
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(info);
			content.setFileId(info.getFileId());
			content.setBlob(Hibernate.createBlob(content.temp));
			session.save(content);
			candidate.setSymbol(info.getFileId());
			
			WardIdDAO t = new WardIdDAO();
			t.setSessionFactory(getSessionFactory());
			//candidate.setConstituency(candidate.getConstituency());
			vdao.setSessionFactory(getSessionFactory());
			udao.setSessionFactory(getSessionFactory());
			uobject = udao.getUser(user);
			if( uobject.getType().compareToIgnoreCase(User.PARTYHEAD)==0){
				uobject.setType(User.CANDIDATEPARTY);
			}
			else
				uobject.setType(User.CANDIDATE);
			session.update(uobject);
			candidate.setPartyType(Candidate.NOPARTY);
			if(vdao.getVoterIdByUser(candidate.getUser())== null)
			{
				throw new Exception();
			}
			
			WardUserDAO wsd = new WardUserDAO();
			wsd.setSessionFactory(getSessionFactory());
			WardUser ws = wsd.getWardUser(candidate.getConstituency());
			reqToWard.setId(ws.getId());
			session.save(reqToWard);
			reqForCandidatureWard = new ReqForCandidatureWard();
			reqForCandidatureWard.setReqId(reqToWard.getReqid());
			reqForCandidatureWard.setCandidateId(candidate.getUser());
			session.save(reqForCandidatureWard);
			
			reqCandidate1= new ReqForCandidature();
			reqCandidate2= new ReqForCandidature();
			reqCandidate3= new ReqForCandidature();
			reqCandidate4= new ReqForCandidature();
			reqCandidate5= new ReqForCandidature();
			reqCandidate6= new ReqForCandidature();
			reqCandidate7= new ReqForCandidature();
			reqCandidate8= new ReqForCandidature();
			reqCandidate9= new ReqForCandidature();
			reqCandidate10= new ReqForCandidature();
			
			reqCandidate1.setCandidateId(candidate.getUser());
			reqCandidate2.setCandidateId(candidate.getUser());
			reqCandidate3.setCandidateId(candidate.getUser());
			reqCandidate4.setCandidateId(candidate.getUser());
			reqCandidate5.setCandidateId(candidate.getUser());
			reqCandidate6.setCandidateId(candidate.getUser());
			reqCandidate7.setCandidateId(candidate.getUser());
			reqCandidate8.setCandidateId(candidate.getUser());
			reqCandidate9.setCandidateId(candidate.getUser());
			reqCandidate10.setCandidateId(candidate.getUser());
			
			//Create Request  Objects
			Request req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate1());
			session.save(req);
			reqCandidate1.setReqId(req.getReqid());
			session.save(reqCandidate1);
			if(candidate.getCandidate2()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate2());
			session.save(req);
			reqCandidate2.setReqId(req.getReqid());
			session.save(reqCandidate2);
			}
			
			if(candidate.getCandidate3()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate3());
			session.save(req);
			reqCandidate3.setReqId(req.getReqid());
			session.save(reqCandidate3);
			}
			
			if(candidate.getCandidate4()!=null){
				req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
			
				new Timestamp(new Date().getTime()),null, candidate.getCandidate4());
				session.save(req);
				reqCandidate4.setReqId(req.getReqid());
				session.save(reqCandidate4);
			}
			if(candidate.getCandidate5()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate5());
			session.save(req);
			reqCandidate5.setReqId(req.getReqid());
			session.save(reqCandidate5);
			}
			
			if(candidate.getCandidate6()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate6());
			session.save(req);
			reqCandidate6.setReqId(req.getReqid());
			session.save(reqCandidate6);
			}
			if(candidate.getCandidate7()  !=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate7());
			session.save(req);
			reqCandidate7.setReqId(req.getReqid());
			session.save(reqCandidate7);
			}
			if(candidate.getCandidate8()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate8());
			session.save(req);
			reqCandidate8.setReqId(req.getReqid());
			session.save(reqCandidate8);
			}
			
			if(candidate.getCandidate9()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate9());
			session.save(req);
			reqCandidate9.setReqId(req.getReqid());
			session.save(reqCandidate9);
			}
			
			if(candidate.getCandidate10()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate10());
			session.save(req);
			reqCandidate10.setReqId(req.getReqid());
			session.save(reqCandidate10);
			}
			session.save(candidate);
			trans.commit();session.flush();
			session.flush();
			return true;
			
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			System.err.println("Error: creating candidature.");
			if(trans!=null)
				trans.rollback();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			System.err.println("Error: creating candidature.");
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Error: creating candidature.");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return false;
	}
	

	 
	 public Boolean writeCandidatureParty( 
			Request reqToWard, Candidate candidate,	WardID wardId, String user, 
			ReqForCandidatureWard reqForCandidatureWard){
		
		VoterIdDAO vdao= new VoterIdDAO();
		Session session=null;
		Transaction trans=null;
		UserDAO udao= new UserDAO();
		PartyDAO pdao= new PartyDAO();
		User uobject= null;
		try{
			
			
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			
			WardIdDAO t = new WardIdDAO();
			t.setSessionFactory(getSessionFactory());
			vdao.setSessionFactory(getSessionFactory());
			udao.setSessionFactory(getSessionFactory());
			pdao.setSessionFactory(getSessionFactory());
			uobject = udao.getUser(user);
			if( uobject.getType().compareToIgnoreCase(User.PARTYHEAD)==0){
				uobject.setType(User.CANDIDATEPARTY);
			}
			else
				uobject.setType(User.CANDIDATE);
			session.update(uobject);
			candidate.setPartyType(Candidate.PARTY);
			if(vdao.getVoterIdByUser(candidate.getUser())== null)
			{
				throw new Exception();
			}
			
			WardUserDAO wsd = new WardUserDAO();
			wsd.setSessionFactory(getSessionFactory());
			WardUser ws = wsd.getWardUser(candidate.getConstituency());
			reqToWard.setId(ws.getId());
			session.save(reqToWard);
			reqForCandidatureWard = new ReqForCandidatureWard();
			reqForCandidatureWard.setReqId(reqToWard.getReqid());
			reqForCandidatureWard.setCandidateId(candidate.getUser());
			session.save(reqForCandidatureWard);
			
			Party p = pdao.getParty(candidate.getRepresentingParty());
			Request req2phead= new Request(Request.CANDIDATUREPARTY,"Verify my candidature under your party name", Request.UNVERIFIED,new Timestamp(new Date().getTime()),null ,p.getHead());
			RequestForPartyHead req4phead = new RequestForPartyHead();
			session.save(req2phead);
			req4phead.setCandidateid(uobject.getId());
			req4phead.setReqid(req2phead.getReqid());
			session.save(req4phead);
	 		session.save(candidate);
			trans.commit();session.flush();
			return true;
			
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			System.err.println("Error: creating candidature.");
			if(trans!=null)
				trans.rollback();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			System.err.println("Error: creating candidature.");
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Error: creating candidature.");
			if(trans!=null)
				trans.rollback();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	 
	 
	 

	 public Boolean updateCandidatureParty( ReqForCandidature reqCandidate1,
				ReqForCandidature reqCandidate2, ReqForCandidature reqCandidate3, ReqForCandidature reqCandidate4,
				ReqForCandidature reqCandidate5, ReqForCandidature reqCandidate6, ReqForCandidature reqCandidate7,
				ReqForCandidature reqCandidate8, ReqForCandidature reqCandidate9, ReqForCandidature reqCandidate10,
				Request reqToWard, Candidate candidate,	WardID wardId, String user, 
				ReqForCandidatureWard reqForCandidatureWard){
		 
		 VoterIdDAO vdao= new VoterIdDAO();
			Session session=null;
			Transaction trans=null;
			UserDAO udao= new UserDAO();
			PartyDAO pdao= new PartyDAO();
			User uobject= null;
			try{
				
				
				session = getSessionFactory().openSession();
				trans = session.beginTransaction();
				
				WardIdDAO t = new WardIdDAO();
				t.setSessionFactory(getSessionFactory());
				vdao.setSessionFactory(getSessionFactory());
				udao.setSessionFactory(getSessionFactory());
				pdao.setSessionFactory(getSessionFactory());
				uobject = udao.getUser(user);
				if( uobject.getType().compareToIgnoreCase(User.PARTYHEAD)==0){
					uobject.setType(User.CANDIDATEPARTY);
				}
				else
					uobject.setType(User.CANDIDATE);
				session.update(uobject);
				candidate.setPartyType(Candidate.PARTY);
				if(vdao.getVoterIdByUser(candidate.getUser())== null)
				{
					throw new Exception();
				}
				
				WardUserDAO wsd = new WardUserDAO();
				wsd.setSessionFactory(getSessionFactory());
				WardUser ws = wsd.getWardUser(candidate.getConstituency());
				reqToWard.setId(ws.getId());
				session.save(reqToWard);
				reqForCandidatureWard = new ReqForCandidatureWard();
				reqForCandidatureWard.setReqId(reqToWard.getReqid());
				reqForCandidatureWard.setCandidateId(candidate.getUser());
				session.save(reqForCandidatureWard);
				
				Party p = pdao.getParty(candidate.getRepresentingParty());
				Request req2phead= new Request(Request.CANDIDATUREPARTY,"Verify my candidature under your party name", Request.UNVERIFIED,new Timestamp(new Date().getTime()),null ,p.getHead());
				RequestForPartyHead req4phead = new RequestForPartyHead();
				session.save(req2phead);
				req4phead.setCandidateid(uobject.getId());
				req4phead.setReqid(req2phead.getReqid());
				session.save(req4phead);
		 		session.update(candidate);
				trans.commit();session.flush();
				return true;
				
			}
			catch(NullPointerException e)
			{
				e.printStackTrace();
				System.err.println("Error: creating candidature.");
				if(trans!=null)
					trans.rollback();
			}
			catch(HibernateException e)
			{
				e.printStackTrace();
				System.err.println("Error: creating candidature.");
				if(trans!=null)
					trans.rollback();
			}catch(Exception e)
			{
				e.printStackTrace();
				System.err.println("Error: creating candidature.");
				if(trans!=null)
					trans.rollback();
			}
			finally
			{
				if(session!=null)
					session.close();
			}
			return false;
		 
	 } 
	
	public Boolean updateCandidatureIndependent(ReqForCandidature reqCandidate1,
			ReqForCandidature reqCandidate2, ReqForCandidature reqCandidate3, ReqForCandidature reqCandidate4,
			ReqForCandidature reqCandidate5, ReqForCandidature reqCandidate6, ReqForCandidature reqCandidate7,
			ReqForCandidature reqCandidate8, ReqForCandidature reqCandidate9, ReqForCandidature reqCandidate10,
			Request reqToWard, FileInfo info, FileContent content, Candidate candidate,
			WardID wardId, String user, ReqForCandidatureWard reqForCandidatureWard){
		
		VoterIdDAO vdao= new VoterIdDAO();
		Session session=null;
		Transaction trans=null;
		UserDAO udao= new UserDAO();
		User uobject= null;
		
		try{
			
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(info);
			content.setFileId(info.getFileId());
			content.setBlob(Hibernate.createBlob(content.temp));
			session.save(content);
			candidate.setSymbol(info.getFileId());
			
			WardIdDAO t = new WardIdDAO();
			t.setSessionFactory(getSessionFactory());
			//candidate.setConstituency(candidate.getConstituency());
			vdao.setSessionFactory(getSessionFactory());
			udao.setSessionFactory(getSessionFactory());
			uobject = udao.getUser(user);
			if( uobject.getType().compareToIgnoreCase(User.PARTYHEAD)==0){
				uobject.setType(User.CANDIDATEPARTY);
			}
			else if(uobject.getType().compareToIgnoreCase(User.CANDIDATEPARTY)==0){
				uobject.setType(User.PARTYHEAD);
			}else
				uobject.setType(User.CANDIDATE);
			session.update(uobject);
			candidate.setPartyType(Candidate.NOPARTY);
			if(vdao.getVoterIdByUser(candidate.getUser())== null)
			{
				throw new Exception();
			}
			
			WardUserDAO wsd = new WardUserDAO();
			wsd.setSessionFactory(getSessionFactory());
			WardUser ws = wsd.getWardUser(candidate.getConstituency());
			reqToWard.setId(ws.getId());
			session.save(reqToWard);
			reqForCandidatureWard = new ReqForCandidatureWard();
			reqForCandidatureWard.setReqId(reqToWard.getReqid());
			reqForCandidatureWard.setCandidateId(candidate.getUser());
			session.save(reqForCandidatureWard);
			
			reqCandidate1= new ReqForCandidature();
			reqCandidate2= new ReqForCandidature();
			reqCandidate3= new ReqForCandidature();
			reqCandidate4= new ReqForCandidature();
			reqCandidate5= new ReqForCandidature();
			reqCandidate6= new ReqForCandidature();
			reqCandidate7= new ReqForCandidature();
			reqCandidate8= new ReqForCandidature();
			reqCandidate9= new ReqForCandidature();
			reqCandidate10= new ReqForCandidature();
			
			reqCandidate1.setCandidateId(candidate.getUser());
			reqCandidate2.setCandidateId(candidate.getUser());
			reqCandidate3.setCandidateId(candidate.getUser());
			reqCandidate4.setCandidateId(candidate.getUser());
			reqCandidate5.setCandidateId(candidate.getUser());
			reqCandidate6.setCandidateId(candidate.getUser());
			reqCandidate7.setCandidateId(candidate.getUser());
			reqCandidate8.setCandidateId(candidate.getUser());
			reqCandidate9.setCandidateId(candidate.getUser());
			reqCandidate10.setCandidateId(candidate.getUser());
			
			//Create Request  Objects
			Request req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate1());
			session.save(req);
			reqCandidate1.setReqId(req.getReqid());
			session.save(reqCandidate1);
			if(candidate.getCandidate2()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate2());
			session.save(req);
			reqCandidate2.setReqId(req.getReqid());
			session.save(reqCandidate2);
			}
			
			if(candidate.getCandidate3()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate3());
			session.save(req);
			reqCandidate3.setReqId(req.getReqid());
			session.save(reqCandidate3);
			}
			
			if(candidate.getCandidate4()!=null){
				req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
			
				new Timestamp(new Date().getTime()),null, candidate.getCandidate4());
				session.save(req);
				reqCandidate4.setReqId(req.getReqid());
				session.save(reqCandidate4);
			}
			if(candidate.getCandidate5()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate5());
			session.save(req);
			reqCandidate5.setReqId(req.getReqid());
			session.save(reqCandidate5);
			}
			
			if(candidate.getCandidate6()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate6());
			session.save(req);
			reqCandidate6.setReqId(req.getReqid());
			session.save(reqCandidate6);
			}
			if(candidate.getCandidate7()  !=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate7());
			session.save(req);
			reqCandidate7.setReqId(req.getReqid());
			session.save(reqCandidate7);
			}
			if(candidate.getCandidate8()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate8());
			session.save(req);
			reqCandidate8.setReqId(req.getReqid());
			session.save(reqCandidate8);
			}
			
			if(candidate.getCandidate9()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate9());
			session.save(req);
			reqCandidate9.setReqId(req.getReqid());
			session.save(reqCandidate9);
			}
			
			if(candidate.getCandidate10()!=null){
			req= new Request(Request.CANDIDATURE, "I'm requesting you to be my reference for my Candidature.",Request.UNVERIFIED,
					new Timestamp(new Date().getTime()),null, candidate.getCandidate10());
			session.save(req);
			reqCandidate10.setReqId(req.getReqid());
			session.save(reqCandidate10);
			}
			
			session.update(candidate);
			trans.commit();session.flush();
			return true;
			
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			System.err.println("Error: creating candidature.");
			if(trans!=null)
				trans.rollback();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			System.err.println("Error: creating candidature.");
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Error: creating candidature.");
			if(trans!=null)
				trans.rollback();
		}
		
		
		finally
		{
			if(session!=null)
				session.close();
		}
		
		return false;
	}
	
	
	
	
	public String getWardId(String currState, String currCity, String currWardNo){
		WardID wardid;
		WardIdDAO t = new WardIdDAO();
		t.setSessionFactory(getSessionFactory());
		wardid = t.getWardId(currState, currCity, currWardNo);
		if(wardid!=null)
			return String.valueOf(wardid.wardId);
		return null;
	}
	
	public Candidate getCandidateId(String CandidateId)
	{
		Candidate cand = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Candidate.class);
			constraint.add(Restrictions.idEq(CandidateId));
			if(constraint.list().size()>0)
			{
				cand = (Candidate) constraint.list().get(0);
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: CandiateDAO Method:getCandidateId()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Candidate.hbm.xml");
		}finally
		{
			if(session!=null)
				session.close();
		}
		return cand;
	}
	
	
	public boolean approveCandidature(String candidateid,Long reqid,String warduser)
	{
		Session session=null;
		Transaction trans = null;
		Request req = null;
		Candidate candidatecard = null;
		Criteria ctr = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Request.class);
			ctr.add(Restrictions.idEq(reqid));
			if(ctr.list().size()>0)
				req = (Request) ctr.list().get(0);
			if(req==null)
				throw new Exception("Cannot find the desired request");
			if(req.getReqstatus().compareToIgnoreCase(Request.VERIFIED)==0)
				throw new Exception("The desired request is served.");
			req.setReqstatus(Request.ACCEPTED);
			req.setReqServ(new Timestamp(new Date().getTime()));
			session.update(req);
			session = getSessionFactory().openSession();
			ctr=session.createCriteria(Candidate.class);
			ctr.add(Restrictions.idEq(candidateid));
			if(ctr.list().size()>0)
			{
				candidatecard = (Candidate) ctr.list().get(0);
			}
			if(candidatecard==null)
				throw new Exception("The requested Candidate doesnot exists doesnt exist.");
			candidatecard.setValidity(Candidate.PARTIALLYACCEPTED);
		
			candidatecard.setDateOfEffect(new Timestamp(new Date().getTime()));
			System.out.println("Candidate: "+candidatecard);
			session.update(candidatecard);
			session.flush();
			System.out.println("Candidate: "+candidatecard);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at CandidateDAO function: approveCandidatureId()");
			e.printStackTrace();
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			System.err.println("Error: at CandidateDAO function: approveCandidatureId()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	public boolean approveCandidatureWard(String candidateid,Long reqid,String warduser)
	{
		Session session=null;
		Transaction trans = null;
		Request req = null;
		Candidate candidatecard = null;
		Criteria ctr = null;
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
			if(req.getReqstatus().compareToIgnoreCase(Request.VERIFIED)==0)
				throw new Exception("The desired request is served.");
			req.setReqstatus(Request.ACCEPTED);
			req.setReqServ(new Timestamp(new Date().getTime()));
			session.update(req);
			candidatecard = getCandidateId(candidateid);
			if(candidatecard==null)
				throw new Exception("The requested Candidate doesnot exists doesnt exist.");
			candidatecard.setValidity(candidatecard.ACCEPTED);
		
			candidatecard.setDateOfEffect(new Timestamp(new Date().getTime()));
			session.update(candidatecard);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at CandidateDAO function: approveCandidatureWard()");
			e.printStackTrace();
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			System.err.println("Error: at CandidateDAO function: approveCandidatureWard()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	public boolean rejectCandidatureWard(String voterid,Long reqid,String warduser)
	{
		Session session=null;
		Transaction trans = null;
		Request req = null;
		Candidate candidatecard = null;
		Criteria ctr = null;
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
			candidatecard = getCandidateId(voterid);
			candidatecard.setValidity(candidatecard.REJECTED);
			if(candidatecard==null)
				throw new Exception("The requested voterid doesnt exist.");
			
			candidatecard.setDateOfEffect(new Timestamp(new Date().getTime()));
			session.update(candidatecard);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at CandidateDAO function: rejectCandidatureWard()");
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			System.err.println("Error: at CandidateDAO function: rejectCandidatureWard()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}

	public boolean rejectCandidature(String voterid,Long reqid,String cuser)
	{
		Session session=null;
		Transaction trans = null;
		Request req = null;
		Candidate candidatecard = null;
		Criteria ctr = null;
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
			candidatecard = getCandidateId(voterid);
			if(candidatecard==null)
				throw new Exception("The requested voterid doesnt exist.");
			
			candidatecard.setDateOfEffect(new Timestamp(new Date().getTime()));
			candidatecard.setValidity(candidatecard.REJECTED);
			session.update(candidatecard);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at CandidateDAO function: rejectCandidature()");
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			System.err.println("Error: at CandidateDAO function: rejectCandidature()");
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

	

