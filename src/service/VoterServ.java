package service;

import java.util.ArrayList;

import dao.Candidate;
import dao.CandidateDAO;
import dao.Election;
import dao.ElectionDAO;
import dao.LocationsDAO;
import dao.Vote;
import dao.VoteDAO;
import dao.VoterId;
import dao.VoterIdDAO;

public class VoterServ {
	VoteDAO dao;
	LocationsDAO ldao;
	ElectionDAO edao;
	VoterIdDAO vdao;
	CandidateDAO cdao;
	
	public CandidateDAO getCdao() {
		return cdao;
	}
	public void setCdao(CandidateDAO cdao) {
		this.cdao = cdao;
	}
	public VoterIdDAO getVdao() {
		return vdao;
	}
	public void setVdao(VoterIdDAO vdao) {
		this.vdao = vdao;
	}
	public VoterId getVoterid() {
		return voterid;
	}
	public void setVoterid(VoterId voterid) {
		this.voterid = voterid;
	}
	VoterId voterid;
	
	public VoteDAO getDao() {
		return dao;
	}
	public void setDao(VoteDAO dao) {
		this.dao = dao;
	}
	public LocationsDAO getLdao() {
		return ldao;
	}
	public void setLdao(LocationsDAO ldao) {
		this.ldao = ldao;
	}
	public ElectionDAO getEdao() {
		return edao;
	}
	public void setEdao(ElectionDAO edao) {
		this.edao = edao;
	}
	public boolean castVote(String candidate,Long constituency,String voterid,String user,String elecid)
	{
		Vote vote = new Vote(elecid,voterid,constituency,user,candidate);
		if(dao.persistVote(vote))
			return true;
		return false;
	}
	public boolean getVoterIdByUser(String user)
	{
		if(vdao!=null)
		{
			this.voterid = vdao.getVoterIdByUser(user);
			if(this.voterid!=null)
				return true;
		}
		return false;
	}
	public boolean getVoterId(String voterid)
	{
		if(vdao!=null)
		{
			this.voterid = vdao.getVoterId(voterid);
			if(this.voterid!=null)
				return true;
		}
		return false;
	}
	public Candidate getCandidateByUser(String user)
	{
		return cdao.getCandidateId(user);
	}
	public ArrayList<Candidate> getCandidate(long constituency,String elecid)
	{
		ArrayList<Candidate> list = null;
		list = ldao.getNumOfCandidateConstituency(constituency, elecid);
		return list;
	}
	public ArrayList<Election> getElectionsInConstituency(Long constituency)
	{
		ArrayList<Election> list = null;
		list = ldao.getElectionByConstituency(constituency);
		return list;
	}
	
	public boolean didVote(String elecid,String voterid)
	{
		return dao.getVoteByUserForElecId(voterid, elecid);
	}
}
