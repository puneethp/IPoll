package dao;

import java.math.BigInteger;

public class VoteRatio implements Comparable<VoteRatio> {
	private String candidateid;
	private BigInteger votes;
	private String resultid;
	private String elecid;
	
	
	
	public BigInteger getVotes() {
		return votes;
	}

	public void setVotes(BigInteger votes) {
		this.votes = votes;
	}

	public String getElecid() {
		return elecid;
	}

	public void setElecid(String elecid) {
		this.elecid = elecid;
	}

	@Override
	public String toString() {
		return "[\""+candidateid+"\","+votes+"]";
	}
	
	public VoteRatio(){
		
	}

	public String getCandidateid() {
		return candidateid;
	}

	public void setCandidateid(String candidateid) {
		this.candidateid = candidateid;
	}

	public String getResultid() {
		return resultid;
	}

	public void setResultid(String resultid) {
		this.resultid = resultid;
	}

	@Override
	public int compareTo(VoteRatio o) {
		return o.resultid.compareTo(this.resultid);
	}
	
	
}
