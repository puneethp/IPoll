package service;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import dao.FileContent;
import dao.FileDAO;
import dao.FileInfo;
import dao.Party;
import dao.PartyDAO;
import dao.ReqForCandidatureWardDAO;
import dao.RequestDAO;
import dao.Request;
import dao.RequestForPartyHead;
import dao.RequestForPartyHeadDAO;
import dao.User;
import dao.UserDAO;
import dao.WardID;
import dao.WardIdDAO;
import dao.WardUser;
import dao.Candidate;
import dao.CandidateDAO;
import dao.VoterIdDAO;
import dao.VoterId;
import dao.ReqForCandidatureWard;

import dao.ReqForCandidature;
import dao.ReqForCandidatureDAO;


public class CandidateServ {

	CandidateDAO dao;
	UserDAO udao;
	Candidate candidateId;
	VoterId vid;
	VoterIdDAO vdao;
	PartyDAO pdao;
	FileInfo file;
	FileContent content;
	Request request;
	ReqForCandidature rc;
	ReqForCandidatureWard rcw;
	ReqForCandidatureDAO rcdao;
	ReqForCandidatureWardDAO rwdao;
	ReqForCandidatureWard reqForCandidatureWard;
	ReqForCandidature reqCandidate1;
	ReqForCandidature reqCandidate2;
	ReqForCandidature reqCandidate3;
	ReqForCandidature reqCandidate4;
	ReqForCandidature reqCandidate5;
	ReqForCandidature reqCandidate6;
	ReqForCandidature reqCandidate7;
	ReqForCandidature reqCandidate8;
	ReqForCandidature reqCandidate9;
	ReqForCandidature reqCandidate10;
	WardID wardId;
	RequestForPartyHeadDAO rfphdao;
	RequestForPartyHead rph;
	
		//send request to party
	
	public PartyDAO getPdao() {
		return pdao;
	}

	public void setPdao(PartyDAO pdao) {
		this.pdao = pdao;
	}

	public RequestForPartyHeadDAO getRfphdao() {
		return rfphdao;
	}

	public void setRfphdao(RequestForPartyHeadDAO rfphdao) {
		this.rfphdao = rfphdao;
	}

	public RequestForPartyHead getRph() {
		return rph;
	}

	public void setRph(RequestForPartyHead rph) {
		this.rph = rph;
	}

	public UserDAO getUdao() {
		return udao;
	}

	public void setUdao(UserDAO udao) {
		this.udao = udao;
	}

	public User getUser(String id)
	{
		return udao.getUser(id);
	}
	
	public Candidate getCandidate(){
		return this.candidateId;
	}
	public Boolean getCandidateId(String candidateId){

		if(dao!=null)
		{
			this.candidateId = dao.getCandidateId(candidateId);
			return true;
		}
		return false;
	}
	
	public Boolean getCandidateIdByUser(String candidateId){ 
		this.candidateId = dao.getCandidateIdByUser(candidateId);
		if(this.candidateId!=null)
			return true;
		return false;
	}
	public Boolean getVoterId(String voterId){
		VoterId vId= vdao.getVoterId(voterId);
		if(vId!=null)
		{
			return true;
		}
		return false;
	}
	public String getVoterIdByUser(String user)
	{
		vid=vdao.getVoterIdByUser(user);
		
		if(vid != null)
		{	
			return vid.getId();
		}
		return null;
	}
	
