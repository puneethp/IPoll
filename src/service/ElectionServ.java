package service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;

import dao.Election;
import dao.ElectionDAO;
import dao.Locations;
import dao.Result;
import dao.ResultDAO;
import dao.VoteRatio;

public class ElectionServ {
	ElectionDAO dao;
	ResultDAO rdao;
	
	public ResultDAO getRdao() {
		return rdao;
	}

	public void setRdao(ResultDAO rdao) {
		this.rdao = rdao;
	}

	Election election;
	public ElectionDAO getDao() {
		return dao;
	}

	public void setDao(ElectionDAO dao) {
		this.dao = dao;
	}
	public ArrayList<Election> getAllElections()
	{
		return dao.getElections();
	}
	
	public ArrayList<Election> getAllDoneElections()
	{
		return dao.getDoneElections();
	}
	
	public ArrayList<Election> getAllElectionsWithResults()
	{
		return dao.getElectionsResults();
	}
	
	public boolean isElection(String elecid)
	{
		election = null;
		election = dao.getElect(elecid);
		if(election!=null)
			return true;
		return false;
	}
	
	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}
	
	public ArrayList<Locations> getLocations(String elecid)
	{
		return dao.getLocationsElecid(elecid);
	}
	
	public boolean writeElection(Timestamp start,Timestamp end,Long locations[],String title)
	{
		Locations[] loc = new Locations[locations.length];
		if(start!=null&&end!=null)
		{
			if(locations.length>0)
			{
				for(int i=0;i<locations.length;i++)
				{
					loc[i] = new Locations();
					loc[i].setConstituency(locations[i]);
				}
				if(dao.writeElection(start, end, loc,title))
					return true;
			}
		}
		return false;
	}
	
	public boolean getResults(String elecid)
	{
		return rdao.findResult(elecid);
	}
	
	
	public ArrayList<Result> getResultForElection(String elecid)
	{
		ArrayList<Result> result =  rdao.getResultForElection(elecid);
		for(Result temp:result){
			temp.voteratio = rdao.getResultVoteRatioForElection(temp.getElecid(),temp.getConstituency());
			temp.total = rdao.registeredVotersForElection(temp.getConstituency());
			temp.turnout = rdao.getParticipantsForElectionPerLocation(elecid, temp.getConstituency());
			temp.mid = temp.total.divide(BigInteger.valueOf(2));
		}
		return result;
	}
}
