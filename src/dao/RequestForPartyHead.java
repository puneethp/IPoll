package dao;

public class RequestForPartyHead {

	private Long reqid;
	private String candidateid;
	
	public RequestForPartyHead()
	{
		
	}

	

	public RequestForPartyHead(Long reqid, String candidateid) {
		super();
		this.reqid = reqid;
		this.candidateid = candidateid;
	}



	public Long getReqid() {
		return reqid;
	}

	public void setReqid(Long reqid) {
		this.reqid = reqid;
	}



	public String getCandidateid() {
		return candidateid;
	}



	public void setCandidateid(String candidateid) {
		this.candidateid = candidateid;
	}

	
}	