	public VoterId getVoterIdByUserObject(String user)
	{
		vid=vdao.getVoterIdByUser(user);
		
		if(vid != null)
		{	
			return vid;
		}
		return null;
	}
	public Integer writeCandidature(String user, String candidate1, String candidate2, 
			String candidate3, String candidate4, String candidate5,
			String candidate6, String candidate7, String candidate8,
			String candidate9, String candidate10, Long constituency,
			String userImageContentType, String userImageFileName, byte[] userImage){
		file = new FileInfo(userImageFileName, userImageContentType, user);
		content = new FileContent(null, userImage);
		request = new Request(Request.CANDIDATURE,"Verify the details of the Candidature: ",Request.UNVERIFIED,new Timestamp(new Date().getTime()),null,null);
		Candidate newCandidate= new Candidate(user,vid.getVoterID(), Candidate.NOTVALIDATED, null, null, null, userImageFileName, constituency, 
				candidate1, candidate2, candidate3, candidate4, candidate5, candidate6,
				candidate7, candidate8, candidate9, candidate10);
		
		
		
		if(dao.writeCandidatureIndependent( reqCandidate1, reqCandidate2, reqCandidate3, reqCandidate4,
				reqCandidate5, reqCandidate6, reqCandidate7, reqCandidate8, reqCandidate9, reqCandidate10,
				request, file, content, newCandidate, wardId, user, reqForCandidatureWard))
			return 1;
		return 0;
	}
	
	public Integer writeCandidatureForParty(String id, String user, String candidate1, String candidate2, 
			String candidate3, String candidate4, String candidate5,
			String candidate6, String candidate7, String candidate8,
			String candidate9, String candidate10, Long constituency,
			String symbol, String representingParty){
		
		request = new Request(Request.CANDIDATURE,"Verify the details of the Candidature: ",Request.UNVERIFIED,new Timestamp(new Date().getTime()),null,null);
		Candidate newCandidate= new Candidate(user,vid.getVoterID(), Candidate.NOTVALIDATED, null, representingParty, null, symbol, constituency, 
				candidate1, candidate2, candidate3, candidate4, candidate5, candidate6,
				candidate7, candidate8, candidate9, candidate10);
		
		if(dao.writeCandidatureParty(request, newCandidate, wardId, user, reqForCandidatureWard))
				return 1;
		
		return 0;
	}
	
	public boolean acceptCandidaturePartyHead(String voterid,Long reqid,String id)
	{
		if(dao.approveCandidature(voterid, reqid, id))
		{
			this.candidateId = dao.getCandidateId(voterid);
			return true;
		}
		
		return false;
	}
	public boolean rejectCandidaturePartyHead(String voterid,Long reqid,String id)
	{
		if(dao.rejectCandidature(voterid, reqid, id))
		{
			this.candidateId = dao.getCandidateId(voterid);
			return true;
		}
		
		return false;
	}
	
	public boolean acceptCandidature(String voterid,Long reqid,String id)
	{
		if(dao.approveCandidature(voterid, reqid, id))
		{
			this.candidateId = dao.getCandidateId(voterid);
			return true;
		}
		
		return false;
	}
	public boolean rejectCandidature(String voterid,Long reqid,String id)
	{
		if(dao.rejectCandidature(voterid, reqid, id))
		{
			this.candidateId = dao.getCandidateId(voterid);
			return true;
		}
		return false;
	}
	
	public boolean acceptCandidatureWard(String voterid,Long reqid,String id)
	{
		if(dao.approveCandidatureWard(voterid, reqid, id))
		{
			this.candidateId = dao.getCandidateId(voterid);
			return true;
		}
		
		return false;
	}
	public boolean rejectCandidatureWard(String voterid,Long reqid,String id)
	{
		if(dao.rejectCandidatureWard(voterid, reqid, id))
		{
			this.candidateId = dao.getCandidateId(voterid);
			return true;
		}
		return false;
	}
	public boolean isRequestForPartyHead(Long reqid)
	{
		this.rph = rfphdao.getRequestForPartyHead(reqid);
		if(rph!=null)
			return true;
		return false;
	}
	public Boolean isWardID(String state,String city,String wardNo)
	{
		if(dao!=null)
			if(dao.getWardId(state, city, wardNo)!= null)
				return true;
		return false;
	}
	public CandidateDAO getDao() {
		return dao;
	}
	public void setDao(CandidateDAO dao) {
		this.dao = dao;
	}
	
