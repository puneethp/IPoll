package service;
import java.util.HashMap;

import dao.CandidateDAO;
import dao.ReqForCandidatureDAO;
import dao.ReqForCandidatureWardDAO;
import dao.Request;
import dao.RequestDAO;
import dao.RequestForPartyDAO;
import dao.RequestForPartyHeadDAO;
import dao.RequestForVoterIdDAO;
import dao.User;
public class RequestServ {
	RequestDAO dao;
	RequestForVoterIdDAO rfvdao;
	RequestForPartyDAO rfpdao;
	CandidateDAO cdao;
	ReqForCandidatureDAO rfcdao;
	ReqForCandidatureWardDAO rfcwdao;
	RequestForPartyHeadDAO rfphdao;

	public RequestForPartyHeadDAO getRfphdao() {
		return rfphdao;
	}

	public void setRfphdao(RequestForPartyHeadDAO rfphdao) {
		this.rfphdao = rfphdao;
	}

	public RequestForPartyDAO getRfpdao() {
		return rfpdao;
	}

	public void setRfpdao(RequestForPartyDAO rfpdao) {
		this.rfpdao = rfpdao;
	}

	public RequestDAO getDao() {
		return dao;
	}

	public void setDao(RequestDAO dao) {
		this.dao = dao;
	}

	public RequestForVoterIdDAO getRfvdao() {
		return rfvdao;
	}

	public void setRfvdao(RequestForVoterIdDAO rfvdao) {
		this.rfvdao = rfvdao;
	}

	public HashMap<Request, dao.RequestForVoterId> getRequestForVoterIdWardUser(String id)
	{
		if(rfvdao!=null)
			return rfvdao.getRequestForVoterIdWardUser(id);
		return null;
	}
	public  HashMap<Request, dao.RequestForParty> getRequestForParty()
	{
		if(rfpdao!=null)
			return rfpdao.getRequestForPartyAdmin("admin");
		return null;
	}
	public HashMap<Request, dao.ReqForCandidatureWard> getRequestForCandidatureWardUser(String id)
	{
		if(rfcwdao!=null)
			return rfcwdao.getRequestForCandidatureWardUser(id);
		return null;
	}
	
	public HashMap<Request, dao.ReqForCandidature> getRequestForCandidature(String id)
	{
		if(rfcdao!=null)
			return rfcdao.getRequestForCandidature(id);
		return null;
	}
	
	public HashMap<Request, dao.RequestForPartyHead> getRequestForPartyHead(String id)
	{
		if(rfphdao!=null)
			return rfphdao.getRequestForPartyHead(id);
		return null;
	}
	
	
	public ReqForCandidatureDAO getRfcdao() {
		return rfcdao;
	}

	public void setRfcdao(ReqForCandidatureDAO rfcdao) {
		this.rfcdao = rfcdao;
	}

	public ReqForCandidatureWardDAO getRfcwdao() {
		return rfcwdao;
	}

	public void setRfcwdao(ReqForCandidatureWardDAO rfcwdao) {
		this.rfcwdao = rfcwdao;
	}
	public CandidateDAO getCdao() {
		return cdao;
	}

	public void setCdao(CandidateDAO cdao) {
		this.cdao = cdao;
	}
}

