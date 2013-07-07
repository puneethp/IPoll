package dao;

public class Vote {
private String elecid;
private String voterid;
private String voteid;
private long constituency;
private String user;
private String candidateid;
public String getElecid() {
	return elecid;
}
public void setElecid(String elecid) {
	this.elecid = elecid;
}

public Vote() {
	super();
}
public String getVoterid() {
	return voterid;
}
public void setVoterid(String voterid) {
	this.voterid = voterid;
}
public String getVoteid() {
	return voteid;
}
public void setVoteid(String voteid) {
	this.voteid = voteid;
}
public long getConstituency() {
	return constituency;
}
public void setConstituency(long constituency) {
	this.constituency = constituency;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public Vote(String elecid, String voterid, long constituency, String user,
		String candidateid) {
	super();
	this.elecid = elecid;
	this.voterid = voterid;
	this.constituency = constituency;
	this.user = user;
	this.candidateid = candidateid;
}
public String getCandidateid() {
	return candidateid;
}
public void setCandidateid(String candidateid) {
	this.candidateid = candidateid;
}
}