	public ReqForCandidatureDAO getRcdao() {
		return rcdao;
	}
	public void setRcdao(ReqForCandidatureDAO rcdao) {
		this.rcdao = rcdao;
	}
	public ReqForCandidatureWardDAO getRwdao() {
		return rwdao;
	}
	public void setRwdao(ReqForCandidatureWardDAO rwdao) {
		this.rwdao = rwdao;
	}
	public boolean isRequestForCandidateId(Long reqid)
	{
		rc = rcdao.getRequestForCandidateId(reqid);
		if(rc!=null)
			return true;
		return false;
	}
	public boolean isRequestForCandidateIdWard(Long reqid)
	{
		rcw = rwdao.getRequestForCandidateIdWard(reqid);
		if(rcw!=null)
			return true;
		return false;
	}
	public ReqForCandidature getRc() {
		return rc;
	}
	public void setRc(ReqForCandidature rc) {
		this.rc = rc;
	}
	
	
	public Candidate getCandidateIdObject(){
		return this.candidateId;
	}
	
	
	public VoterIdDAO getVdao() {
		return vdao;
	}
	public void setVdao(VoterIdDAO vdao) {
		this.vdao = vdao;
	}
	public VoterId getVid() {
		return vid;
	}
	public void setVid(VoterId vid) {
		this.vid = vid;
	}
	public ReqForCandidatureWard getReqForCandidatureWard() {
		return reqForCandidatureWard;
	}
	public void setReqForCandidatureWard(ReqForCandidatureWard reqForCandidatureWard) {
		this.reqForCandidatureWard = reqForCandidatureWard;
	}
	public ReqForCandidatureWard getRcw() {
		return rcw;
	}
	public void setRcw(ReqForCandidatureWard rcw) {
		this.rcw = rcw;
	}

	public Boolean updateCandidature(String user, String candidate1, String candidate2, 
			String candidate3, String candidate4, String candidate5,
			String candidate6, String candidate7, String candidate8,
			String candidate9, String candidate10, Long constituency,
			String userImageContentType, String userImageFileName, byte[] userImage,String representingParty)
	{
		file = new FileInfo(userImageFileName, userImageContentType, user);
		content = new FileContent(null, userImage);
		request = new Request("Candidature","Verify the details of the Candidature: ","unverified",new Timestamp(new Date().getTime()),null,null);
		if(representingParty==null||representingParty.equals("")){
			candidateId= new Candidate(user,vid.getVoterID(), 0, null, null, null, userImageFileName, constituency, candidate1, candidate2, candidate3, candidate4, candidate5, candidate6,
					candidate7, candidate8, candidate9, candidate10);
			if(dao.updateCandidatureIndependent(reqCandidate1, reqCandidate2, reqCandidate3, reqCandidate4,
				reqCandidate5, reqCandidate6, reqCandidate7, reqCandidate8, reqCandidate9, reqCandidate10,
				request, file, content, candidateId, wardId, user, reqForCandidatureWard))
			return true;
		}else{
			String symbol;
			Party p =pdao.getParty(representingParty);
			if(p!=null)
				symbol = p.getSymbol();
			else
				return false;
			request = new Request(Request.CANDIDATURE,"Verify the details of the Candidature: ",Request.UNVERIFIED,new Timestamp(new Date().getTime()),null,null);
			Candidate newCandidate= new Candidate(user,vid.getVoterID(), Candidate.NOTVALIDATED, null, representingParty, null, symbol, constituency, 
					candidate1, candidate2, candidate3, candidate4, candidate5, candidate6,
					candidate7, candidate8, candidate9, candidate10);
			
			if(dao.updateCandidatureParty( reqCandidate1, reqCandidate2, reqCandidate3, reqCandidate4,
						reqCandidate5, reqCandidate6, reqCandidate7, reqCandidate8, reqCandidate9, reqCandidate10,
						request, newCandidate, wardId, user, reqForCandidatureWard))
					return true;
		}
		return false;
	}
}
