package dao;

import java.math.BigInteger;
import java.util.ArrayList;

public class Result implements Comparable<Result> {
	String resultid;
	public String getResultid() {
		return resultid;
	}
	public void setResultid(String resultid) {
		this.resultid = resultid;
	}
	String candidate;
	String elecid;
	Long constituency;
	public BigInteger turnout;
	public BigInteger getTurnout() {
		return turnout;
	}
	public void setTurnout(BigInteger turnout) {
		this.turnout = turnout;
	}
	public BigInteger getTotal() {
		return total;
	}
	public void setTotal(BigInteger total) {
		this.total = total;
	}
	public BigInteger getMid() {
		return mid;
	}
	public void setMid(BigInteger mid) {
		this.mid = mid;
	}
	public BigInteger total;
	public BigInteger mid;
	
	public ArrayList<VoteRatio> voteratio;
	
	public ArrayList<VoteRatio> getVoteratio() {
		return voteratio;
	}
	public void setVoteratio(ArrayList<VoteRatio> voteratio) {
		this.voteratio = voteratio;
	}
	public String getCandidate() {
		return candidate;
	}
	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	public String getElecid() {
		return elecid;
	}
	public void setElecid(String elecid) {
		this.elecid = elecid;
	}
	public Long getConstituency() {
		return constituency;
	}
	public void setConstituency(Long constituency) {
		this.constituency = constituency;
	}
	public Result(String candidate, String elecid, Long constituency) {
		super();
		this.candidate = candidate;
		this.elecid = elecid;
		this.constituency = constituency;
	}
	public Result() {
		super();
	}
	@Override
	public int compareTo(Result o) {
		return this.resultid.compareTo(o.resultid);
	}
	
}
