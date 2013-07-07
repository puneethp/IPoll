package dao;

public class ReqForCandidature {
	String candidateId;
	Long reqId;
	
	public ReqForCandidature(){
		
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public Long getReqId() {
		return reqId;
	}

	public void setReqId(Long reqId) {
		this.reqId = reqId;
	}

	public ReqForCandidature(String candidateId, Long reqId) {
		super();
		this.candidateId = candidateId;
		this.reqId = reqId;
	}
	

}
